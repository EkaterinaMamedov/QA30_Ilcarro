package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

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

        click(By.xpath("//button[text()='Ok']"));

    }

    public String messageFormInputEmailContainer() {
        pause(7000);
        return wd.findElement(By.cssSelector("form > div:nth-child(1) > div")).getText();

    }


    public String messageFormInputPasswordContainer() {
        pause(7000);
        return wd.findElement(By.cssSelector("form > div:nth-child(2) > div")).getText();

    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//*[@href='/logout?url=%2Fsearch']"));
    }

    public void logout() {
        click(By.xpath("//*[@href='/logout?url=%2Fsearch']"));
    }

    public boolean buttonLogin() {
        return buttonLoginDisabled(By.xpath("//*[@disabled]"));
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
}
