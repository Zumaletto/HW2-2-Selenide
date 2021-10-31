package ru.netology.domain;

import java.time.format.DateTimeFormatter;

public class LocalDate{

    static String inputData(int days){
        String inputData = java.time.LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.mm.yyyy"));
    return inputData;
    }
}
