package ru.asphaltica.ABZ;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class UniversalKeyboard extends AppCompatActivity implements View.OnClickListener {

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

    String Value;
    String TotalValue;

    int UniversalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_keyboard);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            // TransMaterial = (Material) arguments.getSerializable("OBJECT");
            Value = arguments.getString("Value");
            UniversalID = arguments.getInt("UniversalID");
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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.KeyBoard0: {
                if (Value.equals("0") == false)
                    if (Value.endsWith("0")) {
                        if (Value.substring(0, Value.length() - 1).endsWith("+") == false)
                            Value = Value + "0";
                    } else {
                        Value = Value + "0";
                    }
                Probezhka();
                break;
            }
            case R.id.KeyBoard1: {

                if (Value.equals("0")) Value = "1";
                else if (Value.endsWith("0")) {
                    if (Value.substring(0, Value.length() - 1).endsWith("+") == false)
                        Value = Value + "1";
                } else {
                    Value = Value + "1";
                }
                Probezhka();
                break;
            }
            case R.id.KeyBoard2: {

                if (Value.equals("0")) Value = "2";
                else if (Value.endsWith("0")) {
                    if (Value.substring(0, Value.length() - 1).endsWith("+") == false)
                        Value = Value + "2";
                } else {
                    Value = Value + "2";
                }
                Probezhka();
                break;
            }
            case R.id.KeyBoard3: {

                if (Value.equals("0")) Value = "3";
                else if (Value.endsWith("0")) {
                    if (Value.substring(0, Value.length() - 1).endsWith("+") == false)
                        Value = Value + "3";
                } else {
                    Value = Value + "3";
                }
                Probezhka();
                break;
            }
            case R.id.KeyBoard4: {

                if (Value.equals("0")) Value = "4";
                else if (Value.endsWith("0")) {
                    if (Value.substring(0, Value.length() - 1).endsWith("+") == false)
                        Value = Value + "4";
                } else {
                    Value = Value + "4";
                }
                Probezhka();
                break;
            }
            case R.id.KeyBoard5: {

                if (Value.equals("0")) Value = "5";
                else if (Value.endsWith("0")) {
                    if (Value.substring(0, Value.length() - 1).endsWith("+") == false)
                        Value = Value + "5";
                } else {
                    Value = Value + "5";
                }
                Probezhka();
                break;
            }
            case R.id.KeyBoard6: {

                if (Value.equals("0")) Value = "6";
                else if (Value.endsWith("0")) {
                    if (Value.substring(0, Value.length() - 1).endsWith("+") == false)
                        Value = Value + "6";
                } else {
                    Value = Value + "6";
                }
                Probezhka();
                break;
            }
            case R.id.KeyBoard7: {

                if (Value.equals("0")) Value = "7";
                else if (Value.endsWith("0")) {
                    if (Value.substring(0, Value.length() - 1).endsWith("+") == false)
                        Value = Value + "7";
                } else {
                    Value = Value + "7";
                }
                Probezhka();
                break;
            }
            case R.id.KeyBoard8: {

                if (Value.equals("0")) Value = "8";
                else if (Value.endsWith("0")) {
                    if (Value.substring(0, Value.length() - 1).endsWith("+") == false)
                        Value = Value + "8";
                } else {
                    Value = Value + "8";
                }
                Probezhka();
                break;
            }
            case R.id.KeyBoard9: {

                if (Value.equals("0")) Value = "9";
                else if (Value.endsWith("0")) {
                    if (Value.substring(0, Value.length() - 1).endsWith("+") == false)
                        Value = Value + "9";
                } else {
                    Value = Value + "9";
                }
                Probezhka();
                break;
            }
            case R.id.KeyBoardDot: {

                if (StringTestDot(Value) == false) Value = Value + "";
                else if (Value.length() >= 1 && Value.substring(Value.length() - 1, Value.length()).equals(".") == false)
                    if (Value.length() >= 1 && Value.substring(Value.length() - 1, Value.length()).equals("+"))
                        Value = Value + "0.";
                    else
                        Value = Value + ".";
                Probezhka();
                break;
            }
            case R.id.KeyBoardPlus: {
                //Если в набираемой строке больше чем один символ и последний символ является + (плюсом), тогда строка остается без изменения, иначе проверяем следующее условие
                if (Value.length() > 1 && Value.substring(Value.length() - 1, Value.length()).equals("+"))
                    Value = Value + "";
                else
                    //Если в набираемой строке больше чем один символ и последний символ является точкой, тогда последний символ должен быть удален, а к оставшейся строке добавляется +, иначе просто добавляем плюс
                    if (Value.length() > 1 && Value.substring(Value.length() - 1, Value.length()).equals("."))
                        Value = Value.substring(0, Value.length() - 1) + "+";
                    else Value = Value + "+";
                Probezhka();
                break;
            }

            case R.id.KeyBoardBackSpace: {
                if (Value.length() == 1) Value = "0";
                else if (Value.length() > 1) Value = Value.substring(0, Value.length() - 1);

                Probezhka();
                break;
            }
            case R.id.KeyBoardClear: {
                Value = "0";
                Probezhka();
                break;
            }
            case R.id.KeyBoardEndEdit: {
                ExitActions();
                break;
            }
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
        for (int i = 0; i < Value.length(); i++) {
            char c = Value.charAt(i);
            if (String.valueOf(c).equals("+") == false) Frag = Frag + String.valueOf(c);
            else {
                Summa = Summa + Double.parseDouble(Frag);
                Frag = "";
            }
        }

        if (Value.endsWith("+") == false) Summa = Summa + Double.parseDouble(Frag);

        // ЭЛЕМЕНТ ВЫСШЕГО ПИЛОТАЖА - ДЕЛАЕМ ЧТО ПРИ форматировании числа в строку вместо 2,56 было 2.56
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        if (UniversalID == 101 || UniversalID == 102 || UniversalID == 103 || UniversalID == 104 || UniversalID == 105) {
            String pattern = "##0.00";
            DecimalFormat df = new DecimalFormat(pattern, otherSymbols);
            TotalValue = df.format(Summa);
        } else {
            String pattern = "##0.0";
            DecimalFormat df = new DecimalFormat(pattern, otherSymbols);
            TotalValue = df.format(Summa);
        }


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

    public void ExitActions() {

        Intent intent = new Intent();
        //TransMaterial.CHOG[ChogID] = Double.parseDouble(CHOG);

        intent.putExtra("VALUE_BACK", TotalValue);
        intent.putExtra("UNIVERSALID_BACK", UniversalID);
        setResult(RESULT_OK, intent);
        finish();

    }


    @Override
    public void onBackPressed() {

        ExitActions();

    }

}
