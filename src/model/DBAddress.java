package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DBAddress {
	
	private static DBAddress instance = null;
	
	public static DBAddress getInstance() {
		if(instance == null) {
			instance = new DBAddress();
		}
		return instance;
	}
	
	ArrayList<Address> addresses;
	
	public DBAddress() {
		addresses = new ArrayList<Address>();
		try {
			addresses = convertExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Address getAddress(int index) {
		if(index == -1) return new Address("NEMA");
		
		return addresses.get(index - 1);
	}
	
	private ArrayList<Address> convertExcel() throws IOException{
		try {
			FileInputStream excelFile = new FileInputStream(new File("testpodaci.xlsx"));
			Workbook workbook = new XSSFWorkbook(excelFile);
			
			Sheet sheet = workbook.getSheet("Adrese");
			Iterator<Row> rows = sheet.iterator();
			
			ArrayList<Address> list = new ArrayList<Address>();
			
			int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = (Row) rows.next();
    			System.out.println(rowNumber);
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			} else if (rowNumber == 13) break;
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();
    			Address address = new Address();
    			
    			int cellIndex = 0;
    			
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = (Cell) cellsInRow.next();
    				
    				switch (cellIndex) {
					case 1:
						address.setStreet(currentCell.getStringCellValue());
						break;
					case 2:
						try {
							address.setNumber(currentCell.getStringCellValue());
						} catch(IllegalStateException e) {
							address.setNumber(Integer.toString((int)currentCell.getNumericCellValue()));
						}
						break;
					case 3:
						address.setCity(currentCell.getStringCellValue());
						break;
					case 4:
						address.setCountry(currentCell.getStringCellValue());
						break;
					default:
						break;
					}
    				
    				cellIndex++;
    			}
    			
    			list.add(address);
    			rowNumber++;
    		}
    		
    		workbook.close();
 
    		return list;
    		
		} finally {
			
		}
	}
}
