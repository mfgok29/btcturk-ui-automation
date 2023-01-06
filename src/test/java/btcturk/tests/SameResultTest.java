package btcturk.tests;

import btcturk.pages.GooglePage;
import btcturk.pages.YandexPage;
import btcturk.utilities.ConfigurationReader;
import btcturk.utilities.Driver;
import btcturk.utilities.ExcelUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SameResultTest extends TestBase{

    GooglePage googleHomePage = new GooglePage();
    YandexPage yandexPage = new YandexPage();

    String filePath = "src/test/resources/files/result.xls";
    ExcelUtils excelUtils = new ExcelUtils(filePath);




    @Test
    @Parameters("keyword")
    public void if_ile_same_bulma(@Optional String keyword) throws IOException {
        if (keyword==null){
            keyword="araba";
        }
        //Parametreyi xml dosyasından okuduğu için buradan çalıştırıldığında keyword=null oluyordu, buradan da hata almadan çalıştırmak için keyword e default bir değer setledik.

        Actions actions = new Actions(Driver.get());
        googleHomePage.searchInput.sendKeys(keyword + Keys.ENTER);
        System.out.println("Google arama motorunda da arama kutucuğuna "+ keyword+ " yazıldı ve enter a tıklandı.");

        int size = googleHomePage.getContext.size();
        System.out.println("İlk sayfada kriterlerimize göre kaç sonuç olduğunu kaydedildi.");

        excelUtils.createSheet("google_result");
        excelUtils.setData("Title", 0, 0);
        excelUtils.setData("Description", 0, 1);
        excelUtils.setData("Url", 0, 2);
        System.out.println("Google sonuçları için excel e sheet açıldı.");

        for (int i = 0; i < size; i++) {

            String result = googleHomePage.getContext.get(i).getText();
            Object[] arrayResult = result.lines().toArray();
            String title = arrayResult[0].toString();
            String description = arrayResult[2].toString();
            String url = googleHomePage.getUrl.get(i).getAttribute("href");
            excelUtils.setData(title, i + 1, 0);
            excelUtils.setData(description, i + 1, 1);
            excelUtils.setData(url, i + 1, 2);


        }
        System.out.println("Google sonuçları excel e yazıldı.");



        if (size < 10) {
            actions.moveToElement(googleHomePage.secondPage);
            googleHomePage.secondPage.click();
            System.out.println("İlk sayfada 10 dan az değer varsa 2. sayfaya gidildi.");
            int lastSize = 10 - size;
            for (int i = 0; i < lastSize+1; i++) {

                String result = googleHomePage.getContext.get(i).getText();
                Object[] arrayResult = result.lines().toArray();
                String title = arrayResult[0].toString();
                String description = arrayResult[2].toString();
                String url = googleHomePage.getUrl.get(i).getAttribute("href");
                excelUtils.setData(title, size+i, 0);
                excelUtils.setData(description, size+i, 1);
                excelUtils.setData(url, size+i , 2);


            }
            System.out.println("10 sonuç olması için kalan değerler 2. sayfadan çekildi.");
            System.out.println("2. sayfadaki sonuçlar excel e yazılır.");
        }
        String yandex_url = ConfigurationReader.get("yandex_url");
        Driver.get().switchTo().newWindow(WindowType.TAB).get(yandex_url);
        System.out.println("Yeni sekme açılır ve yeni sekmede yandex urline gidilir.");

        yandexPage.searchInput.sendKeys(keyword);
        yandexPage.continueButton.click();
        System.out.println("Yandex arama motoruna "+keyword+" yazılır ve arama butonuna basılır.");

        excelUtils.createSheet("yandex_result");
        excelUtils.setData("Title", 0, 0);
        excelUtils.setData("Description", 0, 1);
        excelUtils.setData("Url", 0, 2);
        System.out.println("Yandex sonuçları için excel içerisine sheet açıldı");
        for (int i = 0; i < 10; i++) {

            String title = yandexPage.title.get(i).getText();
            String description = yandexPage.description.get(i).getText();
            String url = yandexPage.getUrl.get(i).getAttribute("href");
            excelUtils.setData(title, i + 1, 0);
            excelUtils.setData(description, i + 1, 1);
            excelUtils.setData(url, i + 1, 2);

        }
        System.out.println("Yandex sonuçları excele yazıldı.");

        List<String> sameTitle = new ArrayList<>();
        List<String> sameDescription = new ArrayList<>();
        List<String> sameUrl = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                for (int k = 0; k <= 2; k++) {
                    String google_result = excelUtils.getData(filePath, "google_result", i, k);
                    String yandex_result = excelUtils.getData(filePath, "yandex_result", j, k);
                    if (google_result.equals(yandex_result)) {
                        if (k == 0) {
                            sameTitle.add(google_result);
                        } else if (k == 1) {
                            sameDescription.add(google_result);
                        } else {
                            sameUrl.add(google_result);
                        }
                    }


                }

            }
        }
        System.out.println("Title, Description ve Url için ayrı ayrı kontroller yapıldı.");

        excelUtils.createSheet("same_title");
        for (int i = 0; i < sameTitle.size(); i++) {
            excelUtils.setData(sameTitle.get(i), i, 0);
        }
        System.out.println("Title ı aynı olanlar için excel de sheet açıldı ve sonuçlar yazıldı.");

        excelUtils.createSheet("same_description");
        for (int i = 0; i < sameDescription.size(); i++) {
            excelUtils.setData(sameDescription.get(i), i, 0);
        }
        System.out.println("Description ı aynı olanlar için excel de sheet açıldı ve sonuçlar yazıldı.");

        excelUtils.createSheet("same_url");
        for (int i = 0; i < sameUrl.size(); i++) {
            excelUtils.setData(sameUrl.get(i), i, 0);
        }
        System.out.println("Url i aynı olanlar için excel de sheet açıldı ve sonuçlar yazıldı.");


    }



}






