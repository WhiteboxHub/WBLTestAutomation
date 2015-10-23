package com.wbl.utils.web;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by Alok on 10/7/2015.
 */
public class WSelect {
    private Logger _logger;
    private Select se;

    public WSelect() {
        _logger = Logger.getLogger(WSelect.class);
    }
    public WSelect(WebElement element) {

        try {
            se = new Select(element);
        }
        catch (Exception e) {
            _logger.error(e);
            e.printStackTrace();
        };
    }


    //Select the option at the given index. This is done by examing the "index" attribute of an element, and not merely by counting.
    public void byIndex(int index) {          se.selectByIndex(index);    }

    //Select all options that display text matching the argument. That is, when given "Bar" this would select an option like: <option value="foo">Bar</option>
    public void byVisibleText(String text)    {
        se.selectByVisibleText(text);
    }


    public  WebElement  firstSelectedOption()    {        return se.getFirstSelectedOption();    }

    //All selected options belonging to this select tag
    public List<WebElement> allSelectedOptions()    {       return se.getAllSelectedOptions();    }

    //All options belonging to this select tag
    public List<WebElement> getallOptions()    {        return se.getOptions();    }

    //Whether this select element support selecting multiple options at the same time? This is done by checking the value of the "multiple" attribute.
    public boolean isMultiple()    {      return   se.isMultiple();    }

    //Select all options that have a value matching the argument. That is, when given "foo" this would select an option like: <option value="foo">Bar</option>
    public void byValue(String value)    {        se.selectByValue(value);    }

    //Clear all selected entries. This is only valid when the SELECT supports multiple selections.
    public void allDeselect()    {        se.deselectAll();    }

    //Deselect all options that have a value matching the argument. That is, when given "foo" this would deselect an option like: <option value="foo">Bar</option>
    public void byValueDeselect(String value)   {   se.deselectByValue(value);  }
    //Deselect the option at the given index. This is done by examing the "index" attribute of an element, and not merely by counting.
    public void byIndexDeselect(int index)  {   se.deselectByIndex(index);  }

    //Deselect all options that display text matching the argument. That is, when given "Bar" this would deselect an option like: <option value="foo">Bar</option>
    public void byVisibleTextDeselect(String text)    {   se.deselectByVisibleText(text);}
}
