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

    @FindBy (xpath = "//a[@title='Написать (w, c)']")
    private WebElement createMessage;

    @FindBy (xpath = "//div[@class='composeYabbles']")
    private WebElement destinationEmail;

    @FindBy (xpath = "//input[@class='composeTextField ComposeSubject-TextField']")
    private WebElement theme;

    @FindBy (xpath = "//div[@role='textbox']")
    private WebElement messageField;

    @FindBy (xpath = "//button[@class='Button2 Button2_pin_circle-circle Button2_view_default Button2_size_l']")
    private WebElement sendMessage;

    @FindBy (xpath = "//span[text()='Письмо отправлено']")
    private WebElement successSendingMessage;

    public MessageListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step ("Нажатие кнопки создания сообщения")
    public void clickToCreateMessage(){
        createMessage.click();
    }

    @Step ("Ввод email в поле получателя")
    public void inputEmail(){
        destinationEmail.sendKeys("andreygrek12@yandex.ru");
    }

    @Step ("Ввод темы письма")
    public void inputTheme(String themeText) {
        theme.sendKeys(themeText);
    }

    @Step ("Получение количества сообщений с заданной темой")
    public int getMessageCountByTheme(String theme) {
        return driver.findElements(By.xpath("//span[@title='" + theme + "']")).size();
    }

    @Step ("Ввод текста сообщения")
    public void inputMessage (String message) {
        messageField.sendKeys(message);
    }

    @Step("Отправка сообщения")
    public void sendMessage () {
        sendMessage.click();
    }

    @Step("Ожидание отправки сообщения")
    public void waitForSending () {
        Waiters.waitForVisibility(driver, successSendingMessage);
    }
}
