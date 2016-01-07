package com.wbl.base;

import com.wbl.utils.Configuration;
import org.apache.log4j.Logger;

/**
 * Created by svelupula on 8/14/2015.
 */
public abstract class BaseRestTest {

    public static final Configuration _config;

    static {
        _config = new Configuration("rest");
    }

    //Rally connection
    public Logger _logger;

    public BaseRestTest() {
        _logger = Logger.getLogger(BaseRestTest.class);

    }

}
