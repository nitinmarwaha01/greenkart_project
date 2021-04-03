package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
	private String excelPath;
	private FileInputStream fis;
	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	
	public ExcelDataProvider() {
		setExcelPath("./testdata/Test Data.xlsx");
		
		if(getExcelPath()!=null) {
			try {
				setFIS(new FileInputStream(new File(this.getExcelPath())));
				
				if(getFIS()!=null) {
					wb = new XSSFWorkbook(this.getFIS());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					getWorkbook().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("Not able to get excel file path.");
		}
	}
	
	public String getExcelPath() {
		return excelPath;
	}
	
	public void setExcelPath(String path) {
		this.excelPath = path;
	}
	
	public FileInputStream getFIS() {
		return fis;
	}
	
	public void setFIS(FileInputStream fis) {
		this.fis = fis;
	}
	
	public XSSFWorkbook getWorkbook() {
		return wb;
	}
	
	public void setWorkbook(XSSFWorkbook wb) {
		this.wb = wb;
	}
	
	public XSSFSheet getSheet() {
		return sheet;
	}
	
	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}

	public int getRowCount(String sheetName) {
		setSheet(wb.getSheet(sheetName));
		if(getSheet()!=null) {
			int rowCount = this.getSheet().getLastRowNum();
			return rowCount;
		}else {
			System.out.println("Not able to find excel sheet with the name provided - " + sheetName);
			return 0;
		}
	}
	
	public int getColCount(String sheetName) {
		setSheet(wb.getSheet(sheetName));
		if(getSheet()!=null) {
			int ColCount = this.getSheet().getRow(0).getLastCellNum();
			return ColCount;
		}else {
			System.out.println("Not able to find excel sheet with the name provided - " + sheetName);
			return 0;
		}
	}
	
	public String getCellValueBasedOnColName(String sheetName, int rowNumber ,String colName) {
		String cellValue="";
		int colNum=0;
		
		setSheet(wb.getSheet(sheetName));
		if(getSheet()!=null) {
			
			int ColCount = this.getSheet().getRow(rowNumber).getLastCellNum();
			for(int colCounter=1; colCounter<ColCount; colCounter++) {
				String rowHeader = this.getSheet().getRow(0).getCell(colCounter).getStringCellValue();
				if(rowHeader.equalsIgnoreCase(colName)) {
					colNum = colCounter;
					break;
				}
			}

			Cell cell = this.getSheet().getRow(rowNumber).getCell(colNum);
				if(cell.getCellType() == CellType.NUMERIC) {
					cellValue = String.valueOf((long) cell.getNumericCellValue());
				}else if(cell.getCellType() == CellType.FORMULA) {
					cellValue = String.valueOf(cell.getRichStringCellValue());
				}else {
					cellValue = cell.getStringCellValue();
				}
		return cellValue;
		}else {
			System.out.println("Not able to find excel sheet with the name provided - " + sheetName);
			return null;
		}
	}
	
	public Map<String,String> getRowData(String sheetName, int rowNum) {
		String keyValue;
		String colValue;
		
		Map<String, String> map = new HashMap<String, String>();
		
		setSheet(wb.getSheet(sheetName));
		if(getSheet()!=null) {
			int ColCount = this.getSheet().getRow(0).getLastCellNum();
			for(int colCounter=1; colCounter<ColCount; colCounter++) {
				keyValue = this.getSheet().getRow(0).getCell(colCounter).getStringCellValue();
				
				Cell cell = this.getSheet().getRow(rowNum).getCell(colCounter);
				if(cell.getCellType() == CellType.NUMERIC) {
					colValue = String.valueOf((long) cell.getNumericCellValue());
				}else if(cell.getCellType() == CellType.FORMULA) {
					colValue = String.valueOf(cell.getRichStringCellValue());
				}else {
					colValue = cell.getStringCellValue();
				}
				
				map.put(keyValue, colValue);
			}	
		return map;
		
		}else {
			System.out.println("Not able to find excel sheet with the name provided - " + sheetName);
			return null;
		}
	}
}
