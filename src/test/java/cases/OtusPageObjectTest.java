package cases;

import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Test;
import pages.MainPage;

import java.util.List;

public class OtusPageObjectTest extends OtusBaseTest {
    private static String myBirthday = "15.09.1988";
    private static String myFirstName = "Имя";
    private static String myLastName = "Фамилия";
    private static String myFirstNameLatin = "Name";
    private static String myLastNameLatin = "Surname";
    private static String myBlogName = "test";
    private static String myFacebook = "test.facebook";
    private static String mySkype = "test.skype";

    @Epic("Page object homework")
    @Feature(value = "Test feature")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Open and work in private cabinet otus.ru")
    @Story (value = "General story")
    @Test
    public void otusPageObjectTest() {

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .auth()
                .login(myLogin, myPass)
                .enter()
                .openPP()
                .names(myFirstName, myLastName, myFirstNameLatin, myLastNameLatin, myBlogName)
                .birthdate(myBirthday)
                .addContacts(myFacebook, mySkype)
                .save();

        driver.manage().deleteAllCookies();
        logger.info("restart browser");

        mainPage = new MainPage(driver);
        List<String> personalInfo;
        personalInfo = mainPage
                .open()
                .auth()
                .login(myLogin, myPass)
                .enter()
                .openPP()
                .getPersonalInfo();

        Assert.assertTrue("First name field is empty", personalInfo.contains(myFirstName));
        Assert.assertTrue("Last name field is empty", personalInfo.contains(myLastName));
        Assert.assertTrue("Latin first name field is empty", personalInfo.contains(myFirstNameLatin));
        Assert.assertTrue("Latin last name field is empty", personalInfo.contains(myLastNameLatin));
        Assert.assertTrue("Blog name field is empty", personalInfo.contains(myBlogName));
        Assert.assertTrue("Birth date field is empty", personalInfo.contains(myBirthday));
        Assert.assertTrue("First contact field is empty", personalInfo.contains(myFacebook));
        Assert.assertTrue("Second contact field is empty", personalInfo.contains(mySkype));
    }

}
