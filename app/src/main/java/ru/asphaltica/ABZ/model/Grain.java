package ru.asphaltica.ABZ.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.asphaltica.ABZ.enumerated.GrainTable;
import ru.asphaltica.ABZ.enumerated.Sito;
@AllArgsConstructor
@Getter
public class Grain implements Serializable {



    Map<Sito, Double> chog = new HashMap<>();

    public boolean isEmpty() {
        //Смотрим заполнены ли частные остатки в материале
        double summa = chog.values().stream().reduce(0.0, (a, b) -> a + b);
        //Если да считаем как положено
        return summa == 0.0 ? true : false;
    }

    public static Grain getDefaultGrain(GrainTable grainTable) {

        Map<Sito, Double> defaultChogs = new HashMap<>();

        switch (grainTable) {
            case GRAIN_TABLE_1:
                defaultChogs.put(Sito.S_E_31_5_R40, 0.0);
                defaultChogs.put(Sito.S_E_22_4, 0.0);
                defaultChogs.put(Sito.S_E_16_R20, 0.0);
                defaultChogs.put(Sito.S_E11_2_R15, 0.0);
                defaultChogs.put(Sito.S_E8_R10, 0.0);
                defaultChogs.put(Sito.S_E5_6, 0.0);
                defaultChogs.put(Sito.S_E4_R5, 0.0);
                defaultChogs.put(Sito.S_E2_R2_5, 0.0);
                defaultChogs.put(Sito.S_E1_R1_25, 916.0);
                defaultChogs.put(Sito.S_E0_5_R0_63, 840.0);
                defaultChogs.put(Sito.S_E0_25_R0_315, 744.0);
                defaultChogs.put(Sito.S_E0_125_R0_16, 394.0);
                defaultChogs.put(Sito.S_E0_0063_R0_0071, 346.0);
                defaultChogs.put(Sito.DNO, 826.0);
                break;
            case GRAIN_TABLE_2:
                defaultChogs.put(Sito.S_E_31_5_R40, 0.0);
                defaultChogs.put(Sito.S_E_22_4, 0.0);
                defaultChogs.put(Sito.S_E_16_R20, 0.0);
                defaultChogs.put(Sito.S_E11_2_R15, 0.0);
                defaultChogs.put(Sito.S_E8_R10, 0.0);
                defaultChogs.put(Sito.S_E5_6, 0.0);
                defaultChogs.put(Sito.S_E4_R5, 10.0);
                defaultChogs.put(Sito.S_E2_R2_5, 80.0);
                defaultChogs.put(Sito.S_E1_R1_25, 10.0);
                defaultChogs.put(Sito.S_E0_5_R0_63, 0.0);
                defaultChogs.put(Sito.S_E0_25_R0_315, 0.0);
                defaultChogs.put(Sito.S_E0_125_R0_16, 0.0);
                defaultChogs.put(Sito.S_E0_0063_R0_0071, 0.0);
                defaultChogs.put(Sito.DNO, 0.0);
                break;
            case GRAIN_TABLE_3:
                defaultChogs.put(Sito.S_E_31_5_R40, 0.0);
                defaultChogs.put(Sito.S_E_22_4, 0.0);
                defaultChogs.put(Sito.S_E_16_R20, 0.0);
                defaultChogs.put(Sito.S_E11_2_R15, 0.0);
                defaultChogs.put(Sito.S_E8_R10, 10.0);
                defaultChogs.put(Sito.S_E5_6, 0.0);
                defaultChogs.put(Sito.S_E4_R5, 80.0);
                defaultChogs.put(Sito.S_E2_R2_5, 10.0);
                defaultChogs.put(Sito.S_E1_R1_25, 0.0);
                defaultChogs.put(Sito.S_E0_5_R0_63, 0.0);
                defaultChogs.put(Sito.S_E0_25_R0_315, 0.0);
                defaultChogs.put(Sito.S_E0_125_R0_16, 0.0);
                defaultChogs.put(Sito.S_E0_0063_R0_0071, 0.0);
                defaultChogs.put(Sito.DNO, 0.0);
                break;
            case GRAIN_TABLE_4:
                defaultChogs.put(Sito.S_E_31_5_R40, 0.0);
                defaultChogs.put(Sito.S_E_22_4, 0.0);
                defaultChogs.put(Sito.S_E_16_R20, 0.0);
                defaultChogs.put(Sito.S_E11_2_R15, 10.0);
                defaultChogs.put(Sito.S_E8_R10, 80.0);
                defaultChogs.put(Sito.S_E5_6, 0.0);
                defaultChogs.put(Sito.S_E4_R5, 10.0);
                defaultChogs.put(Sito.S_E2_R2_5, 0.0);
                defaultChogs.put(Sito.S_E1_R1_25, 0.0);
                defaultChogs.put(Sito.S_E0_5_R0_63, 0.0);
                defaultChogs.put(Sito.S_E0_25_R0_315, 0.0);
                defaultChogs.put(Sito.S_E0_125_R0_16, 0.0);
                defaultChogs.put(Sito.S_E0_0063_R0_0071, 0.0);
                defaultChogs.put(Sito.DNO, 0.0);
                break;
            case GRAIN_TABLE_5:
                defaultChogs.put(Sito.S_E_31_5_R40, 0.0);
                defaultChogs.put(Sito.S_E_22_4, 0.0);
                defaultChogs.put(Sito.S_E_16_R20, 10.0);
                defaultChogs.put(Sito.S_E11_2_R15, 80.0);
                defaultChogs.put(Sito.S_E8_R10, 10.0);
                defaultChogs.put(Sito.S_E5_6, 0.0);
                defaultChogs.put(Sito.S_E4_R5, 0.0);
                defaultChogs.put(Sito.S_E2_R2_5, 0.0);
                defaultChogs.put(Sito.S_E1_R1_25, 0.0);
                defaultChogs.put(Sito.S_E0_5_R0_63, 0.0);
                defaultChogs.put(Sito.S_E0_25_R0_315, 0.0);
                defaultChogs.put(Sito.S_E0_125_R0_16, 0.0);
                defaultChogs.put(Sito.S_E0_0063_R0_0071, 0.0);
                defaultChogs.put(Sito.DNO, 0.0);
                break;
            case GRAIN_TABLE_6:
                defaultChogs.put(Sito.S_E_31_5_R40, 5.0);
                defaultChogs.put(Sito.S_E_22_4, 45.0);
                defaultChogs.put(Sito.S_E_16_R20, 45.0);
                defaultChogs.put(Sito.S_E11_2_R15, 5.0);
                defaultChogs.put(Sito.S_E8_R10, 0.0);
                defaultChogs.put(Sito.S_E5_6, 0.0);
                defaultChogs.put(Sito.S_E4_R5, 0.0);
                defaultChogs.put(Sito.S_E2_R2_5, 0.0);
                defaultChogs.put(Sito.S_E1_R1_25, 0.0);
                defaultChogs.put(Sito.S_E0_5_R0_63, 0.0);
                defaultChogs.put(Sito.S_E0_25_R0_315, 0.0);
                defaultChogs.put(Sito.S_E0_125_R0_16, 0.0);
                defaultChogs.put(Sito.S_E0_0063_R0_0071, 0.0);
                defaultChogs.put(Sito.DNO, 0.0);
                break;
            case GRAIN_TABLE_7:
                defaultChogs.put(Sito.S_E1_R1_25, 0.0);
                defaultChogs.put(Sito.S_E0_5_R0_63, 0.0);
                defaultChogs.put(Sito.S_E0_25_R0_315, 0.0);
                defaultChogs.put(Sito.S_E0_125_R0_16, 0.0);
                defaultChogs.put(Sito.S_E0_0063_R0_0071, 25.0);
                defaultChogs.put(Sito.DNO, 75.0);
                break;
            case GRAIN_TABLE_8:
                defaultChogs.put(Sito.S_E1_R1_25, 0.0);
                defaultChogs.put(Sito.S_E0_5_R0_63, 0.0);
                defaultChogs.put(Sito.S_E0_25_R0_315, 0.0);
                defaultChogs.put(Sito.S_E0_125_R0_16, 0.0);
                defaultChogs.put(Sito.S_E0_0063_R0_0071, 25.0);
                defaultChogs.put(Sito.DNO, 75.0);
                break;
        }

        return new Grain(defaultChogs);
    }


}
