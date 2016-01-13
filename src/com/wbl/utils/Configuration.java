package com.wbl.utils;

import com.wbl.utils.web.Browsers;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by svelupula on 8/8/2015.
 */
public class Configuration {

    public Browsers Browser;
    public String Device;
    public String BaseUrl;
    public String BaseURI;
    public String TestResultPath;
    public String TestDataPath;
    public int WaitTimeout;
    private Logger _logger;
    public String DataFileName;
    public boolean TakeScreenShot;
    public String MobileOs;
    public String Devicename;
    public String APKName;
    public String AppiumURL;
    public String AppPackage;
    public String AppActivity;



    public Configuration(String type){//boolean isWebTest) {
        try {
            _logger = Logger.getLogger(Configuration.class);

            Properties props = loadProperties(type);
            setCommonProps(props);
            if(type=="web")
            {
                setWebProps(props);
            }
            else if(type=="rest")
            {
                setRestProps(props);
            }
            else
            {
                setMobProps(props);
            }

        } catch (Exception ex) {
            _logger.error(ex);
        }

    }

    private Properties loadProperties(String type)//boolean isWebTest)
    {
        Properties props = new Properties();
        try {
            if(type=="web")
            {
                props.load(new FileReader(String.format("%s/config.properties", System.getProperty("user.dir"))));
            }
            else if(type=="rest")
            {
                props.load(new FileReader(String.format("%s/restConfig.properties", System.getProperty("user.dir"))));
            }
            else
            {
                props.load(new FileReader(String.format("%s/mobileConfig.properties", System.getProperty("user.dir"))));
            }
        } catch (IOException e) {
            _logger.error(e);
        }

        return props;
    }

    public void setCommonProps(Properties mProps)
    {
        WaitTimeout = Integer.parseInt(mProps.getProperty("wait-timeout"));
        DataFileName = mProps.getProperty("data-file-name");
    }
    public void setWebProps(Properties mProps)
    {
        //Browser = Browsers.valueOf(props.getProperty("browser"));
        String browser = mProps.getProperty("browser");
        Browser = Browsers.Firefox;
        if(browser.toLowerCase().equals("firefox"))
        {
            Browser = Browsers.Firefox;
        }
        else if(browser.toLowerCase().equals("chrome"))
        {
            Browser = Browsers.Chrome;
        }
        else if(browser.toLowerCase().equals("htmlunit"))
        {
            Browser = Browsers.HtmlUnit;
        }
        else if(browser.toLowerCase().equals("ie"))
        {
            Browser = Browsers.InternetExplorer;
        }

        BaseUrl = mProps.getProperty("url");
        Device = mProps.getProperty("device");
        TestResultPath = mProps.getProperty("test-result-path");
        TestDataPath = mProps.getProperty("test-data-path");
        TakeScreenShot = Boolean.valueOf(mProps.getProperty("takeScreenShot"));
       // ScreenFolderPath = mProps.getProperty("screenshot-folder-path");
    }

    public void setRestProps(Properties mProps)
    {
        BaseURI = mProps.getProperty("uri");
    }

    public void setMobProps(Properties mProps)
    {
        MobileOs =mProps.getProperty("os");
        Devicename = mProps.getProperty("deviceName");
        APKName = mProps.getProperty("apkName");
        AppiumURL = mProps.getProperty("appiumURL");
        Browser = Browsers.Android;
        AppActivity = mProps.getProperty("appActivity");
        AppPackage = mProps.getProperty("appPackage");
    }
}
