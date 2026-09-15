package ru.asphaltica.ABZ.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.asphaltica.ABZ.enumerated.GrainTable;
import ru.asphaltica.ABZ.enumerated.Sito;

@AllArgsConstructor
@Getter
public class ChogCellPosition {
    private GrainTable grainTableName;
    private Sito sito;
}
