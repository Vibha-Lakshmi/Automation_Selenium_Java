package writeExcelFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel2 {
	static Workbook wb = null;


	public void writeExcelFunc(String filePath, String fileName, String sheetName, String[] dataToWrite)
			throws IOException {

		// Create an object of File class to open xlsx file

		File file = new File(filePath + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		
		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			wb = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of XSSFWorkbook class

			wb = new HSSFWorkbook(inputStream);

		}
		
		//CreationHelper
		CreationHelper createHelper = wb.getCreationHelper();

		// Read excel sheet by sheet name
		Sheet sheet = wb.createSheet(sheetName);
		//Sheet sheet = wb.createSheet(sheetName);

		//Sheet sheet = wb.getSheet(sheetName);

		// Get the current count of rows in excel file

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		// Get the first row from the sheet

		Row row = sheet.getRow(0);

		// Create a new row and append it at last of sheet

		Row newRow = sheet.createRow(rowCount + 1);

		// Create a loop over the cell of newly created Row

		for (int j = 0; j < row.getLastCellNum(); j++) {

			// Fill data in row

			Cell cell = newRow.createCell(j);

			cell.setCellValue(dataToWrite[j]);

		}

		// Close input stream

		inputStream.close();

		// Create an object of FileOutputStream class to create write data in excel file

		FileOutputStream outputStream = new FileOutputStream(file);

		// write data in the excel file

		wb.write(outputStream);

		// close output stream

		outputStream.close();
		wb.close();

	}

	public static void main(String[] args) throws Exception {

		// Create an array with the data in the same order in which you expect to be
		// filled in excel file

		String[] valueToWrite = { "vibha.lakshmi@imaginea.com", "vibha" };

		// Create an object of current class

		WriteExcel2 objExcelFile = new WriteExcel2();
		String filePath = System.getProperty("user.dir") + "\\src\\writeExcelFile";
		System.out.println("path " + filePath);

		// Write the file using file name, sheet name and the data to be filled

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMHHmm");
		LocalDateTime now = LocalDateTime.now();
		
		String safeName = WorkbookUtil.createSafeSheetName("["+"Sheet1" + dtf.format(now)+"*?]");
		 
		

		objExcelFile.writeExcelFunc(filePath, "ExportExcelx.xlsx", safeName, valueToWrite);

	}
	// TODO Auto-generated method stub

}
