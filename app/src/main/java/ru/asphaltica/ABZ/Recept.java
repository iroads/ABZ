package ru.asphaltica.ABZ;

public class Recept {

    // Весова дозировка каждого из ингридиентов в %
    Double SOD1;
    Double SOD2;
    Double SOD3;
    Double SOD4;
    Double SOD5;
    Double SOD6;
    Double SODMP;
    Double SODSZ;

    Double SOD_Summa;

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

    Double [] PP_Result = new Double[11];

    public void Calculate() {

        SOD_Summa = SOD1 + SOD2 + SOD3 + SOD4 + SOD5 + SOD6 + SODMP + SODSZ;

        for (int i = 0; i < 11; i++) {

            this.PP1R[i] = (this.SOD1 / 100) * this.PP1[i];
            this.PP2R[i] = (this.SOD2 / 100) * this.PP2[i];
            this.PP3R[i] = (this.SOD3 / 100) * this.PP3[i];
            this.PP4R[i] = (this.SOD4 / 100) * this.PP4[i];
            this.PP5R[i] = (this.SOD5 / 100) * this.PP5[i];
            this.PP6R[i] = (this.SOD6 / 100) * this.PP6[i];
        }

        for (int i = 5; i < 11; i++) {
            this.PPMP[i] = 100.00;
            this.PPSZ[i] = 100.00;
        }

        for (int i = 0; i < 11; i++) {
            this.PPMPR[i] = (this.SODMP / 100) * this.PPMP[i];
            this.PPSZR[i] = (this.SODSZ / 100) * this.PPSZ[i];
        }

        for (int i = 0; i < 11; i++) {

            PP_Result[i] = PP1R[i] +PP2R[i] +PP3R[i] +PP4R[i] +PP5R[i] +PP6R[i] +PPMPR[i] +PPSZR[i];

        }
    }

}
