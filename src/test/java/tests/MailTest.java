package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MessageListPage;


class MailTest extends BaseTest {

    @Test
    @Description ("Проверка отправки сообщения и проверка что сообщение отправилось")
    public void testSendingMessages() {
        LoginPage loginPage = new LoginPage(driver);
        MessageListPage messageListPage = new MessageListPage(driver);
        loginPage.sendEmailInField()
                .sendPasswordInField();
        int startMessageCount = messageListPage.getMessageCountByTheme("Simbirsoft theme");
        messageListPage.clickToCreateMessage()
                .inputEmail("andreygrek12@yandex.ru")
                .inputTheme("Simbirsoft theme")
                .inputMessage("Найдено ")
                .inputMessage(String.valueOf(startMessageCount))
                .inputMessage(" писем/ьма")
                .sendMessage()
                .waitForSending();
        driver.navigate().refresh();
        int endMessageCount = messageListPage.getMessageCountByTheme("Simbirsoft theme");
        Assert.assertEquals(startMessageCount+1, endMessageCount, "Количество писем не изменилось");
    }
}
