package com.wbl.utils.web;

import com.wbl.utils.Configuration;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by svelupula on 8/8/2015.
 */
public class PageDriver implements ElementsContainer {

    public final Configuration _configuration;
    private final Browsers _browser;
    private WebDriver _webDriver;
    private String _mainWindowHandler;
    private Logger _logger;
    private WScreenshot wScreenshot;
    private WSelect wSelect;
    private WwindowHandles wWindowHandles = new WwindowHandles();
    private FileUpload fileUpload =  new FileUpload();

    public PageDriver(Configuration configuration) {
        _configuration = configuration;
        _browser = _configuration.Browser;
        _logger = Logger.getLogger(PageDriver.class);
        start();
    }

    public WebDriver getDriver() {
        if (_webDriver == null) {
            try {
                start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return _webDriver;
    }

    public Browsers getBrowser() {
        return _browser;
    }

    public String getMainWindowHandler() {
        return _mainWindowHandler;
    }

    public HtmlElement findElement(String locator) {
        try {
            return new HtmlElement(this, _webDriver.findElement(WBy.get(locator)));
        } catch (Exception ex) {
            ex.printStackTrace();
            _logger.error(ex);
            return null;
        }
    }

    // Do not throws exceptions, only return null
    public Collection<HtmlElement> findElements(String locator) {
        Collection<HtmlElement> elements = null;
        try {
            Collection<WebElement> webElements = _webDriver.findElements(WBy.get(locator));
            if (webElements.size() > 0) {
                elements = new ArrayList<HtmlElement>();
            }
            for (WebElement element : webElements) {
                HtmlElement el = new HtmlElement(this, element);
                if (elements != null) elements.add(el);
            }
            return elements;
        } catch (Exception ex) {
            ex.printStackTrace();
            _logger.error(ex);
            return null;
        }
    }

    public void quit() {
        if (_webDriver == null) {
            return;
        }
        _webDriver.quit();
        _webDriver = null;

        // TODO: Kill rest driver process: chromedriver.exe, IEDriverServer.exe (test regarding should it be done on start)
    }

    public void close()
    {
        if(_webDriver != null)
        {
            _webDriver.close();
        }
    }

    public void open(String url) {
        _webDriver.navigate().to(url);
        try {
            implicitWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return _webDriver.getTitle();
    }

    public void implicitWait() throws Exception {
        if (_browser != Browsers.HtmlUnit) {
            _webDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
            return;
        }
        Thread.sleep(2000);
    }

    public String getCurrentUrl() {
        return _webDriver.getCurrentUrl();
    }

    public Set<String> getLinks(String locator) {
        Set<String> links = new HashSet<String>();
        for (HtmlElement el : this.findElements(locator)) {
            links.add(el.getLink());
        }
        return links;
    }

    public WebDriver getWebDriver()
    {
    	return _webDriver;
    }
    
    public Object executeJavaScript(String javaScript, String locator) {
        JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) _webDriver;
        try {
            return javaScriptExecutor.executeScript(javaScript, _webDriver.findElement(WBy.get(locator)));
        } catch (Exception e) {
            _logger.error(e);
            e.printStackTrace();
        }
        return null;
    }

    public Object executeAsyncJavaScript(String javaScript, String locator) {
        JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) _webDriver;
        try {
            return javaScriptExecutor.executeAsyncScript(javaScript, _webDriver.findElement(WBy.get(locator)));
        } catch (Exception e) {
            _logger.error(e);
            e.printStackTrace();
        }
        return null;
    }

    public String getDescription() {
        return "Browser";
    }

    public String getCookie(String cookieName)
    {
        String value = null;
        if(_browser == Browsers.InternetExplorer) {
            value = (String) ((JavascriptExecutor) _webDriver).executeScript("return document.cookie;");
            if(value.contains("="))
            {
                value = value.split("=")[1];
            }
        }
        else
        {
            value = _webDriver.manage().getCookieNamed(cookieName).getValue();
        }
        return value;
    }

    public void implicitWait(long timeout)throws Exception
    {
       // if (_browser != Browsers.HtmlUnit) {
        _webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
          Thread.sleep(timeout);
            return;
      //  }

    }


    public void elementClickWait(By locator )
    {
      // if (_browser != Browsers.HtmlUnit) {
            long timeout = Long.valueOf(_configuration.WaitTimeout).longValue();
            WebDriverWait wait = new WebDriverWait(_webDriver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
      //s  }
    }

    public void visibilityWait(By locator)
    {
        //if (_browser != Browsers.HtmlUnit) {
        try {
            long timeout = Long.valueOf(_configuration.WaitTimeout).longValue();
            WebDriverWait wait = new WebDriverWait(_webDriver, timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // }
    }
    public void waitForLoad()
    {
        if (_browser != Browsers.HtmlUnit) {
            ExpectedCondition<Boolean> pageLoadCondition = new
                    ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver _webDriver) {
                            return ((JavascriptExecutor) _webDriver).executeScript("return document.readyState").equals("complete");
                        }
                    };
            long timeout = Long.valueOf(_configuration.WaitTimeout).longValue();
            WebDriverWait wait = new WebDriverWait(_webDriver, timeout);
            wait.until(pageLoadCondition);
       }
    }

    public void presenceWait(By locator)
    {
        try {
        long timeout = Long.valueOf(_configuration.WaitTimeout).longValue();
        WebDriverWait wait = new WebDriverWait(_webDriver, timeout);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Actions initializeAction()
    {
        return new Actions(_webDriver);
    }

    private void start() {
        try {
            switch (_browser) {
                case InternetExplorer:
                    _webDriver = startInternetExplorer();
                    break;
                case Firefox:
                    _webDriver = startFirefox();
                    break;
                case Chrome:
                    _webDriver = startChrome();
                    break;
                case HtmlUnit:
                    _webDriver = startHtmlUnit();
                    break;
                case Android:
                    _webDriver = startAndroidDriver();
                    break;
                default:
                    _webDriver = startHtmlUnit();
                    break;
            }
            _webDriver.get(_configuration.BaseUrl);
            if (_browser != Browsers.HtmlUnit) {
                _webDriver.manage().window().maximize();
                _webDriver.manage().deleteAllCookies();
            }
            _mainWindowHandler = _webDriver.getWindowHandle();

        } catch (Exception ex) {
            _logger.error(ex);
        }
    }

    private InternetExplorerDriver startInternetExplorer() {
        System.setProperty("webdriver.ie.driver", String.format("%s/IEDriverServer.exe", System.getProperty("user.dir")));
        //WindowsUtils.writeStringRegistryValue("HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Microsoft\\Internet Explorer\\Main\\FeatureControl\\FEATURE_BFCACHE", "2.48.2");
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, false);
        caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
        caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, "true");
        caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        return new InternetExplorerDriver(caps);
    }

    private FirefoxDriver startFirefox() {
        // FirefoxProfile firefoxProfile = new FirefoxProfile();
        /*firefoxProfile.setAcceptUntrustedCertificates(true);
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        firefoxProfile.setPreference("browser.download.dir", _configuration.TestResultPath);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");
        firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel;application/msword;application/octet-stream");

        firefoxProfile.setPreference("browser.download.manager.showWhenStarting",
                false);
        firefoxProfile.setPreference("browser.download.manager.focusWhenStarting",
                false);
        firefoxProfile.setPreference("browser.download.useDownloadDir", true);
        firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        firefoxProfile.setPreference("browser.download.manager.closeWhenDone", true);
        firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete",
                false);
        firefoxProfile.setPreference("browser.download.manager.useWindow", false);
        firefoxProfile.setPreference(
                "services.sync.prefs.sync.browser.download.manager.showWhenStarting",
                false);
        firefoxProfile.setPreference("pdfjs.disabled", true);*/
        FirefoxBinary fb = new FirefoxBinary();
        fb.setEnvironmentProperty("DISPLAY", ":2");
        return new FirefoxDriver(fb,null);
    }

    private ChromeDriver startChrome() {
        System.setProperty("webdriver.chrome.driver", String.format("%s/chromedriver.exe", System.getProperty("user.dir")));
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        //Mobile Browsers using Chrome Emulation
        if (!_configuration.Device.toLowerCase().equals("desktop")) {

            Map<String, Object> chromeOptions = new HashMap<String, Object>();
            Map<String, String> mobileEmulation = new HashMap<String, String>();
            mobileEmulation.put("deviceName", _configuration.Device);
            chromeOptions.put("mobileEmulation", mobileEmulation);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized"); //To maximize the browser
            options.addArguments("allow-file-access-from-files");
            options.addArguments("disable-rest-security");
            options.addArguments("ignore-certifcate-errors");
            options.addArguments("--always-authorize-plugins=true");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        }
        return new ChromeDriver(capabilities);
    }

    private HtmlUnitDriver startHtmlUnit() {
        return new HtmlUnitDriver(true);
    }

    private AndroidDriver startAndroidDriver()
    {
        AndroidDriver driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("deviceName", _configuration.Devicename);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"4.4.2" );
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,_configuration.Devicename);
        capabilities.setCapability(MobileCapabilityType.APP,new File(String.format("%s/"+_configuration.APKName, System.getProperty("user.dir"))).getAbsolutePath());
        capabilities.setCapability("appPackage",_configuration.AppPackage);
        capabilities.setCapability("appActivity",_configuration.AppActivity);
        try {
            driver =  new AndroidDriver(new URL(_configuration.AppiumURL), capabilities);
            driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
            Thread.sleep(10000);
        } catch (Exception e) {
            _logger.error(e);
        }

        return driver;
    }

    public void takeScreenShot()throws IOException
    {
        if(_browser != Browsers.HtmlUnit) {
            wScreenshot = new WScreenshot((TakesScreenshot) _webDriver);
            wScreenshot.takeScreenShot(_configuration.TakeScreenShot);
        }
    }

    public void getwWindowHandles(boolean isSingleWindow) {

        if(isSingleWindow)
        {
            wWindowHandles.switchToWindow(_webDriver);
        }
        else
        {
            wWindowHandles.windowHandles(_webDriver);
        }
    }

    public void uploadFile() {
        if(fileUpload != null)
            try {
                fileUpload.uploadFile(fileUpload.getFilePath());
            } catch (IOException e) {
                _logger.error(e);
                e.printStackTrace();
            }
    }

    public WSelect getwSelect(String locator)
    {
        try {
            wSelect = new WSelect(_webDriver.findElement(WBy.get(locator)));
            return wSelect;
        } catch (Exception e) {
            _logger.error(e);
            e.printStackTrace();
        }

        return null;
    }

    public String getPageSource()
    {
        return _webDriver.getPageSource();
    }

    public void installApp(AndroidDriver driver)
    {
        try {
            driver.installApp(new File(String.format("%s/"+_configuration.APKName, System.getProperty("user.dir"))).getAbsolutePath());
            driver.startActivity("io.selendroid.testapp", ".HomeScreenActivity");
        }
        catch(Exception e)
        {
            _logger.error(e);
        }
    }
}
