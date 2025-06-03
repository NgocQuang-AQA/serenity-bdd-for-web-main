package bb.common;

import bb.utils.DataUtils;
import bb.utils.LoggerUtil;
import io.appium.java_client.functions.ExpectedCondition;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.model.ThucydidesSystemProperty;
import org.joda.time.DateTime;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BasePage extends PageObject {

    public static final int TIMEOUT_IN_SECONDS = (int)DataUtils.getWaitTimeoutInSecond();

    protected WebDriverWait getExplicitWait(int timeout) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }

    protected FluentWait<WebDriver> getFluentWait(int timeout) {
        return new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
    }

    public void waitForAlertAndAccept() {
        getExplicitWait(15).until(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();
    }

    public String getAlertText() {
        getExplicitWait(15).until(ExpectedConditions.alertIsPresent());
        return getDriver().switchTo().alert().getText();
    }

    public static String changeCurrencyFormatVND(String balance) {
        double balanceDouble = Double.parseDouble(balance);
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(balanceDouble);
    }

    public boolean checkIfElementIsPresent(WebElementFacade el) {
        try {
            return el.waitUntilVisible().isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitUntilElementInvisibleByXpath(int timeoutInSeconds, String xpath) {
        getFluentWait(timeoutInSeconds)
                .ignoring(org.openqa.selenium.NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitUntilElementVisibleByXpath(int timeout, String xpath) {
        getExplicitWait(timeout).until(ExpectedConditions.visibilityOf($(xpath)));
    }

    public WebElementFacade waitUntilElementInvisible(int timeout, WebElementFacade e) {
        getFluentWait(timeout).until(ExpectedConditions.invisibilityOf(e));
        return e;
    }

    public void waitUntilElementInvisibleById(int timeout, String id) {
        getExplicitWait(timeout).until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
    }

    public WebElementFacade waitUntilElementVisible(int timeoutInSeconds, WebElementFacade e) {
        getFluentWait(timeoutInSeconds).until(ExpectedConditions.visibilityOf(e));
        return e;
    }

    public WebElementFacade waitElementEnable(int timeout, WebElementFacade e) {
        getFluentWait(timeout).until(ExpectedConditions.elementToBeClickable(e));
        return e;
    }

    public void waitUntilElementEnableByXpath(int timeout, String xpath) {
        getExplicitWait(timeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public String formatNumber(String s) {
        return s.replaceAll("[^0-9]+", "");
    }

    public List<String> getTextListElement(By by) {
        return findAll(by).stream().map(WebElementFacade::getText).collect(Collectors.toList());
    }

    public List<String> getTextListElementFormatXpath(String xpathNeedFormat, List<String> listStringFormat) {
        return listStringFormat.stream()
                .map(s -> $(String.format(xpathNeedFormat, s)).getText())
                .collect(Collectors.toList());
    }

    public static String changeAccountNumberFormat(String s) {
        return s.replaceAll("(.{4})", "$1 ").trim();
    }

    public String getLastMonth(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return simpleDateFormat.format(cal.getTime());
    }

    public String getCurrentMonth(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    public static String formatBlankSpace(String s) {
        return s.replaceAll("[\\s\\u00A0]+", "");
    }


    public long randomNumber(long quantity) {
        Random rand = new Random();
        return rand.nextLong(quantity);
    }

    public void sendKeysByKeyboard(String value) {
        Actions actions = new Actions(getDriver());
        actions.sendKeys(value).perform();
    }

    public Point getElementCenterPoint(WebElementFacade element) {
        Rectangle reg = element.getRect();
        return new Point(reg.getX() + reg.getWidth() / 2, reg.getY() + reg.getHeight() / 2);
    }

    public void waitAMilliSeconds(int milliseconds) {
        waitABit(milliseconds);
    }

    public void scrollToElement(WebElementFacade element) {
        evaluateJavascript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public void scrollToBottom() {
        evaluateJavascript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToTop() {
        evaluateJavascript("window.scrollTo(0, 0);");
    }

    public void jsClick(WebElementFacade element) {
        evaluateJavascript("arguments[0].click();", element);
    }

    public void hoverToElement(WebElementFacade element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
    }

    public void waitForPageLoaded() {
        getFluentWait(30).until(driver ->
                evaluateJavascript("return document.readyState").toString().equals("complete")
        );
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    public void highlightElement(WebElementFacade element) {
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].style.border='2px solid red'", element);
    }

    public void highlightElementAndLogText(WebElementFacade element) {
        highlightElement(element);
        String text = element.getText();
        LoggerUtil.logInfo("Click on element " + ((text == null || text.isEmpty() || text.equals(" ")) ? element.toString() : "has text: [" + text + "]"));
    }


    public boolean isElementExist(String xPath) {
        boolean isExist = false;
        List<WebElementFacade> e = findAllElements(xPath);
        if (!e.isEmpty()) {
            highlightElement(e.get(0));
            isExist = true;
        }
        LoggerUtil.logInfo("Is Element " + xPath + " Exist: " + isExist);
        return isExist;
    }


    public void clickOnElement(WebElementFacade element) throws Exception {
        DateTime timeout = DateTime.now().plusSeconds(Integer.parseInt(String.valueOf(TIMEOUT_IN_SECONDS)));
        do {
            try {
                scrollToElement(element);
                highlightElementAndLogText(element);
                element.click();
                break;
            } catch (Exception exception) {
                LoggerUtil.logWarn("Retrying click element...");
                if (!DateTime.now().isBefore(timeout)) throw new Exception("Can not click on element", exception);
            }
        } while (DateTime.now().isBefore(timeout));
        waitForAllLoadingCompleted();
    }
    public By isXpathOrCssSelector(String value) {
        if (value.contains("//")) return By.xpath(value);
        else return By.cssSelector(value);
    }

    public List<WebElementFacade> findAllElements(String xpathOrCss) {
        return findAll(xpathOrCss);
    }

    public WebElementFacade getElement(String xpathOrCss) {
        return find(isXpathOrCssSelector(xpathOrCss));
    }

    public void clickOnElement(String xpathOrCss) {
        LoggerUtil.logInfo("xpathOrCss: " + xpathOrCss);
        WebElementFacade element = getElement(xpathOrCss);
        try {
            clickOnElement(element);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void waitForAllLoadingCompleted() {
        waitUntilJQueryRequestCompleted(TIMEOUT_IN_SECONDS);
        waitForJQueryLoadingCompleted(TIMEOUT_IN_SECONDS);
        waitUntilHTMLReady(TIMEOUT_IN_SECONDS);
    }


    public void waitUntilJQueryRequestCompleted(int timeoutInSeconds) {
        new FluentWait<>(getDriver()).withTimeout(Duration.of(timeoutInSeconds, ChronoUnit.SECONDS))
                .withMessage("**** INFO **** JQUERY STILL LOADING FOR OVER" + timeoutInSeconds + "SECONDS.")
                .pollingEvery(Duration.of(300, ChronoUnit.MILLIS)).until((ExpectedCondition<Boolean>) driver -> {
                    try {
                        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
                        return (Boolean) jsExec.executeScript("return jQuery.active === 0");
                    } catch (Exception e) {
                        return true;
                    }
                });
    }

    public void waitForJQueryLoadingCompleted(int timeoutInSeconds) {
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return $.active") == 0);
            } catch (Exception e) {
                // no jQuery present
                return true;
            }
        };
        getExplicitWait(timeoutInSeconds).until(jQueryLoad);
    }

    public void waitUntilHTMLReady(int timeoutInSeconds) {
        getFluentWait(timeoutInSeconds)
                .pollingEvery(Duration.of(300, ChronoUnit.MILLIS))
                .until(driver -> {
                    try {
                        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
                        return jsExec.executeScript("return document.readyState")
                                .toString().equals("complete");
                    } catch (Exception e) {
                        return true;
                    }
                });
    }

}
