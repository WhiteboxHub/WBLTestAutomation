package com.wbl.tests.api;

import com.wbl.base.BaseApiTest;
import org.apache.http.HttpStatus;
import org.json.JSONException;
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
    public void testSearchData(String query)throws Exception{
            restUtil.getJSONEntity(query);
            assertNotEquals(restUtil.isValidResponse(), null);
            assertEquals(restUtil.getStatusCode(), HttpStatus.SC_OK);
            assertEquals(restUtil.header.getContentType(), "application/json; charset=utf-8");
            if(restUtil.header.getContentLength() != null)
            {
                assertEquals(restUtil.header.getContentLength(),"500");
            }
            assertEquals(restUtil.getJson().getPropertyCount(),30);
            assertTrue(restUtil.getJson().isPropertyArray("items"));
            assertEquals(restUtil.getJson().getArrayCount(),4);
            //restUtil.getJson().getArrayChildObject(1);
            assertTrue(restUtil.getJson().isKeyAvailable("owner"));
    }

}
