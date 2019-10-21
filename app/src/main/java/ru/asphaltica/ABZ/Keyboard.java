package ru.asphaltica.ABZ;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    Material MatBunker1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            MatBunker1 = (Material) arguments.getSerializable("OBJECT");
        }

        CHOG = "";

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



    }

   @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.KeyBoard0: {

                if (CHOG.length()>=1) CHOG = CHOG + "0";
                Probezhka();
                break;
            }
            case R.id.KeyBoard1: {

                CHOG = CHOG + "1";
                Probezhka();
                break;
            }
            case R.id.KeyBoard2: {

                CHOG = CHOG + "2";
                Probezhka();
                break;
            }
            case R.id.KeyBoard3: {

                CHOG = CHOG + "3";
                Probezhka();
                break;
            }
            case R.id.KeyBoard4: {

                CHOG = CHOG + "4";
                Probezhka();
                break;
            }
            case R.id.KeyBoard5: {

                CHOG = CHOG + "5";
                Probezhka();
                break;
            }
            case R.id.KeyBoard6: {

                CHOG = CHOG + "6";
                Probezhka();
                break;
            }
            case R.id.KeyBoard7: {

                CHOG = CHOG + "7";
                Probezhka();
                break;
            }
            case R.id.KeyBoard8: {

                CHOG = CHOG + "8";
                Probezhka();
                break;
            }
            case R.id.KeyBoard9: {

                CHOG = CHOG + "9";
                Probezhka();
                break;
            }
            case R.id.KeyBoardLeftValue: {



                break;
            }
            case R.id.KeyBoardRightValue: {

                break;
            }

            case R.id.KeyBoardBackSpace: {

                if (CHOG.length() >= 1) CHOG = CHOG.substring(0, CHOG.length()-1);
                //if (CHOG.length()==0) CHOG = "0";
                Probezhka();
                break;
            }
            case R.id.KeyBoardClear: {

                CHOG = "";
                Probezhka();
                break;
            }
            case R.id.KeyBoardEndEdit: {
                Intent intent = new Intent();
                MatBunker1.CHOG[2] = Double.parseDouble(CHOG);
                intent.putExtra("name", MatBunker1);
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
}
