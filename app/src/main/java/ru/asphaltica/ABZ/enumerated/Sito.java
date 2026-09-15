package ru.asphaltica.ABZ.enumerated;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Sito implements Serializable {

    DNO("DNO", "DNO", "DNO",-1),
    S_E0_0063_R0_0071("0,063", "0,071", "0,075",0),
    S_E0_125_R0_16("0,125", "0,16", "0,15",1),
    S_E0_25_R0_315("0,25", "0,315", "0,3",2),
    S_E0_5_R0_63("0,5", "0,63", "0,6",3),
    S_E1_R1_25("1", "1,25", "1,18", 4),
    S_E2_R2_5("2", "2,5", "2,36", 5),
    S_E4_R5("4", "5", "4,75", 6),
    S_E5_6("5,6", "-","-", 7),
    S_E8_R10("8", "10", "9,5", 8),
    S_E11_2_R15("11,2", "15", "12,5", 9),
    S_E_16_R20("16", "20", "19", 10),
    S_E_22_4("22,4", "-", "25", 11),
    S_E_31_5_R40("31,5", "40", "37.5", 12);

    private String euroSize;
    private String russianSize;
    private String usaSize;
    private int order;

    public static Sito getByOrder(int order){
        return Arrays.stream(Sito.values()).filter(sito -> sito.getOrder() == order).findFirst().orElse(Sito.DNO);
    }

    public static List<Sito> getPowderSitoList(){
        return List.of(S_E1_R1_25, S_E0_5_R0_63, S_E0_25_R0_315, S_E0_125_R0_16, S_E0_0063_R0_0071, DNO);
    }




}
