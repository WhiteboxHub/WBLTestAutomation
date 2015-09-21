package com.wbl.base;

import com.wbl.utils.ExcelUtils;
import com.wbl.utils.rest.RESTUtil;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by svelupula on 8/14/2015.
 */
public abstract class BaseApiTest extends BaseRestTest {

    public RESTUtil restUtil;
    public ExcelUtils excelUtils;

    @BeforeSuite
    public void beforeSuite() {
        restUtil = new RESTUtil(_config);
        excelUtils = new ExcelUtils();
    }

    @AfterSuite
    public void afterSuite() {
        restUtil = null;
        excelUtils = null;
    }

}
