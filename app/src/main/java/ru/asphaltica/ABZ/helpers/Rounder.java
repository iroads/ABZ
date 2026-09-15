package ru.asphaltica.ABZ.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rounder {
    //Метод для огругления чисел до необходимого знака после запятой
    public static double roundDouble(int countAfterPoint, double number) {
        if (number != 0.0) {
            BigDecimal bigDecimal = BigDecimal.valueOf(number);
            return bigDecimal.setScale(countAfterPoint, RoundingMode.HALF_UP).doubleValue();
        } else {
            return number;
        }
    }


}
