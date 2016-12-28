package Compensation.home;

import Compensation.core.*;
import com.codeborne.selenide.junit.TextReport;
import org.junit.*;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class HomeTest {
    @Rule
    public TestRule report = new TextReport().onFailedTest(true).onSucceededTest(true);
    UserInputs userInputs = new UserInputs();
    Functions functions = new Functions();

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
        userInputs.user();
        $(By.xpath("//a[contains(.,'Sign in')]")).waitUntil(appear, 5000).click();
        $(By.xpath("//input[@value='Login']")).click();
        $$(By.xpath("//span[contains(.,'This field is required.')]")).shouldHaveSize(2);
        $("#id_username").setValue(userInputs.getValue("user", "username"));
        $("#id_password").setValue(userInputs.getValue("user", "password")).pressEnter();
        $(By.xpath("//a[contains(.,'Logout')]")).waitUntil(appear, 5000).shouldBe(exist);
    }

    @Test
    public void aa() {
        functions.confirmUser("autottt");
    }
}
