package com.wbl.utils.web;

import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.io.File;

/**
 * Created by Innovapath on 10/20/2015.
 */
public class FileUpload {

    public String getFilePath()
    {
        Properties props = new Properties();
        String fileName = null;
        try {
            props.load(new FileReader(String.format("%s/autoItScripts/fileName.properties", System.getProperty("user.dir"))));
            fileName = props.getProperty("fileName");
           fileName =  new File(String.format("%s/autoItScripts", System.getProperty("user.dir"))).getAbsolutePath()+"\\"+fileName;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public void uploadFile(String filePath)throws IOException
    {
        Runtime.getRuntime().exec(filePath);
    }

}
