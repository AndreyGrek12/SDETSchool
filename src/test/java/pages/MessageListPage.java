package pages;

import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MessageListPage {

    private final WebDriver driver;

    @FindBy (css = ".mail-ComposeButton")
    private WebElement createMessage;

    @FindBy (xpath = "//div[@class='composeYabbles']")
    private WebElement destinationEmail;

    @FindBy (css = ".composeTextField.ComposeSubject-TextField")
    private WebElement theme;

    @FindBy (xpath = "//div[@role='textbox']")
    private WebElement messageField;

    @FindBy (css = ".Button2.Button2_pin_circle-circle.Button2_view_default.Button2_size_l")
    private WebElement sendMessage;

    @FindBy (css = ".ComposeDoneScreen-Header")
    private WebElement successSendingMessage;

    public MessageListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step ("Нажатие кнопки создания сообщения")
    public MessageListPage clickToCreateMessage(){
        createMessage.click();
        return this;
    }

    @Step ("Ввод email в поле получателя")
    public MessageListPage inputEmail(String email){
        destinationEmail.sendKeys(email);
        return this;
    }

    @Step ("Ввод темы письма")
    public MessageListPage inputTheme(String themeText) {
        theme.sendKeys(themeText);
        return this;
    }

    @Step ("Получение количества сообщений с заданной темой")
    public int getMessageCountByTheme(String theme) {
        return driver.findElements(By.xpath("//span[@title='" + theme + "']")).size();
    }

    @Step ("Ввод текста сообщения")
    public MessageListPage inputMessage (String message) {
        messageField.sendKeys(message);
        return this;
    }

    @Step("Отправка сообщения")
    public MessageListPage sendMessage () {
        sendMessage.click();
        return this;
    }

    @Step("Ожидание отправки сообщения")
    public void waitForSending () {
        Waiters.waitForVisibility(driver, successSendingMessage);
    }
}
