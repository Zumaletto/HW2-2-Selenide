package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTest {

    @BeforeEach
    void setupClass() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldSendValidValue(){
        $("[data-test-id=city] input").setValue("Москва");

        $("").setValue("");

    }

}
