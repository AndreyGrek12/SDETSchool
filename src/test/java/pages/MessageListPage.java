package pages;

import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
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

    @FindBy (xpath = "//input[@name='subject']")
    private WebElement theme;

    @FindBy (xpath = "//div[@role='textbox']")
    private WebElement messageField;

    @FindBy (xpath = "//button[@aria-disabled='false']")
    private WebElement sendMessage;

    @FindBy (css = ".ComposeDoneScreen-Header")
    private WebElement successSendingMessage;

    @FindBy (xpath = "//input[@type='text']")
    private WebElement searchField;

    @FindBy (css = ".mail-MessagesSearchInfo-Title_misc.nb-with-xs-left-gap")
    private WebElement searchResult;

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

    @Step ("Получение количества сообщений")
    public int getMessageCount() {
        String[] result = searchResult.getText().split(" ");
        return Integer.valueOf(result[0]);
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

    @Step("Поиск по сообщениям")
    public MessageListPage inputSearch(String search) {
        Waiters.waitForVisibility(driver, searchField);
        searchField.sendKeys(search, Keys.ENTER);
        return this;
    }
}
