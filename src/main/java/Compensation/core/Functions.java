package Compensation.core;

import org.openqa.selenium.By;

import static Compensation.core.TAGS.USER;
import static Compensation.core.TAGS.USER1;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Functions {
    UserInputs user = new UserInputs();
    Driver driver = new Driver();

    public void driver() {
        driver.Driver();
    }

    public void driverLogin() {
        driver();
        logIn();
    }

    public void logIn() {
        user.user();
        $(By.xpath("//a[contains(.,'Sign in')]")).waitUntil(appear, 5000).click();
        $("#id_username").setValue(user.getValue("user", "username"));
        $("#id_password").setValue(user.getValue("user", "password")).pressEnter();
    }

    public void logIn(int userN) {
        user.user();
        String username = "";
        String password = "";
        switch (userN) {
            case USER:
                username = user.getValue("user", "username");
                password = user.getValue("user", "password");
                break;
            case USER1:
                username = user.getValue("user1", "username");
                password = user.getValue("user1", "password");
                break;
        }
        $(By.xpath("//a[contains(.,'Sign in')]")).waitUntil(appear, 5000).click();
        $("#id_username").setValue(username);
        $("#id_password").setValue(password).pressEnter();
    }

    public void confirmUser(String username) {
        open(driver.url + "/admin");
        user.user();
        $("#id_username").setValue(user.getValue("user", "username"));
        $("#id_password").setValue(user.getValue("user", "password")).pressEnter();
        $(By.xpath("//a[contains(.,'User requests')]")).click();
        $(By.xpath("//a[contains(.,'" +username + "')]//../../td[1]//input[@type='checkbox']")).click();
        $(By.xpath("//select[@name='action']")).click();
        $(By.xpath("//option[contains(.,'Confirm user')]")).click();
        $(By.xpath("//button[contains(.,'Go')]")).click();

    }

    public void driverLogin(int userN) {
        driver();
        switch (userN) {
            case USER:
                logIn(USER);
                break;
            case USER1:
                logIn(USER1);
                break;
        }
    }
}
