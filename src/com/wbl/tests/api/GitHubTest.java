package com.wbl.tests.api;

import com.wbl.base.BaseApiTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.*;

/**
 * Created by svelupula on 8/25/2015.
 */
public class GitHubTest extends BaseApiTest {


    @BeforeClass
    public void beforeClass() {
        //data setup
    }

    @DataProvider(name = "users-data")
    public Object[][] getUsers() {
        return new Object[][]{{"users/whiteboxhub"}};
    }
/*
    @DataProvider(name = "users-data")
    public Object[][] getJsonData() {
        return new Object[][]{{"users/whiteboxhub"}};
    }*/

    @Test(priority = 1, alwaysRun = true, dataProvider = "users-data")
    public void testUser(String username) {
        try {
            restUtil.getJSONEntity(username);
            assertNotEquals(restUtil.isValidResponse(),null);
            assertEquals(restUtil.getStatusCode(), HttpStatus.SC_OK);
            assertEquals(restUtil.header.getContentType(),_config.ContentType);
            if(restUtil.header.getContentLength() != null)
            {
                assertEquals(restUtil.header.getContentLength(),_config.ContentLength);
            }
            assertEquals(restUtil.getLocale(),_config.Locale);
            assertEquals(restUtil.header.getServer(),_config.Server);
            testJsonObject();


        } catch (Exception e) {
            assertFalse(true);
        }
    }

    public void testJsonObject()
    {
        assertTrue(restUtil.getJson().isKeyAvailable("id"));
        assertEquals(restUtil.getJson().getJsonIntValue("id"), 4023110);
        assertEquals(restUtil.getJson().getJsonValue("login"),"WhiteboxHub");
    }

}
