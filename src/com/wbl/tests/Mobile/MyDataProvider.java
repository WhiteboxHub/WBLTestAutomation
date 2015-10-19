package com.wbl.tests.Mobile;

import org.testng.annotations.DataProvider;

public class MyDataProvider {

	@DataProvider(name = "Fillform")

	public static Object[][] FilltheForm() {

		return new Object[][] { {"w","B@g.c","wh","in","Java"}};
//				{"whiteBox","Box@g.com","whiteboxqa","innovapath","Java"},{"whiteBox1","Box@g.com1","whiteboxqa1","innovapath1","Java Script"}};


	}

}
