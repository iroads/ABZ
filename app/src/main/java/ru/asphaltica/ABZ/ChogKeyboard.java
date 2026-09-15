package ru.asphaltica.ABZ;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.asphaltica.ABZ.enumerated.ActivityName;
import ru.asphaltica.ABZ.enumerated.GrainTable;
import ru.asphaltica.ABZ.enumerated.Sito;
import ru.asphaltica.ABZ.model.Grain;

//Новый класс клавиатуры для ввода частных остатков в граммах

public class ChogKeyboard extends AppCompatActivity implements View.OnClickListener {

    Button keyBoard0;
    Button keyBoard1;
    Button keyBoard2;
    Button keyBoard3;
    Button keyBoard4;
    Button keyBoard5;
    Button keyBoard6;
    Button keyBoard7;
    Button keyBoard8;
    Button keyBoard9;

    Button keyBoardBackSpace;
    Button keyBoardClear;
    Button keyBoardEndEdit;

    Button keyBoardLeftValue;
    Button keyBoardRightValue;
    Button keyBoardPlus;

    TextView keyBoardOtsek;
    TextView keyBoardSito;
    TextView keyBoardValue;
    TextView keyBoardTotalValue;

    String value;
    String totalValue;

    GrainTable grainTableName;
    Sito sito;
    Grain grain;
    //Конкретный перечень сит, может зависесть от типа смеси
    ArrayList<Sito> sitos; //При объявлении используем конкретный класс так как это лучше при сериализации
    int currentSitoIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            grainTableName = (GrainTable) arguments.getSerializable("GRAIN_TABLE_NAME");
            sito = (Sito) arguments.getSerializable("SITO");
            grain = (Grain) arguments.getSerializable("TRANSFER_GRAIN");
            sitos = (ArrayList<Sito>) arguments.getSerializable("SITO_LIST");
        }


        currentSitoIndex = sitos.indexOf(sito);

        ViewInit();
        keyBoard0.setOnClickListener(this);
        keyBoard1.setOnClickListener(this);
        keyBoard2.setOnClickListener(this);
        keyBoard3.setOnClickListener(this);
        keyBoard4.setOnClickListener(this);
        keyBoard5.setOnClickListener(this);
        keyBoard6.setOnClickListener(this);
        keyBoard7.setOnClickListener(this);
        keyBoard8.setOnClickListener(this);
        keyBoard9.setOnClickListener(this);

        keyBoardBackSpace.setOnClickListener(this);
        keyBoardClear.setOnClickListener(this);
        keyBoardEndEdit.setOnClickListener(this);

        keyBoardLeftValue.setOnClickListener(this);
        keyBoardRightValue.setOnClickListener(this);
        keyBoardPlus.setOnClickListener(this);


        keyBoardOtsek.setText(grainTableName.getDescription());


        keyBoardSito.setText(sito.getEuroSize());

//
       // int yourScale0 = 0;
        //value = BigDecimal.valueOf(grain.getChog().get(sitos(currentSitoIndex))).setScale(yourScale0, BigDecimal.ROUND_HALF_UP).toString();
        value = grain.getChog().get(sitos.get(currentSitoIndex)).toString();
        keyBoardValue.setText(value);



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

        } else if (id == R.id.KeyBoardLeftValue) {

            if (currentSitoIndex != 0) {

                grain.getChog().put(
                        sitos.get(currentSitoIndex),
                        Double.parseDouble(GetTotalValue(value))
                );

                currentSitoIndex--;

                keyBoardSito.setText(
                        sitos.get(currentSitoIndex).getEuroSize()
                );
            }

            value = grain.getChog()
                    .get(sitos.get(currentSitoIndex))
                    .toString();

            keyBoardValue.setText(value);

            Probezhka();

        } else if (id == R.id.KeyBoardRightValue) {

            if (currentSitoIndex != sitos.size() - 1) {

                grain.getChog().put(
                        sitos.get(currentSitoIndex),
                        Double.parseDouble(GetTotalValue(value))
                );

                currentSitoIndex++;

                keyBoardSito.setText(
                        sitos.get(currentSitoIndex).getEuroSize()
                );
            }

            value = grain.getChog()
                    .get(sitos.get(currentSitoIndex))
                    .toString();

            keyBoardValue.setText(value);

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
        }
    }

    public void ViewInit() {

        keyBoard0 = (Button) findViewById(R.id.KeyBoard0);
        keyBoard1 = (Button) findViewById(R.id.KeyBoard1);
        keyBoard2 = (Button) findViewById(R.id.KeyBoard2);
        keyBoard3 = (Button) findViewById(R.id.KeyBoard3);
        keyBoard4 = (Button) findViewById(R.id.KeyBoard4);
        keyBoard5 = (Button) findViewById(R.id.KeyBoard5);
        keyBoard6 = (Button) findViewById(R.id.KeyBoard6);
        keyBoard7 = (Button) findViewById(R.id.KeyBoard7);
        keyBoard8 = (Button) findViewById(R.id.KeyBoard8);
        keyBoard9 = (Button) findViewById(R.id.KeyBoard9);

        keyBoardBackSpace = (Button) findViewById(R.id.KeyBoardBackSpace);
        keyBoardClear = (Button) findViewById(R.id.KeyBoardClear);
        keyBoardEndEdit = (Button) findViewById(R.id.KeyBoardEndEdit);

        keyBoardLeftValue = (Button) findViewById(R.id.KeyBoardLeftValue);
        keyBoardRightValue = (Button) findViewById(R.id.KeyBoardRightValue);
        keyBoardPlus = (Button) findViewById(R.id.KeyBoardPlus);

        keyBoardOtsek = (TextView) findViewById(R.id.KeyBoardOtsek);
        keyBoardSito = (TextView) findViewById(R.id.KeyBoardSito);
        keyBoardValue = (TextView) findViewById(R.id.KeyBoardValue);
        keyBoardTotalValue = (TextView) findViewById(R.id.KeyBoardTotalValue);

    }

    public void Probezhka() {

        totalValue = GetTotalValue(value);

        if (value.length() <= 10) keyBoardValue.setTextSize(50);
        if (value.length() > 10) keyBoardValue.setTextSize(25);

        if (totalValue.length() <= 8) keyBoardTotalValue.setTextSize(50);
        if (totalValue.length() > 8) keyBoardTotalValue.setTextSize(25);

        keyBoardTotalValue.setText(totalValue);
        keyBoardValue.setText(value);

    }


    boolean StringTestDot(String Value) {

        boolean Result = true;

        int Dot = Value.lastIndexOf(".");
        int Plus = Value.lastIndexOf("+");

        if (Value.endsWith("+") == false && Value.endsWith(".") == false) //Если строка не заканчивается на плюс и точку
            //есть ли они в строке, если нет значит точку ставить можно, также можно ставить если плюсы вообще есть и нет точек если есть проверяем дальше
            if (Dot == -1 && Plus == -1 || Dot == -1 && Plus > -1) Result = true;
            else if (Dot > Plus)
                Result = false; //проверяем если последняя точка встретилась позже плюса, значит ее ставить нельзя

        return Result;
    }

    String GetTotalValue(String Value) {

        String Frag = "";
        String Total;
        double Summa = 0;
        for (int i = 0; i < Value.length(); i++) {
            char c = Value.charAt(i);
            if (String.valueOf(c).equals("+") == false) Frag = Frag + String.valueOf(c);
            else {
                Summa = Summa + Double.parseDouble(Frag);
                Frag = "";
            }
        }

        if (Value.endsWith("+") == false) Summa = Summa + Double.parseDouble(Frag);

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        String pattern = "##0.0";
        DecimalFormat df = new DecimalFormat(pattern, otherSymbols);
        Total = df.format(Summa);

        return Total;
    }

    public void ExitActions() {

        Intent intent = new Intent();
        grain.getChog().put(sitos.get(currentSitoIndex), Double.parseDouble(GetTotalValue(value)));
        intent.putExtra("OBJECT_BACK", grain);
        intent.putExtra("GRAIN_TABLE_NAME", grainTableName);
        intent.putExtra("ACTIVITY_NAME", ActivityName.CHOG_KEYBOARD);

//        TransMaterial.CHOG[ChogID] = Double.parseDouble(TotalValue);
//        intent.putExtra("OBJECT_BACK", TransMaterial);
//        intent.putExtra("BUNKERID_BACK", BunkerID);
        setResult(RESULT_OK, intent);
        finish();

    }





}
