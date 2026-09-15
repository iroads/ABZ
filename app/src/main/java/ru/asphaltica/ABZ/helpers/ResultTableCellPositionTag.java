package ru.asphaltica.ABZ.helpers;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.asphaltica.ABZ.enumerated.GrainTable;
import ru.asphaltica.ABZ.enumerated.ResultTableRow;
import ru.asphaltica.ABZ.enumerated.Sito;

@AllArgsConstructor
@Getter
public class ResultTableCellPositionTag implements Serializable {
    private ResultTableRow resultTableRow;
    private Sito sito;
}
