package ru.asphaltica.ABZ;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import ru.asphaltica.ABZ.enumerated.ActivityName;

public class SimpleKeyboard extends AppCompatActivity implements View.OnClickListener {
    Button KeyBoard0;
    Button KeyBoard1;
    Button KeyBoard2;
    Button KeyBoard3;
    Button KeyBoard4;
    Button KeyBoard5;
    Button KeyBoard6;
    Button KeyBoard7;
    Button KeyBoard8;
    Button KeyBoard9;

    Button KeyBoardBackSpace;
    Button KeyBoardClear;
    Button KeyBoardEndEdit;

    Button KeyBoardDot;
    Button KeyBoardPlus;

    TextView KeyBoardValue;
    TextView KeyBoardTotalValue;

    String value;
    String totalValue;
    Serializable tag;

    //int universalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_keyboard);

        Bundle arguments = getIntent().getExtras();

//        Intent intent = new Intent(this, SimpleKeyboard.class);
//        intent.putExtra("RESULT_TABLE_ROW_NAME", rowName);
//        intent.putExtra("SITO", sito);
//        intent.putExtra("OLD_VALUE", oldValue);
//        startActivityForResult(intent, 1);

        if (arguments != null) {
            value = arguments.getString("OLD_VALUE");
            tag = arguments.getSerializable("TAG");
        }


        ViewInit();

        KeyBoard0.setOnClickListener(this);
        KeyBoard1.setOnClickListener(this);
        KeyBoard2.setOnClickListener(this);
        KeyBoard3.setOnClickListener(this);
        KeyBoard4.setOnClickListener(this);
        KeyBoard5.setOnClickListener(this);
        KeyBoard6.setOnClickListener(this);
        KeyBoard7.setOnClickListener(this);
        KeyBoard8.setOnClickListener(this);
        KeyBoard9.setOnClickListener(this);

        KeyBoardBackSpace.setOnClickListener(this);
        KeyBoardClear.setOnClickListener(this);
        KeyBoardEndEdit.setOnClickListener(this);

        KeyBoardDot.setOnClickListener(this);
        KeyBoardPlus.setOnClickListener(this);

        Probezhka();

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ExitActions();
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.KeyBoard0 ||
                id == R.id.KeyBoard1 ||
                id == R.id.KeyBoard2 ||
                id == R.id.KeyBoard3 ||
                id == R.id.KeyBoard4 ||
                id == R.id.KeyBoard5 ||
                id == R.id.KeyBoard6 ||
                id == R.id.KeyBoard7 ||
                id == R.id.KeyBoard8 ||
                id == R.id.KeyBoard9) {

            String digit;

            if (id == R.id.KeyBoard0) {
                digit = "0";
            } else if (id == R.id.KeyBoard1) {
                digit = "1";
            } else if (id == R.id.KeyBoard2) {
                digit = "2";
            } else if (id == R.id.KeyBoard3) {
                digit = "3";
            } else if (id == R.id.KeyBoard4) {
                digit = "4";
            } else if (id == R.id.KeyBoard5) {
                digit = "5";
            } else if (id == R.id.KeyBoard6) {
                digit = "6";
            } else if (id == R.id.KeyBoard7) {
                digit = "7";
            } else if (id == R.id.KeyBoard8) {
                digit = "8";
            } else {
                digit = "9";
            }

            if (value.equals("0")) {
                value = digit;
            } else if (value.endsWith("0")) {
                if (!value.substring(0, value.length() - 1).endsWith("+")) {
                    value = value + digit;
                }
            } else {
                value = value + digit;
            }

            Probezhka();

        } else if (id == R.id.KeyBoardDot) {

            if (!StringTestDot(value)) {
                value = value + "";
            } else if (value.length() >= 1 &&
                    !value.substring(value.length() - 1).equals(".")) {

                if (value.substring(value.length() - 1).equals("+")) {
                    value = value + "0.";
                } else {
                    value = value + ".";
                }
            }

            Probezhka();

        } else if (id == R.id.KeyBoardPlus) {

            if (value.length() > 1 &&
                    value.substring(value.length() - 1).equals("+")) {

                value = value + "";

            } else if (value.length() > 1 &&
                    value.substring(value.length() - 1).equals(".")) {

                value = value.substring(0, value.length() - 1) + "+";

            } else {

                value = value + "+";
            }

            Probezhka();

        } else if (id == R.id.KeyBoardBackSpace) {

            if (value.length() == 1) {
                value = "0";
            } else if (value.length() > 1) {
                value = value.substring(0, value.length() - 1);
            }

            Probezhka();

        } else if (id == R.id.KeyBoardClear) {

            value = "0";
            Probezhka();

        } else if (id == R.id.KeyBoardEndEdit) {

            ExitActions();
        }
    }


    public void ViewInit() {

        KeyBoard0 = (Button) findViewById(R.id.KeyBoard0);
        KeyBoard1 = (Button) findViewById(R.id.KeyBoard1);
        KeyBoard2 = (Button) findViewById(R.id.KeyBoard2);
        KeyBoard3 = (Button) findViewById(R.id.KeyBoard3);
        KeyBoard4 = (Button) findViewById(R.id.KeyBoard4);
        KeyBoard5 = (Button) findViewById(R.id.KeyBoard5);
        KeyBoard6 = (Button) findViewById(R.id.KeyBoard6);
        KeyBoard7 = (Button) findViewById(R.id.KeyBoard7);
        KeyBoard8 = (Button) findViewById(R.id.KeyBoard8);
        KeyBoard9 = (Button) findViewById(R.id.KeyBoard9);

        KeyBoardBackSpace = (Button) findViewById(R.id.KeyBoardBackSpace);
        KeyBoardClear = (Button) findViewById(R.id.KeyBoardClear);
        KeyBoardEndEdit = (Button) findViewById(R.id.KeyBoardEndEdit);

        KeyBoardDot = (Button) findViewById(R.id.KeyBoardDot);
        KeyBoardPlus = (Button) findViewById(R.id.KeyBoardPlus);

        KeyBoardValue = (TextView) findViewById(R.id.KeyBoardValue);
        KeyBoardTotalValue = (TextView) findViewById(R.id.KeyBoardTotalValue);

    }

    public void Probezhka() {


        String Frag = "";
        double Summa = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (String.valueOf(c).equals("+") == false) Frag = Frag + String.valueOf(c);
            else {
                Summa = Summa + Double.parseDouble(Frag);
                Frag = "";
            }
        }

        if (value.endsWith("+") == false) Summa = Summa + Double.parseDouble(Frag);

        // ЭЛЕМЕНТ ВЫСШЕГО ПИЛОТАЖА - ДЕЛАЕМ ЧТО ПРИ форматировании числа в строку вместо 2,56 было 2.56
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
//        if (universalID == 101 || universalID == 102 || universalID == 103 || universalID == 104 || universalID == 105) {
//            String pattern = "##0.00";
//            DecimalFormat df = new DecimalFormat(pattern, otherSymbols);
//            totalValue = df.format(Summa);
//        } else {
            String pattern = "##0.0";
            DecimalFormat df = new DecimalFormat(pattern, otherSymbols);
            totalValue = df.format(Summa);
//        }


        if (value.length() <= 10) KeyBoardValue.setTextSize(50);
        if (value.length() > 10) KeyBoardValue.setTextSize(25);

        if (totalValue.length() <= 8) KeyBoardTotalValue.setTextSize(50);
        if (totalValue.length() > 8) KeyBoardTotalValue.setTextSize(25);

        KeyBoardTotalValue.setText(totalValue);
        KeyBoardValue.setText(value);

    }

    boolean StringTestDot(String value) {

        boolean result = true;

        int Dot = value.lastIndexOf(".");
        int Plus = value.lastIndexOf("+");

        if (value.endsWith("+") == false && value.endsWith(".") == false) //Если строка не заканчивается на плюс и точку
            //есть ли они в строке, если нет значит точку ставить можно, также можно ставить если плюсы вообще есть и нет точек если есть проверяем дальше
            if (Dot == -1 && Plus == -1 || Dot == -1 && Plus > -1) result = true;
            else if (Dot > Plus)
                result = false; //проверяем если последняя точка встретилась позже плюса, значит ее ставить нельзя

        return result;
    }

    public void ExitActions() {

        Intent intent = new Intent();
        intent.putExtra("NEW_VALUE", totalValue);
        intent.putExtra("ACTIVITY_NAME", ActivityName.SIMPLE_KEYBOARD);
        intent.putExtra("TAG", tag);
        setResult(RESULT_OK, intent);
        finish();

    }



}
