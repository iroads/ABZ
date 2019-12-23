package ru.asphaltica.ABZ;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;


public class WorkComposeActivity extends AppCompatActivity implements View.OnClickListener {

    Recept TransRecept;

    TextView NameOfMaterialWC1;
    TextView NameOfMaterialWC2;
    TextView NameOfMaterialWC3;
    TextView NameOfMaterialWC4;
    TextView NameOfMaterialWC5;
    TextView NameOfMaterialWC6;

    TextView ValueOfMaterial1;
    TextView ValueOfMaterial2;
    TextView ValueOfMaterial3;
    TextView ValueOfMaterial4;
    TextView ValueOfMaterial5;
    TextView ValueOfMaterial6;

    TextView ValueOfMaterialMP;
    TextView ValueOfMaterialSZ;
    TextView ValueOfMaterialBITUM;
    TextView ValueOfMaterialSD;
    TextView ValueOfMaterialDD;
    TextView ValueOfMaterialAD;

    TextView ValueOfMaterial1KG;
    TextView ValueOfMaterial2KG;
    TextView ValueOfMaterial3KG;
    TextView ValueOfMaterial4KG;
    TextView ValueOfMaterial5KG;
    TextView ValueOfMaterial6KG;

    TextView ValueOfMaterialMPKG;
    TextView ValueOfMaterialSZKG;
    TextView ValueOfMaterialBITUMKG;
    TextView ValueOfMaterialSDKG;
    TextView ValueOfMaterialDDKG;
    TextView ValueOfMaterialADKG;


    Button BackToPodbor;

    Button ComponentValueBitumUp100;
    Button ComponentValueBitumIn100;
    Button ComponentValueSD;
    Button ComponentValueDD;
    Button ComponentValueAD;
    Button MassaZamesa;

    EditText Primechanie;

    CheckBox SDCheckBox;
    CheckBox DDCheckBox;
    CheckBox ADCheckBox;


    int SDChecked;
    int DDChecked;
    int ADChecked;

    Double Summa;

    Double SummaKamen;

    Double SummaKamenBitIn100;

    Double ZAMES;

    Double MassaKamen;


    Double SODBitumUp100;
    Double SODM1;
    Double SODM2;
    Double SODM3;
    Double SODM4;
    Double SODM5;
    Double SODM6;
    Double SODMMP;
    Double SODMSZ;
    Double SODMBitum;
    Double SODMSD;
    Double SODMDD;
    Double SODMAD;

    Double SODBitumUp100KG;
    Double SODM1KG;
    Double SODM2KG;
    Double SODM3KG;
    Double SODM4KG;
    Double SODM5KG;
    Double SODM6KG;
    Double SODMMPKG;
    Double SODMSZKG;
    Double SODMBITUMKG;
    Double SODMSDKG;
    Double SODMDDKG;
    Double SODMADKG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_compose);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            TransRecept = (Recept) arguments.getSerializable("OBJECT");

        }

        ViewInit();


        NameOfMaterialWC1.setText(TransRecept.NameOfMaterial1);
        NameOfMaterialWC2.setText(TransRecept.NameOfMaterial2);
        NameOfMaterialWC3.setText(TransRecept.NameOfMaterial3);
        NameOfMaterialWC4.setText(TransRecept.NameOfMaterial4);
        NameOfMaterialWC5.setText(TransRecept.NameOfMaterial5);
        NameOfMaterialWC6.setText(TransRecept.NameOfMaterial6);

        DatabaseReader(1);

        if (SDChecked == 1) SDCheckBox.setChecked(true);
        else SDCheckBox.setChecked(false);
        if (DDChecked == 1) DDCheckBox.setChecked(true);
        else DDCheckBox.setChecked(false);
        if (ADChecked == 1) ADCheckBox.setChecked(true);
        else ADCheckBox.setChecked(false);

        Probezhka();

    }


    public void onClick(View v) {

        boolean UniversalPushDetector = false;
        int UniversalID = 0;
        String Value = "";

        switch (v.getId()) {

            case R.id.BackToPodbor: {

                ExitActions();
                break;
            }
            case R.id.BitumUp100: {
                UniversalPushDetector = true;
                UniversalID = 101;
                Value = String.valueOf(ComponentValueBitumUp100.getText());
                break;
            }
            case R.id.BitumIn100: {
                UniversalPushDetector = true;
                UniversalID = 102;
                Value = String.valueOf(ComponentValueBitumIn100.getText());
                break;
            }
            case R.id.SD: {
                UniversalPushDetector = true;
                UniversalID = 103;
                Value = String.valueOf(ComponentValueSD.getText());
                break;
            }
            case R.id.DD: {
                UniversalPushDetector = true;
                UniversalID = 104;
                Value = String.valueOf(ComponentValueDD.getText());
                break;
            }
            case R.id.AD: {
                UniversalPushDetector = true;
                UniversalID = 105;
                Value = String.valueOf(ComponentValueAD.getText());
                break;
            }
            case R.id.MassaZamesa: {
                UniversalPushDetector = true;
                UniversalID = 6;
                Value = String.valueOf(MassaZamesa.getText());
                break;
            }

        }

        if (UniversalPushDetector) {
            UniversalPushDetector = false;
            Intent intent = new Intent(this, UniversalKeyboard.class);
            intent.putExtra("UniversalID", UniversalID);
            intent.putExtra("Value", Value);
            startActivityForResult(intent, 1);

        }
    }


    private void ExitActions() {

        DatabaseWriter(1);

        Intent intent = new Intent();

        //TransMaterial.CHOG[ChogID] = Double.parseDouble(TotalValue);
        //intent.putExtra("OBJECT_BACK", TransMaterial);
        //intent.putExtra("BUNKERID_BACK", BunkerID);
        //setResult(RESULT_OK, intent);
        finish();

    }

    private void Probezhka() {

        DatabaseWriter(1);

        Calculate();

        ValueOfMaterial1.setText(BigDecimal.valueOf(SODM1).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterial2.setText(BigDecimal.valueOf(SODM2).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterial3.setText(BigDecimal.valueOf(SODM3).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterial4.setText(BigDecimal.valueOf(SODM4).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterial5.setText(BigDecimal.valueOf(SODM5).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterial6.setText(BigDecimal.valueOf(SODM6).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterialMP.setText(BigDecimal.valueOf(SODMMP).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterialSZ.setText(BigDecimal.valueOf(SODMSZ).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterialBITUM.setText(BigDecimal.valueOf(SODMBitum).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterialSD.setText(BigDecimal.valueOf(SODMSD).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterialDD.setText(BigDecimal.valueOf(SODMDD).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterialAD.setText(BigDecimal.valueOf(SODMAD).setScale(3, BigDecimal.ROUND_HALF_UP).toString());

        ValueOfMaterial1KG.setText(BigDecimal.valueOf(SODM1KG).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterial2KG.setText(BigDecimal.valueOf(SODM2KG).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterial3KG.setText(BigDecimal.valueOf(SODM3KG).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterial4KG.setText(BigDecimal.valueOf(SODM4KG).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterial5KG.setText(BigDecimal.valueOf(SODM5KG).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterial6KG.setText(BigDecimal.valueOf(SODM6KG).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterialMPKG.setText(BigDecimal.valueOf(SODMMPKG).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterialSZKG.setText(BigDecimal.valueOf(SODMSZKG).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterialBITUMKG.setText(BigDecimal.valueOf(SODMBITUMKG).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterialSDKG.setText(BigDecimal.valueOf(SODMSDKG).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterialDDKG.setText(BigDecimal.valueOf(SODMDDKG).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        ValueOfMaterialADKG.setText(BigDecimal.valueOf(SODMADKG).setScale(3, BigDecimal.ROUND_HALF_UP).toString());

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data == null) {
            return;
        }

        int UniversalID;
        UniversalID = data.getIntExtra("UNIVERSALID_BACK", 0);
        if (UniversalID == 101) {
            ComponentValueBitumUp100.setText(data.getStringExtra("VALUE_BACK"));
            Double Bup100 = Double.parseDouble(data.getStringExtra("VALUE_BACK"));
            Double Bin100 = (Bup100*100)/(100+Bup100);
            ComponentValueBitumIn100.setText(BigDecimal.valueOf(Bin100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        }
        if (UniversalID == 102) {
            ComponentValueBitumIn100.setText(data.getStringExtra("VALUE_BACK"));
            Double Bin100 = Double.parseDouble(data.getStringExtra("VALUE_BACK"));
            Double Bup100 = (Bin100*100)/(100-Bin100);
            ComponentValueBitumUp100.setText(BigDecimal.valueOf(Bup100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        }
        if (UniversalID == 103) ComponentValueSD.setText(data.getStringExtra("VALUE_BACK"));
        if (UniversalID == 104) ComponentValueDD.setText(data.getStringExtra("VALUE_BACK"));
        if (UniversalID == 105) ComponentValueAD.setText(data.getStringExtra("VALUE_BACK"));
        if (UniversalID == 6) MassaZamesa.setText(data.getStringExtra("VALUE_BACK"));

        Probezhka();

    }

    private void ViewInit() {
        NameOfMaterialWC1 = (TextView) findViewById(R.id.NameOfMaterialWC1);
        NameOfMaterialWC2 = (TextView) findViewById(R.id.NameOfMaterialWC2);
        NameOfMaterialWC3 = (TextView) findViewById(R.id.NameOfMaterialWC3);
        NameOfMaterialWC4 = (TextView) findViewById(R.id.NameOfMaterialWC4);
        NameOfMaterialWC5 = (TextView) findViewById(R.id.NameOfMaterialWC5);
        NameOfMaterialWC6 = (TextView) findViewById(R.id.NameOfMaterialWC6);


        ValueOfMaterial1 = (TextView) findViewById(R.id.ValueOfMaterial1);
        ValueOfMaterial2 = (TextView) findViewById(R.id.ValueOfMaterial2);
        ValueOfMaterial3 = (TextView) findViewById(R.id.ValueOfMaterial3);
        ValueOfMaterial4 = (TextView) findViewById(R.id.ValueOfMaterial4);
        ValueOfMaterial5 = (TextView) findViewById(R.id.ValueOfMaterial5);
        ValueOfMaterial6 = (TextView) findViewById(R.id.ValueOfMaterial6);

        ValueOfMaterialMP = (TextView) findViewById(R.id.ValueOfMaterialMP);
        ValueOfMaterialSZ = (TextView) findViewById(R.id.ValueOfMaterialSZ);
        ValueOfMaterialBITUM = (TextView) findViewById(R.id.ValueOfMaterialBITUM);
        ValueOfMaterialSD = (TextView) findViewById(R.id.ValueOfMaterialSD);
        ValueOfMaterialDD = (TextView) findViewById(R.id.ValueOfMaterialDD);
        ValueOfMaterialAD = (TextView) findViewById(R.id.ValueOfMaterialAD);

        ValueOfMaterial1KG = (TextView) findViewById(R.id.ValueOfMaterial1KG);
        ValueOfMaterial2KG = (TextView) findViewById(R.id.ValueOfMaterial2KG);
        ValueOfMaterial3KG = (TextView) findViewById(R.id.ValueOfMaterial3KG);
        ValueOfMaterial4KG = (TextView) findViewById(R.id.ValueOfMaterial4KG);
        ValueOfMaterial5KG = (TextView) findViewById(R.id.ValueOfMaterial5KG);
        ValueOfMaterial6KG = (TextView) findViewById(R.id.ValueOfMaterial6KG);

        ValueOfMaterialMPKG = (TextView) findViewById(R.id.ValueOfMaterialMPKG);
        ValueOfMaterialSZKG = (TextView) findViewById(R.id.ValueOfMaterialSZKG);
        ValueOfMaterialBITUMKG = (TextView) findViewById(R.id.ValueOfMaterialBITUMKG);
        ValueOfMaterialSDKG = (TextView) findViewById(R.id.ValueOfMaterialSDKG);
        ValueOfMaterialDDKG = (TextView) findViewById(R.id.ValueOfMaterialDDKG);
        ValueOfMaterialADKG = (TextView) findViewById(R.id.ValueOfMaterialADKG);

        BackToPodbor = (Button) findViewById(R.id.BackToPodbor);
        ComponentValueBitumUp100 = (Button) findViewById(R.id.BitumUp100);
        ComponentValueBitumIn100 = (Button) findViewById(R.id.BitumIn100);
        ComponentValueSD = (Button) findViewById(R.id.SD);
        ComponentValueDD = (Button) findViewById(R.id.DD);
        ComponentValueAD = (Button) findViewById(R.id.AD);
        MassaZamesa = (Button) findViewById(R.id.MassaZamesa);
        Primechanie = (EditText) findViewById(R.id.Primechanie);

        SDCheckBox = (CheckBox) findViewById(R.id.SDCheckBox);
        DDCheckBox = (CheckBox) findViewById(R.id.DDCheckBox);
        ADCheckBox = (CheckBox) findViewById(R.id.ADCheckBox);

        BackToPodbor.setOnClickListener(this);
        ComponentValueBitumUp100.setOnClickListener(this);
        ComponentValueBitumIn100.setOnClickListener(this);
        ComponentValueSD.setOnClickListener(this);
        ComponentValueDD.setOnClickListener(this);
        ComponentValueAD.setOnClickListener(this);
        MassaZamesa.setOnClickListener(this);


        SDCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //Toast.makeText(getApplicationContext(), "SD Включено", Toast.LENGTH_SHORT).show();
                    SDChecked = 1;
                    Probezhka();
                } else {
                    //Toast.makeText(getApplicationContext(), "SD Выключено", Toast.LENGTH_SHORT).show();
                    SDChecked = 0;
                    Probezhka();
                }
            }
        });

        DDCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //Toast.makeText(getApplicationContext(), "DD Включено", Toast.LENGTH_SHORT).show();
                    DDChecked = 1;
                    Probezhka();
                } else {
                    //Toast.makeText(getApplicationContext(), "DD Выключено", Toast.LENGTH_SHORT).show();
                    DDChecked = 0;
                    Probezhka();
                }
            }
        });

        ADCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //Toast.makeText(getApplicationContext(), "AD Включено", Toast.LENGTH_SHORT).show();
                    ADChecked = 1;
                    Probezhka();
                } else {
                    //Toast.makeText(getApplicationContext(), "AD Выключено", Toast.LENGTH_SHORT).show();
                    ADChecked = 0;
                    Probezhka();
                }
            }
        });
    }

    private void DatabaseReader(int id) {
        SQLiteOpenHelper abzDatabaseHelper = new ABZDatabaseHelper(this);
        try {
            //Получение ссылки на базу данных
            SQLiteDatabase MyDatabase = abzDatabaseHelper.getReadableDatabase();
            //Создаем курсор содержащий все столбцы одной строки, строки id которой равен 1
            Cursor cursor = MyDatabase.query("ZERN_SOSTAV",
                    new String[]{"_id",
                            "BITUMUP100", "BITUMIN100", "SD", "DD", "AD", "MASSAZAMESA", "SDCHECKED", "DDCHECKED",
                            "ADCHECKED", "PRIMECHANIE"
                    },
                    "_id = ?",
                    new String[]{Integer.toString(id)}, null, null, null);
            if (cursor.moveToFirst()) { //обязательное перемещение курсора
                //Достаем данные из курсора
                ComponentValueBitumUp100.setText(BigDecimal.valueOf(cursor.getDouble(1)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                ComponentValueBitumIn100.setText(BigDecimal.valueOf(cursor.getDouble(2)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                ComponentValueSD.setText(BigDecimal.valueOf(cursor.getDouble(3)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                ComponentValueDD.setText(BigDecimal.valueOf(cursor.getDouble(4)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                ComponentValueAD.setText(BigDecimal.valueOf(cursor.getDouble(5)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                MassaZamesa.setText(BigDecimal.valueOf(cursor.getDouble(6)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                SDChecked = cursor.getInt(7);
                DDChecked = cursor.getInt(8);
                ADChecked = cursor.getInt(9);
                Primechanie.setText(cursor.getString(10));
            }
            cursor.close(); //Закрываем курсор и базу данных
            MyDatabase.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable READ", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void DatabaseWriter(int id) {
        ContentValues values = new ContentValues();
        values.put("BITUMUP100", Double.parseDouble(ComponentValueBitumUp100.getText().toString()));
        values.put("BITUMIN100", Double.parseDouble(ComponentValueBitumIn100.getText().toString()));
        values.put("SD", Double.parseDouble(ComponentValueSD.getText().toString()));
        values.put("DD", Double.parseDouble(ComponentValueDD.getText().toString()));
        values.put("AD", Double.parseDouble(ComponentValueAD.getText().toString()));
        values.put("MASSAZAMESA", Double.parseDouble(MassaZamesa.getText().toString()));
        values.put("PRIMECHANIE", Primechanie.getText().toString());
        values.put("SDCHECKED", SDChecked);
        values.put("DDCHECKED", DDChecked);
        values.put("ADCHECKED", ADChecked);
        // values.put("NAMEOFMATERIAL1", NameOfMaterial1.getText().toString());
        SQLiteOpenHelper abzDatabaseHelper = new ABZDatabaseHelper(this);
        try {
            //Получение ссылки на базу данных
            SQLiteDatabase MyDatabase = abzDatabaseHelper.getWritableDatabase();
            MyDatabase.update("ZERN_SOSTAV", values, "_id = ?", new String[]{Integer.toString(id)});
            MyDatabase.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable WRITE", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onBackPressed() {

        ExitActions();

    }

    private void Calculate() {

        SODBitumUp100 = Double.parseDouble(ComponentValueBitumUp100.getText().toString());

        ZAMES = Double.parseDouble(MassaZamesa.getText().toString());

        Summa = TransRecept.SOD1 + TransRecept.SOD2 + TransRecept.SOD3 + TransRecept.SOD4 + TransRecept.SOD5 + TransRecept.SOD6 + TransRecept.SODMP + TransRecept.SODSZ + SODBitumUp100;

        SummaKamen = TransRecept.SOD1 + TransRecept.SOD2 + TransRecept.SOD3 + TransRecept.SOD4 + TransRecept.SOD5 + TransRecept.SOD6 + TransRecept.SODMP + TransRecept.SODSZ;

        if (SDChecked == 1) {
            Summa = Summa + Double.parseDouble(ComponentValueSD.getText().toString());
        }

        if (DDChecked == 1) {
            Summa = Summa + Double.parseDouble(ComponentValueDD.getText().toString());
        }

        if (ADChecked == 1) {
            Summa = Summa + (Double.parseDouble(ComponentValueAD.getText().toString()) * SODBitumUp100) / 100;
        }

        SODM1 = (TransRecept.SOD1 * 100) / Summa;
        SODM2 = (TransRecept.SOD2 * 100) / Summa;
        SODM3 = (TransRecept.SOD3 * 100) / Summa;
        SODM4 = (TransRecept.SOD4 * 100) / Summa;
        SODM5 = (TransRecept.SOD5 * 100) / Summa;
        SODM6 = (TransRecept.SOD6 * 100) / Summa;
        SODMMP = (TransRecept.SODMP * 100) / Summa;
        SODMSZ = (TransRecept.SODSZ * 100) / Summa;
        SODMBitum = (SODBitumUp100 * 100) / Summa;

        SummaKamenBitIn100 = SODM1 + SODM2 +SODM3 +SODM4 +SODM5 +SODM6 +SODMMP +SODMSZ;

        SODMSD = ((Double.parseDouble(ComponentValueSD.getText().toString()))*SummaKamenBitIn100)/100;
        SODMDD = ((Double.parseDouble(ComponentValueDD.getText().toString()))*SummaKamenBitIn100)/100;
        SODMAD = (Double.parseDouble(ComponentValueAD.getText().toString()) * SODMBitum) / 100;

        SODM1KG = (SODM1 * ZAMES) / 100;
        SODM2KG = (SODM2 * ZAMES) / 100;
        SODM3KG = (SODM3 * ZAMES) / 100;
        SODM4KG = (SODM4 * ZAMES) / 100;
        SODM5KG = (SODM5 * ZAMES) / 100;
        SODM6KG = (SODM6 * ZAMES) / 100;
        SODMMPKG = (SODMMP * ZAMES) / 100;
        SODMSZKG = (SODMSZ * ZAMES) / 100;
        SODMBITUMKG = (SODMBitum * ZAMES) / 100;
        SODMSDKG = (SODMSD * ZAMES) / 100;
        SODMDDKG = (SODMDD * ZAMES) / 100;
        SODMADKG = (SODMAD * ZAMES) / 100;

    }
}
