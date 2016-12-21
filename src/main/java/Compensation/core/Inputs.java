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

    public By validator = By.xpath("//p[contains(@class,'validation-invalid ng-scope')]");
    public By toast = By.id("toast-container");
    public By toastM = By.id("//div[contains(@class,'toast-message')]");
    public By textBox = By.xpath("//input[@role='textbox']");
    public By combobox = By.xpath("//span[@role='combobox']");
    public String auto = "auto" + randomAtoZ();

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

    public String id() {
        /*First six digits are YYMMDD, znext 4 can be anything, next can be 0 or 1, next can be anything, last is a Luhn digit.
        Total 13 digits..*/
        Random rand = new Random();
        int randomNum = rand.nextInt((9 - 0) + 1) + 0;
        String val4 = "" + ((int) (Math.random() * 9000) + 1000);
        String id1 = "121212" + val4 + "0" + randomNum;
        String id = id1 + generateLuhnDigit(id1);
        return id;
    }

    private int generateLuhnDigit(String input) {
        int total = 0;
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            int multiple = (count % 2) + 1;
            count++;
            int temp = multiple * Integer.parseInt(String.valueOf(input.charAt(i)));
            temp = (int) Math.floor(temp / 10) + (temp % 10);
            total += temp;
        }
        total = (total * 9) % 10;
        return total;
    }

    public String serialNumber() {
        Random rd = new Random();
        String serialnumber = "";
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < 1; i++) {
            char c = chars[i];
            serialnumber = "" + rd.nextInt(100) + c + rd.nextInt(10);
        }
        return serialnumber;
    }


}
