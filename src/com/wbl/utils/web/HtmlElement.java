package com.wbl.utils.web;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by svelupula on 8/8/2015.
 */
public class HtmlElement implements ElementsContainer {

    private final PageDriver _browser;
    private final WebElement _element;
    private Logger _logger;

    public HtmlElement(PageDriver browser, WebElement element) {
        _element = element;
        _browser = browser;
        _logger = Logger.getLogger(HtmlElement.class);
    }

    public HtmlElement findElement(String locator) {
        try {
            return new HtmlElement(this._browser, _element.findElement(WBy.get(locator)));
        } catch (Exception ex) {
            _logger.error(ex);
            return null;
        }
    }

    // Do not throws exceptions, only return null
    public Collection<HtmlElement> findElements(String locator) {
        Collection<HtmlElement> elements = null;
        try {
            Collection<WebElement> webElements = _element.findElements(WBy.get(locator));
            if (webElements.size() > 0) {
                elements = new ArrayList<HtmlElement>();
            }
            for (WebElement element : webElements) {
                HtmlElement el = new HtmlElement(this._browser, element);
                if (elements != null) elements.add(el);
            }
            return elements;
        } catch (Exception ex) {
            _logger.error(ex);
            return null;
        }
    }

    public boolean isDisplayed() {
        return _element.isDisplayed();
    }

    public String getValue() {
        return _element.getAttribute("value");
    }

    public String getCssClass() {
        return _element.getAttribute("class");
    }

    public String getLink() {
        return _element.getAttribute("href");
    }

    public String getText() {
        return _element.getText();
    }

    public void type(String text) {
        if (text == null) {
            return;
        }
        clear();
        _element.sendKeys(text);
        //Value.Should().BeEquivalentTo(text, "should be similar to entered text");
    }

    public void clear() {
        _element.clear();
    }

    public void pressEnter() {
        _element.sendKeys(Keys.RETURN);
    }

    public void click() {
        _element.click();
    }

    public void focus() {
        if (_element.getTagName().equals("input")) {
            _element.sendKeys("");
        } else {
            new Actions(_browser.getDriver()).moveToElement(_element).perform();
        }
    }

    public String getDescription() {
        return String.format("<{0} class=\"{1}\" />", _element.getTagName(), getCssClass());
    }

    public void sendKeys(String chars)
    {
        _element.sendKeys(chars);
    }

    public String getAttribute(String attribute)
    {
        return _element.getAttribute(attribute);
    }

    public void performClickAndHold(Actions action)
    {
        action.clickAndHold(_element).perform();
    }

    public void mouseOver(Actions action)
    {
        action.moveToElement(_element).build().perform();
    }

}
