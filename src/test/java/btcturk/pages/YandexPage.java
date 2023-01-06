package btcturk.pages;


import btcturk.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YandexPage {

    public YandexPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id = "text")
    public WebElement searchInput;

    @FindBy(xpath = "//button[@role='button']")
    public WebElement continueButton;

    @FindBy(css = "span.OrganicTitleContentSpan")
    public List<WebElement> title;

    @FindBy(css = "span.OrganicTextContentSpan")
    public List<WebElement> description;

    @FindBy(xpath = "//div[contains(@class,'Organic-Path')]/a")
    public List<WebElement> getUrl;



}
