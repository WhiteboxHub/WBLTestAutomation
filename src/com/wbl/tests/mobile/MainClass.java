package com.wbl.tests.mobile;

import com.wbl.utils.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by bharti on 1/6/2016.
 */
public class MainClass {

    public static void main(String[] args)
    {
        try {
            Configuration con = new Configuration("mob");
          String path =  new File(String.format("%s/"+con.APKName, System.getProperty("user.dir"))).getAbsolutePath();
            System.out.println(path);
        }
        catch(Exception e)
        {
        }
    }
}
