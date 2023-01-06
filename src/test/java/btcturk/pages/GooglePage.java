package btcturk.pages;


import btcturk.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GooglePage {

    public GooglePage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(name = "q")
    public WebElement searchInput;

    @FindBy(xpath = "//*[@jscontroller='SC7lYd']")
    public List<WebElement> getContext;

    @FindBy(xpath = "//*[@data-header-feature='0']/div/a")
    public List<WebElement> getUrl;

    @FindBy(xpath = "//a[@aria-label='Page 2']")
    public WebElement secondPage;



}
