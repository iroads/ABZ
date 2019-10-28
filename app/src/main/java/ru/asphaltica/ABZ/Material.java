package ru.asphaltica.ABZ;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Material implements Serializable {

    String name;

    double [] CHOG = new double[12];
    double[] CHOP = new double[CHOG.length];
    double[] FullPr = new double[CHOG.length];
    double [] FullOst = new double[CHOG.length];


    Map<Integer, String> Sita = new HashMap<Integer, String>();
    Map<Integer, String> SitaNames = new HashMap<Integer, String>();

    Material(){


        Sita.put(0, "DNO"); Sita.put(1, "0_071"); Sita.put(2, "0_16"); Sita.put(3, "0_315"); Sita.put(4, "0_63"); Sita.put(5, "1_25");
        Sita.put(6, "2_5"); Sita.put(7, "5"); Sita.put(8, "10"); Sita.put(9, "15"); Sita.put(10, "20"); Sita.put(11, "40");

        SitaNames.put(0, "DNO"); SitaNames.put(1, "0.071"); SitaNames.put(2, "0.16"); SitaNames.put(3, "0.315"); SitaNames.put(4, "0.63"); SitaNames.put(5, "1.25");
        SitaNames.put(6, "2.5"); SitaNames.put(7, "5"); SitaNames.put(8, "10"); SitaNames.put(9, "15"); SitaNames.put(10, "20"); SitaNames.put(11, "40");

    }

    // Массив CHOG для хранения результатов определения частных остатков на ситах
    // 0 элемент массива соотвествует остатку на дне
    //1 элемент соотвествует остатку на сите 0,071
    //2 элемент соотвествует остатку на сите 0,16
    //3 элемент соотвествует остатку на сите 0,315
    //4 элемент соотвествует остатку на сите 0,63
    //5 элемент соотвествует остатку на сите 1,25
    //6 элемент соотвествует остатку на сите 2,5
    //7 элемент соотвествует остатку на сите 5
    //8 элемент соотвествует остатку на сите 10
    //9 элемент соотвествует остатку на сите 15
    //10 элемент соотвествует остатку на сите 20
    //11 элемент соотвествует остатку на сите 40




    public void CHOP() { //Здесь рассчитываем частные остатки в %
        double summa = 0;
        for (int i=0; i<this.CHOG.length; i++) {
            summa = summa + this.CHOG[i];
        }
        if (summa!=0) {
            for (int i=0; i<this.CHOG.length; i++) {
                this.CHOP[i] = (this.CHOG[i]*100)/summa;
            }
        }
    }

    public void FullPr() { //Здесь рассчитываем полные проходы в %
        double summa = 0;
        for (int j = 0; j<this.CHOG.length; j++) {
            summa = 0;
            for (int i = 0; i<j; i++) {
                summa = summa +  this.CHOP[i];
            }
            this.FullPr[j] = summa;
        }
    }

    public void FullOst() { //Здесь рассчитываем полные остатки в %
        for (int i=0; i<this.CHOG.length; i++) {
            this.FullOst[i] =(100 - this.FullPr[i]);
        }
    }

    public double[] FullPrDepenceOfSoderz(double SoderzhanieComponenta) { //Здесь пересчитываем полные проходы с учетом % содержания компонета
        DecimalFormat df = new DecimalFormat("#.##");

        double[] FullPrDep = new double[CHOG.length];

        System.out.print("полн проход сод.  ");
        for (int i = 0; i<CHOG.length; i++) {
            FullPrDep[i] = (FullPr[i]*SoderzhanieComponenta)/100;
            System.out.print(df.format(FullPrDep[i])+"  ");
        }
        System.out.println();

        return FullPrDep;

    }

}
