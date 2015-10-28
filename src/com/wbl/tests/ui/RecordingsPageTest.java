package com.wbl.tests.ui;

import com.wbl.base.BaseWebTest;
import com.wbl.pages.RecordingsPage;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

/**
 * Created by Shilpi on 9/24/2015.
 */
public class RecordingsPageTest extends BaseWebTest {

    private RecordingsPage _rp;
    private String sheetName;

    @BeforeTest
    @Parameters("sheetName")
    public void init(String sheetName)
    {
        _rp = new RecordingsPage(driver);
        this.sheetName = sheetName;
    }

    @DataProvider(name = "recording-data")
    public Object[][] getUsers() throws Exception {
        Object[][] data = excelUtils.getSimpleExcelData(driver._configuration.DataFileName,sheetName);
        return data;
    }

    @Test(priority = 1,dataProvider = "recording-data")
    public void testRecordings(String uname,String pwd,String batch,String recording) throws InterruptedException,Exception
    {
        boolean actual = _rp.playRecordings(uname, pwd, batch, recording);
        assertTrue(actual);
        driver.takeScreenShot();
    }

    @AfterTest
    public void close()
    {
        driver.close();
    }

}
