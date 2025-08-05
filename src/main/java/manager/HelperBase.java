package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    public void type(By locator, String text) {
        WebElement we = wd.findElement(locator);
        we.click();
        we.clear();
        if (text != null) {
            we.sendKeys(text);
        }
    }

    public void click(By locator) {
        WebElement we = wd.findElement(locator);
        we.click();
    }

    public boolean isElementPresent(By locator) {
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;
    }

    public boolean loginSuccessOrFailed(By locator) {
        WebElement we = wd.findElement(locator);
        return we.getText().contains("Logged in");
    }

    public boolean buttonLoginDisabled(By locator) {
        WebElement we = wd.findElement(locator);
        return we != null;
    }
//   public boolean containsText(By locator,String text){
//        WebElement we = wd.findElement(locator);
//        return we.getText().contains(text);
//
//   }

    public boolean checkXButton(By locator, String text) {
        WebElement we = wd.findElement(locator);
        return we.getAttribute("class").contains(text);
    }
}
