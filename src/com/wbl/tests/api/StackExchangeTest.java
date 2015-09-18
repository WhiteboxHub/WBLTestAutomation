package com.wbl.tests.api;

import com.wbl.base.BaseApiTest;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * Created by svelupula on 8/25/2015.
 */
public class StackExchangeTest extends BaseApiTest {

    @BeforeTest
    @Parameters({"uri"})
    public void setBaseUri(String uri) {
        //data setup
        restUtil.setUri(uri);
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
            assertEquals(restUtil.getJson().getPropertyCount(),4);
            assertTrue(restUtil.getJson().isPropertyArray("items"));
            assertEquals(restUtil.getJson().getArrayCount(),30);
            //restUtil.getJson().getArrayChildObject(1);
           // assertTrue(restUtil.getJson().isKeyAvailable("owner"));
    }

    @AfterTest
    public void clear()
    {
        restUtil.setUri(null);
    }
}
