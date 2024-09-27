package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataSupllier {
	 @DataProvider(name = "logindata")
	 public String[][] exceldataProvider() throws Exception  {
			//..............................................EDIT File path location..........................
			File excelFile = new File("C:\\Users\\USER\\Desktop\\dataprovide.xlsx");
			System.out.print(excelFile.exists());
			FileInputStream fis = new FileInputStream(excelFile);
			
			// This is for new version of XLXS and it selected from XSSFWorkbook Inputstream.
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			//Here LoginData is tab name of XLXS sheet........................EDIT getSheet name...................
			XSSFSheet sheet = workbook.getSheet("sheet1");
			
			// To get number of row
			int noOfRow = sheet.getPhysicalNumberOfRows();
			
			//To get datas skipping header row
			int noOfColumn = sheet.getRow(0).getLastCellNum();
			
			// noOfRow-1 is for collect data skipping header
			String[][] data = new String[noOfRow-1][noOfColumn];
			
			for(int i = 0; i<noOfRow-1; i++) {
				for(int j = 0; j<noOfColumn; j++) {
					
					//DataFormatter is used to collect all types data from xlxs as if data is int, Float, String any types.
					DataFormatter dataFormatter = new DataFormatter();
					
					//Reading entire data from array
					data[i][j] = dataFormatter.formatCellValue(sheet.getRow(i+1).getCell(j));
					
				}
				
			}
			
			
			//Have to close connection other wise memory leakage will be happened
			workbook.close();
			fis.close();
			
			// This for each is for testing purpose
			for(String[] dataArray : data) {
				System.out.println(Arrays.toString(dataArray));
			}
			
			
			return data;
				
			}
}
