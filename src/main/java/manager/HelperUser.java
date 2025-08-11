package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void clearForm() {
        clearDate(By.id("email"));
        clearDate(By.id("password"));
    }

    public void openLoginForm() {
        click(By.cssSelector("a[href='/login?url=%2Fsearch']"));
    }


    public void fillLoginForm(String email, String password) {
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//input[@id='password']"), password);
    }

    public void fillLoginForm(User user) {
        type(By.xpath("//input[@id='email']"), user.getEmail());
        type(By.xpath("//input[@id='password']"), user.getPassword());
    }

    public void submit() {
        click(By.xpath("//button[@type='submit']"));
    }

    public String getMessage() {
        pause(7000);
        try {
            return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
        } catch (Exception e) {
            return null;
        }

    }

    public void submitOk() {
        if (isElementPresent(By.xpath("//button[text()='Ok']"))) {
            click(By.xpath("//button[text()='Ok']"));
        }

    }

    public String getErrorText() {
        pause(7000);
        return wd.findElement(By.cssSelector("div.error")).getText();

    }


    public boolean isLogged() {
        return isElementPresent(By.xpath("//*[@href='/logout?url=%2Fsearch']"));
    }

    public void logout() {
        click(By.xpath("//*[@href='/logout?url=%2Fsearch']"));
    }

    public boolean buttonSubmitOff() {
        return buttonSubmitDisabled(By.xpath("//*[@disabled]"));
    }

    //------------------registration---------------------------
    public void openRegistrationForm() {
        click(By.xpath("//*[@href='/registration?url=%2Fsearch']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        //  click(By.id("terms-of-use"));
        // click(By.xpath("//label[@for='terms-of-use']"));
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click()");
    }

    public void checkPolicyXY() {
        WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
        Rectangle rect = label.getRect();
        int w = rect.getWidth();

        // Dimension size = wd.manage().window().getSize();

        int xOffSet = -w / 2;
        Actions actions = new Actions(wd);
        actions.moveToElement(label, xOffSet, 0).click().release().perform();
    }
    public boolean xButtonOff() {
        return buttonSubmitDisabled(By.xpath("//*[@class='ng-dirty ng-touched ng-invalid']"));
    }
}
