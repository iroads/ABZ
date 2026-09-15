package ru.asphaltica.ABZ.autopodbor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.asphaltica.ABZ.enumerated.GrainTable;
import ru.asphaltica.ABZ.enumerated.Sito;
import ru.asphaltica.ABZ.helpers.Calculations;
import ru.asphaltica.ABZ.helpers.Rounder;
import ru.asphaltica.ABZ.model.Grain;

import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

public class AutoPodbor {

    private AutoPodbor() {


    }

    static double[] SIEVES = {31.5, 22.4, 16, 11.2, 8, 5.6, 4, 2, 1, 0.5, 0.25, 0.125, 0.063};

    //Значимость сит
    static double[] sieveWeights = {
            1.0,  // 31.5
            1.0,  // 22.4
            1.0,  // 16
            1.0,  // 11.2
            2.0,  // 8
            1.0,  // 5.6
            5.0,  // 4
            5.0,  // 2
            5.0,  // 1
            1.0,  // 0.5
            1.0,  // 0.25
            1.0,  // 0.125
            10.0   // 0.063
    };

    public static Map<GrainTable, Double> getDosageByMix(Map<GrainTable, Grain> grains, Map<Sito, Double> targetMixGrain, double szDosage) {

        //targetMixGrain.remove(Sito.S_E5_6);

        Map<GrainTable, Double> dosageMap = new HashMap<>();

        Map<GrainTable, Map<Sito, Double>> ppGraisMap = new HashMap<>();

        //Заполняем Map который будет содержать зерновые составы представленные полными проходами
        for (GrainTable grainTable : GrainTable.values()) {
            Map<Sito, Double> row;
            row = Calculations.ppCalculateByChogsMap(grains.get(grainTable).getChog());
            row.remove(Sito.DNO);
            ppGraisMap.put(grainTable, row);
        }

        // Названия компонентов — замените/дополните под свой набор
        GrainTable[] ingredients = {
                GrainTable.GRAIN_TABLE_1,
                GrainTable.GRAIN_TABLE_2,
                GrainTable.GRAIN_TABLE_3,
                GrainTable.GRAIN_TABLE_4,
                GrainTable.GRAIN_TABLE_5,
                GrainTable.GRAIN_TABLE_6,
                GrainTable.GRAIN_TABLE_7,
                GrainTable.GRAIN_TABLE_8
        };

        //количество ингредиентов
        int n = ingredients.length;

        //определяем количество сит
        int sitoCount = (int) ppGraisMap.get(GrainTable.GRAIN_TABLE_1).values().stream().count();

        //Подготавливаем матрицу в которой каждая строка это зерновой состав каждого ингредиента
        double[][] matrix = new double[n][13];
        //заполняем массив 100
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 13; j++) {
                matrix[i][j] = 100.0;
            }
        }

        for (int i = 0; i < n; i++) {
            Map<Sito, Double> row = ppGraisMap.get(ingredients[i]);
            for (int j = 0; j < sitoCount; j++) {
                //полные проходы записываются в матрицу в обратном порядке у каждого сита есть порядковый номер
                Double value = row.get(Sito.getByOrder(sitoCount - j - 1));
                if (value != null) {
                    matrix[i][j] = value;
                }
            }
        }

//                // a[i][j] = % полного (кумулятивного) прохода компонента i через сито j (SIEVES[j])
//                // ЗАПОЛНИТЕ РЕАЛЬНЫМИ ДАННЫМИ — ниже просто пример структуры (условные цифры).
//                double[][] a = {
//                        /* 22,4-31,5 */ {100, 100, 70, 15, 3, 0, 0, 0, 0, 0, 0, 0, 0},
//                        /* 16-22,4   */ {100, 100, 100, 60, 10, 1, 0, 0, 0, 0, 0, 0, 0},
//                        /* 11,2-16   */ {100, 100, 100, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                        /* 8-11,2    */ {100, 100, 100, 100, 100, 0, 0, 0, 0, 0, 0, 0, 0},
//                        /* 4-8       */ {100, 100, 100, 100, 100, 100, 0, 0, 0, 0, 0, 0, 0},
//                        /* 2-4       */ {100, 100, 100, 100, 100, 100, 100, 0, 0, 0, 0, 0, 0},
//                        /* Песок     */ {100, 100, 100, 100, 100, 100, 100, 98, 69.59,	52.11,	38.18,	28.37,	18.97},
//                        /* МП        */ {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 98, 90, 74},
//                };

        // Целевая (заданная) кривая смеси — % полного прохода по тем же ситам
        //double[] target = {100, 100, 100, 100, 80, 63, 39, 25.3, 17.6, 13.19, 9.6, 7.18, 7};

        //готовим массив целевой кривой заполняем его значениями 100.0
        double[] target = new double[sitoCount];

        for (int i = 0; i < sitoCount; i++) {
            target[i] = 100.0;
        }
        for (Sito sito : targetMixGrain.keySet()) {
            //пропускаем сито если его нет в списке
            target[sitoCount - sito.getOrder() - 1] = targetMixGrain.get(sito);
        }
//                List<RatioConstraint> ratios = List.of(
//                        RatioConstraint.exact(GrainTable.GRAIN_TABLE_2, GrainTable.GRAIN_TABLE_1, 1)
//                );


        List<RatioConstraint> ratios = new ArrayList<>();

        List<FixedConstraint> fixed = List.of(
                FixedConstraint.exact(
                        GrainTable.GRAIN_TABLE_8,
                        Rounder.roundDouble(2,szDosage/100)
                )
        );


        FitResult fit = solveVerbose(
                ingredients,
                matrix,
                target,
                ratios,
                fixed,
                sieveWeights
        );
        double[] w = fit.weights;
        System.out.println("Статус решения: " + fit.state);

        System.out.println("\nПодобранные доли компонентов:");
        double sum = 0;
        for (int i = 0; i < n; i++) {
            System.out.printf("  %-22s %6.2f %%%n", ingredients[i].getDescription(), w[i] * 100);
            sum += w[i] * 100;
            dosageMap.put(ingredients[i], Rounder.roundDouble(1, w[i] * 100));
        }
        System.out.printf("  %-22s %6.2f %%%n", "ИТОГО", sum);

        if (w[6] > 1e-9) {
            System.out.printf("%nСоотношение 'Щебень 2-4' / 'Отсев 0-4' = %.3f%n", w[5] / w[6]);
        }

        System.out.println("\nСито, мм   Целевой   Расчётный   Отклонение");
        double[] mix = mix(matrix, w);
        for (int j = 0; j < SIEVES.length; j++) {
            System.out.printf("  %-8.3f %7.2f   %9.2f   %+8.2f%n",
                    SIEVES[j], target[j], mix[j], mix[j] - target[j]);
        }


        return getAlignedDosageMap(dosageMap);
    }

//            /** Доли компонентов без имён (компоненты нумеруются c0, c1, ...) и без ограничений на соотношения. */
//            static double[] solve(double[][] a, double[] target) {
//                return solve(autoNames(a.length), a, target, List.of());
//            }

    /**
     * Доли компонентов по именам, без ограничений на соотношения.
     */
//    static double[] solve(GrainTable[] ingredients, double[][] a, double[] target) {
//        return solve(ingredients, a, target, List.of(), List.of());
//    }

    /**
     * Доли компонентов по именам, с ограничениями на соотношения между парами компонентов.
     */
    static double[] solve(
            GrainTable[] ingredients,
            double[][] a,
            double[] target,
            List<RatioConstraint> ratios,
            List<FixedConstraint> fixed,
            double[] sieveWeights) {

        return solveVerbose(
                ingredients,
                a,
                target,
                ratios,
                fixed,
                sieveWeights
        ).weights;
    }

    /**
     * То же самое, что {@link #solve}, но дополнительно возвращает статус
     * решения солвера (OPTIMAL / INFEASIBLE / ...) и значение целевой
     * функции — удобно для юнит-тестов и диагностики.
     *
     * @param ingredients имена компонентов (используются в {@link RatioConstraint})
     * @param a           a[i][j] — % прохода компонента i через сито j
     * @param target      target[j] — целевой % прохода через сито j
     * @param ratios      ограничения на соотношения долей пар компонентов (может быть пустым списком)
     */
    static FitResult solveVerbose(
            GrainTable[] ingredients,
            double[][] a,
            double[] target,
            List<RatioConstraint> ratios,
            List<FixedConstraint> fixed,
            double[] sieveWeights) {
        int n = a.length;        // число компонентов
        int m = target.length;   // число сит

        if (ingredients.length != n) {
            throw new IllegalArgumentException("Число имён компонентов не совпадает с числом строк в a[][]");
        }

        ExpressionsBasedModel model = new ExpressionsBasedModel();

        // --- Переменные: доли компонентов ---
        Variable[] w = new Variable[n];
        for (int i = 0; i < n; i++) {
            w[i] = model.addVariable("w_" + i).lower(0).upper(1);
        }

        // --- sum(w_i) = 1 (100%) ---
        Expression sumToOne = model.addExpression("SumToOne").level(1);
        for (int i = 0; i < n; i++) {
            sumToOne.set(w[i], 1);
        }

        // --- Ограничения на соотношения между компонентами ---
        for (RatioConstraint rc : ratios) {
            int ia = indexOf(ingredients, rc.getComponentA());
            int ib = indexOf(ingredients, rc.getComponentB());
            if (ia < 0) {
                throw new IllegalArgumentException("Неизвестный компонент в RatioConstraint: " + rc.getComponentA());
            }
            if (ib < 0) {
                throw new IllegalArgumentException("Неизвестный компонент в RatioConstraint: " + rc.getComponentB());
            }

            double minRatio = rc.getMinRatio();
            double maxRatio = rc.getMaxRatio();
            String tag = rc.getComponentA() + "_vs_" + rc.getComponentB();

            if (minRatio == maxRatio) {
                // w[A] - ratio * w[B] = 0
                Expression eq = model.addExpression("ratio_eq_" + tag).level(0);
                eq.set(w[ia], 1);
                eq.set(w[ib], -minRatio);
            } else {
                if (Double.isFinite(minRatio) && minRatio > 0) {
                    // w[A] - minRatio * w[B] >= 0
                    Expression lo = model.addExpression("ratio_min_" + tag).lower(0);
                    lo.set(w[ia], 1);
                    lo.set(w[ib], -minRatio);
                }
                if (Double.isFinite(maxRatio)) {
                    // maxRatio * w[B] - w[A] >= 0
                    Expression hi = model.addExpression("ratio_max_" + tag).lower(0);
                    hi.set(w[ib], maxRatio);
                    hi.set(w[ia], -1);
                }
            }
        }

        // --- Фиксированные доли компонентов ---
        for (FixedConstraint fc : fixed) {

            int index = indexOf(
                    ingredients,
                    fc.getComponent()
            );

            if (index < 0) {
                throw new IllegalArgumentException(
                        "Неизвестный компонент: "
                                + fc.getComponent()
                );
            }

            Expression fixedExpression =
                    model.addExpression(
                            "fixed_" + index
                    ).level(fc.getValue());

            fixedExpression.set(w[index], 1);
        }

        // --- Вспомогательные переменные: модуль отклонения на каждом сите ---
        Variable[] e = new Variable[m];
        for (int j = 0; j < m; j++) {
            e[j] = model.addVariable("e_" + j).lower(0);
        }

        // e_j >= mix_j - target_j   <=>   e_j - sum(a_ij*w_i) >= -target_j
        // e_j >= target_j - mix_j   <=>   e_j + sum(a_ij*w_i) >= target_j
        for (int j = 0; j < m; j++) {
            Expression devPos = model.addExpression("dev_pos_" + j).lower(-target[j]);
            devPos.set(e[j], 1);
            for (int i = 0; i < n; i++) {
                devPos.set(w[i], -a[i][j]);
            }

            Expression devNeg = model.addExpression("dev_neg_" + j).lower(target[j]);
            devNeg.set(e[j], 1);
            for (int i = 0; i < n; i++) {
                devNeg.set(w[i], a[i][j]);
            }
        }

        // --- Целевая функция: минимизировать сумму отклонений ---
        Expression objective = model.addExpression("Objective").weight(1);
        for (int j = 0; j < m; j++) {
            objective.set(e[j], sieveWeights[j]);
        }

        Optimisation.Result result = model.minimise();

        double[] out = new double[n];
        for (int i = 0; i < n; i++) {
            out[i] = w[i].getValue().doubleValue();
        }
        return new FitResult(out, result.getState(), result.getValue());
    }

    /**
     * Считает итоговую (расчётную) кривую прохода по ситам для данных долей.
     */
    static double[] mix(double[][] a, double[] weights) {
        int m = a[0].length;
        double[] out = new double[m];
        for (int j = 0; j < m; j++) {
            double s = 0;
            for (int i = 0; i < a.length; i++) {
                s += a[i][j] * weights[i];
            }
            out[j] = s;
        }
        return out;
    }

    private static String[] autoNames(int n) {
        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            names[i] = "c" + i;
        }
        return names;
    }

    private static int indexOf(GrainTable[] ingredients, GrainTable ingredient) {
        for (int i = 0; i < ingredients.length; i++) {
            if (ingredients[i].equals(ingredient)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Результат решения: доли компонентов, статус солвера, значение целевой функции.
     */
    static final class FitResult {
        final double[] weights;
        final Optimisation.State state;
        final double objectiveValue;

        FitResult(double[] weights, Optimisation.State state, double objectiveValue) {
            this.weights = weights;
            this.state = state;
            this.objectiveValue = objectiveValue;
        }
    }

    private static Map<GrainTable, Double> getAlignedDosageMap(Map<GrainTable, Double> dosageMap){
        double summa = dosageMap.values().stream().reduce(0.0, (a,b) -> a + b).doubleValue();

        GrainTable maxDosageGrainTable = dosageMap.entrySet().stream()
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey)
                .orElse(GrainTable.GRAIN_TABLE_1);

        if (100 - summa != 0.0) {
            double oldvalue = dosageMap.get(maxDosageGrainTable);
            double newValue = Rounder.roundDouble(1, oldvalue - (summa - 100));
            dosageMap.put(maxDosageGrainTable, newValue);
        }
        return dosageMap;
    }


}
