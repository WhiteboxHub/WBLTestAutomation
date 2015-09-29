package com.wbl.tests.ui;

import com.wbl.base.BaseWebTest;
import com.wbl.pages.PresentationPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Shilpi on 9/24/2015.
 */
public class PresentationPageTest extends BaseWebTest {

    private PresentationPage _pp;
    private String sheetName;

    @BeforeTest
    @Parameters("sheetName")
    public void init(String sheetName)
    {
        _pp = new PresentationPage(driver);
        this.sheetName = sheetName;
    }

    @DataProvider(name = "ppt-data")
    public Object[][] getUsers() throws Exception {
        Object[][] data = excelUtils.getSimpleExcelData(driver._configuration.FilePath,sheetName);
        return data;
    }

    @Test(priority = 1,dataProvider = "ppt-data")
    public void testPresentations(String uname,String pwd,String pptName,String pptPwd) throws InterruptedException,Exception
    {
        _pp.getPresentation(uname, pwd, pptName, pptPwd);
    }


}
