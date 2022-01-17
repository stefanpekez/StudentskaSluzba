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
	
	ArrayList<Department> departments;
	
	private DBDepartments() {
		try {
			departments = convertExcel();
		} catch(IOException e) {
			e.printStackTrace();
		}
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
	
	public boolean setDepartmentBoss(int selectedDepartment, int selectedProfessor) {
		
		Professor prof = DBProfessor.getInstance().getProfessor(selectedProfessor);
		
		//	Checks if selectedProfessor is already head of a department
		for(Department d: departments) {
			if(d.getDepartmentHead() == prof) {
				System.out.println("Professor " + prof.getName() + " " + prof.getSurname() + " is already a head of a department");
				return false;
			}
		}
		
		getSelectedDepartment(selectedDepartment).setDepartmentHead(DBProfessor.getInstance().getProfessor(selectedProfessor));
		return true;
	}
	
	public void serialize() throws IOException {
		File f = new File("saves\\departments.json");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		
		try {
			XStream xs = new XStream(new JettisonMappedXmlDriver());
			xs.addPermission(AnyTypePermission.ANY);
			String s = xs.toXML(departments);
			xs.toXML(departments, os);
		} finally {
			os.close();
		}
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
							dep.setDepartmentHead(DBProfessor.getInstance().getProfessor((int) currentCell.getNumericCellValue() - 1));
						} catch(IllegalStateException e) {
							dep.setDepartmentHead(null);
						}
						break;
					default:
						break;
					}
    				
    				cellIndex++;
    			}
    			
    			list.add(dep);
    			rowNumber++;
    		}
    		
    		workbook.close();
 
    		return list;
    		
		} finally {
			
		}
	}
}
