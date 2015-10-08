package com.wbl.utils.web;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by ${supriya} on 10/8/2015.
 */
public class Wactions {

    private PageDriver _browser ;
    private WebElement _element ;
    private Logger _logger;
    private Select se;

    public Wactions(PageDriver browser, WebElement element) {
        _element = element;
        _browser = browser;
        _logger = Logger.getLogger(WSelect.class);
    }
    public Wactions(String locator) {

        try {
            WebElement e=_element.findElement(WBy.get(locator));
            se = new Select(e);
        }
        catch (Exception e) {
            _logger.error(e);
            e.printStackTrace();
        };
    }
}
