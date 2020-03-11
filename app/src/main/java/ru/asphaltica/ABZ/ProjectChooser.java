package ru.asphaltica.ABZ;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProjectChooser extends AppCompatActivity implements View.OnClickListener {

    Button SLButton1;
    Button SLButton2;
    Button SLButton3;
    Button SLButton4;
    Button SLButton5;
    Button SLButton6;
    Button SLButton7;
    Button SLButton8;
    Button SLButton9;
    Button SLButton10;

    ArrayList<Button> SLButton = new ArrayList<Button>();

    Button CorrectNameSLButton1;
    Button CorrectNameSLButton2;
    Button CorrectNameSLButton3;
    Button CorrectNameSLButton4;
    Button CorrectNameSLButton5;
    Button CorrectNameSLButton6;
    Button CorrectNameSLButton7;
    Button CorrectNameSLButton8;
    Button CorrectNameSLButton9;
    Button CorrectNameSLButton10;

    ArrayList<Button> CorrectNameSLButton = new ArrayList<Button>();

    TextView SLActivityHeader;

    String Type_Of_Choose;

    int TheChoose;
    int ChooseOfCorrect;

    SharedPreferences MyPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_chooser);

        LayoutInit();

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            Type_Of_Choose = arguments.getString("TYPE_OF_CHOOSE");
        }

        if (Type_Of_Choose.equals("SAVE")) SLActivityHeader.setText(R.string.SaveData);
        if (Type_Of_Choose.equals("LOAD")) SLActivityHeader.setText(R.string.LoadData);

        // Создаем объект для работы с системой хранения настроек
        // Здесь имя файла Values - должно быть единым для всего приложения
        MyPref = getSharedPreferences("Values", MODE_PRIVATE);

        // Заполняем

        for (int i = 1; i <= 10; i++ ) {

            if (MyPref.getString(Integer.toString(i),"").equals("") == false) { SLButton.get(i).setText(MyPref.getString(Integer.toString(i), ""));}

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.SLButton1:
                TheChoose = 1;
                showDialog(1);
                break;
            case R.id.SLButton2:
                TheChoose = 2;
                showDialog(1);
                break;
            case R.id.SLButton3:
                TheChoose = 3;
                showDialog(1);
                break;
            case R.id.SLButton4:
                TheChoose = 4;
                showDialog(1);
                break;
            case R.id.SLButton5:
                TheChoose = 5;
                showDialog(1);
                break;
            case R.id.SLButton6:
                TheChoose = 6;
                showDialog(1);
                break;
            case R.id.SLButton7:
                TheChoose = 7;
                showDialog(1);
                break;
            case R.id.SLButton8:
                TheChoose = 8;
                showDialog(1);
                break;
            case R.id.SLButton9:
                TheChoose = 9;
                showDialog(1);
                break;
            case R.id.SLButton10:
                TheChoose = 10;
                showDialog(1);
                break;

            case R.id.CorrectNameSLButton1:
                ChooseOfCorrect = 1;
                showDialog(2);
                break;
            case R.id.CorrectNameSLButton2:
                ChooseOfCorrect = 2;
                showDialog(2);
                break;
            case R.id.CorrectNameSLButton3:
                ChooseOfCorrect = 3;
                showDialog(2);
                break;
            case R.id.CorrectNameSLButton4:
                ChooseOfCorrect = 4;
                showDialog(2);
                break;
            case R.id.CorrectNameSLButton5:
                ChooseOfCorrect = 5;
                showDialog(2);
                break;
            case R.id.CorrectNameSLButton6:
                ChooseOfCorrect = 6;
                showDialog(2);
                break;
            case R.id.CorrectNameSLButton7:
                ChooseOfCorrect = 7;
                showDialog(2);
                break;
            case R.id.CorrectNameSLButton8:
                ChooseOfCorrect = 8;
                showDialog(2);
                break;
            case R.id.CorrectNameSLButton9:
                ChooseOfCorrect = 9;
                showDialog(2);
                break;
            case R.id.CorrectNameSLButton10:
                ChooseOfCorrect = 10;
                showDialog(2);
                break;
        }
    }

    protected Dialog onCreateDialog(int id) {

        if (id == 1) {
            AlertDialog.Builder YesOrNo = new AlertDialog.Builder(this);

            if (Type_Of_Choose.equals("SAVE"))
                YesOrNo.setTitle("Сохранение данных" + " в ячейку №" + TheChoose);
            if (Type_Of_Choose.equals("LOAD"))
                YesOrNo.setTitle("Загрузка данных" + " из ячейки №" + TheChoose);
            YesOrNo.setMessage("Вы уверены?");
            YesOrNo.setPositiveButton("Да", myDialogClickListener);
            YesOrNo.setNegativeButton("Нет", myDialogClickListener);

            return YesOrNo.create();
        }

        if (id == 2) {
            AlertDialog.Builder CorrectD = new AlertDialog.Builder(this);


            CorrectD.setTitle("Изменение имени ячеки" + " №" + ChooseOfCorrect);
            CorrectD.setMessage("Введите новое имя:");

            final EditText input = new EditText(this);
            CorrectD.setView(input);

            CorrectD.setPositiveButton("Изменить", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    SLButton.get(ChooseOfCorrect).setText(input.getText());
                    removeDialog(2);
                }
            });

            CorrectD.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    removeDialog(2);
                    // Canceled.
                }
            });




            return CorrectD.create();
        }

        return super.onCreateDialog(id);
    }

    DialogInterface.OnClickListener myDialogClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:

                    ExitActions();
                    removeDialog(1);
                    break;
                // негативная кнопка
                case Dialog.BUTTON_NEGATIVE:
                    removeDialog(1);

                    break;

            }
        }
    };


    public void ExitActions() {

        // Блок сохранения значения полей


        // Создаем объект Editor для записи пар ключ значение
        SharedPreferences.Editor editor = MyPref.edit();

        // Кладем значение нужного поля в систему хранения настроек при этом ключ совпадает с именем поля

        for (int i = 1; i<=10; i++) {

            String key = Integer.toString(i);

            editor.putString(key, SLButton.get(i).getText().toString());

        }

        editor.commit();


        Intent intent = new Intent();
        intent.putExtra("TYPE_OF_CHOOSE_BACK", Type_Of_Choose);
        intent.putExtra("THE_CHOOSE_BACK", TheChoose);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {


        ExitActions();

    }



    public void LayoutInit() {

        SLButton1 = (Button) findViewById(R.id.SLButton1);
        SLButton2 = (Button) findViewById(R.id.SLButton2);
        SLButton3 = (Button) findViewById(R.id.SLButton3);
        SLButton4 = (Button) findViewById(R.id.SLButton4);
        SLButton5 = (Button) findViewById(R.id.SLButton5);
        SLButton6 = (Button) findViewById(R.id.SLButton6);
        SLButton7 = (Button) findViewById(R.id.SLButton7);
        SLButton8 = (Button) findViewById(R.id.SLButton8);
        SLButton9 = (Button) findViewById(R.id.SLButton9);
        SLButton10 = (Button) findViewById(R.id.SLButton10);

        SLButton1.setOnClickListener(this);
        SLButton2.setOnClickListener(this);
        SLButton3.setOnClickListener(this);
        SLButton4.setOnClickListener(this);
        SLButton5.setOnClickListener(this);
        SLButton6.setOnClickListener(this);
        SLButton7.setOnClickListener(this);
        SLButton8.setOnClickListener(this);
        SLButton9.setOnClickListener(this);
        SLButton10.setOnClickListener(this);

        SLButton.add(0, SLButton1);
        SLButton.add(1, SLButton1);
        SLButton.add(2, SLButton2);
        SLButton.add(3, SLButton3);
        SLButton.add(4, SLButton4);
        SLButton.add(5, SLButton5);
        SLButton.add(6, SLButton6);
        SLButton.add(7, SLButton7);
        SLButton.add(8, SLButton8);
        SLButton.add(9, SLButton9);
        SLButton.add(10, SLButton10);




        CorrectNameSLButton1 = (Button) findViewById(R.id.CorrectNameSLButton1);
        CorrectNameSLButton2 = (Button) findViewById(R.id.CorrectNameSLButton2);
        CorrectNameSLButton3 = (Button) findViewById(R.id.CorrectNameSLButton3);
        CorrectNameSLButton4 = (Button) findViewById(R.id.CorrectNameSLButton4);
        CorrectNameSLButton5 = (Button) findViewById(R.id.CorrectNameSLButton5);
        CorrectNameSLButton6 = (Button) findViewById(R.id.CorrectNameSLButton6);
        CorrectNameSLButton7 = (Button) findViewById(R.id.CorrectNameSLButton7);
        CorrectNameSLButton8 = (Button) findViewById(R.id.CorrectNameSLButton8);
        CorrectNameSLButton9 = (Button) findViewById(R.id.CorrectNameSLButton9);
        CorrectNameSLButton10 = (Button) findViewById(R.id.CorrectNameSLButton10);

        CorrectNameSLButton1.setOnClickListener(this);
        CorrectNameSLButton2.setOnClickListener(this);
        CorrectNameSLButton3.setOnClickListener(this);
        CorrectNameSLButton4.setOnClickListener(this);
        CorrectNameSLButton5.setOnClickListener(this);
        CorrectNameSLButton6.setOnClickListener(this);
        CorrectNameSLButton7.setOnClickListener(this);
        CorrectNameSLButton8.setOnClickListener(this);
        CorrectNameSLButton9.setOnClickListener(this);
        CorrectNameSLButton10.setOnClickListener(this);


        CorrectNameSLButton.add(0, CorrectNameSLButton1);
        CorrectNameSLButton.add(1, CorrectNameSLButton1);
        CorrectNameSLButton.add(2, CorrectNameSLButton2);
        CorrectNameSLButton.add(3, CorrectNameSLButton3);
        CorrectNameSLButton.add(4, CorrectNameSLButton4);
        CorrectNameSLButton.add(5, CorrectNameSLButton5);
        CorrectNameSLButton.add(6, CorrectNameSLButton6);
        CorrectNameSLButton.add(7, CorrectNameSLButton7);
        CorrectNameSLButton.add(8, CorrectNameSLButton8);
        CorrectNameSLButton.add(9, CorrectNameSLButton9);
        CorrectNameSLButton.add(10, CorrectNameSLButton10);



        SLActivityHeader = (TextView) findViewById(R.id.SLActivityHeader);

    }


}
