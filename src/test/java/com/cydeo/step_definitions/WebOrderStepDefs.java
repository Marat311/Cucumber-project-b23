package com.cydeo.step_definitions;

import com.cydeo.pages.WCommonArea;
import com.cydeo.pages.WOrderPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class WebOrderStepDefs {

    WOrderPage orderPage = new WOrderPage();

    @When("we select {string} tab from sidebar")
    public void weSelectTabFromSidebar(String tabName) {
        System.out.println("tabName = " + tabName);
        WCommonArea commonArea = new WCommonArea();
        switch (tabName) {
            case "View all products":
                commonArea.viewAllProductTab.click();
                break;
            case "View all orders":
                commonArea.viewAllOrderTab.click();
                break;
            case "Order":
                commonArea.orderTab.click();
                break;
        }


    }

    @Then("we should see below options in Product dropdown list")
    public void weShouldSeeBelowOptionsInProductDropdownList(List<String> expectedOptions) {

        System.out.println("expectedOptions = " + expectedOptions);
        WOrderPage orderPage = new WOrderPage();

        List<String> actualOptions = orderPage.getAllProductOptionFromList();

        // assert these two list are equal
        // import static org.junit.Assert.assertEquals;
        assertEquals(expectedOptions, actualOptions) ;
    }


    @Then("side bar tabs should be as below")
    public void sideBarTabsShouldBeAsBelow(List<String> expectedbarTabs) {

        System.out.println("expectedbarTabs = " + expectedbarTabs);

        List <WebElement> actualBarTabs = Driver.getDriver().findElements(By.xpath("//ul[@class='menu']/li"));

        for(int i =0; i<actualBarTabs.size(); i++){
            assertEquals(expectedbarTabs.get(i), actualBarTabs.get(i).getText());
        }
      /* int num =0;
      for (WebElement barTab : actualBarTabs) {
            assertEquals(expectedbarTabs.get(num), barTab.getText());
            num++;
        }
*/



    }


    @Then("we should see table with below content")
    public void weShouldSeeTableWithBelowContent(List<Map<String, Object>>tableRow) {

        System.out.println("tableRow = " + tableRow);
     List<WebElement> tables = Driver.getDriver().findElements(By.xpath("//table[@class='ProductsTable']//tr/td"));

        List<String> tablesName = new ArrayList<>();
     for (WebElement element : tables) {
            tablesName.add(element.getText());
         System.out.println("element.getText() = " + element.getText());
     }
     int n = 0;
        for (Map<String, Object> objectMap : tableRow) {
            for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                assertTrue(entry.getValue().equals(tablesName.get(n)));
                n++;
            }


        }

    }

    @Then("we should see three section as below")
    public void weShouldSeeThreeSectionAsBelow(List<String> expectedSections) {
        System.out.println("expectedSections = " + expectedSections);
        List<WebElement> visibleSections = Driver.getDriver().findElements(By.xpath("//table[@id='ctl00_MainContent_fmwOrder']//h3"));
        for (int i = 0; i<visibleSections.size(); i++) {
            assertEquals(visibleSections.get(i).getText(), expectedSections.get(i));
        }

    }

    @And("select each product from dropdown should change the unit price accordingly")
    public void selectEachProductFromDropdownShouldChangeTheUnitPriceAccordingly(Map<String, Object>dropdownProduct) {

        System.out.println("dropdownProduct = " + dropdownProduct);
        WOrderPage orderPage = new WOrderPage();
        Map<String, Object> allpriceAndProduct = orderPage.getUnitPriceAndProducts();
        assertEquals(dropdownProduct, allpriceAndProduct);

    }

    @Then("selecting blow product and quantity should show correct total and discount")
    public void selectingBlowProductAndQuantityShouldShowCorrectTotalAndDiscount(List<Map<String, String>> allProductsInfo) {

        for (Map<String, String> eachProduct : allProductsInfo) {
            orderPage.getOneProduct(eachProduct.get("Product"));
            orderPage.quantityBox.sendKeys(eachProduct.get("Quantity"));
            orderPage.calculateButton.click();
            String discount = orderPage.discountBox.getAttribute("value");
            System.out.println("orderPage.discountBox.getText() = " + discount);
            assertEquals(discount, eachProduct.get("Discount"));
            String total = orderPage.totalBox.getAttribute("value");
            System.out.println("orderPage.totalBox.getText() = " + total);
            assertEquals(total, eachProduct.get("Total"));
            orderPage.resetBtn.click();
        }
    }

    @And("submit the form")
    public void submitTheForm() {
    orderPage.processButton.click();
    }

    @Then("below error messages should be visible on screen")
    public void belowErrorMessagesShouldBeVisibleOnScreen(List<String> expectedMessage) {
        for (String message : expectedMessage) {


           WebElement actualMessage = Driver.getDriver().findElement(By.xpath("//span[normalize-space(.)='"+message+"']"));

                assertEquals(actualMessage.getText(), message);
                System.out.println("element.getText() = " + actualMessage.getText());


        }
    }

}
/*
public class WebOrderStepDef {

    @When("we select {string} tab from sidebar")
    public void we_select_tab_from_sidebar(String tabName) {
        System.out.println("tabName = " + tabName);
        WCommonArea commonArea = new WCommonArea();
        commonArea.orderTab.click();
    }
    @Then("we should see below options in Product dropdown list")
    public void we_should_see_below_options_in_product_dropdown_list(List<String> expectedOptions) {

        System.out.println("products = " + expectedOptions);
        WOrderPage orderPage = new WOrderPage();

       /* List<String> actualOptions = selectObj.getOptions().stream()
                .map(eachOption-> eachOption.getText()).collect(Collectors.toList());
*/
     /*   another longer way
     //getOptions method from select class is used
        //to return all dropdowns options as List of webelement
        List<WebElement> allProducts = selectObj.getOptions();
        //this is the list to store actual option so er can compare with expected
        List<String>actualOptions = new ArrayList<>();
        for (WebElement eachOption : allProducts) {
            System.out.println("eachOption.getText() = "+eachOption.getText());
            actualOptions.add(eachOption.getText());
        }

        //assert these two list are aqual
       // assertEquals(expectedOptions, actualOptions);


    }
}
*/