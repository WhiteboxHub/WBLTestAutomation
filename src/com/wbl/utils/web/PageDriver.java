package com.wbl.utils.web;

import com.wbl.utils.Configuration;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by svelupula on 8/8/2015.
 */
public class PageDriver implements ElementsContainer {

    private final Configuration _configuration;
    private final Browsers _browser;
    private WebDriver _webDriver;
    private String _mainWindowHandler;
    private Logger _logger;

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

    public void saveScreenShot(String path) {
        try {
            FileUtils.copyFile(((TakesScreenshot) _webDriver).getScreenshotAs(OutputType.FILE), new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object ExecuteJavaScript(String javaScript, Object[] args) {
        JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) _webDriver;

        return javaScriptExecutor.executeScript(javaScript, args);
    }

    public String getDescription() {
        return "Browser";
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
        return new FirefoxDriver();
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

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); //To maximize the browser
        options.addArguments("allow-file-access-from-files");
        options.addArguments("disable-rest-security");
        options.addArguments("ignore-certifcate-errors");
        options.addArguments("--always-authorize-plugins=true");

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return new ChromeDriver(capabilities);
    }

    private HtmlUnitDriver startHtmlUnit() {
        return new HtmlUnitDriver();
    }

}
