package ru.asphaltica.ABZ;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MixChooser extends AppCompatActivity implements View.OnClickListener {

    Button V_TipA_NS;
    Button V_TipB_NS;
    Button V_TipD_NS;
    Button V_TipV_NS;
    Button V_TipG_NS;

    Button V_TipA_PS;
    Button V_TipB_PS;

    Button N_TipA_NS;
    Button N_TipB_NS;

    Button N_TipA_PS;
    Button N_TipB_PS;
    Button PORIST;

    Button VysPoristSh;
    Button VysPoristPes;

    Button SMA10;
    Button SMA15;
    Button SMA20;

    Button SP9_5;
    Button SP12_5;
    Button SP19;

    Button SP8;
    Button SP11;
    Button SP16;

    Button SMA8;
    Button SMA11;
    Button SMA16;





    int NumberOfMix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix_chooser);

        LayoutInit();

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            NumberOfMix = arguments.getInt("NUMBER_OF_MIX");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.V_TipA_NS: {
                NumberOfMix = 1;
                ExitActions();
                break;
            }
            case R.id.V_TipB_NS: {
                NumberOfMix = 2;
                ExitActions();
                break;
            }
            case R.id.V_TipV_NS: {
                NumberOfMix = 3;
                ExitActions();
                break;
            }
            case R.id.V_TipG_NS: {
                NumberOfMix = 4;
                ExitActions();
                break;
            }
            case R.id.V_TipD_NS: {
                NumberOfMix = 5;
                ExitActions();
                break;
            }
            case R.id.V_TipA_PS: {
                NumberOfMix = 6;
                ExitActions();
                break;
            }
            case R.id.V_TipB_PS: {
                NumberOfMix = 7;
                ExitActions();
                break;
            }
            case R.id.N_TipA_NS: {
                NumberOfMix = 8;
                ExitActions();
                break;
            }
            case R.id.N_TipB_NS: {
                NumberOfMix = 9;
                ExitActions();
                break;
            }
            case R.id.N_TipA_PS: {
                NumberOfMix = 10;
                ExitActions();
                break;
            }
            case R.id.N_TipB_PS: {
                NumberOfMix = 11;
                ExitActions();
                break;
            }
            case R.id.Porist: {
                NumberOfMix = 12;
                ExitActions();
                break;
            }
            case R.id.VysPoristSh: {
                NumberOfMix = 13;
                ExitActions();
                break;
            }
            case R.id.VysPoristPes: {
                NumberOfMix = 14;
                ExitActions();
                break;
            }
            case R.id.SMA10: {
                NumberOfMix = 15;
                ExitActions();
                break;
            }
            case R.id.SMA15: {
                NumberOfMix = 16;
                ExitActions();
                break;
            }
            case R.id.SMA20: {
                NumberOfMix = 17;
                ExitActions();
                break;
            }
            case R.id.SP9_5: {
                NumberOfMix = 18;
                ExitActions();
                break;
            }
            case R.id.SP12_5: {
                NumberOfMix = 19;
                ExitActions();
                break;
            }
            case R.id.SP19: {
                NumberOfMix = 20;
                ExitActions();
                break;
            }
            case R.id.SP8: {
                NumberOfMix = 21;
                ExitActions();
                break;
            }
            case R.id.SP11: {
                NumberOfMix = 22;
                ExitActions();
                break;
            }
            case R.id.SP16: {
                NumberOfMix = 23;
                ExitActions();
                break;
            }
            case R.id.SMA8: {
                NumberOfMix = 24;
                ExitActions();
                break;
            }
            case R.id.SMA11: {
                NumberOfMix = 25;
                ExitActions();
                break;
            }
            case R.id.SMA16: {
                NumberOfMix = 26;
                ExitActions();
                break;
            }




        }
    }

    public void ExitActions() {
        Intent intent = new Intent();
        intent.putExtra("NUMBER_OF_MIX_BACK", NumberOfMix);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        ExitActions();

    }

    private void LayoutInit() {

        V_TipA_NS = (Button) findViewById(R.id.V_TipA_NS);
        V_TipB_NS = (Button) findViewById(R.id.V_TipB_NS);
        V_TipV_NS = (Button) findViewById(R.id.V_TipV_NS);
        V_TipG_NS = (Button) findViewById(R.id.V_TipG_NS);
        V_TipD_NS = (Button) findViewById(R.id.V_TipD_NS);

        V_TipA_PS = (Button) findViewById(R.id.V_TipA_PS);
        V_TipB_PS = (Button) findViewById(R.id.V_TipB_PS);

        N_TipA_NS = (Button) findViewById(R.id.N_TipA_NS);
        N_TipB_NS = (Button) findViewById(R.id.N_TipB_NS);

        N_TipA_PS = (Button) findViewById(R.id.N_TipA_PS);
        N_TipB_PS = (Button) findViewById(R.id.N_TipB_PS);
        PORIST = (Button) findViewById(R.id.Porist);
        VysPoristSh = (Button) findViewById(R.id.VysPoristSh);
        VysPoristPes = (Button) findViewById(R.id.VysPoristPes);

        SMA10 = (Button) findViewById(R.id.SMA10);
        SMA15 = (Button) findViewById(R.id.SMA15);
        SMA20 = (Button) findViewById(R.id.SMA20);

        SP9_5 = (Button) findViewById(R.id.SP9_5);
        SP12_5 = (Button) findViewById(R.id.SP12_5);
        SP19 = (Button) findViewById(R.id.SP19);

         SP8 = (Button) findViewById(R.id.SP8);
         SP11 = (Button) findViewById(R.id.SP11);
         SP16 = (Button) findViewById(R.id.SP16);

         SMA8 = (Button) findViewById(R.id.SMA8);
         SMA11 = (Button) findViewById(R.id.SMA11);
         SMA16 = (Button) findViewById(R.id.SMA16);



        V_TipA_NS.setOnClickListener(this);
        V_TipB_NS.setOnClickListener(this);
        V_TipV_NS.setOnClickListener(this);
        V_TipG_NS.setOnClickListener(this);
        V_TipD_NS.setOnClickListener(this);

        V_TipA_PS.setOnClickListener(this);
        V_TipB_PS.setOnClickListener(this);

        N_TipA_NS.setOnClickListener(this);
        N_TipB_NS.setOnClickListener(this);

        N_TipA_PS.setOnClickListener(this);
        N_TipB_PS.setOnClickListener(this);
        PORIST.setOnClickListener(this);
        VysPoristSh.setOnClickListener(this);
        VysPoristPes.setOnClickListener(this);

        SMA10.setOnClickListener(this);
        SMA15.setOnClickListener(this);
        SMA20.setOnClickListener(this);

        SP9_5.setOnClickListener(this);
        SP12_5.setOnClickListener(this);
        SP19.setOnClickListener(this);

        SP8.setOnClickListener(this);
        SP11.setOnClickListener(this);
        SP16.setOnClickListener(this);

        SMA8.setOnClickListener(this);
        SMA11.setOnClickListener(this);
        SMA16.setOnClickListener(this);



    }

}
