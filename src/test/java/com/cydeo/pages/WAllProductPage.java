package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class WAllProductPage {

    public WAllProductPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//table[@class='ProductsTable']//tr/th")
    private List<WebElement>allHeaderRowCells;

    @FindBy(xpath = "//table[@class='ProductsTable']//tr[2]/td")
    private List<WebElement> firstRowCells;

    @FindBy(xpath = "//table[@class='ProductsTable']//tr[3]/td")
    private List<WebElement> secondRowCells;

    @FindBy(xpath = "//table[@class='ProductsTable']//tr[4]/td")
    private List<WebElement> thirdRowCells;

    //store all row as List of <String>
    public List<String>getAllHeaderText(){
        List<String> allTextList = new ArrayList<>();

        for (WebElement eachElement: allHeaderRowCells) {
            allTextList.add(eachElement.getText());
        }


        return allTextList;
    }


}
