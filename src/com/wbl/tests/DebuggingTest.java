package com.wbl.tests;

import com.wbl.utils.web.WBy;

/**
 * Created by svelupula on 8/14/2015.
 */
public class DebuggingTest {

    public static void main(String[] args) {

        WBy.loadJsonMap(String.format("%s/locators.json", System.getProperty("user.dir")));

    }
}
