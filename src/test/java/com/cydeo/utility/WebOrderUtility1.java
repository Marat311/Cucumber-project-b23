package com.cydeo.utility;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;



public class WebOrderUtility1 {

    static WebDriver driver = Driver.getDriver();
    static WebDriverWait wait = new WebDriverWait(driver, 2);

    public static void openWebOrderApp(){
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
    }

    public static void login(){

         // enter username
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        // enter password
        driver.findElement(By.id("ctl00_MainContent_password")) .sendKeys("test");
        // click login
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();

    }

    public static void login( String username ,String password){

         // enter username
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(username);
        // enter password
        driver.findElement(By.id("ctl00_MainContent_password")) .sendKeys(password);
        // click login
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();

    }

    public static void logout(){
        // logout link has id of ctl00_logout
        driver.findElement(By.id("ctl00_logout")).click();

    }

    public static boolean isAtOrderPage(){

        boolean result = false ;
        try{

             wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space(.)='List of All Orders']")));
             System.out.println("ELEMENT WAS IDENTIFIED ");
            result = true ;
        }catch (NoSuchElementException e){
            System.out.println("NO Such element! you are not at the right page ");
        }catch (TimeoutException e){
            System.out.println("Time out! you are not at the right page ");
        }
        return result ;
    }

    public static boolean verifyUserName(String username){
        WebElement linkContainsUsername = driver.findElement(By.xpath("//div[@class='login_info']"));
        System.out.println("Link contains username "+linkContainsUsername.getText());
        if(linkContainsUsername.getText().contains(username)){
            System.out.println(linkContainsUsername.getText()+" contains "+username);
            return true;
        }
        return false;


    }


    public static boolean checkAll () {
        WebElement checkAll = driver.findElement(By.xpath("//a[@id='ctl00_MainContent_btnCheckAll']"));
        checkAll.click();

        List<WebElement> checkBox = driver.findElements(By.xpath("//td//input[@checked='checked']"));

        for (WebElement element : checkBox) {
            if(element.isSelected()){
                System.out.println("CheckBoxes are selected");
                System.out.println("checkBox.size() = " + checkBox.size());
                return true;
            }
        }

        System.out.println("CheckBoxes are not selected");
        return false;
    }

    public static boolean uncheckAll () {
        WebElement uncheckAll = driver.findElement(By.xpath("//a[@id='ctl00_MainContent_btnUncheckAll']"));
        uncheckAll.click();

        List<WebElement> checkBox = driver.findElements(By.xpath("//td//input[@type='checkbox']"));

        for (WebElement element : checkBox) {
            if(element.isEnabled()){
                System.out.println("CheckBoxes are unchecked");
                System.out.println("checkBox.size() = " + checkBox.size());
                return true;
            }
        }

        System.out.println("CheckBoxes are not selected");
        return false;
    }

    public static void selectSideBarTab(String tabName){
        WebElement secondTab = driver.findElement(By.xpath("//a[.='View all products']"));
       assertTrue(secondTab.getText().equals(tabName));
        secondTab.click();

      /*  if(secondTab.getText().equals(tabName)){
            System.out.println("Test PASS! TabName was selected");
        }else{
            System.out.println("Test FAILED! Wrong tabName");
        }

       */
    }

    public static List <String> getAAllProducts(){
        selectSideBarTab("View all products");

        List<WebElement> productsName = driver.findElements(By.xpath("//table[@class='ProductsTable']//td[1]"));
        List<String>result = new ArrayList<>();
        for (WebElement element : productsName) {
            result.add(element.getText());
        }
        return result;
    }

    public static int getUnitPriceFromForm(String productName){
     WebElement ordersBtn = driver.findElement(By.xpath("//a[text()='Order']"));
     ordersBtn.click();
     WebElement product = driver.findElement(By.name("ctl00$MainContent$fmwOrder$ddlProduct"));
     Select productType = new Select(product);
     productType.selectByVisibleText(productName);
     WebElement submitBtn = driver.findElement(By.xpath("//input[@value='Calculate']"));
     submitBtn.click();

     WebElement unitPrice = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_fmwOrder']//input[@name='ctl00$MainContent$fmwOrder$txtUnitPrice']"));
     int result = Integer.parseInt(unitPrice.getAttribute("value"));
     return result;
    }

    /**
     * Check for login error message is visible or not , by calling the BrowserUtil method we created
     * @return true if error message displayed , false if not
     */
    public static boolean loginErrorMsgVisible(){

        boolean elementFound =
                BrowserUtil.checkVisibilityOfElement(By.xpath("//span[. ='Invalid Login or Password.']"),2);
        return elementFound ;
    }

}
