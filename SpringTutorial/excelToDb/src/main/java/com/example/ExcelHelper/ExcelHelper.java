package com.example.ExcelHelper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.example.model.ExcelModel;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "id", "name", "age", "phone" };
	public static String SHEET = "user";
	
	public static boolean isExcelFormat(MultipartFile file) {
		if(!TYPE.equals(file.getContentType())) {
			
			return false;
		}
//		System.out.println("an excel format.");
		return true;
	}
	
	public static List<ExcelModel> excelTODB(InputStream in){
		try {
//			Workbook work = WorkbookFactory.create(in);
			Workbook workBook = new XSSFWorkbook(in);
		    Sheet sheet = workBook.getSheet(SHEET);
			
			Iterator<Row> rows = sheet.iterator();
			
			List<ExcelModel> sheets = new ArrayList<>();
			int rowNum = 0;
			while(rows.hasNext()) {
				Row curRow = rows.next();
				if (rowNum == 0) {
					  rowNum++;
			          continue;
			       	}
//				System.out.println(curRow.getPhysicalNumberOfCells());
				Iterator<Cell>cells = curRow.cellIterator();
				ExcelModel excelModel = new ExcelModel();
				int cellId = 0;
				
				while(cells.hasNext()) {
					Cell curCell = cells.next();
					switch (cellId) {
					case 0: {
						excelModel.setId((int)curCell.getNumericCellValue());
						break;
					}
					case 1 :{
						excelModel.setName(curCell.getStringCellValue());
						break;
					}
					case 2 : {
						excelModel.setAge((int)curCell.getNumericCellValue());
						break;
					}
					case 3 : {
						excelModel.setPhone((long)curCell.getNumericCellValue());
						break;
						
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + cellId);
					}
					cellId++;
					
				}
				sheets.add(excelModel);
			}
			workBook.close();
			return sheets;
		}catch(Exception e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}
