package ru.asphaltica.ABZ.view_group;

import android.content.Context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.asphaltica.ABZ.enumerated.GrainTable;

@Getter
@AllArgsConstructor
@Setter
public class DesignModuleBlock {

    private Context context;
    private int primaryColor;
    private int color;
    private GrainTable grainTableName;
}
