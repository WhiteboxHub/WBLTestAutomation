package com.wbl.tests.api;

import com.wbl.base.BaseApiTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by svelupula on 8/25/2015.
 */
public class StackExchangeTest extends BaseApiTest {


    @BeforeClass
    public void beforeClass() {
        //data setup
    }

    @DataProvider(name = "search-data")
    public Object[][] getSearchQuery() {
        return new Object[][]{{"2.2/questions?site=stackoverflow&tagged=selenium"}};
    }

    @Test(priority = 1, alwaysRun = true, dataProvider = "search-data")
    public void testSearchData(String query) {
        try {
            restUtil.getJSONEntity(query);
            assertNotEquals(restUtil.isValidResponse(), null);
            assertEquals(restUtil.getStatusCode(), HttpStatus.SC_OK);
            assertEquals(restUtil.header.getContentType(),_config.ContentType);
            if(restUtil.header.getContentLength() != null)
            {
                assertEquals(restUtil.header.getContentLength(),_config.ContentLength);
            }
            testJson();
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    public void testJson()
    {
        assertTrue(restUtil.getJson().isPropertyArray("items"));
        restUtil.getJson().getArrayChildObject(1);
        assertTrue(restUtil.getJson().isKeyAvailable("owner"));
    }

}
