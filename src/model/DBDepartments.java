package model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class DBDepartments {
	private static DBDepartments instance;
	
	public static DBDepartments getInstance() {
		if(instance == null) instance = new DBDepartments();
		
		return instance;
	}
	
	private ArrayList<Department> departments;
	
	private static int generatedId = 0;
	
	public int generateNextID() {
		return generatedId++;
	}
	
	private DBDepartments() {
		try {
			departments = deserialize();
			//departments = convertExcel();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public Department findByPrimaryId(int id) {
		for(Department p: departments) {
			if(p.getPrimaryId() == id) return p;
		}
		
		return null;
	}
	
	public ArrayList<Department> getAllDepartments() {
		return departments;
	}
	
	public String[] getList() {
		String[] list = new String[departments.size()];
		
		for(int i = 0; i < departments.size(); ++i) {
			list[i] = departments.get(i).toString();
		}
		
		return list;
	}
	
	public Department getSelectedDepartment(int row) {
		return departments.get(row);
	}
	
	public boolean setDepartmentBoss(int selectedDepartment, String selectedProfessor) {
		String[] parse = selectedProfessor.split(",");
		
		Professor prof = DBProfessor.getInstance().getProfessor(parse[0]);
		//	Checks if selectedProfessor is already head of a department
		for(Department d: departments) {
			if(d.getDepartmentHead() == prof) {
				return false;
			}
		}
		getSelectedDepartment(selectedDepartment).setDepartmentHead(prof);
		return true;
	}
	
	public void addProfessorToDepartment(int department, Professor prof) {
		departments.get(department).addProfessor(prof.setDepartmentID(department));
	}
	
	public void serialize() throws IOException {
		File f = new File("saves" + File.separator + File.separator + "departments.json");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		
		try {
			XStream xs = new XStream(new JettisonMappedXmlDriver());
			xs.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
			xs.addPermission(AnyTypePermission.ANY);
			
			headSerialize();
			
			String s = xs.toXML(departments);
			xs.toXML(departments, os);
		} finally {
			os.close();
		}
	}
	
	private void headSerialize() {
		for(Department d: departments) {
			if(d.getDepartmentHead() != null) {
				DepartmentHeadSerialization.getInstance().addRelation(new DepartmentHeadRelation(d.getPrimaryId(), d.getDepartmentHead().getPrimaryId()));
				d.setDepartmentHead(null);
			}
		}
	}
	
	
	public ArrayList<Department> deserialize() throws IOException {
		FileInputStream f = new FileInputStream("saves" + File.separator + File.separator + "departments.json");
		try {
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.addPermission(AnyTypePermission.ANY);
			
			departments = (ArrayList<Department>) xstream.fromXML(f);
				
			setupHeads();
			
			return departments;
			
			}
		finally {
			f.close();
		}
	}
	
	private void setupHeads() {
		for(DepartmentHeadRelation dprel: DepartmentHeadSerialization.getInstance().getRelations3()) {
			findByPrimaryId(dprel.getDepartmentId()).setDepartmentHead(DBProfessor.getInstance().findByPrimaryId(dprel.getProfessorId()));
			findByPrimaryId(dprel.getDepartmentId()).addProfessor(DBProfessor.getInstance().findByPrimaryId(dprel.getProfessorId()));
		}
		
		DepartmentHeadSerialization.getInstance().flush();
	}
	
	private ArrayList<Department> convertExcel() throws IOException{
		try {
			FileInputStream excelFile = new FileInputStream(new File("testpodaci.xlsx"));
			Workbook workbook = new XSSFWorkbook(excelFile);
			
			Sheet sheet = workbook.getSheet("Katedre");
			Iterator<Row> rows = sheet.iterator();
			
			ArrayList<Department> list = new ArrayList<Department>();
			
			int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = (Row) rows.next();
    			System.out.println(rowNumber);
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			} else if (rowNumber == 7) break;
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();
    			Department dep = new Department();
    			
    			int cellIndex = 0;
    			
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = (Cell) cellsInRow.next();
    				
    				switch (cellIndex) {
					case 1:
						dep.setSerialCode(currentCell.getStringCellValue());
						break;
					case 2:
						dep.setName(currentCell.getStringCellValue());
						break;
					case 3:
						try {
							Professor prof = DBProfessor.getInstance().getProfessor((int) currentCell.getNumericCellValue() - 1);
							dep.setDepartmentHead(prof);
							dep.addProfessor(prof.setDepartmentID(rowNumber - 1));
						} catch(IllegalStateException e) {
							dep.setDepartmentHead(null);
						}
						break;
					default:
						break;
					}
    				
    				cellIndex++;
    			}
    			
    			dep.setPrimaryId(generateNextID());
    			
    			list.add(dep);
    			rowNumber++;
    		}
    		
    		workbook.close();
 
    		return list;
    		
		} finally {
			
		}
	}
}
