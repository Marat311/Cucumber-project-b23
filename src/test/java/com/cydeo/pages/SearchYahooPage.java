package com.cydeo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://search.yahoo.com/
public class SearchYahooPage {

    @FindBy(id = "yschsp")
    public WebElement pInput;

    @FindBy(css = ".mag-glass")
    public WebElement icosycSpan;

    @FindBy(xpath = "//*[@id='yucs-apps_button']")
    public WebElement yucsappsbuttonLink;

    public SearchYahooPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}