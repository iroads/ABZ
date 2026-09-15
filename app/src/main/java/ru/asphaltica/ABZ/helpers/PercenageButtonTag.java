package ru.asphaltica.ABZ.helpers;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.asphaltica.ABZ.enumerated.GrainTable;
import ru.asphaltica.ABZ.enumerated.PercentageButtonType;
import ru.asphaltica.ABZ.enumerated.Sito;

@AllArgsConstructor
@Getter
public class PercenageButtonTag implements Serializable {
    private GrainTable grainTableName;
    private PercentageButtonType percentageButtonType;
}
