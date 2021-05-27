package utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import base.BaseTest;

public class DataUtil extends BaseTest {
	
	@DataProvider(name ="dp")
	public static Object[][] getData(Method m){
		String sheetName = m.getName();
		System.out.println("sheet Name is "+ sheetName);
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		System.out.println("Rows are "+ rows + "   cols are "+ cols);
		
		Object data[][] = new Object[rows-1][cols];
		
		for(int row = 2;row <=rows;row++) {
			for(int col = 0;col<cols;col++) {
				data[row-2][col] = excel.getCellData(sheetName, col, row);
			}
		}
		
		return data;
	}
	

}
