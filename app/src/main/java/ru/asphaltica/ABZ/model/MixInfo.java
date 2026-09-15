package ru.asphaltica.ABZ.model;

import static ru.asphaltica.ABZ.enumerated.Sito.DNO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import ru.asphaltica.ABZ.enumerated.MixType;
import ru.asphaltica.ABZ.enumerated.RequirementsType;
import ru.asphaltica.ABZ.enumerated.Sito;
import ru.asphaltica.ABZ.enumerated.SitoType;

@Getter
@Setter
public class MixInfo {

    MixType mixType;

    //Тип набора сит, Российские или Европейские
    SitoType sitoType;

    //Допуски
    Map<Sito, Double> dopuskMap;

    //Границы
    Map<Sito, Double> boundaryUpMap;
    Map<Sito, Double> boundaryDownMap;

    public List<Sito> getSitoListForGrainTable(){
        return this.sitoType.equals(SitoType.EURO) ? getEuroSitoListForGrainTable() : getRussianSitoListForGrainTable();
    }

    public List<Sito> getSitoListForResultTable(){
        return this.sitoType.equals(SitoType.EURO) ? getEuroSitoListForResultTable(this.mixType.getLastSito())
                : getRussianSitoListForResultTable(this.mixType.getLastSito());
    }


    private static List<Sito> getEuroSitoListForGrainTable(){
        List<Sito> toReverse = Arrays.asList(Sito.values());
        Collections.reverse(toReverse);
        return toReverse;
    }

    private static List<Sito> getRussianSitoListForGrainTable(){
        return getEuroSitoListForGrainTable().stream()
                .filter(s -> s != Sito.S_E5_6 && s != Sito.S_E_22_4)
                .collect(Collectors.toList());
    }

    private static List<Sito> getRussianSitoListForResultTable(Sito lastSito){
        return getRussianSitoListForGrainTable().stream()
                .filter(s -> s.getOrder() <= lastSito.getOrder() && !s.equals(DNO))
                .collect(Collectors.toList());
    }

    private static List<Sito> getEuroSitoListForResultTable(Sito lastSito){
        return getEuroSitoListForGrainTable().stream()
                .filter(s -> s.getOrder() <= lastSito.getOrder() && !s.equals(DNO))
                .collect(Collectors.toList());
    }
}
