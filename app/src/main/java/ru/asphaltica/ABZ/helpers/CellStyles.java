package ru.asphaltica.ABZ.helpers;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import androidx.core.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CellStyles {

    public static void applyBaseStyle(TextView tv) {

        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        LinearLayout.LayoutParams params =

                new LinearLayout.LayoutParams(

                        0,

                        70,

                        1f

                );

        params.setMargins(2,2,2,2);


        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
    }

    public static void applyStyleForMixType(TextView tv) {

        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        tv.setTextColor(Color.BLACK);

        LinearLayout.LayoutParams params =

                new LinearLayout.LayoutParams(

                        0,

                        ViewGroup.LayoutParams.MATCH_PARENT,

                        1f

                );

        params.setMargins(3,3,3,3);



        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
    }

    public static void applyDesignBlockStyle(TextView tv) {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(

                        0,

                        140,

                        1f

                );
        params.setMargins(2,2,2,2);
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
    }



    public static void applyInfoRowOfGrainTable(TextView tv){
        applyBaseStyle(tv);
        tv.setGravity(Gravity.CENTER_VERTICAL);
    }

}
