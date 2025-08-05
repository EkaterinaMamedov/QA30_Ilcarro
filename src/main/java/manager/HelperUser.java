package manager;

import org.openqa.selenium.By;
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

    public void submitLogin() {
        click(By.xpath("//button[@type='submit']"));
    }

    public void submitOk() {
        click(By.xpath("//button[text()='Ok']"));
    }

    public boolean loginResult() {
        return loginSuccessOrFailed(By.xpath("//h1[@ _ngcontent-vfo-c36]"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()='Delete account']"));
    }

    public void logout() {
        click(By.xpath("//*[@href='/logout?url=%2Fsearch']"));
    }

    public boolean buttonLogin() {
        return buttonLoginDisabled(By.xpath("//*[@disabled]"));
    }

}
