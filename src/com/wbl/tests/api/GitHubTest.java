package com.wbl.tests.api;

import com.wbl.base.BaseApiTest;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * Created by svelupula on 8/25/2015.
 */
public class GitHubTest extends BaseApiTest {

    public String repoURL;

    @BeforeTest
    @Parameters({"uri"})
    public void setBaseUri(String uri) {
        //data setup
        restUtil.setUri(uri);
    }

    @DataProvider(name = "users-data")
    public Object[][] getUsers() throws Exception {
        Object[][] data = excelUtils.getComplexExcelData(restUtil._configuration.DataFileName);
        return data;
    }

    @Test(priority = 1, alwaysRun = true, dataProvider = "users-data")
    public void testUser(String resource,Map<Object,Object> data) throws Exception{
        restUtil.getJSONEntity(resource);
        assertNotEquals(restUtil.isValidResponse(), null);
        String statusCode = String.valueOf(data.get("RESULT-STATUSCODE"));
        assertEquals(restUtil.getStatusCode(),statusCode);
        String locale = String.valueOf(data.get("RESULT-LOCALE"));
        assertEquals(restUtil.getLocale(), locale);
        int propertyCount = Integer.valueOf(String.valueOf(data.get("PROPERTY-COUNT")));
        assertEquals(restUtil.getJson().getPropertyCount(),propertyCount);

        String headerString = String.valueOf(data.get("HEADER"));
        if(!StringUtils.isEmpty(headerString)) {
            Map<String, String> headerFields = restUtil.getFields(headerString);
            String contentType = headerFields.get("Content-Type");
            assertEquals(restUtil.header.getContentType(), "contentType");
            if (restUtil.header.getContentLength() != null) {
                String contentLength = headerFields.get("Content-Length");
                assertEquals(restUtil.header.getContentLength(), contentLength);
            }
            String server = String.valueOf(headerFields.get("Server"));
            assertEquals(restUtil.header.getServer(), server);
        }

        String jsonString = String.valueOf(data.get("JSON"));
        if(!StringUtils.isEmpty(jsonString)) {
            Map<String, String> jsonFields = restUtil.getFields(jsonString);
            Iterator<String> keys = jsonFields.keySet().iterator();
            while(keys.hasNext()) {
                String key = keys.next();
                assertTrue(restUtil.getJson().isKeyAvailable(key));
                assertEquals(restUtil.getJson().getJsonValue(key),String.valueOf(jsonFields.get(key)));
                if(key.equalsIgnoreCase("repos_url"))
                {
                    //get repository url
                    this.repoURL = restUtil.getResource(restUtil.getJson().getJsonValue(key));
                }
            }


        }
    }

    @Test(dependsOnMethods = {"testUser"})
    public void testRepos()throws Exception
    {
        restUtil.getJSONArray(repoURL);
        assertEquals(restUtil.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(restUtil.header.getContentType(), "application/json; charset=utf-8");
        if(restUtil.header.getContentLength() != null)
        {
            assertEquals(restUtil.header.getContentLength(),"500");
        }
        assertEquals(restUtil.getJson().getArrayCount(),9);
        restUtil.getJson().getJsonArrayObject(3);
        assertEquals(restUtil.getJson().getPropertyCount(), 67);
        assertEquals(restUtil.getJson().getJsonValue("url"),"https://api.github.com/repos/WhiteboxHub/Programming-Practice");
        assertEquals(restUtil.getJson().getJsonIntValue("id"),40985176);

    }

    @AfterTest
    public void clear()
    {
        restUtil.setUri(null);
    }
}
