package ru.asphaltica.ABZ.helpers;

import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import ru.asphaltica.ABZ.enumerated.GrainTable;
import ru.asphaltica.ABZ.enumerated.Sito;
import ru.asphaltica.ABZ.model.Grain;

public final class Calculations {

    private Calculations() {
    }

    //Метод принимает на вход Map c частными остатками на ситах и возвращает Map c частными остатками в граммах
    public static Map<Sito, Double> chopsCalculate(Map<Sito, Double> chogsMap) {

        double summa = 0;
        //Считаем сумму
        for (Double value : chogsMap.values()) {
            summa += value;
        }

        Map<Sito, Double> chopsMap = new HashMap<>();
        //Если сумма равна нулю создаем Map c аналогичными элементами которые содержат частные остатки равные нулю
        if (summa == 0.0) {
            for (Sito sito : chogsMap.keySet()) {
                chopsMap.put(sito, 0.0);
            }
        } else {
            for (Sito sito : chogsMap.keySet()) {
                double chop = chogsMap.get(sito) * 100 / summa;
                chopsMap.put(sito, chop);
            }
        }
        return chopsMap;
    }

    //Метод принимает Map с частными остатками в процентах и возвращает Map c полными остатками
    public static Map<Sito, Double> poCalculate(Map<Sito, Double> chops, List<Sito> orderList) {

        Map<Sito, Double> poMap = new HashMap<>();

        double summa = 0;
        for (Sito sito : orderList) {
            summa = summa + chops.get(sito);
            poMap.put(sito, summa);
        }
        return poMap;
    }

    //Метод принимает Map с полными остатками в процентах и возвращает Map c полными проходами
    public static Map<Sito, Double> ppCalculate(Map<Sito, Double> poMap) {
        Map<Sito, Double> ppMap = new HashMap<>();
        for (Sito sito : poMap.keySet()) {
            Double pp = sito.equals(Sito.DNO) ? 0.0 : 100 - poMap.get(sito);
            ppMap.put(sito, pp);
        }
        return ppMap;
    }

    //Метод принимает Map с частными остатками в граммах и возвращает Map c полными проходами
    public static Map<Sito, Double> ppCalculateByChogsMap(Map<Sito, Double> chogMap) {
        Map<Sito, Double> ppMap = new HashMap<>();
        //Создаем упорядоченный список используемых сит
        List<Sito> orderList = new ArrayList<>(chogMap.keySet());
        orderList.sort(Comparator.comparing(Sito::getOrder).reversed());
        ppMap = ppCalculate(poCalculate(chopsCalculate(chogMap), orderList));
        return ppMap;
    }

    //Метод принимает Map с частными остатками в граммах и возвращает их сумму
    public static double chogSumma(Map<Sito, Double> chogMap) {
        double summa = 0;
        for (Double chog : chogMap.values()) {
            summa = summa + chog;
        }
        return summa;
    }

    public static Map<Sito, Double> calculateResultMix(Map<GrainTable, Grain> grains,
                                                       Map<GrainTable, Double> percentageMap) {

        Map<Sito, Double> resultMix = new HashMap<>();
        //Создаем список для трасформированных строк зернового состава
        List<Map<Sito, Double>> transformedGrains = new ArrayList<>();
        //Создаем упорядоченный список используемых сит
        List<Sito> commonOrderedActualSitoList = new ArrayList<>(grains.get(GrainTable.GRAIN_TABLE_1)
                .getChog().keySet());
        commonOrderedActualSitoList.sort(Comparator.comparing(Sito::getOrder).reversed());


        for (GrainTable grainTableName : GrainTable.values()) {

            //Создаем строку измененных значений полного прохода
            Map<Sito, Double> transformedGrain = new HashMap<>();

            Grain grain = grains.get(grainTableName);
            //Достаем строку частных остатков в граммах
            Map<Sito, Double> grainChogs = grain.getChog();

            //Получаем упорядоченный список сит, нужен для рассчета зерновго состава
            List<Sito> localOrderedActualSitoList = new ArrayList<>(grainChogs.keySet());
            localOrderedActualSitoList.sort(Comparator.comparing(Sito::getOrder).reversed());

            Map<Sito, Double> chopsMap = Calculations.chopsCalculate(grainChogs);
            Map<Sito, Double> poMap = Calculations.poCalculate(chopsMap, localOrderedActualSitoList);
            Map<Sito, Double> ppMap = Calculations.ppCalculate(poMap);
            //Достаем процент содержания фракции
            double percentage = percentageMap.get(grainTableName);

            if (grain.isEmpty()) {
                transformedGrain = getEmptyRowBySitoList(commonOrderedActualSitoList);
            } else {

                for (Sito sito : commonOrderedActualSitoList) {
                    Double ppValue = ppMap.get(sito) != null ? ppMap.get(sito) : 100;
                    transformedGrain.put(sito, ppValue * percentage / 100);
                }
            }

            transformedGrains.add(transformedGrain);
        }

        for (Sito sito : commonOrderedActualSitoList) {
            double summaPP = 0;
            for (Map<Sito, Double> row : transformedGrains) {
                summaPP = summaPP + row.get(sito);
            }
            resultMix.put(sito, Rounder.roundDouble(1, summaPP));
        }
        return resultMix;
    }

    private static Map<Sito, Double> getEmptyRowBySitoList(List<Sito> list) {
        Map<Sito, Double> emptyMap = new HashMap<>();
        for (Sito sito : list) {
            emptyMap.put(sito, 0.0);
        }
        return emptyMap;
    }
}
