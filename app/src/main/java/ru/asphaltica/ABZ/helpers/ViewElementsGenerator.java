package ru.asphaltica.ABZ.helpers;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.asphaltica.ABZ.R;

public class ViewElementsGenerator {

    private ViewElementsGenerator() {
    }

    //LinearLayout для строки
    public static LinearLayout createLinearLayoutForRow(Context context) {
        LinearLayout linearLayoutForRow = new LinearLayout(context);
        linearLayoutForRow.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayoutForRow.setOrientation(LinearLayout.HORIZONTAL);
        return linearLayoutForRow;
    }

    public static TextView createTextView(Context context, String text) {
        TextView tv = new TextView(context);
        tv.setText(text);
        CellStyles.applyBaseStyle(tv);
        return tv;
    }

    public static TextView createTextView(Context context, String text, int backGroundColor) {
        TextView tv = createTextView(context, text);
        tv.setBackgroundColor(ContextCompat.getColor(context, backGroundColor));
        return tv;
    }

    //Метод программно создает EditText
    public static EditText createEditText(Context context, String text) {
        EditText et = new EditText(context);
        et.setText(text);
        CellStyles.applyBaseStyle(et);
        return et;
    }

    public static TextView createTextViewForDesignBlock(Context context, String text, int backGroundColor) {
        TextView tv = new TextView(context);
        tv.setText(text);
        CellStyles.applyDesignBlockStyle(tv);
        tv.setBackgroundColor(ContextCompat.getColor(context, backGroundColor));
        return tv;
    }

    public static Button createDesignButton(Context context, String text, int backGroundColor) {
        Button button = new Button(context);

        //Сбрасываем ограничения
        button.setMinHeight(0);
        button.setMinimumHeight(0);
        button.setMinWidth(0);
        button.setMinimumWidth(0);
        button.setPadding(0, 0, 0, 0);

        button.setText(text);
        CellStyles.applyDesignBlockStyle(button);
        button.setBackgroundColor(ContextCompat.getColor(context, backGroundColor));
        return button;
    }

    //LinearLayout для блоков дозировки
    public static LinearLayout createLinearLayoutForPercentageRow(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        //linearLayout.setPadding(6,6,6,6);
        return linearLayout;
    }

    public static TextView createHeaderPercentageTextView(Context context, String text, int height, int backGroundColor){
        TextView tv = createTextView(context, text, backGroundColor);
//        LinearLayout.LayoutParams descriptionParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, height, 1f);
        LinearLayout.LayoutParams headerParams =
                new LinearLayout.LayoutParams(0,
                        height, 3f);
        headerParams.gravity = Gravity.CENTER_VERTICAL;

        headerParams.setMargins(3, 3, 3, 3);
        tv.setLayoutParams(headerParams);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    public static TextView createPercentageTextView(Context context, String text, int height, int backGroundColor){
        TextView tv = createTextView(context, text, backGroundColor);
        LinearLayout.LayoutParams percentageParams =
                new LinearLayout.LayoutParams(0,
                        height, 1f);

        percentageParams.gravity = Gravity.CENTER;

        percentageParams.setMargins(3, 3, 3, 3);
        tv.setLayoutParams(percentageParams);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    public static Button createPercentageButton(Context context, String text, int height, int backGroundColor){
        Button button = new Button(context);
        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(0,
                        height, 1f);

        buttonParams.gravity = Gravity.CENTER;

        buttonParams.setMargins(3, 3, 3, 3);
        button.setPadding(0, 0, 0, 0);
        button.setLayoutParams(buttonParams);
        button.setBackgroundColor(ContextCompat.getColor(context, backGroundColor));
        button.setGravity(Gravity.CENTER);
        button.setText(text);
        return button;
    }




    //
    //LinearLayout для информационной строки над каждым блоком зернового состава
    public static LinearLayout createLinearLayoutForInfoRow(Context context) {
        LinearLayout linearLayoutForInfoRow = new LinearLayout(context);
        linearLayoutForInfoRow.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutForInfoRow.setGravity(Gravity.CENTER_VERTICAL);
        linearLayoutForInfoRow.setPadding(12,6,12,6);
        return linearLayoutForInfoRow;
    }

    public static TextView createDescriptionTextView(Context context, String text, int height){
        TextView tv = createTextView(context, text);
        LinearLayout.LayoutParams descriptionParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, height, 1f);
        descriptionParams.setMargins(4, 0, 8, 0);
        tv.setLayoutParams(descriptionParams);
        return tv;
    }

    public static EditText createBunkerNameEditText(Context context, String text, int height){
        EditText et = createEditText(context, text);
        LinearLayout.LayoutParams bunkerParams =
                new LinearLayout.LayoutParams(300, height, 1.5f);
        bunkerParams.setMargins(4, 0, 12, 0);
        et.setLayoutParams(bunkerParams);
        return et;
    }

    public static TextView createLabelTextView(Context context, String text, int height){
        TextView tv = createTextView(context, text);
        LinearLayout.LayoutParams labelParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        height
                );
        tv.setLayoutParams(labelParams);
        return tv;
    }

    public static TextView createSummaTextView(Context context, String text, int height){
        TextView tv = createTextView(context, text);
        LinearLayout.LayoutParams summaParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, height, 1f);
        summaParams.setMargins(8, 0, 4, 0);
        tv.setLayoutParams(summaParams);
        return tv;
    }

    public static TextView createResultTableTextView(Context context, String text, int height, int backGroundColor){
        TextView tv = createTextView(context, text, backGroundColor);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(0,
                        height, 1f);

        params.gravity = Gravity.CENTER;

        params.setMargins(3, 3, 3, 3);
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    public static TextView createTextViewForMixType(Context context, String text) {
        TextView tv = new TextView(context);
        int paddingPx = dpToPx(context, 8);
        tv.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);
        tv.setText(text);
        CellStyles.applyStyleForMixType(tv);
        tv.setBackgroundResource(R.color.FirstMaterialTable);
        return tv;
    }

    // Вспомогательный метод для перевода dp в px
    private static int dpToPx(Context context, int dp) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }





}
