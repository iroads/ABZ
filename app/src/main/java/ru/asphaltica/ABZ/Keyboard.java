package ru.asphaltica.ABZ;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Keyboard extends AppCompatActivity implements View.OnClickListener {

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

    Button KeyBoardLeftValue;
    Button KeyBoardRightValue;
    Button KeyBoardPlus;

    TextView KeyBoardOtsek;
    TextView KeyBoardSito;
    TextView KeyBoardValue;
    TextView KeyBoardTotalValue;

    String Value;
    String TotalValue;

    Material TransMaterial;
    private Recept recept;
    int ChogID;
    int BunkerID;
    int LeftStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            TransMaterial = (Material) arguments.getSerializable("OBJECT");
            recept = (Recept) arguments.getSerializable("RECEPT");
            ChogID = arguments.getInt("CHOGID");
            BunkerID = arguments.getInt("BUNKERID");
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

        KeyBoardLeftValue.setOnClickListener(this);
        KeyBoardRightValue.setOnClickListener(this);
        KeyBoardPlus.setOnClickListener(this);


        if (BunkerID == 7) {
            KeyBoardOtsek.setText("Мин.Порошок");
            LeftStop = 5;
        } else if (BunkerID == 8) {
            KeyBoardOtsek.setText("Пыль");
            LeftStop = 5;
        } else {
            KeyBoardOtsek.setText("Отсек№" + BunkerID);
            LeftStop = 11;
        }

        if (TransMaterial.SitaNames.get(ChogID).equals("DNO"))
            KeyBoardSito.setText(TransMaterial.SitaNames.get(ChogID));
        else KeyBoardSito.setText("Сито " + recept.Sita[ChogID - 1]);

        int yourScale0 = 0;
        Value = BigDecimal.valueOf(TransMaterial.CHOG[ChogID]).setScale(yourScale0, BigDecimal.ROUND_HALF_UP).toString();
        KeyBoardValue.setText(Value);

        Probezhka();

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

            if (id == R.id.KeyBoard0) digit = "0";
            else if (id == R.id.KeyBoard1) digit = "1";
            else if (id == R.id.KeyBoard2) digit = "2";
            else if (id == R.id.KeyBoard3) digit = "3";
            else if (id == R.id.KeyBoard4) digit = "4";
            else if (id == R.id.KeyBoard5) digit = "5";
            else if (id == R.id.KeyBoard6) digit = "6";
            else if (id == R.id.KeyBoard7) digit = "7";
            else if (id == R.id.KeyBoard8) digit = "8";
            else digit = "9";

            if (Value.equals("0")) {
                Value = digit;
            } else if (Value.endsWith("0")) {
                if (!Value.substring(0, Value.length() - 1).endsWith("+")) {
                    Value = Value + digit;
                }
            } else {
                Value = Value + digit;
            }

            Probezhka();

        } else if (id == R.id.KeyBoardDot) {

            if (StringTestDot(Value) == false) {
                Value = Value + "";
            } else if (Value.length() >= 1 &&
                    !Value.substring(Value.length() - 1).equals(".")) {

                if (Value.substring(Value.length() - 1).equals("+")) {
                    Value = Value + "0.";
                } else {
                    Value = Value + ".";
                }
            }

            Probezhka();

        } else if (id == R.id.KeyBoardLeftValue) {

            if (ChogID != LeftStop) {

                int yourScale0 = 0;

                TransMaterial.CHOG[ChogID] =
                        Double.parseDouble(GetTotalValue(Value));

                ChogID = ChogID + 1;

                Value = BigDecimal
                        .valueOf(TransMaterial.CHOG[ChogID])
                        .setScale(yourScale0, BigDecimal.ROUND_HALF_UP)
                        .toString();

                KeyBoardValue.setText(Value);

                if (TransMaterial.SitaNames.get(ChogID).equals("DNO")) {
                    KeyBoardSito.setText(
                            TransMaterial.SitaNames.get(ChogID)
                    );
                } else {
                    KeyBoardSito.setText(
                            "Сито " + recept.Sita[ChogID - 1]
                    );
                }
            }

            Probezhka();

        } else if (id == R.id.KeyBoardRightValue) {

            if (ChogID != 0) {

                int yourScale0 = 0;

                TransMaterial.CHOG[ChogID] =
                        Double.parseDouble(GetTotalValue(Value));

                ChogID = ChogID - 1;

                Value = BigDecimal
                        .valueOf(TransMaterial.CHOG[ChogID])
                        .setScale(yourScale0, BigDecimal.ROUND_HALF_UP)
                        .toString();

                KeyBoardValue.setText(Value);

                if (TransMaterial.SitaNames.get(ChogID).equals("DNO")) {
                    KeyBoardSito.setText(
                            TransMaterial.SitaNames.get(ChogID)
                    );
                } else {
                    KeyBoardSito.setText(
                            "Сито " + recept.Sita[ChogID - 1]
                    );
                }
            }

            Probezhka();

        } else if (id == R.id.KeyBoardBackSpace) {

            if (Value.length() == 1) {
                Value = "0";
            } else if (Value.length() > 1) {
                Value = Value.substring(0, Value.length() - 1);
            }

            Probezhka();

        } else if (id == R.id.KeyBoardClear) {

            Value = "0";
            Probezhka();

        } else if (id == R.id.KeyBoardEndEdit) {

            ExitActions();

        } else if (id == R.id.KeyBoardPlus) {

            if (Value.length() > 1 &&
                    Value.substring(Value.length() - 1).equals("+")) {

                Value = Value + "";

            } else if (Value.length() > 1 &&
                    Value.substring(Value.length() - 1).equals(".")) {

                Value = Value.substring(0, Value.length() - 1) + "+";

            } else {

                Value = Value + "+";
            }

            Probezhka();
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

        KeyBoardLeftValue = (Button) findViewById(R.id.KeyBoardLeftValue);
        KeyBoardRightValue = (Button) findViewById(R.id.KeyBoardRightValue);
        KeyBoardPlus = (Button) findViewById(R.id.KeyBoardPlus);

        KeyBoardOtsek = (TextView) findViewById(R.id.KeyBoardOtsek);
        KeyBoardSito = (TextView) findViewById(R.id.KeyBoardSito);
        KeyBoardValue = (TextView) findViewById(R.id.KeyBoardValue);
        KeyBoardTotalValue = (TextView) findViewById(R.id.KeyBoardTotalValue);

    }

    public void Probezhka() {

        TotalValue = GetTotalValue(Value);

        if (Value.length() <= 10) KeyBoardValue.setTextSize(50);
        if (Value.length() > 10) KeyBoardValue.setTextSize(25);

        if (TotalValue.length() <= 8) KeyBoardTotalValue.setTextSize(50);
        if (TotalValue.length() > 8) KeyBoardTotalValue.setTextSize(25);

        KeyBoardTotalValue.setText(TotalValue);
        KeyBoardValue.setText(Value);

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

        TransMaterial.CHOG[ChogID] = Double.parseDouble(TotalValue);
        intent.putExtra("OBJECT_BACK", TransMaterial);
        intent.putExtra("BUNKERID_BACK", BunkerID);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void onBackPressed() {

        ExitActions();

    }
}
