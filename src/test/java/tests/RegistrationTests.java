package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess() {
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);
        System.out.println(System.currentTimeMillis());


        int z = (int) (System.currentTimeMillis() / 1000 % 3600);

        User user = new User()
                .setFirstName("Lisar")
                .setLastName("Snowww")
                .setEmail("snowww1" + z + "@gmail.com")
                .setPassword("Snow123456!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
    }


    @Test
    public void registrationEmptyName() {
        User user = new User()
                .setFirstName("")
                .setLastName("Snowww")
                .setEmail("snowww1@gmail.com")
                .setPassword("Snow123456!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Name is required");
        Assert.assertTrue(app.getHelperUser().buttonSubmitOff());
    }

    @Test
    public void registrationEmptyLastName() {
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("")
                .setEmail("snowww1@gmail.com")
                .setPassword("Snow123456!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Last name is required");
        Assert.assertTrue(app.getHelperUser().buttonSubmitOff());
    }

    @Test
    public void registrationWrongEmail() {
        User user = new User()
                .setFirstName("lisa")
                .setLastName("Snowww")
                .setEmail("snowww1gmail.com")
                .setPassword("Snow123456!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().buttonSubmitOff());

    }

    @Test
    public void registrationEmptyEmail() {
        User user = new User()
                .setFirstName("lisa")
                .setLastName("Snowww")
                .setEmail("")
                .setPassword("Snow123456!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        Assert.assertTrue(app.getHelperUser().buttonSubmitOff());
    }

    @Test
    public void registrationWrongPassword() {
        User user = new User()
                .setFirstName("Lisar")
                .setLastName("Snowww")
                .setEmail("snowww1@gmail.com")
                .setPassword("Snow");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        Assert.assertTrue(app.getHelperUser().buttonSubmitOff());
    }

    @Test
    public void registrationEmptyPassword() {
        User user = new User()
                .setFirstName("Lisar")
                .setLastName("Snowww")
                .setEmail("snowww1@gmail.com")
                .setPassword("");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
        Assert.assertTrue(app.getHelperUser().buttonSubmitOff());
    }

    @Test
    public void registrationPolicyButtonNotChecked() {
        int z = (int) (System.currentTimeMillis() / 1000 % 3600);

        User user = new User()
                .setFirstName("Lisar")
                .setLastName("Snowww")
                .setEmail("snowww1" + z + "@gmail.com")
                .setPassword("Snow123456!");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().buttonSubmitOff());
    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().submitOk();
        if (!app.getHelperUser().xButtonOff()) {
            app.getHelperUser().checkPolicyXY();

        }
    }
    // @Test(enabled = false) не будет запускаться
}
