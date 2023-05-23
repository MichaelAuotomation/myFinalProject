package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
    }


    public void fillText(WebElement el, String text){
        waitForElement(el);
        //highLight
        highlightElement(el, "orange");
        el.click();
        el.clear();
        el.sendKeys(text);
        sleep(200);
    }

    public void click (WebElement el){
        waitForElement(el);
        //highLight
        highlightElement(el, "green");
        el.click();
        sleep(200);
    }

    public String getText(WebElement el) {
        waitForElement(el);
        //highLight
        highlightElement(el, "blue");
        sleep(200);
        return el.getText();
    }

    public void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void select(WebElement el, int index){
        waitForElement(el);
        //highlight
        highlightElement(el, "blue");
        Select s = new Select(el);
        s.selectByIndex(index);
    }

     //Call this method with your element and a color like (red,green,orange etc...)
    private void highlightElement(WebElement element, String color) {
        //keep the old style to change it back
        String originalStyle = element.getAttribute("style");
        String newStyle = "background-color: yellow; border: 1px solid " + color + ";" + originalStyle;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Change the style
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
                element);
        // Change the style back after few milliseconds
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
                + originalStyle + "');},400);", element);
    }

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}