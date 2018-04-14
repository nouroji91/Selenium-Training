package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseRunner.TestNGBaseRunner;
import common.ExcelConfig;
import common.PrintUtils;
import common.ResultClass;


public class LoginTestcases extends TestNGBaseRunner {

	public ExcelConfig _excel;
	
	@BeforeClass
	public void BeforeClass() throws Exception 
	{
		_excel = new ExcelConfig("./TestData/TestDatas.xlsx", "Login");
	}
	
	@Test(priority=1)
	public void validateLoginFunctionality() throws Exception 

	{
		_excel.readTestData("validateLoginFunctionality");
		
		PrintUtils.logMessage("validateLoginFunctionality testcase started");
		try
		{
			_pagecompenets._homepage.verifyHomePageTitle(_excel.hmap.get("ExpectedValue"));		
		}
		catch(Exception ex)
		{
			PrintUtils.logError("Exception block called inside testcase" + ex.getMessage());
			ResultClass.testStatus(false);
		}
		finally 
		{
			ResultClass.assertAll();
		}

	}


	@Test(priority=2)
	public void validateForgetLink() 

	{
		_excel.readTestData("validateForgetLink");
		try {
			PrintUtils.logMessage("validateForgetLink testcase started");
			String a = _excel.hmap.get("ExpectedValue");
			PrintUtils.logMessage("Expected title from excel is ::" +a );
			_pagecompenets._forgetpasswordpage.clickForgetLinkAndVerifyTitle(a);
			
    		}
			catch(Exception ex)
			{
				PrintUtils.logError("Exception block called inside testcase" + ex.getMessage());
				ResultClass.testStatus(false);
			}
			finally 
			{
				ResultClass.assertAll();
			}
		}

	}