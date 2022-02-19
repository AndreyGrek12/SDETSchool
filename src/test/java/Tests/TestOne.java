package Tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MessageListPage;


class TestOne extends BaseTest {

    @Test
    @Description ("Проверка отправки сообщения и проверка что сообщение отправилось")
    public void testSendingMessages() {
        LoginPage loginPage = new LoginPage(driver);
        MessageListPage messageListPage = new MessageListPage(driver);
        loginPage.sendEmailInField();
        loginPage.sendPasswordInField();
        int startMessageCount = messageListPage.getMessageCountByTheme("Simbirsoft theme");
        messageListPage.clickToCreateMessage();
        messageListPage.inputEmail();
        messageListPage.inputTheme("Simbirsoft theme");
        messageListPage.inputMessage("Найдено "
                + startMessageCount
                + " писем/ьма");
        messageListPage.sendMessage();
        messageListPage.waitForSending();
        driver.navigate().refresh();
        int endMessageCount = messageListPage.getMessageCountByTheme("Simbirsoft theme");
        Assert.assertEquals(startMessageCount+1, endMessageCount);
    }
}
