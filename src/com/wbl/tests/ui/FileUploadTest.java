package com.wbl.tests.ui;

import com.wbl.base.BaseWebTest;
import com.wbl.pages.FileUploadPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Created by Innovapath on 10/20/2015.
 */
public class FileUploadTest extends BaseWebTest {

    private FileUploadPage _fp;

    @BeforeClass
    public void beforeClass() {
        _fp = new FileUploadPage(driver);
    }

    @Test
    public void testFileUpload()
    {
        assertTrue(_fp.openFile());
    }
}
