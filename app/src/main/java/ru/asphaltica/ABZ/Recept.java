package ru.asphaltica.ABZ;

import java.io.Serializable;
import java.util.zip.CheckedInputStream;

public class Recept implements Serializable {

    // Весова дозировка каждого из ингридиентов в %
    Double SOD1;
    Double SOD2;
    Double SOD3;
    Double SOD4;
    Double SOD5;
    Double SOD6;
    Double SODMP;
    Double SODSZ;

    String NameOfMaterial1;
    String NameOfMaterial2;
    String NameOfMaterial3;
    String NameOfMaterial4;
    String NameOfMaterial5;
    String NameOfMaterial6;


    Double SOD_Summa;

    int Koridor = 0; //переменная в которой хранится ширина коридора прохождения кривой при автоподборе

    // Зерновой состав каждого из ингридиентов в виде полных проходов
    Double[] PP1 = new Double[11];
    Double[] PP2 = new Double[11];
    Double[] PP3 = new Double[11];
    Double[] PP4 = new Double[11];
    Double[] PP5 = new Double[11];
    Double[] PP6 = new Double[11];
    Double[] PPMP = new Double[11];
    Double[] PPSZ = new Double[11];

    // Трасформированные зерновые составы ингидиентов с учетом их весовой дозировки в %
    Double[] PP1R = new Double[11];
    Double[] PP2R = new Double[11];
    Double[] PP3R = new Double[11];
    Double[] PP4R = new Double[11];
    Double[] PP5R = new Double[11];
    Double[] PP6R = new Double[11];
    Double[] PPMPR = new Double[11];
    Double[] PPSZR = new Double[11];

    // Сумарные полные проходы по всем ситам

    Double[] PP_Result = new Double[11];

    // Целевой зерновой состав

    Double[] PP_Target = new Double[11];//{10.0, 13.0, 16.0, 21.0, 28.0, 41.0, 54.0, 66.0, 82.0, 97.0, 100.0};

    Double[] PP_TargetPlus = new Double[11];
    Double[] PP_TargetMinus = new Double[11];


    int NumberOfMix;

    String NameOfMix;

    String[] MixUp = new String[]{"10", "12", "16", "20", "28", "38", "50", "100", "100", "100", "100"};
    String[] MixDown = new String[]{"4", "6", "10", "14", "20", "28", "40", "62", "75", "90", "100"};


    String[] SitaRF = new String[]{"0,071", "0,16", "0,315", "0,63", "1,25", "2,5", "5", "10", "15", "20", "40"};

    String[] SitaUSA = new String[]{"0,075", "0,15", "0,3", "0,6", "1,18", "2,36", "4,75", "9,5", "12,5", "19", "25"};

    String[] SitaEURO = new String[]{"0,063", "0,125", "0,25", "0,5", "1,0", "2", "4", "8", "11,2", "16", "22,4"};

    String[] Sita = SitaRF;


    public void Calculate() {

        SOD_Summa = SOD1 + SOD2 + SOD3 + SOD4 + SOD5 + SOD6 + SODMP + SODSZ;

        for (int i = 0; i < 11; i++) {

            this.PP1R[i] = (this.SOD1 / 100) * this.PP1[i];
            this.PP2R[i] = (this.SOD2 / 100) * this.PP2[i];
            this.PP3R[i] = (this.SOD3 / 100) * this.PP3[i];
            this.PP4R[i] = (this.SOD4 / 100) * this.PP4[i];
            this.PP5R[i] = (this.SOD5 / 100) * this.PP5[i];
            this.PP6R[i] = (this.SOD6 / 100) * this.PP6[i];
            this.PPMPR[i] = (this.SODMP / 100) * this.PPMP[i];
            this.PPSZR[i] = (this.SODSZ / 100) * this.PPSZ[i];

        }
        for (int i = 0; i < 11; i++) {
            PP_Result[i] = PP1R[i] + PP2R[i] + PP3R[i] + PP4R[i] + PP5R[i] + PP6R[i] + PPMPR[i] + PPSZR[i];
        }
    }

    public void CalculateCustom(Double SOD1, Double SOD2, Double SOD3, Double SOD4, Double SOD5) {

        Double Summa = SOD1 + SOD2 + SOD3 + SOD4 + SOD5;// + SOD6 + SODMP + SODSZ;

        for (int i = 0; i < 11; i++) {

            this.PP1R[i] = (SOD1 / 100) * this.PP1[i];
            this.PP2R[i] = (SOD2 / 100) * this.PP2[i];
            this.PP3R[i] = (SOD3 / 100) * this.PP3[i];
            this.PP4R[i] = (SOD4 / 100) * this.PP4[i];
            this.PP5R[i] = (SOD5 / 100) * this.PP5[i];
            //  this.PP6R[i] = (SOD6 / 100) * this.PP6[i];
        }

        //for (int i = 5; i < 11; i++) {
        //    this.PPMP[i] = 100.00;
        //    this.PPSZ[i] = 100.00;
        //}

        // for (int i = 0; i < 11; i++) {
        //      this.PPMPR[i] = (SODMP / 100) * this.PPMP[i];
        //       this.PPSZR[i] = (SODSZ / 100) * this.PPSZ[i];
        //    }

        for (int i = 0; i < 11; i++) {

            PP_Result[i] = PP1R[i] + PP2R[i] + PP3R[i] + PP4R[i] + PP5R[i];// +PP6R[i] +PPMPR[i] +PPSZR[i];

        }
    }

    public void AutoPodbor() {

        double S1;

        double S2;

        double S3;

        double S4;

        double S5;


        Koridor = 0;

        for (int z = 1; z < 5; z++) {

            if (Koridor > 0) break;

            for (int n = 0; n < 11; n++) {
                PP_TargetPlus[n] = PP_Target[n] + z;
                PP_TargetMinus[n] = PP_Target[n] - z;
            }


            for (int c = 0; c <= 100; c++) {

                S1 = c;
                for (int b = 0; b <= 100; b++) {

                    S2 = b;

                    for (int a = 0; a <= 100; a++) {

                        S3 = a;

                        for (int i = 0; i <= 100; i++) {

                            S4 = i;

                            for (int j = 0; j <= 100; j++) {

                                S5 = j;

                                if (b + a + i + j == 100) {
                                    CalculateCustom((double) c, (double) b, (double) a, (double) i, (double) j);

                                    int Checker = 0;

                                    for (int l = 0; l < 11; l++) {


                                        if ((PP_Result[l] > PP_TargetMinus[l]) && (PP_Result[l] < PP_TargetPlus[l])) {

                                            Checker = Checker + 1;

                                        }
                                    }
                                    if (Checker == 11) {
                                        this.SOD1 = S1;
                                        this.SOD2 = S2;
                                        this.SOD3 = S3;
                                        this.SOD4 = S4;
                                        this.SOD5 = S5;


                                        Koridor = z;


                                    }
                                }


                            }
                        }
                    }
                }
            }
        }


    }

    public void SetMix(int NumberOfMix) {

        //ГОСТ 9128-2009

        if (NumberOfMix == 1) {

            this.NameOfMix = "В Тип-А нзс";
            Sita = SitaRF;
            this.MixUp = new String[]{"10", "12", "16", "20", "28", "38", "50", "100", "100", "100", "100"};
            this.MixDown = new String[]{"4", "6", "10", "14", "20", "28", "40", "62", "75", "90", "100"};


        }

        if (NumberOfMix == 2) {

            this.NameOfMix = "В Тип-Б нзс";
            Sita = SitaRF;
            this.MixUp = new String[]{"12", "16", "22", "28", "37", "48", "60", "100", "100", "100", "100"};
            this.MixDown = new String[]{"6", "10", "14", "20", "28", "38", "50", "70", "80", "90", "100"};


        }

        if (NumberOfMix == 3) {

            this.NameOfMix = "В Тип-В нзс";
            Sita = SitaRF;
            this.MixUp = new String[]{"14", "20", "30", "40", "50", "60", "70", "100", "100", "100", "100"};
            this.MixDown = new String[]{"8", "13", "20", "28", "37", "48", "60", "75", "85", "90", "100"};


        }

        if (NumberOfMix == 4) {

            this.NameOfMix = "В Тип-Г нзс";
            Sita = SitaRF;
            this.MixUp = new String[]{"16", "25", "36", "50", "65", "82", "100", "100", "100", "100", "100"};
            this.MixDown = new String[]{"8", "15", "20", "30", "42", "56", "70", "100", "100", "100", "100"};


        }
        if (NumberOfMix == 5) {

            this.NameOfMix = "В Тип-Д нзс";
            Sita = SitaRF;
            this.MixUp = new String[]{"16", "33", "55", "75", "85", "93", "100", "100", "100", "100", "100"};
            this.MixDown = new String[]{"10", "15", "20", "30", "42", "60", "70", "100", "100", "100", "100"};


        }

        if (NumberOfMix == 6) {

            this.NameOfMix = "В Тип-А пзс";
            Sita = SitaRF;
            this.MixUp = new String[]{"10", "16", "28", "50", "50", "50", "50", "100", "100", "100", "100"};
            this.MixDown = new String[]{"4", "6", "10", "14", "20", "28", "40", "62", "75", "90", "100"};


        }

        if (NumberOfMix == 7) {

            this.NameOfMix = "В Тип-Б пзс";
            Sita = SitaRF;
            this.MixUp = new String[]{"12", "20", "34", "60", "60", "60", "60", "100", "100", "100", "100"};
            this.MixDown = new String[]{"6", "10", "14", "20", "28", "38", "50", "70", "80", "90", "100"};


        }

        if (NumberOfMix == 8) {

            this.NameOfMix = "Н Тип-А нзс";
            Sita = SitaRF;
            this.MixUp = new String[]{"10", "12", "16", "20", "28", "38", "50", "62", "70", "90", "100"};
            this.MixDown = new String[]{"4", "6", "10", "14", "20", "28", "40", "48", "56", "66", "90"};


        }

        if (NumberOfMix == 9) {

            this.NameOfMix = "Н Тип-Б нзс";
            Sita = SitaRF;
            this.MixUp = new String[]{"12", "16", "22", "28", "37", "48", "60", "72", "80", "90", "100"};
            this.MixDown = new String[]{"6", "10", "14", "20", "28", "38", "50", "60", "68", "76", "90"};


        }

        if (NumberOfMix == 10) {

            this.NameOfMix = "Н Тип-А пзс";
            Sita = SitaRF;
            this.MixUp = new String[]{"10", "16", "28", "50", "50", "50", "50", "62", "70", "90", "100"};
            this.MixDown = new String[]{"4", "6", "10", "14", "20", "28", "40", "48", "56", "66", "90"};


        }

        if (NumberOfMix == 11) {

            this.NameOfMix = "Н Тип-Б пзс";
            Sita = SitaRF;
            this.MixUp = new String[]{"12", "20", "34", "60", "60", "60", "60", "72", "80", "90", "100"};
            this.MixDown = new String[]{"6", "10", "14", "20", "28", "38", "50", "60", "68", "76", "90"};


        }

        if (NumberOfMix == 12) {

            this.NameOfMix = "ПОРИСТАЯ";
            Sita = SitaRF;
            this.MixUp = new String[]{"8", "20", "37", "60", "60", "60", "60", "88", "100", "100", "100"};
            this.MixDown = new String[]{"2", "5", "8", "10", "16", "28", "40", "52", "64", "75", "90"};


        }

        if (NumberOfMix == 13) {

            this.NameOfMix = "ВЫС.П.ЩЕБ.";
            Sita = SitaRF;
            this.MixUp = new String[]{"4", "5", "8", "10", "16", "28", "40", "52", "64", "75", "100"};
            this.MixDown = new String[]{"1", "1", "2", "3", "5", "10", "15", "22", "35", "55", "90"};


        }

        if (NumberOfMix == 14) {

            this.NameOfMix = "ВЫС.П.ПЕС.";
            Sita = SitaRF;
            this.MixUp = new String[]{"10", "45", "72", "85", "100", "100", "100", "100", "100", "100", "100"};
            this.MixDown = new String[]{"4", "10", "17", "25", "41", "64", "70", "100", "100", "100", "100"};


        }

        if (NumberOfMix == 15) {

            this.NameOfMix = "ЩМА - 10";
            Sita = SitaRF;
            this.MixUp = new String[]{"15", "17", "20", "22", "26", "29", "40", "100", "100", "100", "100"};
            this.MixDown = new String[]{"10", "10", "11", "13", "16", "19", "30", "90", "100", "100", "100"};


        }

        if (NumberOfMix == 16) {

            this.NameOfMix = "ЩМА - 15";
            Sita = SitaRF;
            this.MixUp = new String[]{"14", "16", "20", "22", "25", "28", "35", "60", "100", "100", "100"};
            this.MixDown = new String[]{"9", "9", "10", "12", "15", "18", "25", "40", "90", "100", "100"};


        }

        if (NumberOfMix == 17) {

            this.NameOfMix = "ЩМА - 20";
            Sita = SitaRF;
            this.MixUp = new String[]{"13", "15", "19", "21", "24", "25", "30", "42", "70", "100", "100"};
            this.MixDown = new String[]{"8", "8", "9", "11", "13", "15", "20", "25", "50", "90", "100"};


        }

        if (NumberOfMix == 18) {

            this.NameOfMix = "SP - 9,5";
            Sita = SitaUSA;
            this.MixUp = new String[]{"10", "-", "-", "-", "-", "67", "90", "100", "100", "100", "100"};
            this.MixDown = new String[]{"2", "-", "-", "-", "-", "32", "-", "90", "100", "100", "100"};


        }

        if (NumberOfMix == 19) {

            this.NameOfMix = "SP - 12,5";
            Sita = SitaUSA;
            this.MixUp = new String[]{"10", "-", "-", "-", "-", "58", "-", "90", "100", "100", "100"};
            this.MixDown = new String[]{"2", "-", "-", "-", "-", "28", "-", "-", "90", "100", "100"};


        }

        if (NumberOfMix == 20) {

            this.NameOfMix = "SP - 19";
            Sita = SitaUSA;
            this.MixUp = new String[]{"8", "-", "-", "-", "-", "49", "-", "-", "90", "100", "100"};
            this.MixDown = new String[]{"2", "-", "-", "-", "-", "23", "-", "-", "-", "90", "100"};


        }

        if (NumberOfMix == 21) {

            this.NameOfMix = "SP - 8";
            Sita = SitaEURO;
            this.MixUp = new String[]{"10", "-", "-", "-", "-", "66", "90", "100", "100", "100", "100"};
            this.MixDown = new String[]{"2", "-", "-", "-", "-", "31", "-", "90", "100", "100", "100"};


        }

        if (NumberOfMix == 22) {

            this.NameOfMix = "SP - 11";
            Sita = SitaEURO;
            this.MixUp = new String[]{"10", "-", "-", "-", "-", "58", "-", "90", "100", "100", "100"};
            this.MixDown = new String[]{"2", "-", "-", "-", "-", "28", "-", "-", "90", "100", "100"};


        }

        if (NumberOfMix == 23) {

            this.NameOfMix = "SP - 16";
            Sita = SitaEURO;
            this.MixUp = new String[]{"8", "-", "-", "-", "-", "48", "-", "-", "90", "100", "100"};
            this.MixDown = new String[]{"2", "-", "-", "-", "-", "22", "-", "-", "-", "90", "100"};


        }

        if (NumberOfMix == 24) {

            this.NameOfMix = "SMA - 8";
            Sita = SitaEURO;
            this.MixUp = new String[]{"12", "-", "15", "17", "20", "29", "48", "93", "100", "100", "100"};
            this.MixDown = new String[]{"8", "-", "-", "-", "-", "19", "28", "68", "100", "100", "100"};


        }

        if (NumberOfMix == 25) {

            this.NameOfMix = "SMA - 11";
            Sita = SitaEURO;
            this.MixUp = new String[]{"11", "-", "-", "-", "-", "24", "35", "80", "100", "100", "100"};
            this.MixDown = new String[]{"8", "-", "-", "-", "-", "16", "20", "50", "90", "100", "100"};


        }

        if (NumberOfMix == 26) {

            this.NameOfMix = "SMA - 16";
            Sita = SitaEURO;
            this.MixUp = new String[]{"11", "-", "-", "-", "-", "23", "27", "59", "86", "100", "100"};
            this.MixDown = new String[]{"8", "-", "-", "-", "-", "15", "19", "24", "48", "90", "100"};


        }


    }


}


