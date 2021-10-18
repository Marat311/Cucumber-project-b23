package com.cydeo.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//this ain't no test class so we can not extend testBase
public class WebDriverFactory {

    public static WebDriver getDriver(String browsedName){

        WebDriver driver;

        switch (browsedName){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            default:
                driver = null;
                System.out.println("UNKNOWN BROWSER TYPE!!! "+browsedName);
        }
        driver.manage().window().maximize();
        return driver;
    }
}
