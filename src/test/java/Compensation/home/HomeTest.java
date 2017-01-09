package Compensation.home;

import Compensation.core.*;
import com.codeborne.selenide.junit.TextReport;
import org.junit.*;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;

import static Compensation.core.TAGS.USER1;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomeTest {

    @Rule
    public TestRule report = new TextReport().onFailedTest(true).onSucceededTest(true);
    Functions functions = new Functions();
    UserInputs userInputs = new UserInputs();
    Driver driver = new Driver();
    Inputs inputs = new Inputs();
    String auto = inputs.auto;
    String password = inputs.password;
    String email = auto + "@mailinator.com";
    String phoneNumber = "+123123123";

    @Before
    public void doBefore() {
        functions.driver();
    }

    @After
    public void doAfter() {
        close();
    }

    @Test
    public void singIn() {
        //empty fields
        $(By.xpath("//a[contains(.,'Sign in')]")).waitUntil(appear, 5000).click();
        $(By.xpath("//input[@value='Login']")).click();
        $$(inputs.required).shouldHaveSize(2);
        //invalid credentials
        userInputs.user();
        $("#id_username").setValue(userInputs.getValue("user", "username"));
        $("#id_password").setValue(auto).pressEnter();
        $(By.xpath("//li[contains(.,'Invalid username or password')]")).waitUntil(appear, 5000).shouldBe(exist);
        //valid credentials
        $("#id_username").setValue(userInputs.getValue("user", "username"));
        $("#id_password").setValue(userInputs.getValue("user", "password")).pressEnter();
        //Logout
        $(By.xpath("//a[contains(.,'Logout')]")).waitUntil(appear, 5000).click();
        $(By.xpath("//a[contains(.,'Sign in')]")).waitUntil(appear, 5000).shouldBe(exist);
    }

    @Test
    public void blockedIp() {
        $(By.xpath("//a[contains(.,'Sign in')]")).waitUntil(appear, 5000).click();
        while ($(By.xpath("//div[contains(.,'Sorry, you were blocked')]")).is(not(appear))) {
            $("#id_username").setValue(auto);
            $("#id_password").setValue(auto).pressEnter();
        }
        functions.unblockIp();
    }

    @Test
    public void forgotPassword() {
        $(By.xpath("//a[contains(.,'Sign in')]")).waitUntil(appear, 5000).click();
        $(By.xpath("//a[contains(.,'Forgot password?')]")).click();
        $("#reset_submit").click();
        $(inputs.required).shouldBe(exist);
        $("#id_email").setValue(auto).pressEnter();
        $(By.xpath("//span[contains(.,'The email account that you tried to reach does not exist')]")).shouldBe(appear);
        userInputs.user();
        $("#id_email").setValue(userInputs.getValue("user1", "email")).pressEnter();
        $(By.xpath("//div[contains(.,'Please check your email address for change your account password')]")).shouldBe(appear);
        functions.openEmail();
        $(By.xpath("//b[contains(.,'Reset account password')]")).waitUntil(appear, 5000).click();
        if ($(By.xpath("//a[contains(.,'- Show quoted text -')]")).is(appear)) {
            $(By.xpath("//a[contains(.,'- Show quoted text -')]")).click();
        }
        $(By.xpath("//a[contains(.,' here ')]")).waitUntil(appear, 5000).click();
        switchTo().window(1);
        $("#change_password_submit").waitUntil(appear, 10000).click();
        $$(inputs.required).shouldHaveSize(2);
        $("#id_password").setValue("1");
        $(By.xpath("//span[contains(.,'Please enter at least 2 characters.')]")).shouldBe(appear);
        $("#id_password").setValue(auto);
        $("#id_password2").setValue(auto + auto);
        $(By.xpath("//span[contains(.,'Please enter the same value again.')]")).shouldBe(appear);
        $("#id_password").setValue(password);
        $("#id_password2").setValue(password).pressEnter();
        $(By.xpath("//div[contains(.,'Your password has been successfully changed')]")).shouldBe(appear);
        functions.logIn(USER1);
    }

    @Test
    public void signUp() {
        $(By.xpath("//a[contains(.,'Sign Up')]")).waitUntil(appear, 5000).click();
        $(By.xpath("//input[contains(@value,'Sign-Up')]")).click();
        $$(inputs.required).shouldHaveSize(6);
        $("#id_name").setValue(auto);
        userInputs.user();
        $("#id_email").setValue(userInputs.getValue("user", "email"));
        $("#id_job_title").click();
        $(By.xpath("//span[contains(.,'This email is already taken')]")).waitUntil(appear, 5000).shouldBe(exist);
        $("#id_email").setValue(auto);
        $(By.xpath("//span[contains(.,'Please enter a valid email address.')]")).waitUntil(appear, 5000).shouldBe(exist);
        $("#id_email").clear();
        $("#id_email").setValue(email);
        $("#id_job_title").setValue(auto);
        $("#id_phone_number").setValue(auto);
        $(By.xpath("//span[contains(.,'Please enter a valid phone number')]")).waitUntil(appear, 5000).shouldBe(exist);
        $("#id_phone_number").setValue(phoneNumber);
        $("#id_company").setValue(auto);
        $("#id_comment").setValue(auto);
        $(By.xpath("//input[@value='Sign-Up']")).click();
        $(By.xpath("//div[contains(.,'Your request has been sent successfully')]")).waitUntil(appear, 5000).shouldBe(exist);
        //try to log in without confirmation of the account
        functions.logIn(auto, password);
        $(By.xpath("//li[contains(.,'Invalid username or password')]")).shouldBe(appear);
        System.out.println("username = " + auto);
        functions.confirmUser(auto);
        open(driver.url);
        //login after confirmation of account
        functions.logIn(auto, password);
        $(By.xpath("//a[contains(.,'Logout')]")).waitUntil(appear, 5000).shouldBe(exist);
    }

    @Test
    public void contactAs() {
        $(By.xpath("//a[contains(.,'Contact Us')]")).waitUntil(appear, 5000).click();
        $(By.xpath("//input[@value='SUBMIT']")).click();
        $$(inputs.required).shouldHaveSize(3);
        $("#id_name").setValue(auto);
        $("#id_email").setValue(auto);
        $(By.xpath("//span[contains(.,'Please enter a valid email address.')]")).waitUntil(appear, 5000).shouldBe(exist);
        $("#id_email").setValue(email);
        $("#id_job_title").setValue(auto);
        $("#id_phone_number").setValue(auto);
        $("#id_company").click();
        $(By.xpath("//span[contains(.,'Please enter a valid phone number')]")).waitUntil(appear, 5000).shouldBe(exist);
        $("#id_phone_number").setValue(phoneNumber);
        $("#id_company").setValue(auto);
        $("#id_message").setValue(auto);
        $(By.xpath("//input[@value='SUBMIT']")).click();
        $(By.xpath("//div[contains(.,'Your message has been successfully sent')]")).waitUntil(appear, 5000).shouldBe(exist);
        functions.openEmail();
        $(By.xpath("//b[contains(.,'" + auto + "')]/../../td[3]")).waitUntil(appear, 5000).click();
        $(By.xpath("//div[contains(.,'Contact request!')]")).waitUntil(appear, 5000).shouldHave(text(auto));
        $(By.xpath("//div[contains(.,'Contact request!')]")).waitUntil(appear, 5000).shouldHave(text(email));
        $(By.xpath("//div[contains(.,'Contact request!')]")).waitUntil(appear, 5000).shouldHave(text(phoneNumber));
    }

    @Test
    public void aboutAs() {
        $(By.xpath("//a[contains(.,'About Us')]")).waitUntil(appear, 5000).click();
        $(By.xpath("//h6[contains(.,'About Us')]")).waitUntil(appear, 5000).shouldBe(exist);
    }

    @Test
    public void viewADemo() {
        $(By.xpath("//a[contains(.,'View a demo')]")).waitUntil(appear, 5000).click();
        $(By.xpath("//h6[contains(.,'View Demo')]")).waitUntil(appear, 5000).shouldBe(exist);
    }
}
