package com.wbl.utils.web;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Innovapath on 10/14/2015.
 */
public class WScreenshot {

    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    TakesScreenshot takesScreenshot;

    public WScreenshot(TakesScreenshot takesScreenshot)
    {
        this.takesScreenshot = takesScreenshot;
    }

    public void takeScreenShot(boolean isTakeScreens)throws IOException
    {
        if(isTakeScreens)
        {
            String date = getFormattedDate();
            String parentPath =  new File(String.format("%s/screenshots", System.getProperty("user.dir"))).getAbsolutePath();
            String newDir = parentPath+"/"+date;
            File file = new File(newDir);
            if(!file.exists()) {
                new File(newDir).mkdir();
            }
            String latestFilePath = file.getPath();
            int count =  new File(latestFilePath).listFiles().length+1 ;
            File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            // File file =  new File(_configuration.ScreenFolderPath+"\\"+date.toString());
            String path = newDir+"/Screen"+count+".png";
            FileUtils.copyFile(scrFile, new File(path));

        }
    }
    public String getFormattedDate()
    {
        return format.format(new Date()).toString();
    }
}
