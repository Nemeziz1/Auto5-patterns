package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class RequestTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        SelenideElement request = $(".form");
        request.$("[class='input__inner'] [type='text']").setValue(String.valueOf(DataGenerator.Generate.getCityDelivery()));
        request.$("[class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        request.$("[class='input__box'] [placeholder='Дата встречи']").setValue(String.valueOf(DataGenerator.Generate.getFirstDateDelivery()));
        request.$("[data-test-id=name] input.input__control").setValue(String.valueOf(DataGenerator.Generate.getNameDelivery()));
        request.$("[class='input__box'] [name='phone']").setValue(String.valueOf(DataGenerator.Generate.getPhoneDelivery()));
        request.$("[data-test-id=agreement]").click();
        request.$("[class='button__content'] [class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000).shouldHave(exactText("Успешно! Встреча успешно забронирована на "));
    }
}

