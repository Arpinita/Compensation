package Compensation.core;

import org.openqa.selenium.By;

import static Compensation.core.TAGS.USER;
import static Compensation.core.TAGS.USER1;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Functions {
    UserInputs userInputs = new UserInputs();
    Inputs inputs = new Inputs();
    Driver driver = new Driver();

    public void driver() {
        driver.Driver();
    }

    public void driverLogin() {
        driver();
        logIn();
    }

    public void logIn() {
        userInputs.user();
        if ($(By.xpath("//a[contains(.,'Logout')]")).exists())
            $(By.xpath("//a[contains(.,'Logout')]")).click();
        $(By.xpath("//a[contains(.,'Sign in')]")).waitUntil(appear, 5000).click();
        $("#id_username").setValue(userInputs.getValue("user", "username"));
        $("#id_password").setValue(userInputs.getValue("user", "password")).pressEnter();
    }

    public void logIn(String username, String password) {
        if ($(By.xpath("//a[contains(.,'Logout')]")).exists())
            $(By.xpath("//a[contains(.,'Logout')]")).click();
        $(By.xpath("//a[contains(.,'Sign in')]")).waitUntil(appear, 5000).click();
        $("#id_username").setValue(username);
        $("#id_password").setValue(password).pressEnter();
    }

    public void logIn(int userN) {
        if ($(By.xpath("//a[contains(.,'Logout')]")).exists())
            $(By.xpath("//a[contains(.,'Logout')]")).click();
        userInputs.user();
        String username = "";
        String password = "";
        switch (userN) {
            case USER:
                username = userInputs.getValue("user", "username");
                password = userInputs.getValue("user", "password");
                break;
            case USER1:
                username = userInputs.getValue("user1", "username");
                password = userInputs.getValue("user1", "password");
                break;
        }
        $(By.xpath("//a[contains(.,'Sign in')]")).waitUntil(appear, 5000).click();
        $("#id_username").setValue(username);
        $("#id_password").setValue(password).pressEnter();
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

    public void confirmUser(String username) {
        open(driver.url + "/admin");
        userInputs.user();
        $("#id_username").setValue(userInputs.getValue("user", "username"));
        $("#id_password").setValue(userInputs.getValue("user", "password")).pressEnter();
        $(By.xpath("//a[contains(.,'User requests')]")).click();
        $(By.xpath("//a[contains(.,'" + username + "')]//../../td[1]//input[@type='checkbox']")).click();
        $(By.xpath("//select[@name='action']")).click();
        $(By.xpath("//option[contains(.,'Confirm user')]")).click();
        $(By.xpath("//button[contains(.,'Go')]")).click();
        $("#id_username").setValue(username);
        $("#id_password").setValue(inputs.password);
        System.out.println("password = " + inputs.password);
        $(By.xpath("//input[@type='submit']")).click();
    }

    public void unblockIp() {
        open("http://www.whatsmyip.org/");
        String ip = $(By.xpath("//span[@id='ip']")).getText();
        System.out.println(ip);
        open(driver.url + "/admin/core/blockedip/");
        userInputs.user();
        $("#id_username").setValue(userInputs.getValue("user", "username"));
        $("#id_password").setValue(userInputs.getValue("user", "password")).pressEnter();
        $(By.xpath("//a[contains(.,'" + ip + "')]/../..//input[@class='action-select']")).waitUntil(appear, 5000).click();
        $(By.xpath("//select[@name='action']")).click();
        $(By.xpath("//option[contains(.,'Delete selected blocked ips')]")).click();
        $(By.xpath("//button[contains(.,'Go')]")).click();
        $(By.xpath("//input[contains(@type,'submit')]")).click();
    }

    public void openEmail() {
        userInputs.user();
        open("https://www.gmail.com");
        $("#Email").setValue(userInputs.getValue("user99", "email")).pressEnter();
        $("#Passwd").setValue(userInputs.getValue("user99", "password")).pressEnter();
        open("http://mail.google.com/mail/h/.");
    }

    public void openEmail(String user) {
        userInputs.user();
        open("https://www.gmail.com");
        $("#Email").setValue(userInputs.getValue(user, "email")).pressEnter();
        $("#Passwd").setValue(userInputs.getValue(user, "password")).pressEnter();
        open("http://mail.google.com/mail/h/.");
    }
}
