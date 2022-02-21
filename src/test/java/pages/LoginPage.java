package pages;


import helpers.Data;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage{

    private final WebDriver driver;

    @FindBy(id = "passp-field-login")
    private WebElement eMailField;

    @FindBy(xpath = "//input[@placeholder='* * * * * * * *']")
    private WebElement passwordField;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод email")
    public LoginPage sendEmailInField() {
        eMailField.sendKeys(Data.getProperty("login"), Keys.ENTER);
        return this;
    }

    @Step ("Ввод пароля")
    public LoginPage sendPasswordInField() {
        passwordField.sendKeys(Data.getProperty("password"), Keys.ENTER);
        return this;
    }

}
