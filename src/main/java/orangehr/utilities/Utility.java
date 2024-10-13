package orangehr.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	
	
// Method to use for synchronization
public static final int Implicit_Wait_Time = 10;
public static final int Page_Load_Time = 10;


//Method to generate time stamp	
public static String generatetimestampusername() {
		
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "Admin"+timestamp+"abc";
	}



// Method to Fetch data from Excel file 

public static Object[][] getTestDataFromExcel(String sheetName) 
{
	XSSFWorkbook workbook = null;
	File excelfile = new File (System.getProperty("user.dir")+"\\src\\main\\java\\orangehrm\\TestData\\OrangeHrmTestData.xlsx");
	try {
	FileInputStream fisexcel = new FileInputStream(excelfile);
	 workbook = new XSSFWorkbook(fisexcel);
	}catch(Throwable e) {
      e.printStackTrace();
	}
	XSSFSheet sheet = workbook.getSheet(sheetName);
	
	int rows  = sheet.getLastRowNum();
	int cols = sheet.getRow(0).getLastCellNum();
	
	Object [][] data = new Object[rows][cols];
	for(int i=0;i<rows;i++) 
	{
		 XSSFRow row = sheet.getRow(i+1);
	
	for(int j=0; j<cols;j++) 
	{
      XSSFCell cell = row.getCell(j);
      CellType celltype = cell.getCellType();
      
      switch(celltype) 
      {
      case STRING:
    	  data [i][j] = cell.getStringCellValue();
    	  break;
    	  
      case NUMERIC:
    	  data [i][j] = Integer.toString((int)cell.getNumericCellValue());
    	  break;
    	  
      case BOOLEAN:
    	  data [i][j] = cell.getBooleanCellValue();
    	  break;
      }
	}
}
return data ;
}

//Method to take Screenshot 

public static String CaptureScreenShot(WebDriver driver,String testname) 
{
	File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
	//Code for copying screenshot to a particular destination
	
	String DestinationScreenshotpath = (System.getProperty("user.dir")+"\\ManuallyCreatedScreenShotFolder\\"+testname+".png");
	try {
		FileHandler.copy(srcScreenshot, new File (DestinationScreenshotpath));
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return DestinationScreenshotpath;
}

}


