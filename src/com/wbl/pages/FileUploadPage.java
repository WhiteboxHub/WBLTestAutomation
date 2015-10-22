package com.wbl.pages;

import com.wbl.utils.web.*;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;

/**
 * Created by Innovapath on 10/20/2015.
 */
public class FileUploadPage extends PortalPage{

    public FileUploadPage(PageDriver driver) {
         super(driver);
    }

    public boolean openFile()
    {
        boolean status = true;
        try{
         driver.open("http://www.fotor.com/");
         driver.findElement("fileupload.name").click();
         driver.uploadFile();
        }
        catch(Exception ex)
        {
            status = false;
            _logger.error(ex);
            ex.printStackTrace();
        }

        return status;
    }

}
