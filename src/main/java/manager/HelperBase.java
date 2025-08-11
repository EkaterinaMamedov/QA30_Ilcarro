package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperBase {

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void clearDate(By locator) {
        WebElement we = wd.findElement(locator);
        we.click();
        we.clear();

    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        WebElement we = wd.findElement(locator);
        we.click();
        we.clear();
        clearNew(we);
        if (text != null) {
            we.sendKeys(text);
        }
    }

    public void clearNew(WebElement element) {
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public boolean buttonSubmitDisabled(By locator) {
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));
        //******************************
        WebElement element = wd.findElement(By.cssSelector("button[type='submit']"));
        boolean result = element.isEnabled();

        return res && !result;
    }
        public boolean checkXButton(By locator, String text) {
        WebElement we = wd.findElement(locator);
        return we.getAttribute("class").contains(text);
    }
}

//   public boolean containsText(By locator,String text){
//        WebElement we = wd.findElement(locator);
//        return we.getText().contains(text);
//
//   }


