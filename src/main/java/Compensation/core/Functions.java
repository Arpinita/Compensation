package Compensation.core;

import org.openqa.selenium.By;

import java.io.File;

import static Compensation.core.Inputs.files;
import static Compensation.core.TAGS.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
public class Functions {
    UserInputs user = new UserInputs();
    By firstNameSearch = By.id("search_first_name");
    By lastNameSearch = By.id("search_last_name");
    By companyNameSearch = By.id("search_company_name");
    By idNumberSearch = By.id("id_number_search");
    By policyNumberSearch = By.id("search_policy_number");
    By siteNameSearch = By.id("search_company_name");
    By firstNameTable = By.xpath("//tr[1]/td[1]");
    By lastNameTable = By.xpath("//tr[1]/td[2]");
    By idNumberTable = By.xpath("//tr[1]/td[5]");
    By companyNameTable = By.xpath("//tr[1]/td[1]");
    By policyNumberTable = By.xpath("//tr[1]/td[2]");
    By siteNameTable = By.xpath("//tr[1]/td[2]");
    By search = By.id("Search");

    public void logIn() {
        user.user();
        $(By.xpath("//a[contains(.,'Sign in')]")).waitUntil(appear, 5000 ).click();
        $("#id_username").setValue(user.getValue("user", "username"));
        $("#id_password").setValue(user.getValue("user", "password")).pressEnter();
    }

    public void slider(int status) {
        By slider = By.xpath("//div[@class='slider round']");
        By enable = By.xpath("//label[contains(.,'Enable editing')]");
        By disable = By.xpath("//label[contains(.,'Disable editing')]");
        switch (status) {
            case ON:
                if ($(enable).waitUntil(appear, 5000).is(exist)) {
                    $(slider).click();
                }
                break;
            case OFF: {
                if ($(disable).waitUntil(appear, 5000).is(appear)) {
                    $(slider).click();
                }
                break;
            }
        }
    }

    //upload
    public static class Upload {
        By validPDF = By.xpath("//p[contains(.,'pdf')]");
        By validDocx = By.xpath("//p[contains(.,'docx')]");
        By validator = By.xpath("//p[contains(@class,'validation-invalid ng-scope')]");
        By validImage = By.xpath("//img[contains(@src,'data:image/')]");
        By invalidImage = By.xpath("//img[contains(@src,'img/placeholder.png')]");
        File jpg = new File(files.get("stdev.jpg"));
        File jpeg = new File(files.get("stdev.jpeg"));
        File png = new File(files.get("stdev.png"));
        File gif = new File(files.get("stdev.gif"));
        File tif = new File(files.get("stdev.tif"));
        File pdf = new File(files.get("STDev.pdf"));
        File docx = new File(files.get("STDev.docx"));
        File txt = new File(files.get("empty.txt"));

        public void uploadChecker(int typeInt, int status) {
            String type;
            if (typeInt == IMAGE) {
                if (status == POSITIVE) {
                    $(validImage).shouldBe(exist);
                    $(validator).shouldNotBe(exist);
                } else if (status == NEGATIVE) {
                    $(invalidImage).shouldBe(exist);
                    $(validator).shouldNotBe(hidden);
                }
            }
            if (typeInt == DOCDOCX) {
                if (status == POSITIVE) {
                    $(validDocx).shouldBe(exist);
                    $(validator).shouldNotBe(exist);
                } else if (status == NEGATIVE) {
                    $(validator).shouldNotBe(hidden);
                }
            }
            if (typeInt == DOCPDF) {
                if (status == POSITIVE) {
                    $(validPDF).shouldBe(exist);
                    $(validator).shouldNotBe(exist);
                } else if (status == NEGATIVE) {
                    $(validator).shouldNotBe(hidden);
                }
            }
            if (typeInt == DOCXTERMS) {
                if (status == POSITIVE) {
                    $(validPDF).shouldBe(exist);
                    $(validator).shouldNotBe(exist);
                } else if (status == NEGATIVE) {
                    $(validator).shouldNotBe(hidden);
                }
            }
        }

        public void upload(int typeInt, int file) {
            String type;
            if (typeInt == IMAGE) {
                type = "#Upload_Logo";
            } else if (typeInt == DOCDOCX) {
                type = "#Choose_File";
            } else if (typeInt == DOCPDF) {
                type = "#Choose_File";
            } else if (typeInt == DOCXTERMS) {
                type = "#Choose_File_terms";
            } else {
                type = "";
            }

            switch (file) {
                case JPG:
                    $(type).uploadFile(jpg);
                    break;
                case JPEG:
                    $(type).uploadFile(jpeg);
                    break;
                case PNG:
                    $(type).uploadFile(png);
                    break;
                case GIF:
                    $(type).uploadFile(gif);
                    break;
                case TIF:
                    $(type).uploadFile(tif);
                    break;
                case DOCX:
                    $(type).uploadFile(docx);
                    break;
                case PDF:
                    $(type).uploadFile(pdf);
                    break;
                case TXT:
                    $(type).uploadFile(txt);
                    break;
            }
        }

        public void uploadAllLogos() {
            upload(IMAGE, JPG);
            uploadChecker(IMAGE, POSITIVE);
            upload(IMAGE, JPEG);
            uploadChecker(IMAGE, POSITIVE);
            upload(IMAGE, PNG);
            uploadChecker(IMAGE, POSITIVE);
            upload(IMAGE, GIF);
            uploadChecker(IMAGE, POSITIVE);
            upload(IMAGE, TIF);
            uploadChecker(IMAGE, NEGATIVE);
            upload(IMAGE, DOCX);
            uploadChecker(IMAGE, NEGATIVE);
            upload(IMAGE, PDF);
            uploadChecker(IMAGE, NEGATIVE);
            upload(IMAGE, TXT);
            uploadChecker(IMAGE, NEGATIVE);
        }

        public void uploadAllPDFs() {
            upload(DOCPDF, PDF);
            uploadChecker(DOCPDF, POSITIVE);
            upload(DOCPDF, JPG);
            uploadChecker(DOCPDF, NEGATIVE);
            upload(DOCPDF, JPEG);
            uploadChecker(DOCPDF, NEGATIVE);
            upload(DOCPDF, PNG);
            uploadChecker(DOCPDF, NEGATIVE);
            upload(DOCPDF, GIF);
            uploadChecker(DOCPDF, NEGATIVE);
            upload(DOCPDF, TIF);
            uploadChecker(DOCPDF, NEGATIVE);
            upload(DOCPDF, DOCDOCX);
            uploadChecker(DOCPDF, NEGATIVE);
            upload(DOCPDF, TXT);
            uploadChecker(DOCPDF, NEGATIVE);
        }

        public void uploadAllDocxs() {
            upload(DOCDOCX, DOCX);
            uploadChecker(DOCDOCX, POSITIVE);
            upload(DOCDOCX, JPG);
            uploadChecker(DOCDOCX, NEGATIVE);
            upload(TAGS.DOCDOCX, JPEG);
            uploadChecker(DOCDOCX, NEGATIVE);
            upload(TAGS.DOCDOCX, PNG);
            uploadChecker(DOCDOCX, NEGATIVE);
            upload(TAGS.DOCDOCX, GIF);
            uploadChecker(DOCDOCX, NEGATIVE);
            upload(TAGS.DOCDOCX, TIF);
            uploadChecker(DOCDOCX, NEGATIVE);
            upload(TAGS.DOCDOCX, PDF);
            uploadChecker(DOCDOCX, NEGATIVE);
            upload(TAGS.DOCDOCX, TXT);
            uploadChecker(DOCDOCX, NEGATIVE);
        }
    }
}
