package Compensation.core;

import org.openqa.selenium.By;

import java.io.File;
import java.util.*;

public class Inputs {
    public static Map<String, String> files = new HashMap<String, String>();

    static {
        files.put("stdev.jpg", System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "pictures" + File.separator + "stdev.jpg");
        files.put("stdev.png", System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "pictures" + File.separator + "stdev.png");
        files.put("stdev.jpeg", System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "pictures" + File.separator + "stdev.jpeg");
        files.put("stdev.gif", System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "pictures" + File.separator + "stdev.gif");
        files.put("stdev.tif", System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "pictures" + File.separator + "stdev.tif");
        files.put("empty.docx", System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "documents" + File.separator + "empty.docx");
        files.put("STDev.docx", System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "documents" + File.separator + "STDev.docx");
        files.put("STDev.pdf", System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "documents" + File.separator + "STDev.pdf");
        files.put("empty.txt", System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "documents" + File.separator + "empty.txt");
    }

    public String auto = "auto" + randomAtoZ();
    public String password = "Password";
    public By required = By.xpath("//span[contains(.,'This field is required.')]");

    public String randomAtoZ() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

    public String randomNumber() {
        Random random = new Random();
        String randomNumber = "" + random.nextInt(99999);
        return randomNumber;
    }

}
