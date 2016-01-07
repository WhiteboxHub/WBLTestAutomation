package com.wbl.base.mobile;

import com.wbl.utils.Configuration;
import org.apache.log4j.Logger;

/**
 * Created by svelupula on 8/14/2015.
 */
public abstract class BaseTest {

    public static final Configuration _config;

    static {
        _config = new Configuration("mob");
    }

    //Rally connection
    public Logger _logger;

    public BaseTest() {
        _logger = Logger.getLogger(BaseTest.class);

    }

}
