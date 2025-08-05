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

    public void openLoginRegistrationForm() {
        click(By.cssSelector("a[href='/login?url=%2Fsearch']"));
    }

    public void openRegistrationForm() {
        click(By.cssSelector("a[href='/registration?url=%2Fsearch']"));
    }

    public void fillLoginRegistrationForm(String email, String password) {
        type(By.id("email"), email);
        type(By.id("password"), password);
    }

    public void fillRegistrationForm(String name, String lastName, String email, String password) {
        type(By.id("name"), name);
        type(By.id("lastName"), lastName);
        type(By.id("email"), email);
        type(By.id("password"), password);
        submitXButton();
    }

    public void submitXButton() {//*[@id='terms-of-use']
        click(By.xpath("//label[contains(text(),'I agree to the')]['/label']"));
    }

    public void submitLogin() {
        click(By.xpath("//button[text()='Y’alla!']"));
    }

    public void submitOk() {
        click(By.xpath("//*[@class='positive-button ng-star-inserted']"));
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

    public boolean isXButtonClicked() {
        return checkXButton(By.xpath("//*[@class='ng-dirty ng-touched ng-valid']"), "ng-dirty ng-touched ng-valid");
    }
}
