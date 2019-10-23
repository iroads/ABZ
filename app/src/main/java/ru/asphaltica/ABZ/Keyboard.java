package ru.asphaltica.ABZ;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

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

    TextView KeyBoardOtsek;
    TextView KeyBoardSito;
    TextView KeyBoardCHOG;

    String CHOG;

    Material TransMaterial;
    int ChogID;
    int BunkerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            TransMaterial = (Material) arguments.getSerializable("OBJECT");
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

        KeyBoardOtsek.setText("Отсек№"+BunkerID);
        KeyBoardSito.setText("Сито "+TransMaterial.Sita.get(ChogID));

        int yourScale0 = 0;
        CHOG = BigDecimal.valueOf(TransMaterial.CHOG[ChogID]).setScale(yourScale0, BigDecimal.ROUND_HALF_UP).toString();
        KeyBoardCHOG.setText(CHOG);

    }

   @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.KeyBoard0: {

                if (CHOG.length()>=1 && CHOG.equals("0") == false) CHOG = CHOG + "0";
                Probezhka();
                break;
            }
            case R.id.KeyBoard1: {

                if (CHOG.equals("0")) CHOG = "1";
                else CHOG = CHOG + "1";
                Probezhka();
                break;
            }
            case R.id.KeyBoard2: {

                if (CHOG.equals("0")) CHOG = "2";
                else CHOG = CHOG + "2";
                Probezhka();
                break;
            }
            case R.id.KeyBoard3: {

                if (CHOG.equals("0")) CHOG = "3";
                else CHOG = CHOG + "3";
                Probezhka();
                break;
            }
            case R.id.KeyBoard4: {

                if (CHOG.equals("0")) CHOG = "4";
                else CHOG = CHOG + "4";
                Probezhka();
                break;
            }
            case R.id.KeyBoard5: {

                if (CHOG.equals("0")) CHOG = "5";
                else CHOG = CHOG + "5";
                Probezhka();
                break;
            }
            case R.id.KeyBoard6: {

                if (CHOG.equals("0")) CHOG = "6";
                else CHOG = CHOG + "6";
                Probezhka();
                break;
            }
            case R.id.KeyBoard7: {

                if (CHOG.equals("0")) CHOG = "7";
                else CHOG = CHOG + "7";
                Probezhka();
                break;
            }
            case R.id.KeyBoard8: {

                if (CHOG.equals("0")) CHOG = "8";
                else CHOG = CHOG + "8";
                Probezhka();
                break;
            }
            case R.id.KeyBoard9: {

                if (CHOG.equals("0")) CHOG = "9";
                else CHOG = CHOG + "9";
                Probezhka();
                break;
            }
            case R.id.KeyBoardLeftValue: {

                if (ChogID != TransMaterial.CHOG.length-1) {
                    int yourScale0 = 0;
                    TransMaterial.CHOG[ChogID] = Double.parseDouble(CHOG);
                    ChogID = ChogID + 1;
                    CHOG = BigDecimal.valueOf(TransMaterial.CHOG[ChogID]).setScale(yourScale0, BigDecimal.ROUND_HALF_UP).toString();
                    KeyBoardCHOG.setText(CHOG);
                    KeyBoardSito.setText("Сито "+TransMaterial.Sita.get(ChogID));
                }
                break;
            }
            case R.id.KeyBoardRightValue: {

                if (ChogID!=0) {
                    int yourScale0 = 0;
                    TransMaterial.CHOG[ChogID] = Double.parseDouble(CHOG);
                    ChogID = ChogID - 1;
                    CHOG = BigDecimal.valueOf(TransMaterial.CHOG[ChogID]).setScale(yourScale0, BigDecimal.ROUND_HALF_UP).toString();
                    KeyBoardCHOG.setText(CHOG);
                    KeyBoardSito.setText("Сито "+TransMaterial.Sita.get(ChogID));
                }

                break;
            }

            case R.id.KeyBoardBackSpace: {
                if (CHOG.length() == 1) CHOG = "0"; else
                if (CHOG.length() > 1)  CHOG = CHOG.substring(0, CHOG.length()-1);

                Probezhka();
                break;
            }
            case R.id.KeyBoardClear: {
                CHOG = "0";
                Probezhka();
                break;
            }
            case R.id.KeyBoardEndEdit: {
                Intent intent = new Intent();
                TransMaterial.CHOG[ChogID] = Double.parseDouble(CHOG);
                intent.putExtra("OBJECT_BACK", TransMaterial);
                intent.putExtra("BUNKERID_BACK", BunkerID);
                setResult(RESULT_OK, intent);
                finish();
                break;
            }
        }
    }

    public void ViewInit () {

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

        KeyBoardOtsek = (TextView) findViewById(R.id.KeyBoardOtsek);
        KeyBoardSito = (TextView) findViewById(R.id.KeyBoardSito);
        KeyBoardCHOG = (TextView) findViewById(R.id.KeyBoardCHOG);

    }

    public void Probezhka () {
        KeyBoardCHOG.setText(CHOG);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        TransMaterial.CHOG[ChogID] = Double.parseDouble(CHOG);
        intent.putExtra("OBJECT_BACK", TransMaterial);
        intent.putExtra("BUNKERID_BACK", BunkerID);
        setResult(RESULT_OK, intent);
        finish();
    }
}
