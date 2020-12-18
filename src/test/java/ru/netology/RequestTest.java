package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.Generate.generateDeliveryDate;


public class RequestTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        CardDelivery user = DataGenerator.Generate.generateUserData("ru");
        SelenideElement request = $("[action]");
        request.$("[class='input__inner'] [type='text']").setValue(user.getCity());
        request.$("[class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        request.$("[class='input__box'] [placeholder='Дата встречи']").setValue(generateDeliveryDate(4));
        request.$("[data-test-id=name] input.input__control").setValue(user.getName());
        request.$("[class='input__box'] [name='phone']").setValue(user.getPhone());
        request.$("[data-test-id=agreement]").click();
        request.$("[class='button__content'] [class='button__text']").click();
        $("[data-test-id=success-notification]").waitUntil(Condition.visible, 15000).shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateDeliveryDate(4)));
        request.$("[class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        request.$("[class='input__box'] [placeholder='Дата встречи']").setValue(generateDeliveryDate(5));
        request.$("[class='button__content'] [class='button__text']").click();
        $(byText("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $(".notification_has-closer .button").click();
        $("[data-test-id=success-notification]").waitUntil(Condition.visible, 15000).shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateDeliveryDate(5)));
    }
    @Test
    void shouldNotSubmitRequest() {
        SelenideElement request = $("[action]");
        CardDelivery user = DataGenerator.Generate.generateUserData("ru");
        request.$("[class='input__inner'] [type='text']").setValue(user.getCity());
        request.$("[class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        request.$("[class='input__box'] [placeholder='Дата встречи']").setValue(generateDeliveryDate(4));
        request.$("[data-test-id=name] input.input__control").setValue(user.getName());
        request.$("[class='input__box'] [name='phone']").setValue(DataGenerator.Generate.generateInvalidPhoneNumber());
        request.$("[data-test-id=agreement]").click();
        request.$("[class='button__content'] [class='button__text']").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}



