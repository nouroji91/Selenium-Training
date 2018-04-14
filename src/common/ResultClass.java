package common;

import org.testng.asserts.*;

public class ResultClass {

	
 public static SoftAssert sa = null;
 
 public static void callSoftAssert() 
 {
	 sa= new SoftAssert();
 }
 
 public static void testStatus(boolean value) 
 {
	 if(value==true)
	 {
		 sa.assertTrue(value);
	 }else 
	 {
		 sa.fail();
	 } 
 }
 
 public static void assertAll() 
 {
	 sa.assertAll();
 }
 
}
