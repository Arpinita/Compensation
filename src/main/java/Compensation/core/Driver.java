package Compensation.core;

import com.codeborne.selenide.Configuration;

import java.io.File;

import static Compensation.core.TAGS.*;
import static com.codeborne.selenide.Selenide.open;

public class Driver {
    int drive = CHROME;
    String url = "http://compensation.codebnb.me";


    public void Driver() {
        String webdriver = "";
        String path = System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "webdrivers" + File.separator;
        Boolean arch32 = System.getProperty("os.arch").contains("32");
        Boolean arch64 = System.getProperty("os.arch").contains("64");
        Boolean Windows = System.getProperty("os.name").contains("Windows");
        Boolean Linux = System.getProperty("os.name").contains("Linux");
        if (Windows == true && drive == CHROME) {
            webdriver = path + "chromedriver.exe";
        } else if (Linux == true && arch64 == true && drive == CHROME) {
            webdriver = path + "chromedriver64linux";
        } else if (Linux == true && arch32 == true && drive == CHROME) {
            webdriver = path + "chromedriver32linux";
        } else if (Windows && arch64 == true && drive == OPERA) {
            webdriver = path + "operadriver64.exe";
        } else if (Windows && arch32 == true && drive == OPERA) {
            webdriver = path + "operadriver32.exe";
        } else if (Linux == true && arch64 == true && drive == OPERA) {
            webdriver = path + "operadriver64";
        } else if (Linux && arch32 == true && drive == OPERA) {
            webdriver = path + "operadriver32";
        } else if (Windows && drive == EDGE) {
            webdriver = path + "edge.exe";
        } else if (Linux && arch64 == true && drive == FIREFOX) {
            webdriver = path + "geckodriver64";
        } else if (Linux && arch32 == true && drive == FIREFOX) {
            webdriver = path + "geckodriver32";
        } else if (Windows && drive == IE) {
            webdriver = path + "iedriver.exe";
        }

        switch (drive) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", webdriver);
                Configuration.browser = "chrome";
                open(url);
                break;
            case IE:
                System.setProperty("webdriver.chrome.driver", webdriver);
                Configuration.browser = "chrome";
                open(url);
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", webdriver);
                Configuration.browser = "firefox";
                open(url);
                break;
            case OPERA:
                System.setProperty("webdriver.chrome.driver", webdriver);
                Configuration.browser = "chrome";
                open(url);
                break;
            case EDGE:
                System.setProperty("webdriver.chrome.driver", webdriver);
                Configuration.browser = "chrome";
                open(url);
                break;
        }
    }
}

