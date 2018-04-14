package common;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.SkipException;

public class ExcelConfig {


	public String SheetName;
	public String WorkBookName;
	public XSSFWorkbook wb;
	public XSSFSheet sheet ;

	public String TestCaseName;
	public String TestDesc;
	public String TestDatas;
	public String TestRun;


	public HashMap<String, String> hmap;

	public ExcelConfig(String workbook, String sheetName) throws Exception
	{
		this.WorkBookName =workbook;
		this.SheetName=sheetName;
		System.out.println("Workbook and SheetName is :: " + WorkBookName +"##"+SheetName);

		File file = new File(workbook);
		FileInputStream fis = new FileInputStream(file);

		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetName);


	}

	public void readTestData(String testcaseNames) 
	{
		for(int i=1;i<=sheet.getLastRowNum(); i++)
		{

			TestCaseName= sheet.getRow(i).getCell(0).getStringCellValue();
			if(TestCaseName.equals(testcaseNames))
			{
				TestRun= sheet.getRow(i).getCell(3).getStringCellValue();
				if(TestRun.toUpperCase().equals("YES"))
				{
				TestDesc= sheet.getRow(i).getCell(1).getStringCellValue();
				TestDatas= sheet.getRow(i).getCell(2).getStringCellValue();
				splitTestDatas(TestDatas);
				ExtentReport.setTestName(TestCaseName, TestDesc);
				break;
				}
				else 
				{
					throw new SkipException("Run Mode Set to No for the testcase - please verify TestDatas in Excel");
				}
				
			}			
		}
	}

	public void splitTestDatas(String TestDatas) 
	{
		hmap = new HashMap<>();
		for(int i =0; i< TestDatas.split("@@").length; i++)
		{
			String data = TestDatas.split("@@")[i];

			for(int j=0; j<data.split("##").length; j++)
			{
				String key = data.split("##")[0];
				String value = data.split("##")[1];

				hmap.put(key, value);
			}
		}
	}
}





















