package ru.netology.domain;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTest {

    public String meetingDay(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setupClass() {
        open("http://localhost:9999/");
        $("[action]");
    }

    @Test
    void shouldSendValidValue() {

        String date = meetingDay(3);
        $("[data-test-id=city] .input__control").sendKeys("Санкт-Петербург");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] .input__control").sendKeys(date);
        $("[data-test-id=name] .input__control").sendKeys("Петров Петр");
        $("[data-test-id=phone] .input__control").sendKeys("+79999999999");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("[class=button__text]").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification]  .notification__title")
                .shouldHave(exactText("Успешно!"));
        $("[data-test-id=notification]  .notification__content")
                .shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    void shouldSelectFromListCity() {

        String date = meetingDay(3);
        $("[data-test-id=city] .input__control").sendKeys("Са");
        $(withText("Санкт-Петербург")).click();
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] .input__control").sendKeys(date);
        $("[data-test-id=name] .input__control").sendKeys("Петров Петр");
        $("[data-test-id=phone] .input__control").sendKeys("+79999999999");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("[class=button__text]").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification]  .notification__title")
                .shouldHave(exactText("Успешно!"));
        $("[data-test-id=notification]  .notification__content")
                .shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    void shouldSendEmptyForm() {

        $("[data-test-id=agreement] .checkbox__box").click();
        $("[class=button__text]").click();
        $(withText("Поле обязательно для заполнения"))
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendWithOutCity() {

        String date = meetingDay(3);
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] .input__control").sendKeys(date);
        $("[data-test-id=name] .input__control").sendKeys("Петров Петр");
        $("[data-test-id=phone] .input__control").sendKeys("+79999999999");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("[class=button__text]").click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendInvalidCity() {

        $("[data-test-id=city] .input__control").sendKeys("Petersburg");
        $("[class=button__text]").click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldSendWithOutDate() {

        $("[data-test-id=city] .input__control").sendKeys("Санкт-Петербург");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=name] .input__control").sendKeys("Петров Петр");
        $("[data-test-id=phone] .input__control").sendKeys("+79999999999");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("[class=button__text]").click();
        $("[data-test-id=date] .input_invalid .input__sub")
                .shouldHave(Condition.exactText("Неверно введена дата"));
    }

    @Test
    void shouldSendInvalidDate() {

        String date = meetingDay(0);
        $("[data-test-id=city] .input__control").sendKeys("Санкт-Петербург");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] .input__control").sendKeys(date);
        $("[data-test-id=name] .input__control").sendKeys("Петров Петр");
        $("[data-test-id=phone] .input__control").sendKeys("+79999999999");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("[class=button__text]").click();
        $("[data-test-id=date] .input_invalid .input__sub")
                .shouldHave(Condition.exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldSendWithOutName() {

        String date = meetingDay(3);
        $("[data-test-id=city] .input__control").sendKeys("Санкт-Петербург");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] .input__control").sendKeys(date);
        $("[data-test-id=phone] .input__control").sendKeys("+79999999999");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("[class=button__text]").click();
        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendInvalidName() {

        String date = meetingDay(3);
        $("[data-test-id=city] .input__control").sendKeys("Санкт-Петербург");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] .input__control").sendKeys(date);
        $("[data-test-id=name] .input__control").sendKeys("Petrov Petr");
        $("[data-test-id=phone] .input__control").sendKeys("+79999999999");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("[class=button__text]").click();
        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSendWithOutPhone() {

        String date = meetingDay(3);
        $("[data-test-id=city] .input__control").sendKeys("Санкт-Петербург");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] .input__control").sendKeys(date);
        $("[data-test-id=name] .input__control").sendKeys("Петров Петр");
        $("[data-test-id=phone] .input__control").sendKeys("+799999");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("[class=button__text]").click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSendInvalidPhone() {

        String date = meetingDay(3);
        $("[data-test-id=city] .input__control").sendKeys("Санкт-Петербург");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] .input__control").sendKeys(date);
        $("[data-test-id=name] .input__control").sendKeys("Петров Петр");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("[class=button__text]").click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendWithOutAgreement() {

        String date = meetingDay(3);
        $("[data-test-id=city] .input__control").sendKeys("Санкт-Петербург");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] .input__control").sendKeys(date);
        $("[data-test-id=name] .input__control").sendKeys("Петров Петр");
        $("[data-test-id=phone] .input__control").sendKeys("+79999999999");
        $("[class=button__text]").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text")
                .shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

}
