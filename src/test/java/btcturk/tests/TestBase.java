package btcturk.tests;

import btcturk.utilities.ConfigurationReader;
import btcturk.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {

    //Testten önce ve sonra yapılması gereken metodlar

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = Driver.get();
        System.out.println("Driver ayağa kaldırıldı");
        driver.manage().window().maximize();
        System.out.println("Ekran maximize yapıldı.");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().deleteAllCookies();
        System.out.println("Cookiler temizlendi.");
        String url = ConfigurationReader.get("google_url");
        driver.get(url);
        System.out.println("Google url i açıldı.");



    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }

}
