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
   // public String ScreenFolderPath;
    //public ArrayList<String> SheetNameList = new ArrayList<String>();


    public Configuration(boolean isWebTest) {
        try {
            _logger = Logger.getLogger(Configuration.class);

            Properties props = loadProperties(isWebTest);
            setCommonProps(props);
            if(isWebTest)
            {
                setWebProps(props);
            }
            else
            {
                setRestProps(props);
            }

        } catch (Exception ex) {
            _logger.error(ex);
        }

    }

    private Properties loadProperties(boolean isWebTest)
    {
        Properties props = new Properties();
        try {
        if(isWebTest) {
            props.load(new FileReader(String.format("%s/config.properties", System.getProperty("user.dir"))));
        }
        else{
            props.load(new FileReader(String.format("%s/restConfig.properties", System.getProperty("user.dir"))));
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
        //setSheetNames(mProps);
       // setJsonProps(mProps);
    }

    /*public void setJsonProps(Properties mProps)
    {
        String jsonKeys = mProps.getProperty("json-keys");
        String jsonValues = mProps.getProperty("json-values");
        String[] jsonKeysArr = jsonKeys.split(",");
        String[] jsonValuesArr = jsonValues.split(",");
        int count = 0;
        for (String key : jsonKeysArr)
        {
            JsonMap.put(key,jsonValuesArr[count]);
            count++;
        }
    }*/

    /*public void setSheetNames(Properties mProps)
    {
        String sheetName = mProps.getProperty("sheet-name");
        if(sheetName.contains(","))
        {
            String[] names = sheetName.split(",");
            for(int i=0;i<names.length;i++)
            {
                SheetNameList.add(names[i]);
            }
        }
        else
        {
            SheetNameList.add(sheetName);
        }
    }*/
}
