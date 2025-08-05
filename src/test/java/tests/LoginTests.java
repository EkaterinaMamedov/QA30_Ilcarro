package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }


    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tmmmmmtttt@gmail.com", "Qwerty!12");
        app.getHelperUser().submitLogin();
        app.getHelperUser().submitOk();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tmmmmmtttt@gmail.com", "Qwerty!12");
        app.getHelperUser().submitLogin();
        app.getHelperUser().submitOk();
        Assert.assertTrue(app.getHelperUser().isLogged());

    }

    @Test
    public void loginWrongEmail() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tmmmmmttttgmail.com", "Qwerty!12");
        if (!app.getHelperUser().buttonLogin()) {
            app.getHelperUser().submitLogin();
            if (!app.getHelperUser().loginResult()) {
                System.out.println("wrong--> logged in");
            }
            app.getHelperUser().submitOk();
        }

        Assert.assertFalse(app.getHelperUser().isLogged());
        app.getHelperUser().clearForm();

    }

    @Test
    public void loginWrongPassword() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tmmmmmtttt@gmail.com", "Qwert");
        app.getHelperUser().submitLogin();
        app.getHelperUser().submitOk();
        Assert.assertFalse(app.getHelperUser().isLogged());
        app.getHelperUser().clearForm();
    }

    @Test
    public void loginUnregisteredUser() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tmmmmmttttttttt@gmail.com", "Qwerty!12");
        app.getHelperUser().submitLogin();
        app.getHelperUser().submitOk();
        Assert.assertFalse(app.getHelperUser().isLogged());
        app.getHelperUser().clearForm();
    }

}
