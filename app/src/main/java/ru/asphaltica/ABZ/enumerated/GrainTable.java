package ru.asphaltica.ABZ.enumerated;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GrainTable implements Serializable {
    GRAIN_TABLE_1("Отсек №1", "0 - 2", 25),
    GRAIN_TABLE_2("Отсек №2", "2 - 4", 15),
    GRAIN_TABLE_3("Отсек №3", "4 - 8", 25),
    GRAIN_TABLE_4("Отсек №4", "8 - 11,2", 15),
    GRAIN_TABLE_5("Отсек №5", "11,2 - 16",16),
    GRAIN_TABLE_6("Отсек №6", "16 - 31,5", 0),
    GRAIN_TABLE_7("Мин.Порошок", "МП", 1),
    GRAIN_TABLE_8("Пыль", "СЗ", 1);

    private String description;
    private String defaultName;
    private double defaultPercentageValue;

}
