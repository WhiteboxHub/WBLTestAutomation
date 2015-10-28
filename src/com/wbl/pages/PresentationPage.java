package com.wbl.pages;

import com.wbl.utils.web.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Created by svelupula on 8/14/2015.
 */
public class PresentationPage extends PortalPage {

    LoginPage _lp;

    public PresentationPage(PageDriver driver) {
        super(driver);
        this._lp = new LoginPage(driver);
    }

    public boolean getPresentation(String userName,String pwd,String pptName,String pptPwd)
    {
        boolean status = false;
        try {
            _lp.getLoginPage();
            driver.waitForLoad();
            status = _lp.perfromLogin(userName, pwd);
            if (status) {
                Actions action = driver.initializeAction();
                HtmlElement resourceElement = driver.findElement("home:resource");
                resourceElement.mouseOver(action);
                //resourceElement.performClickAndHold(action);
                 driver.takeScreenShot();
                clickOnPresentation();
                driver.takeScreenShot();
                openPresentation(pptName, pptPwd);
            }
        }
        catch (Exception e)
        {
            status = false;
            _logger.error(e);
        }
        return status;
    }

    public void clickOnPresentation() throws Exception
    {
        List<HtmlElement> elements = (List)driver.findElements("home:resource.list");
        for (HtmlElement element : elements)
        {
            if(element.getAttribute("href").contains("presentations"))
            {
               // driver.visibilityWait(WBy.get("link=presentation.text"));
                element.click();
                break;
            }
        }
    }

    public void openPresentation(String pptName, String pptPwd)throws InterruptedException,Exception
    {
        List<HtmlElement> pptElements = (List)driver.findElements("xpath=presentation:list");
        for (HtmlElement ppt : pptElements)
        {
            if(ppt.getText().equalsIgnoreCase(pptName))
            {
                ppt.click();
                driver.getwWindowHandles(false);
                driver.waitForLoad();
                //driver.visibilityWait(WBy.get("class=presentation.dialog"));
                HtmlElement pwdDialog = driver.findElement("presentation.dialog.input");
                System.out.println(pwdDialog);
                if(pwdDialog != null && pwdDialog.isDisplayed())
                {
                    driver.getwWindowHandles(true);
                    driver.takeScreenShot();
                    driver.findElement("presentation.dialog.input").sendKeys(pptPwd);
                    driver.findElement("presentation.dialog.submit").click();
                    HtmlElement downloadLink = driver.findElement("presentation.dialog.download");
                    driver.takeScreenShot();
                    if(downloadLink != null && driver.findElement("presentation.dialog.download").isDisplayed())
                    {
                        driver.findElement("presentation.dialog.download").click();
                    }
                }
                break;
            }
        }
    }
}
