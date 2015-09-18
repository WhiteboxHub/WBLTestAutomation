package com.wbl.tests.api;

import com.wbl.base.BaseApiTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;

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
    public Object[][] getUsers() {
        return new Object[][]{{"users/whiteboxhub"}};
    }

    @Test(priority = 1, alwaysRun = true, dataProvider = "users-data")
    public void testUser(String username) throws Exception{
        restUtil.getJSONEntity(username);
        assertNotEquals(restUtil.isValidResponse(), null);
        assertEquals(restUtil.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(restUtil.header.getContentType(), "application/json; charset=utf-8");
        if(restUtil.header.getContentLength() != null)
        {
            assertEquals(restUtil.header.getContentLength(),"500");
        }
        assertEquals(restUtil.getJson().getPropertyCount(),30);
        assertEquals(restUtil.getLocale(),"en_US");
        assertEquals(restUtil.header.getServer(), "GitHub.com");
        assertTrue(restUtil.getJson().isKeyAvailable("id"));
        assertEquals(restUtil.getJson().getJsonIntValue("id"), 4023110);
        assertEquals(restUtil.getJson().getJsonValue("login"), "WhiteboxHub");

        //get repository url

        this.repoURL = restUtil.getResource(restUtil.getJson().getJsonValue("repos_url"));
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
