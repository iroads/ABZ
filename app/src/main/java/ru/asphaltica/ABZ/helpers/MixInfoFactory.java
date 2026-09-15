package ru.asphaltica.ABZ.helpers;

import java.util.HashMap;
import java.util.Map;

import ru.asphaltica.ABZ.enumerated.MixType;
import ru.asphaltica.ABZ.enumerated.Sito;
import ru.asphaltica.ABZ.enumerated.SitoType;
import ru.asphaltica.ABZ.model.MixInfo;

public class MixInfoFactory {

    private MixInfoFactory() {
    }

    public static MixInfo getMixInfo(MixType mixType) {

        MixInfo mixInfo = new MixInfo();
        mixInfo.setMixType(mixType);

        //генерация допусков для смесей верхенго слоя
        if (MixType.getAbsMarshallUpLayerRow().contains(mixType)
                && !MixType.getAbsMarshallUpLayerOnlyLightRow().contains(mixType)) {
            mixInfo.setSitoType(SitoType.EURO);
            mixInfo.setDopuskMap(getDopuskMapForAVtAndAVn(mixType.getLastSito()));
        }
        //легкие условия
        if (MixType.getAbsMarshallUpLayerOnlyLightRow().contains(mixType)) {
            mixInfo.setSitoType(SitoType.EURO);
            mixInfo.setDopuskMap(getDopuskMapForAVl(mixType.getLastSito()));
        }
        //генерация допусков для смесей нижнего слоя
        if (MixType.getAbsMarshallDownLayerRow().contains(mixType)
                && !MixType.getAbsMarshallDownLayerOnlyLightRow().contains(mixType)) {
            mixInfo.setSitoType(SitoType.EURO);
            mixInfo.setDopuskMap(getDopuskMapForANtAndANn(mixType.getLastSito()));
        }
        //легкие условия
        if (MixType.getAbsMarshallDownLayerOnlyLightRow().contains(mixType)) {
            mixInfo.setSitoType(SitoType.EURO);
            mixInfo.setDopuskMap(getDopuskMapForANl(mixType.getLastSito()));
        }

        //генерация допусков для смесей нижнего слоя
        if (MixType.getAbsMarshallBaseLayerRow().contains(mixType)
                && !MixType.getAbsMarshallBaseLayerOnlyLightRow().contains(mixType)) {
            mixInfo.setSitoType(SitoType.EURO);
            mixInfo.setDopuskMap(getDopuskMapForAOtAndAOn(mixType.getLastSito()));
        }
        //легкие условия
        if (MixType.getAbsMarshallBaseLayerOnlyLightRow().contains(mixType)) {
            mixInfo.setSitoType(SitoType.EURO);
            mixInfo.setDopuskMap(getDopuskMapForAOl(mixType.getLastSito()));
        }

        //ЩМА
        if (MixType.getAbsMarshallSHMARow().contains(mixType)) {
            mixInfo.setSitoType(SitoType.EURO);
            mixInfo.setDopuskMap(getDopuskMapForSHMA(mixType.getLastSito()));
        }

        //генерация допусков для смесей верхенго слоя Superpave
        if (MixType.getAbsSuperpaveUpLayerRow().contains(mixType)) {
            mixInfo.setSitoType(SitoType.EURO);
            mixInfo.setDopuskMap(getDopuskMapForUpLayerSuperpave());
        }

        //генерация допусков для смесей верхенго слоя Superpave
        if (MixType.getAbsSuperpaveDownLayerRow().contains(mixType)) {
            mixInfo.setSitoType(SitoType.EURO);
            mixInfo.setDopuskMap(getDopuskMapForDownAndBaseLayerSuperpave());
        }

        //генерация допусков для смесей слоя основания Superpave
        if (MixType.getAbsSuperpaveBaseLayerRow().contains(mixType)) {
            mixInfo.setSitoType(SitoType.EURO);
            mixInfo.setDopuskMap(getDopuskMapForDownAndBaseLayerSuperpave());
        }

        //генерация допусков для смесей тротуара Superpave
        if (MixType.getAbsSuperpaveTrotuarLayerRow().contains(mixType)) {
            mixInfo.setSitoType(SitoType.EURO);
            mixInfo.setDopuskMap(getDopuskMapForUpLayerSuperpave());
        }

        //генерация допусков для смесей SMA Superpave
        if (MixType.getAbsSuperpaveSMALayerRow().contains(mixType)) {
            mixInfo.setSitoType(SitoType.EURO);
            mixInfo.setDopuskMap(getDopuskMapForUpLayerSuperpave());
        }



        switch (mixType) {
            //Верхний слой непрерывные составы
            case V_TIP_A_NS:{
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForV_TipA_NS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForV_TipA_NS());
                break;
            }
            case V_TIP_B_NS: {
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForV_TipB_NS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForV_TipB_NS());
                break;
            }
            case V_TIP_V_NS: {
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForV_TipV_NS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForV_TipV_NS());
                break;
            }
            case V_TIP_G_NS: {
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForV_TipG_NS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForV_TipG_NS());
                break;
            }
            case V_TIP_D_NS: {
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForV_TipD_NS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForV_TipD_NS());
                break;
            }
            //верхний слой прерывистые составы
            case V_TIP_A_PS:{
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForV_TipA_PS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForV_TipA_PS());
                break;
            }
            case V_TIP_B_PS: {
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForV_TipB_PS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForV_TipB_PS());
                break;
            }
            //Нижний слой непрерывные составы
            case N_TIP_A_NS:{
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForN_TipA_NS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForN_TipA_NS());
                break;
            }
            case N_TIP_B_NS: {
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForN_TipB_NS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForN_TipB_NS());
                break;
            }
            //Нижний слой прерывистые составы
            case N_TIP_A_PS:{
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForN_TipA_PS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForN_TipA_PS());
                break;
            }
            case N_TIP_B_PS: {
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForN_TipB_PS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForN_TipB_PS());
                break;
            }
            case N_PORISTAYA: {
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForN_Porist_PS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForN_Porist_PS());
                break;
            }
            case N_VYSOKO_PORISTAYA_COARSE: {
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForN_Vysoko_Porist_Coarse_PS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForN_Vysoko_Porist_Coarse_PS());
                break;
            }
            case N_VYSOKO_PORISTAYA_FINE: {
                mixInfo.setSitoType(SitoType.RUSSIAN);
                mixInfo.setBoundaryUpMap(getBoundaryUpForN_Vysoko_Porist_Fine_PS());
                mixInfo.setBoundaryDownMap(getBoundaryDownForN_Vysoko_Porist_Fine_PS());
                break;
            }
        }
        return mixInfo;
    }

    //Метод возвращает допуски для смесей АВт и АВн первый столбец таблицы 18 ГОСТ 58406.2-2020
    private static Map<Sito, Double> getDopuskMapForAVtAndAVn(Sito lastSito) {
        Map<Sito, Double> dopuskMap = new HashMap<>();
        //добавляем допуск на предпоследнем сите только если крупность выше 8мм
        if (!lastSito.equals(Sito.S_E4_R5)) {
            dopuskMap.put(getPreviosSito(lastSito), 5.0);
        }
        dopuskMap.put(Sito.S_E4_R5, 5.0);
        dopuskMap.put(Sito.S_E2_R2_5, 5.0);
        dopuskMap.put(Sito.S_E1_R1_25, 3.0);
        dopuskMap.put(Sito.S_E0_0063_R0_0071, 3.0);
        return dopuskMap;
    }

    //Метод возвращает допуски для смесей АВл второй столбец таблицы 18 ГОСТ 58406.2-2020
    private static Map<Sito, Double> getDopuskMapForAVl(Sito lastSito) {
        Map<Sito, Double> dopuskMap = new HashMap<>();
        //добавляем допуск на предпоследнем сите только если крупность выше 8мм
        if (!lastSito.equals(Sito.S_E4_R5)) {
            dopuskMap.put(getPreviosSito(lastSito), 6.0);
        }
        dopuskMap.put(Sito.S_E4_R5, 6.0);
        dopuskMap.put(Sito.S_E2_R2_5, 6.0);
        dopuskMap.put(Sito.S_E0_125_R0_16, 4.0);
        dopuskMap.put(Sito.S_E0_0063_R0_0071, 4.0);
        return dopuskMap;
    }

    //Метод возвращает допуски для смесей АНт и АНн третий столбец таблицы 18 ГОСТ 58406.2-2020
    private static Map<Sito, Double> getDopuskMapForANtAndANn(Sito lastSito) {
        Map<Sito, Double> dopuskMap = new HashMap<>();
        dopuskMap.put(getPreviosSito(lastSito), 5.0);
        dopuskMap.put(Sito.S_E4_R5, 6.0);
        dopuskMap.put(Sito.S_E2_R2_5, 5.0);
        dopuskMap.put(Sito.S_E0_125_R0_16, 3.0);
        dopuskMap.put(Sito.S_E0_0063_R0_0071, 3.0);
        return dopuskMap;
    }

    //Метод возвращает допуски для смесей АНл четвертый столбец таблицы 18 ГОСТ 58406.2-2020
    private static Map<Sito, Double> getDopuskMapForANl(Sito lastSito) {
        Map<Sito, Double> dopuskMap = new HashMap<>();
        //добавляем допуск на предпоследнем сите только если крупность выше 8мм
        if (!lastSito.equals(Sito.S_E4_R5)) {
            dopuskMap.put(getPreviosSito(lastSito), 6.0);
        }
        dopuskMap.put(Sito.S_E4_R5, 7.0);
        dopuskMap.put(Sito.S_E2_R2_5, 6.0);
        dopuskMap.put(Sito.S_E0_125_R0_16, 4.0);
        dopuskMap.put(Sito.S_E0_0063_R0_0071, 4.0);
        return dopuskMap;
    }

    //Метод возвращает допуски для смесей АОт и АОн пятый столбец таблицы 18 ГОСТ 58406.2-2020
    private static Map<Sito, Double> getDopuskMapForAOtAndAOn(Sito lastSito) {
        Map<Sito, Double> dopuskMap = new HashMap<>();
        dopuskMap.put(getPreviosSito(lastSito), 6.0);
        dopuskMap.put(Sito.S_E4_R5, 6.0);
        dopuskMap.put(Sito.S_E2_R2_5, 5.0);
        dopuskMap.put(Sito.S_E0_125_R0_16, 4.0);
        dopuskMap.put(Sito.S_E0_0063_R0_0071, 4.0);
        return dopuskMap;
    }

    //Метод возвращает допуски для смесей АОл шестой столбец таблицы 18 ГОСТ 58406.2-2020
    private static Map<Sito, Double> getDopuskMapForAOl(Sito lastSito) {
        Map<Sito, Double> dopuskMap = new HashMap<>();
        dopuskMap.put(getPreviosSito(lastSito), 6.0);
        dopuskMap.put(Sito.S_E4_R5, 7.0);
        dopuskMap.put(Sito.S_E2_R2_5, 6.0);
        dopuskMap.put(Sito.S_E0_125_R0_16, 5.0);
        dopuskMap.put(Sito.S_E0_0063_R0_0071, 5.0);
        return dopuskMap;
    }

    //Метод возвращает допуски для щебеночно-мастичных смесей таблица 6 ГОСТ 58406.1-2020
    private static Map<Sito, Double> getDopuskMapForSHMA(Sito lastSito) {
        Map<Sito, Double> dopuskMap = new HashMap<>();
        dopuskMap.put(getPreviosSito(lastSito), 5.0);
        dopuskMap.put(Sito.S_E8_R10, 5.0);
        dopuskMap.put(Sito.S_E4_R5, 5.0);
        dopuskMap.put(Sito.S_E2_R2_5, 4.0);
        dopuskMap.put(Sito.S_E0_0063_R0_0071, 3.0);
        return dopuskMap;
    }

    //Метод возвращает допуски для смесей верхнего слоя первый столбец таблицы 1 ГОСТ 58401.5-2019
    private static Map<Sito, Double> getDopuskMapForUpLayerSuperpave() {
        Map<Sito, Double> dopuskMap = new HashMap<>();
        dopuskMap.put(Sito.S_E_22_4, 5.0);
        dopuskMap.put(Sito.S_E_16_R20, 5.0);
        dopuskMap.put(Sito.S_E11_2_R15, 4.0);
        dopuskMap.put(Sito.S_E8_R10, 3.5);
        dopuskMap.put(Sito.S_E4_R5, 3.5);
        dopuskMap.put(Sito.S_E2_R2_5, 3.5);
        dopuskMap.put(Sito.S_E0_0063_R0_0071, 2.0);
        return dopuskMap;
    }

    //Метод возвращает допуски для смесей нижнего слоя третий столбец таблицы 1 ГОСТ 58401.5-2019
    private static Map<Sito, Double> getDopuskMapForDownAndBaseLayerSuperpave() {
        Map<Sito, Double> dopuskMap = new HashMap<>();
        dopuskMap.put(Sito.S_E_31_5_R40, 6.0);
        dopuskMap.put(Sito.S_E_22_4, 6.0);
        dopuskMap.put(Sito.S_E_16_R20, 5.0);
        dopuskMap.put(Sito.S_E11_2_R15, 4.5);
        dopuskMap.put(Sito.S_E8_R10, 4.0);
        dopuskMap.put(Sito.S_E4_R5, 4.0);
        dopuskMap.put(Sito.S_E2_R2_5, 4.0);
        dopuskMap.put(Sito.S_E0_0063_R0_0071, 2.5);
        return dopuskMap;
    }

    private static Map<Sito, Double> getBoundaryUpForV_TipA_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 10.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 12.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 16.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 20.0);
        boundaryMap.put(Sito.S_E1_R1_25, 28.0);
        boundaryMap.put(Sito.S_E2_R2_5, 38.0);
        boundaryMap.put(Sito.S_E4_R5, 50.0);
        boundaryMap.put(Sito.S_E8_R10, 100.0);
        boundaryMap.put(Sito.S_E11_2_R15, 100.0);
        boundaryMap.put(Sito.S_E_16_R20, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForV_TipA_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 4.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 6.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 10.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 14.0);
        boundaryMap.put(Sito.S_E1_R1_25, 20.0);
        boundaryMap.put(Sito.S_E2_R2_5, 28.0);
        boundaryMap.put(Sito.S_E4_R5, 40.0);
        boundaryMap.put(Sito.S_E8_R10, 62.0);
        boundaryMap.put(Sito.S_E11_2_R15, 75.0);
        boundaryMap.put(Sito.S_E_16_R20, 90.0);
        return boundaryMap;
    }


    private static Map<Sito, Double> getBoundaryUpForV_TipB_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 12.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 16.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 22.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 28.0);
        boundaryMap.put(Sito.S_E1_R1_25, 37.0);
        boundaryMap.put(Sito.S_E2_R2_5, 48.0);
        boundaryMap.put(Sito.S_E4_R5, 60.0);
        boundaryMap.put(Sito.S_E8_R10, 100.0);
        boundaryMap.put(Sito.S_E11_2_R15, 100.0);
        boundaryMap.put(Sito.S_E_16_R20, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForV_TipB_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 6.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 10.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 14.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 20.0);
        boundaryMap.put(Sito.S_E1_R1_25, 28.0);
        boundaryMap.put(Sito.S_E2_R2_5, 38.0);
        boundaryMap.put(Sito.S_E4_R5, 50.0);
        boundaryMap.put(Sito.S_E8_R10, 70.0);
        boundaryMap.put(Sito.S_E11_2_R15, 80.0);
        boundaryMap.put(Sito.S_E_16_R20, 90.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryUpForV_TipV_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 14.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 20.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 30.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 40.0);
        boundaryMap.put(Sito.S_E1_R1_25, 50.0);
        boundaryMap.put(Sito.S_E2_R2_5, 60.0);
        boundaryMap.put(Sito.S_E4_R5, 70.0);
        boundaryMap.put(Sito.S_E8_R10, 100.0);
        boundaryMap.put(Sito.S_E11_2_R15, 100.0);
        boundaryMap.put(Sito.S_E_16_R20, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForV_TipV_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 8.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 13.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 20.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 28.0);
        boundaryMap.put(Sito.S_E1_R1_25, 37.0);
        boundaryMap.put(Sito.S_E2_R2_5, 48.0);
        boundaryMap.put(Sito.S_E4_R5, 60.0);
        boundaryMap.put(Sito.S_E8_R10, 75.0);
        boundaryMap.put(Sito.S_E11_2_R15, 85.0);
        boundaryMap.put(Sito.S_E_16_R20, 90.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryUpForV_TipG_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 16.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 25.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 36.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 50.0);
        boundaryMap.put(Sito.S_E1_R1_25, 65.0);
        boundaryMap.put(Sito.S_E2_R2_5, 82.0);
        boundaryMap.put(Sito.S_E4_R5, 100.0);
        boundaryMap.put(Sito.S_E8_R10, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForV_TipG_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 8.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 15.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 20.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 30.0);
        boundaryMap.put(Sito.S_E1_R1_25, 42.0);
        boundaryMap.put(Sito.S_E2_R2_5, 56.0);
        boundaryMap.put(Sito.S_E4_R5, 70.0);
        boundaryMap.put(Sito.S_E8_R10, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryUpForV_TipD_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 16.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 33.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 55.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 75.0);
        boundaryMap.put(Sito.S_E1_R1_25, 85.0);
        boundaryMap.put(Sito.S_E2_R2_5, 93.0);
        boundaryMap.put(Sito.S_E4_R5, 100.0);
        boundaryMap.put(Sito.S_E8_R10, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForV_TipD_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 10.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 15.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 20.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 30.0);
        boundaryMap.put(Sito.S_E1_R1_25, 42.0);
        boundaryMap.put(Sito.S_E2_R2_5, 60.0);
        boundaryMap.put(Sito.S_E4_R5, 70.0);
        boundaryMap.put(Sito.S_E8_R10, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryUpForV_TipA_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 10.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 16.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 28.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 50.0);
        boundaryMap.put(Sito.S_E1_R1_25, 50.0);
        boundaryMap.put(Sito.S_E2_R2_5, 50.0);
        boundaryMap.put(Sito.S_E4_R5, 50.0);
        boundaryMap.put(Sito.S_E8_R10, 100.0);
        boundaryMap.put(Sito.S_E11_2_R15, 100.0);
        boundaryMap.put(Sito.S_E_16_R20, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForV_TipA_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 4.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 6.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 10.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 14.0);
        boundaryMap.put(Sito.S_E1_R1_25, 20.0);
        boundaryMap.put(Sito.S_E2_R2_5, 28.0);
        boundaryMap.put(Sito.S_E4_R5, 40.0);
        boundaryMap.put(Sito.S_E8_R10, 62.0);
        boundaryMap.put(Sito.S_E11_2_R15, 75.0);
        boundaryMap.put(Sito.S_E_16_R20, 90.0);
        return boundaryMap;
    }


    private static Map<Sito, Double> getBoundaryUpForV_TipB_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 12.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 20.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 34.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 60.0);
        boundaryMap.put(Sito.S_E1_R1_25, 60.0);
        boundaryMap.put(Sito.S_E2_R2_5, 60.0);
        boundaryMap.put(Sito.S_E4_R5, 60.0);
        boundaryMap.put(Sito.S_E8_R10, 100.0);
        boundaryMap.put(Sito.S_E11_2_R15, 100.0);
        boundaryMap.put(Sito.S_E_16_R20, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForV_TipB_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 6.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 10.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 14.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 20.0);
        boundaryMap.put(Sito.S_E1_R1_25, 28.0);
        boundaryMap.put(Sito.S_E2_R2_5, 38.0);
        boundaryMap.put(Sito.S_E4_R5, 50.0);
        boundaryMap.put(Sito.S_E8_R10, 70.0);
        boundaryMap.put(Sito.S_E11_2_R15, 80.0);
        boundaryMap.put(Sito.S_E_16_R20, 90.0);
        return boundaryMap;
    }
    //Нижние слои непрерывные зерновые составы
    private static Map<Sito, Double> getBoundaryUpForN_TipA_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 10.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 12.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 16.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 20.0);
        boundaryMap.put(Sito.S_E1_R1_25, 28.0);
        boundaryMap.put(Sito.S_E2_R2_5, 38.0);
        boundaryMap.put(Sito.S_E4_R5, 50.0);
        boundaryMap.put(Sito.S_E8_R10, 62.0);
        boundaryMap.put(Sito.S_E11_2_R15, 70.0);
        boundaryMap.put(Sito.S_E_16_R20, 90.0);
        boundaryMap.put(Sito.S_E_31_5_R40, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForN_TipA_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 4.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 6.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 10.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 14.0);
        boundaryMap.put(Sito.S_E1_R1_25, 20.0);
        boundaryMap.put(Sito.S_E2_R2_5, 28.0);
        boundaryMap.put(Sito.S_E4_R5, 40.0);
        boundaryMap.put(Sito.S_E8_R10, 48.0);
        boundaryMap.put(Sito.S_E11_2_R15, 56.0);
        boundaryMap.put(Sito.S_E_16_R20, 66.0);
        boundaryMap.put(Sito.S_E_31_5_R40, 90.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryUpForN_TipB_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 12.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 16.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 22.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 28.0);
        boundaryMap.put(Sito.S_E1_R1_25, 37.0);
        boundaryMap.put(Sito.S_E2_R2_5, 48.0);
        boundaryMap.put(Sito.S_E4_R5, 60.0);
        boundaryMap.put(Sito.S_E8_R10, 72.0);
        boundaryMap.put(Sito.S_E11_2_R15, 80.0);
        boundaryMap.put(Sito.S_E_16_R20, 90.0);
        boundaryMap.put(Sito.S_E_31_5_R40, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForN_TipB_NS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 6.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 10.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 14.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 20.0);
        boundaryMap.put(Sito.S_E1_R1_25, 28.0);
        boundaryMap.put(Sito.S_E2_R2_5, 38.0);
        boundaryMap.put(Sito.S_E4_R5, 50.0);
        boundaryMap.put(Sito.S_E8_R10, 60.0);
        boundaryMap.put(Sito.S_E11_2_R15, 68.0);
        boundaryMap.put(Sito.S_E_16_R20, 76.0);
        boundaryMap.put(Sito.S_E_31_5_R40, 90.0);
        return boundaryMap;
    }

    //Нижние слои прерывистые зерновые составы
    private static Map<Sito, Double> getBoundaryUpForN_TipA_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 10.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 16.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 28.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 50.0);
        boundaryMap.put(Sito.S_E1_R1_25, 50.0);
        boundaryMap.put(Sito.S_E2_R2_5, 50.0);
        boundaryMap.put(Sito.S_E4_R5, 50.0);
        boundaryMap.put(Sito.S_E8_R10, 62.0);
        boundaryMap.put(Sito.S_E11_2_R15, 70.0);
        boundaryMap.put(Sito.S_E_16_R20, 90.0);
        boundaryMap.put(Sito.S_E_31_5_R40, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForN_TipA_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 4.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 6.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 10.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 14.0);
        boundaryMap.put(Sito.S_E1_R1_25, 20.0);
        boundaryMap.put(Sito.S_E2_R2_5, 28.0);
        boundaryMap.put(Sito.S_E4_R5, 40.0);
        boundaryMap.put(Sito.S_E8_R10, 48.0);
        boundaryMap.put(Sito.S_E11_2_R15, 56.0);
        boundaryMap.put(Sito.S_E_16_R20, 66.0);
        boundaryMap.put(Sito.S_E_31_5_R40, 90.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryUpForN_TipB_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 12.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 20.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 34.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 60.0);
        boundaryMap.put(Sito.S_E1_R1_25, 60.0);
        boundaryMap.put(Sito.S_E2_R2_5, 60.0);
        boundaryMap.put(Sito.S_E4_R5, 60.0);
        boundaryMap.put(Sito.S_E8_R10, 72.0);
        boundaryMap.put(Sito.S_E11_2_R15, 80.0);
        boundaryMap.put(Sito.S_E_16_R20, 90.0);
        boundaryMap.put(Sito.S_E_31_5_R40, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForN_TipB_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 6.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 10.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 14.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 20.0);
        boundaryMap.put(Sito.S_E1_R1_25, 28.0);
        boundaryMap.put(Sito.S_E2_R2_5, 38.0);
        boundaryMap.put(Sito.S_E4_R5, 50.0);
        boundaryMap.put(Sito.S_E8_R10, 60.0);
        boundaryMap.put(Sito.S_E11_2_R15, 68.0);
        boundaryMap.put(Sito.S_E_16_R20, 76.0);
        boundaryMap.put(Sito.S_E_31_5_R40, 90.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryUpForN_Porist_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 8.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 20.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 37.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 60.0);
        boundaryMap.put(Sito.S_E1_R1_25, 60.0);
        boundaryMap.put(Sito.S_E2_R2_5, 60.0);
        boundaryMap.put(Sito.S_E4_R5, 60.0);
        boundaryMap.put(Sito.S_E8_R10, 88.0);
        boundaryMap.put(Sito.S_E11_2_R15, 100.0);
        boundaryMap.put(Sito.S_E_16_R20, 100.0);
        boundaryMap.put(Sito.S_E_31_5_R40, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForN_Porist_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 2.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 5.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 8.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 10.0);
        boundaryMap.put(Sito.S_E1_R1_25, 16.0);
        boundaryMap.put(Sito.S_E2_R2_5, 28.0);
        boundaryMap.put(Sito.S_E4_R5, 40.0);
        boundaryMap.put(Sito.S_E8_R10, 52.0);
        boundaryMap.put(Sito.S_E11_2_R15, 64.0);
        boundaryMap.put(Sito.S_E_16_R20, 75.0);
        boundaryMap.put(Sito.S_E_31_5_R40, 90.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryUpForN_Vysoko_Porist_Coarse_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 4.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 5.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 8.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 10.0);
        boundaryMap.put(Sito.S_E1_R1_25, 16.0);
        boundaryMap.put(Sito.S_E2_R2_5, 28.0);
        boundaryMap.put(Sito.S_E4_R5, 40.0);
        boundaryMap.put(Sito.S_E8_R10, 52.0);
        boundaryMap.put(Sito.S_E11_2_R15, 64.0);
        boundaryMap.put(Sito.S_E_16_R20, 75.0);
        boundaryMap.put(Sito.S_E_31_5_R40, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForN_Vysoko_Porist_Coarse_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 1.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 1.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 2.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 3.0);
        boundaryMap.put(Sito.S_E1_R1_25, 5.0);
        boundaryMap.put(Sito.S_E2_R2_5, 10.0);
        boundaryMap.put(Sito.S_E4_R5, 15.0);
        boundaryMap.put(Sito.S_E8_R10, 22.0);
        boundaryMap.put(Sito.S_E11_2_R15, 35.0);
        boundaryMap.put(Sito.S_E_16_R20, 55.0);
        boundaryMap.put(Sito.S_E_31_5_R40, 90.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryUpForN_Vysoko_Porist_Fine_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 10.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 45.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 72.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 85.0);
        boundaryMap.put(Sito.S_E1_R1_25, 100.0);
        boundaryMap.put(Sito.S_E2_R2_5, 100.0);
        boundaryMap.put(Sito.S_E4_R5, 100.0);
        return boundaryMap;
    }

    private static Map<Sito, Double> getBoundaryDownForN_Vysoko_Porist_Fine_PS() {
        Map<Sito, Double> boundaryMap = new HashMap<>();
        boundaryMap.put(Sito.S_E0_0063_R0_0071, 4.0);
        boundaryMap.put(Sito.S_E0_125_R0_16, 10.0);
        boundaryMap.put(Sito.S_E0_25_R0_315, 17.0);
        boundaryMap.put(Sito.S_E0_5_R0_63, 25.0);
        boundaryMap.put(Sito.S_E1_R1_25, 41.0);
        boundaryMap.put(Sito.S_E2_R2_5, 64.0);
        boundaryMap.put(Sito.S_E4_R5, 70.0);
        return boundaryMap;
    }



    private static Sito getPreviosSito(Sito lastSito) {
        return Sito.getByOrder(lastSito.getOrder() - 1);
    }

}
