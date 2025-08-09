package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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
    public void loginSuccess1() {
        User user = new User().setEmail("tmmmmmtttt@gmail.com").setPassword("Qwerty!12");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tmmmmmtttt@gmail.com", "Qwerty!12");
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tmmmmmtttt@gmail.com", "Qwerty!12");
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

    }

    @Test
    public void loginWrongEmailWithoutAt() {
        User user = new User().setEmail("tmmmmmttttgmail.com").setPassword("Qwerty!12");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        Assert.assertEquals(app.getHelperUser().messageFormInputEmailContainer(), "It\'snot look like email");
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void loginWrongEmailWithoutDot() {
        User user = new User().setEmail("tmmmmmtttt@gmailcom").setPassword("Qwerty!12");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void loginEmptyEmail() {
        User user = new User().setEmail("").setPassword("Qwerty!12");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        Assert.assertEquals(app.getHelperUser().messageFormInputEmailContainer(), "Email is required");
        Assert.assertFalse(app.getHelperUser().isLogged());
    }


    @Test
    public void loginWrongPassword() {
        User user = new User().setEmail("tmmmmmtttt@gmail.com").setPassword("Qwert");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void loginEmptyPassword() {
        User user = new User().setEmail("tmmmmmtttt@gmail.com").setPassword("");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().click(By.id("email"));
        Assert.assertEquals(app.getHelperUser().messageFormInputPasswordContainer(), "Password is required");
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void loginUnregisteredUser() {
        User user = new User().setEmail("tmmmmmttttttttt@gmail.com").setPassword("Qwerty!12");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @AfterMethod
    public void afterCondition() {
        if (app.getHelperUser().getMessage() != null) {
            app.getHelperUser().submitOk();
        }
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().clearForm();
        }

    }
}
