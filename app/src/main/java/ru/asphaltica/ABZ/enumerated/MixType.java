package ru.asphaltica.ABZ.enumerated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MixType {

    //АБС для верхнего слоя покрытия по ГОСТ Р 58406.2 (Маршалл)
    A22VT("А22ВТ", "А22ВТ", Sito.S_E_22_4),
    A16VT("А16ВТ", "А16ВТ", Sito.S_E_16_R20),
    A11VT("А11ВТ", "А11ВТ", Sito.S_E11_2_R15),

    A22VN("А22ВН", "А22ВН", Sito.S_E_22_4),
    A16VN("А16ВН", "А16ВН", Sito.S_E_16_R20),
    A11VN("А11ВН", "А11ВН", Sito.S_E11_2_R15),
    A8VN("А8ВН", "А8ВН", Sito.S_E8_R10),

    A16VL("А16ВЛ", "А16ВЛ", Sito.S_E_16_R20),
    A11VL("А11ВЛ", "А11ВЛ", Sito.S_E11_2_R15),
    A8VL("А8ВЛ", "А8ВЛ", Sito.S_E8_R10),
    A5VL("А5ВЛ", "А5ВЛ", Sito.S_E4_R5),

    //АБС для нижнего слоя покрытия по ГОСТ Р 58406.2 (Маршалл)
    A32NT("А32НТ", "А32НТ", Sito.S_E_31_5_R40),
    A22NT("А22НТ", "А22НТ", Sito.S_E_22_4),
    A16NT("А16НТ", "А16НТ", Sito.S_E_16_R20),

    A32NN("А32НН", "А32НН", Sito.S_E_31_5_R40),
    A22NN("А22НН", "А22НН", Sito.S_E_22_4),
    A16NN("А16НН", "А16НН", Sito.S_E_16_R20),
    A11NN("А11НН", "А11НН", Sito.S_E11_2_R15),

    A22NL("А22НЛ", "А22НЛ", Sito.S_E_22_4),
    A16NL("А16НЛ", "А16НЛ", Sito.S_E_16_R20),
    A11NL("А11НЛ", "А11НЛ", Sito.S_E11_2_R15),
    A8NL("А8НЛ", "А8НЛ", Sito.S_E8_R10),
    A5NL("А5НЛ", "А5НЛ", Sito.S_E4_R5),

    //АБС для слоя основания по ГОСТ Р 58406.2 (Маршалл)
    A32OT("А32ОТ", "А32ОТ", Sito.S_E_31_5_R40),
    A22OT("А22ОТ", "А22ОТ", Sito.S_E_22_4),
    A16OT("А16ОТ", "А16ОТ", Sito.S_E_16_R20),

    A32ON("А32ОН", "А32ОН", Sito.S_E_31_5_R40),
    A22ON("А22ОН", "А22ОН", Sito.S_E_22_4),
    A16ON("А16ОН", "А16ОН", Sito.S_E_16_R20),

    A32OL("А32ОЛ", "А32ОЛ", Sito.S_E_31_5_R40),
    A22OL("А22ОЛ", "А22ОЛ", Sito.S_E_22_4),
    A16OL("А16ОЛ", "А16ОЛ", Sito.S_E_16_R20),

    //ЩМА (Маршалл)
    SHMA22("ЩМА-22", "ЩМА-22", Sito.S_E_22_4),
    SHMA16("ЩМА-16", "ЩМА-16", Sito.S_E_16_R20),
    SHMA11("ЩМА-11", "ЩМА-11", Sito.S_E11_2_R15),
    SHMA8("ЩМА-8", "ЩМА-8", Sito.S_E8_R10),

    //АБС для верхнего слоя покрытия по ГОСТ Р 58401.1 (Суперпейв)

    SP_22EV("SP-22Э верхний слой", "SP-22Э", Sito.S_E_22_4),
    SP_22TV("SP-22Т верхний слой", "SP-22Т", Sito.S_E_22_4),

    SP_16EV("SP-16Э верхний слой", "SP-16Э", Sito.S_E_16_R20),
    SP_16TV("SP-16Т верхний слой", "SP-16Т", Sito.S_E_16_R20),
    SP_16NV("SP-16Н верхний слой", "SP-16Н", Sito.S_E_16_R20),
    SP_16LV("SP-16Л верхний слой", "SP-16Л", Sito.S_E_16_R20),

    SP_11TV("SP-11Т верхний слой", "SP-11Т", Sito.S_E11_2_R15),
    SP_11NV("SP-11Н верхний слой", "SP-11Н", Sito.S_E11_2_R15),
    SP_11LV("SP-11Л верхний слой", "SP-11Л", Sito.S_E11_2_R15),

    SP_8NV("SP-8Н верхний слой", "SP-8Н", Sito.S_E8_R10),
    SP_8LV("SP-8Л верхний слой", "SP-8Л", Sito.S_E8_R10),

    //АБС для нижнего слоя покрытия по ГОСТ Р 58401.1 (Суперпейв)

    SP_32EN("SP-32Э нижний слой", "SP-32Э", Sito.S_E_31_5_R40),
    SP_32TN("SP-32Т нижний слой", "SP-32Т", Sito.S_E_31_5_R40),
    SP_32NN("SP-32Н нижний слой", "SP-32Н", Sito.S_E_31_5_R40),
    SP_32LN("SP-32Л нижний слой", "SP-32Л", Sito.S_E_31_5_R40),

    SP_22EN("SP-22Э нижний слой", "SP-22Э", Sito.S_E_22_4),
    SP_22TN("SP-22Т нижний слой", "SP-22Т", Sito.S_E_22_4),
    SP_22NN("SP-22Н нижний слой", "SP-22Н", Sito.S_E_22_4),
    SP_22LN("SP-22Л нижний слой", "SP-22Л", Sito.S_E_22_4),

    SP_16TN("SP-16Т нижний слой", "SP-16Т", Sito.S_E_16_R20),
    SP_16NN("SP-16Н нижний слой", "SP-16Н", Sito.S_E_16_R20),
    SP_16LN("SP-16Л нижний слой", "SP-16Л", Sito.S_E_16_R20),

    //АБС для слоя основания по ГОСТ Р 58401.1 (Суперпейв)

    SP_32EO("SP-32Э основание", "SP-32Э", Sito.S_E_31_5_R40),
    SP_32TO("SP-32Т основание", "SP-32Т", Sito.S_E_31_5_R40),
    SP_32NO("SP-32Н основание", "SP-32Н", Sito.S_E_31_5_R40),

    SP_22TO("SP-22Т основание", "SP-22Т", Sito.S_E_22_4),
    SP_22NO("SP-22Н основание", "SP-22Н", Sito.S_E_22_4),
    SP_22LO("SP-22Л основание", "SP-22Л", Sito.S_E_22_4),

    //АБС для тротуара по ГОСТ Р 58401.1 (Суперпейв)
    SP_8LT("SP-8Л тротуар", "SP-8Л", Sito.S_E8_R10),
    SP_4LT("SP-4Л тротуар", "SP-4Л", Sito.S_E4_R5),

    //ЩМА по ГОСТ Р 58401.2 (Суперпейв)
    SMA22("SMA-22", "SMA-22", Sito.S_E_22_4),
    SMA16("SMA-16", "SMA-16", Sito.S_E_16_R20),
    SMA11("SMA-11", "SMA-11", Sito.S_E11_2_R15),
    SMA8("SMA-8", "SMA-8", Sito.S_E8_R10),

    //ГОСТ 9128
    //Верхние слои
    //Непрерывный зерновой состав
    V_TIP_A_NS("Тип-А-НС верхний слой", "Тип-А", Sito.S_E_16_R20),
    V_TIP_B_NS("Тип-Б-НС верхний слой", "Тип-Б", Sito.S_E_16_R20),
    V_TIP_V_NS("Тип-В-НС верхний слой", "Тип-В", Sito.S_E_16_R20),
    V_TIP_G_NS("Тип-Г-НС верхний слой", "Тип-Г", Sito.S_E8_R10),
    V_TIP_D_NS("Тип-Д-НС верхний слой", "Тип-Д", Sito.S_E8_R10),
    //Прерывистый зерновой состав
    V_TIP_A_PS("Тип-А-ПС верхний слой", "Тип-А", Sito.S_E_16_R20),
    V_TIP_B_PS("Тип-Б-ПС верхний слой", "Тип-Б", Sito.S_E_16_R20),

    //Нижние слои
    //Непрерывный зерновой состав
    N_TIP_A_NS("Тип-А-НС нижний слой", "Тип-А", Sito.S_E_31_5_R40),
    N_TIP_B_NS("Тип-Б-НС нижний слой", "Тип-Б", Sito.S_E_31_5_R40),
    //Прерывистый зерновой состав
    N_TIP_A_PS("Тип-А-ПС нижний слой", "Тип-А", Sito.S_E_31_5_R40),
    N_TIP_B_PS("Тип-Б-ПС нижний слой", "Тип-Б", Sito.S_E_31_5_R40),
    N_PORISTAYA("Пористая", "Пористая", Sito.S_E_31_5_R40),
    N_VYSOKO_PORISTAYA_COARSE("Высокопорист щебеночная", "Высокопористая щебеночная", Sito.S_E_31_5_R40),
    N_VYSOKO_PORISTAYA_FINE("Высокопорист песчаная", "Высокопористая песчаная", Sito.S_E4_R5);


    private String mixName;
    private String shortName;
    private Sito lastSito;

    //Список типов смесей для верхнего слоя
    public static List<MixType> getAbsMarshallUpLayerRow() {
        return Arrays.asList(A22VT, A16VT, A11VT, A22VN, A16VN, A11VN, A8VN, A16VL, A11VL, A8VL, A5VL);
    }

    //Список типов смесей для верхнего слоя только легкие условия
    public static List<MixType> getAbsMarshallUpLayerOnlyLightRow() {
        return Arrays.asList(A16VL, A11VL, A8VL, A5VL);
    }

    //Список типов смесей для нижнего слоя
    public static List<MixType> getAbsMarshallDownLayerRow() {
        return Arrays.asList(A32NT, A22NT, A16NT, A32NN, A22NN, A16NN, A11NN, A22NL, A16NL, A11NL, A8NL, A5NL);
    }

    //Список типов смесей для нижнего слоя только легкие условия
    public static List<MixType> getAbsMarshallDownLayerOnlyLightRow() {
        return Arrays.asList(A22NL, A16NL, A11NL, A8NL, A5NL);
    }

    //Список типов смесей для слоя основания
    public static List<MixType> getAbsMarshallBaseLayerRow() {
        return Arrays.asList(A32OT, A22OT, A16OT, A32ON, A22ON, A16ON, A32OL, A22OL, A16OL);
    }

    //Список типов смесей для слоя основания только легкие условия
    public static List<MixType> getAbsMarshallBaseLayerOnlyLightRow() {
        return Arrays.asList(A32OL, A22OL, A16OL);
    }

    //Список типов щебеночно-мастичных смесей
    public static List<MixType> getAbsMarshallSHMARow() {
        return Arrays.asList(SHMA22, SHMA16, SHMA11, SHMA8);
    }

    //Список типов смесей для верхнего слоя Superpave
    public static List<MixType> getAbsSuperpaveUpLayerRow() {
        return Arrays.asList(SP_22EV, SP_22TV, SP_16EV, SP_16TV, SP_16NV, SP_16LV, SP_11TV, SP_11NV, SP_11LV, SP_8NV, SP_8LV);
    }

    //Список типов смесей для нижнего слоя Superpave
    public static List<MixType> getAbsSuperpaveDownLayerRow() {
        return Arrays.asList(SP_32EN, SP_32TN, SP_32NN, SP_32LN, SP_22EN, SP_22TN, SP_22NN, SP_22LN, SP_16TN, SP_16NN, SP_16LN);
    }

    //Список типов смесей для слоя основания Superpave
    public static List<MixType> getAbsSuperpaveBaseLayerRow() {
        return Arrays.asList(SP_32EO, SP_32TO, SP_32TN, SP_22TO, SP_22NO, SP_22LO);
    }

    //Список типов смесей для тротуара Superpave
    public static List<MixType> getAbsSuperpaveTrotuarLayerRow() {
        return Arrays.asList(SP_8LT, SP_4LT);
    }

    //Список типов щебеночно-мастичных асфальтобетонных смесей Superpave
    public static List<MixType> getAbsSuperpaveSMALayerRow() {
        return Arrays.asList(SMA22, SMA16, SMA11, SMA8);
    }

    //Список типов АБС для верхнего слоя с непрерывным зерновым составом
    public static List<MixType> getAbs9128UpLayerNepreryvRow() {
        return Arrays.asList(V_TIP_A_NS, V_TIP_B_NS, V_TIP_V_NS, V_TIP_G_NS, V_TIP_D_NS);
    }

    //Список типов АБС для верхнего слоя с прерывистым зерновым составом
    public static List<MixType> getAbs9128UpLayerPreryvRow() {
        return Arrays.asList(V_TIP_A_PS, V_TIP_B_PS);
    }

    //Список типов АБС для нижнего слоя с непрерывным зерновым составом
    public static List<MixType> getAbs9128DownLayerNepreryvRow() {
        return Arrays.asList(N_TIP_A_NS, N_TIP_B_NS);
    }

    //Список типов АБС для нижнего слоя с прерывистым зерновым составом
    public static List<MixType> getAbs9128DownLayerPreryvRow() {
        return Arrays.asList(N_TIP_A_PS, N_TIP_B_PS, N_PORISTAYA, N_VYSOKO_PORISTAYA_COARSE, N_VYSOKO_PORISTAYA_FINE);
    }


}
