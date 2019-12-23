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


    //Массив для автоматического расчета дозировок

    Double[] SOD = new Double[8];

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

    public void CalculateCustom(Double SOD1, Double SOD2, Double SOD3) {

        Double Summa = SOD1 + SOD2 + SOD3;// + SOD4 + SOD5 + SOD6 + SODMP + SODSZ;

        for (int i = 0; i < 11; i++) {

            this.PP1R[i] = (SOD1 / 100) * this.PP1[i];
            this.PP2R[i] = (SOD2 / 100) * this.PP2[i];
            this.PP3R[i] = (SOD3 / 100) * this.PP3[i];
            // this.PP4R[i] = (SOD4 / 100) * this.PP4[i];
            //  this.PP5R[i] = (SOD5 / 100) * this.PP5[i];
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

            PP_Result[i] = PP1R[i] + PP2R[i] + PP3R[i];// +PP4R[i] +PP5R[i] +PP6R[i] +PPMPR[i] +PPSZR[i];

        }
    }

    public void AutoPodbor() {

        double S1;

        double S2;

        double S3;


        Koridor = 0;

        for (int z = 1; z < 5; z++) {

            if (Koridor > 0) break;

            for (int n = 0; n < 11; n++) {
                PP_TargetPlus[n] = PP_Target[n] + z;
                PP_TargetMinus[n] = PP_Target[n] - z;
            }

            for (int a = 0; a <= 100; a++) {

                S1 = a;

                for (int i = 0; i <= 100; i++) {

                    S2 = i;

                    for (int j = 0; j <= 100; j++) {

                        S3 = j;

                        if (a + i + j == 100) {
                            CalculateCustom((double) a, (double) i, (double) j);

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

                                Koridor = z;


                            }
                        }


                    }
                }
            }
        }


    }


}


