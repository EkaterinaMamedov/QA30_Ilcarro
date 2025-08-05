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
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tmmmmmtttt@gmail.com", "Qwerty!12");
        app.getHelperUser().submitLogin();
//        if(app.getHelperUser().loginResult()) {
        app.getHelperUser().submitOk();
//        }else {
//            System.out.println("wrong-->not logged in");
//        }
        Assert.assertTrue(app.getHelperUser().isLogged());
        // Assert.assertTrue(app.getHelperUser().containsText(By.xpath("//h1[@class='message']"),"Logged in success"));
    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm("Bart", "Simpson", "r2d2@gmail.com", "Qwerty!12");
        // app.getHelperUser().submitLogin();
//        if (app.getHelperUser().loginResult()) {
        // app.getHelperUser().submitOk();
//        } else {
//            System.out.println("wrong-->not logged in");
//        }

        Assert.assertTrue(app.getHelperUser().isXButtonClicked());
        // Assert.assertTrue(app.getHelperUser().isLogged());

    }

    @Test
    public void loginWrongEmail() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tmmmmmttttgmail.com", "Qwerty!12");
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
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tmmmmmtttt@gmail.com", "Qwert");
        app.getHelperUser().submitLogin();
//        if (!app.getHelperUser().loginResult()) {
//            System.out.println("wrong--> logged in");
//        }
        app.getHelperUser().submitOk();
        Assert.assertFalse(app.getHelperUser().isLogged());
        app.getHelperUser().clearForm();
    }

    @Test
    public void loginUnregisteredUser() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("tmmmmmttttttttt@gmail.com", "Qwerty!12");
        app.getHelperUser().submitLogin();
//        if (!app.getHelperUser().loginResult()) {
//            System.out.println("wrong--> logged in");
//        }
        app.getHelperUser().submitOk();
        Assert.assertFalse(app.getHelperUser().isLogged());
        app.getHelperUser().clearForm();
    }

}
