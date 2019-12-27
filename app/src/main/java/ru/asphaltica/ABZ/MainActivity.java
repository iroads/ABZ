package ru.asphaltica.ABZ;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Выбор элемента в данном списке будет изменять нормативные полные проходы
    Button SpinnerMixType;

    //Создаем объект типа Recept
    //Данный объект будет содержать дозировку в % каждого компонента
    //Данный объект может рассчитывать полные проходы для каждого компонета с учетом дозировки, а также расчитывает полный зерновой состав смеси
    Recept recept = new Recept();


    //ТАБЛИЦА МАТЕРИАЛА БУНКЕРА №1

    Material MatBunker1 = new Material();

    ArrayList<TextView> CHOG = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля ввода частных остатков в граммах для их перебора в цикле
    ArrayList<TextView> CHOP = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода частных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PO = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PP = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных проходов в процентах для их перебора в цикле


    TextView CHOG40;
    TextView CHOG20;
    TextView CHOG15;
    TextView CHOG10;
    TextView CHOG5;
    TextView CHOG2_5;
    TextView CHOG1_25;
    TextView CHOG0_63;
    TextView CHOG0_315;
    TextView CHOG0_16;
    TextView CHOG0_071;
    TextView CHOGDNO;


    TextView CHOP40;
    TextView CHOP20;
    TextView CHOP15;
    TextView CHOP10;
    TextView CHOP5;
    TextView CHOP2_5;
    TextView CHOP1_25;
    TextView CHOP0_63;
    TextView CHOP0_315;
    TextView CHOP0_16;
    TextView CHOP0_071;
    TextView CHOPDNO;

    TextView PO40;
    TextView PO20;
    TextView PO15;
    TextView PO10;
    TextView PO5;
    TextView PO2_5;
    TextView PO1_25;
    TextView PO0_63;
    TextView PO0_315;
    TextView PO0_16;
    TextView PO0_071;
    TextView PODNO;

    TextView PP40;
    TextView PP20;
    TextView PP15;
    TextView PP10;
    TextView PP5;
    TextView PP2_5;
    TextView PP1_25;
    TextView PP0_63;
    TextView PP0_315;
    TextView PP0_16;
    TextView PP0_071;
    TextView PPDNO;

    //ТАБЛИЦА МАТЕРИАЛА БУНКЕРА №2

    Material MatBunker2 = new Material();

    ArrayList<TextView> CHOG2 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля ввода частных остатков в граммах для их перебора в цикле
    ArrayList<TextView> CHOP2 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода частных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PO2 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PP2 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных проходов в процентах для их перебора в цикле


    TextView CHOG40_2;
    TextView CHOG20_2;
    TextView CHOG15_2;
    TextView CHOG10_2;
    TextView CHOG5_2;
    TextView CHOG2_5_2;
    TextView CHOG1_25_2;
    TextView CHOG0_63_2;
    TextView CHOG0_315_2;
    TextView CHOG0_16_2;
    TextView CHOG0_071_2;
    TextView CHOGDNO_2;


    TextView CHOP40_2;
    TextView CHOP20_2;
    TextView CHOP15_2;
    TextView CHOP10_2;
    TextView CHOP5_2;
    TextView CHOP2_5_2;
    TextView CHOP1_25_2;
    TextView CHOP0_63_2;
    TextView CHOP0_315_2;
    TextView CHOP0_16_2;
    TextView CHOP0_071_2;
    TextView CHOPDNO_2;

    TextView PO40_2;
    TextView PO20_2;
    TextView PO15_2;
    TextView PO10_2;
    TextView PO5_2;
    TextView PO2_5_2;
    TextView PO1_25_2;
    TextView PO0_63_2;
    TextView PO0_315_2;
    TextView PO0_16_2;
    TextView PO0_071_2;
    TextView PODNO_2;

    TextView PP40_2;
    TextView PP20_2;
    TextView PP15_2;
    TextView PP10_2;
    TextView PP5_2;
    TextView PP2_5_2;
    TextView PP1_25_2;
    TextView PP0_63_2;
    TextView PP0_315_2;
    TextView PP0_16_2;
    TextView PP0_071_2;
    TextView PPDNO_2;

    //ТАБЛИЦА МАТЕРИАЛА БУНКЕРА №3

    Material MatBunker3 = new Material();

    ArrayList<TextView> CHOG3 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля ввода частных остатков в граммах для их перебора в цикле
    ArrayList<TextView> CHOP3 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода частных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PO3 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PP3 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных проходов в процентах для их перебора в цикле


    TextView CHOG40_3;
    TextView CHOG20_3;
    TextView CHOG15_3;
    TextView CHOG10_3;
    TextView CHOG5_3;
    TextView CHOG2_5_3;
    TextView CHOG1_25_3;
    TextView CHOG0_63_3;
    TextView CHOG0_315_3;
    TextView CHOG0_16_3;
    TextView CHOG0_071_3;
    TextView CHOGDNO_3;


    TextView CHOP40_3;
    TextView CHOP20_3;
    TextView CHOP15_3;
    TextView CHOP10_3;
    TextView CHOP5_3;
    TextView CHOP2_5_3;
    TextView CHOP1_25_3;
    TextView CHOP0_63_3;
    TextView CHOP0_315_3;
    TextView CHOP0_16_3;
    TextView CHOP0_071_3;
    TextView CHOPDNO_3;

    TextView PO40_3;
    TextView PO20_3;
    TextView PO15_3;
    TextView PO10_3;
    TextView PO5_3;
    TextView PO2_5_3;
    TextView PO1_25_3;
    TextView PO0_63_3;
    TextView PO0_315_3;
    TextView PO0_16_3;
    TextView PO0_071_3;
    TextView PODNO_3;

    TextView PP40_3;
    TextView PP20_3;
    TextView PP15_3;
    TextView PP10_3;
    TextView PP5_3;
    TextView PP2_5_3;
    TextView PP1_25_3;
    TextView PP0_63_3;
    TextView PP0_315_3;
    TextView PP0_16_3;
    TextView PP0_071_3;
    TextView PPDNO_3;

    //ТАБЛИЦА МАТЕРИАЛА БУНКЕРА №4

    Material MatBunker4 = new Material();

    ArrayList<TextView> CHOG4 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля ввода частных остатков в граммах для их перебора в цикле
    ArrayList<TextView> CHOP4 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода частных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PO4 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PP4 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных проходов в процентах для их перебора в цикле


    TextView CHOG40_4;
    TextView CHOG20_4;
    TextView CHOG15_4;
    TextView CHOG10_4;
    TextView CHOG5_4;
    TextView CHOG2_5_4;
    TextView CHOG1_25_4;
    TextView CHOG0_63_4;
    TextView CHOG0_315_4;
    TextView CHOG0_16_4;
    TextView CHOG0_071_4;
    TextView CHOGDNO_4;


    TextView CHOP40_4;
    TextView CHOP20_4;
    TextView CHOP15_4;
    TextView CHOP10_4;
    TextView CHOP5_4;
    TextView CHOP2_5_4;
    TextView CHOP1_25_4;
    TextView CHOP0_63_4;
    TextView CHOP0_315_4;
    TextView CHOP0_16_4;
    TextView CHOP0_071_4;
    TextView CHOPDNO_4;

    TextView PO40_4;
    TextView PO20_4;
    TextView PO15_4;
    TextView PO10_4;
    TextView PO5_4;
    TextView PO2_5_4;
    TextView PO1_25_4;
    TextView PO0_63_4;
    TextView PO0_315_4;
    TextView PO0_16_4;
    TextView PO0_071_4;
    TextView PODNO_4;

    TextView PP40_4;
    TextView PP20_4;
    TextView PP15_4;
    TextView PP10_4;
    TextView PP5_4;
    TextView PP2_5_4;
    TextView PP1_25_4;
    TextView PP0_63_4;
    TextView PP0_315_4;
    TextView PP0_16_4;
    TextView PP0_071_4;
    TextView PPDNO_4;

    //ТАБЛИЦА МАТЕРИАЛА БУНКЕРА №5

    Material MatBunker5 = new Material();

    ArrayList<TextView> CHOG50 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля ввода частных остатков в граммах для их перебора в цикле
    ArrayList<TextView> CHOP50 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода частных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PO50 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PP50 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных проходов в процентах для их перебора в цикле


    TextView CHOG40_5;
    TextView CHOG20_5;
    TextView CHOG15_5;
    TextView CHOG10_5;
    TextView CHOG5_5;
    TextView CHOG2_5_5;
    TextView CHOG1_25_5;
    TextView CHOG0_63_5;
    TextView CHOG0_315_5;
    TextView CHOG0_16_5;
    TextView CHOG0_071_5;
    TextView CHOGDNO_5;


    TextView CHOP40_5;
    TextView CHOP20_5;
    TextView CHOP15_5;
    TextView CHOP10_5;
    TextView CHOP5_5;
    TextView CHOP2_5_5;
    TextView CHOP1_25_5;
    TextView CHOP0_63_5;
    TextView CHOP0_315_5;
    TextView CHOP0_16_5;
    TextView CHOP0_071_5;
    TextView CHOPDNO_5;

    TextView PO40_5;
    TextView PO20_5;
    TextView PO15_5;
    TextView PO10_5;
    TextView PO5_5;
    TextView PO2_5_5;
    TextView PO1_25_5;
    TextView PO0_63_5;
    TextView PO0_315_5;
    TextView PO0_16_5;
    TextView PO0_071_5;
    TextView PODNO_5;

    TextView PP40_5;
    TextView PP20_5;
    TextView PP15_5;
    TextView PP10_5;
    TextView PP5_5;
    TextView PP2_5_5;
    TextView PP1_25_5;
    TextView PP0_63_5;
    TextView PP0_315_5;
    TextView PP0_16_5;
    TextView PP0_071_5;
    TextView PPDNO_5;

    //ТАБЛИЦА МАТЕРИАЛА БУНКЕРА №6

    Material MatBunker6 = new Material();

    ArrayList<TextView> CHOG6 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля ввода частных остатков в граммах для их перебора в цикле
    ArrayList<TextView> CHOP6 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода частных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PO6 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PP6 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных проходов в процентах для их перебора в цикле


    TextView CHOG40_6;
    TextView CHOG20_6;
    TextView CHOG15_6;
    TextView CHOG10_6;
    TextView CHOG5_6;
    TextView CHOG2_5_6;
    TextView CHOG1_25_6;
    TextView CHOG0_63_6;
    TextView CHOG0_315_6;
    TextView CHOG0_16_6;
    TextView CHOG0_071_6;
    TextView CHOGDNO_6;


    TextView CHOP40_6;
    TextView CHOP20_6;
    TextView CHOP15_6;
    TextView CHOP10_6;
    TextView CHOP5_6;
    TextView CHOP2_5_6;
    TextView CHOP1_25_6;
    TextView CHOP0_63_6;
    TextView CHOP0_315_6;
    TextView CHOP0_16_6;
    TextView CHOP0_071_6;
    TextView CHOPDNO_6;

    TextView PO40_6;
    TextView PO20_6;
    TextView PO15_6;
    TextView PO10_6;
    TextView PO5_6;
    TextView PO2_5_6;
    TextView PO1_25_6;
    TextView PO0_63_6;
    TextView PO0_315_6;
    TextView PO0_16_6;
    TextView PO0_071_6;
    TextView PODNO_6;

    TextView PP40_6;
    TextView PP20_6;
    TextView PP15_6;
    TextView PP10_6;
    TextView PP5_6;
    TextView PP2_5_6;
    TextView PP1_25_6;
    TextView PP0_63_6;
    TextView PP0_315_6;
    TextView PP0_16_6;
    TextView PP0_071_6;
    TextView PPDNO_6;

    //ТАБЛИЦА МАТЕРИАЛА МИНЕРАЛЬНЫЙ ПОРОШОК

    Material MatBunkerMP = new Material();

    ArrayList<TextView> CHOG_MP = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля ввода частных остатков в граммах для их перебора в цикле
    ArrayList<TextView> CHOP_MP = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода частных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PO_MP = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PP_MP = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных проходов в процентах для их перебора в цикле

    TextView CHOG1_25_MP;
    TextView CHOG0_63_MP;
    TextView CHOG0_315_MP;
    TextView CHOG0_16_MP;
    TextView CHOG0_071_MP;
    TextView CHOGDNO_MP;

    TextView CHOP1_25_MP;
    TextView CHOP0_63_MP;
    TextView CHOP0_315_MP;
    TextView CHOP0_16_MP;
    TextView CHOP0_071_MP;
    TextView CHOPDNO_MP;

    TextView PO1_25_MP;
    TextView PO0_63_MP;
    TextView PO0_315_MP;
    TextView PO0_16_MP;
    TextView PO0_071_MP;
    TextView PODNO_MP;

    TextView PP1_25_MP;
    TextView PP0_63_MP;
    TextView PP0_315_MP;
    TextView PP0_16_MP;
    TextView PP0_071_MP;
    TextView PPDNO_MP;

    //ТАБЛИЦА МАТЕРИАЛА СОБСТВЕННЫЙ ЗАПОЛНИТЕЛЬ

    Material MatBunkerSZ = new Material();

    ArrayList<TextView> CHOG_SZ = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля ввода частных остатков в граммах для их перебора в цикле
    ArrayList<TextView> CHOP_SZ = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода частных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PO_SZ = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных остатков в процентах для их перебора в цикле
    ArrayList<TextView> PP_SZ = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля вывода полных проходов в процентах для их перебора в цикле

    TextView CHOG1_25_SZ;
    TextView CHOG0_63_SZ;
    TextView CHOG0_315_SZ;
    TextView CHOG0_16_SZ;
    TextView CHOG0_071_SZ;
    TextView CHOGDNO_SZ;

    TextView CHOP1_25_SZ;
    TextView CHOP0_63_SZ;
    TextView CHOP0_315_SZ;
    TextView CHOP0_16_SZ;
    TextView CHOP0_071_SZ;
    TextView CHOPDNO_SZ;

    TextView PO1_25_SZ;
    TextView PO0_63_SZ;
    TextView PO0_315_SZ;
    TextView PO0_16_SZ;
    TextView PO0_071_SZ;
    TextView PODNO_SZ;

    TextView PP1_25_SZ;
    TextView PP0_63_SZ;
    TextView PP0_315_SZ;
    TextView PP0_16_SZ;
    TextView PP0_071_SZ;
    TextView PPDNO_SZ;

    ArrayList<TextView> PP_R_1 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля отображения проходов с учетом содержания компонента
    ArrayList<TextView> PP_R_2 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля отображения проходов с учетом содержания компонента
    ArrayList<TextView> PP_R_3 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля отображения проходов с учетом содержания компонента
    ArrayList<TextView> PP_R_4 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля отображения проходов с учетом содержания компонента
    ArrayList<TextView> PP_R_5 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля отображения проходов с учетом содержания компонента
    ArrayList<TextView> PP_R_6 = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля отображения проходов с учетом содержания компонента
    ArrayList<TextView> PP_R_MP = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля отображения проходов с учетом содержания компонента
    ArrayList<TextView> PP_R_SZ = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля отображения проходов с учетом содержания компонента

    TextView PP40_R_1;
    TextView PP40_R_2;
    TextView PP40_R_3;
    TextView PP40_R_4;
    TextView PP40_R_5;
    TextView PP40_R_6;
    TextView PP40_R_MP;
    TextView PP40_R_SZ;
    TextView PP20_R_1;
    TextView PP20_R_2;
    TextView PP20_R_3;
    TextView PP20_R_4;
    TextView PP20_R_5;
    TextView PP20_R_6;
    TextView PP20_R_MP;
    TextView PP20_R_SZ;
    TextView PP15_R_1;
    TextView PP15_R_2;
    TextView PP15_R_3;
    TextView PP15_R_4;
    TextView PP15_R_5;
    TextView PP15_R_6;
    TextView PP15_R_MP;
    TextView PP15_R_SZ;
    TextView PP10_R_1;
    TextView PP10_R_2;
    TextView PP10_R_3;
    TextView PP10_R_4;
    TextView PP10_R_5;
    TextView PP10_R_6;
    TextView PP10_R_MP;
    TextView PP10_R_SZ;
    TextView PP5_R_1;
    TextView PP5_R_2;
    TextView PP5_R_3;
    TextView PP5_R_4;
    TextView PP5_R_5;
    TextView PP5_R_6;
    TextView PP5_R_MP;
    TextView PP5_R_SZ;
    TextView PP2_5_R_1;
    TextView PP2_5_R_2;
    TextView PP2_5_R_3;
    TextView PP2_5_R_4;
    TextView PP2_5_R_5;
    TextView PP2_5_R_6;
    TextView PP2_5_R_MP;
    TextView PP2_5_R_SZ;
    TextView PP1_25_R_1;
    TextView PP1_25_R_2;
    TextView PP1_25_R_3;
    TextView PP1_25_R_4;
    TextView PP1_25_R_5;
    TextView PP1_25_R_6;
    TextView PP1_25_R_MP;
    TextView PP1_25_R_SZ;
    TextView PP0_63_R_1;
    TextView PP0_63_R_2;
    TextView PP0_63_R_3;
    TextView PP0_63_R_4;
    TextView PP0_63_R_5;
    TextView PP0_63_R_6;
    TextView PP0_63_R_MP;
    TextView PP0_63_R_SZ;
    TextView PP0_315_R_1;
    TextView PP0_315_R_2;
    TextView PP0_315_R_3;
    TextView PP0_315_R_4;
    TextView PP0_315_R_5;
    TextView PP0_315_R_6;
    TextView PP0_315_R_MP;
    TextView PP0_315_R_SZ;
    TextView PP0_16_R_1;
    TextView PP0_16_R_2;
    TextView PP0_16_R_3;
    TextView PP0_16_R_4;
    TextView PP0_16_R_5;
    TextView PP0_16_R_6;
    TextView PP0_16_R_MP;
    TextView PP0_16_R_SZ;
    TextView PP0_071_R_1;
    TextView PP0_071_R_2;
    TextView PP0_071_R_3;
    TextView PP0_071_R_4;
    TextView PP0_071_R_5;
    TextView PP0_071_R_6;
    TextView PP0_071_R_MP;
    TextView PP0_071_R_SZ;

    ArrayList<TextView> PP_R_OT = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля отображения нормативных проходов

    TextView PP40_R_OT;
    TextView PP20_R_OT;
    TextView PP15_R_OT;
    TextView PP10_R_OT;
    TextView PP5_R_OT;
    TextView PP2_5_R_OT;
    TextView PP1_25_R_OT;
    TextView PP0_63_R_OT;
    TextView PP0_315_R_OT;
    TextView PP0_16_R_OT;
    TextView PP0_071_R_OT;

    ArrayList<TextView> PP_R_DO = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля отображения нормативных проходов

    TextView PP40_R_DO;
    TextView PP20_R_DO;
    TextView PP15_R_DO;
    TextView PP10_R_DO;
    TextView PP5_R_DO;
    TextView PP2_5_R_DO;
    TextView PP1_25_R_DO;
    TextView PP0_63_R_DO;
    TextView PP0_315_R_DO;
    TextView PP0_16_R_DO;
    TextView PP0_071_R_DO;

    // Строка с итоговым зерновыми составов

    ArrayList<TextView> PP_R_Result = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля отображения нормативных проходов

    TextView PP40_R_Result;
    TextView PP20_R_Result;
    TextView PP15_R_Result;
    TextView PP10_R_Result;
    TextView PP5_R_Result;
    TextView PP2_5_R_Result;
    TextView PP1_25_R_Result;
    TextView PP0_63_R_Result;
    TextView PP0_315_R_Result;
    TextView PP0_16_R_Result;
    TextView PP0_071_R_Result;

    TextView PP_Summa;

    // Строка с размерами ячеек сит

    ArrayList<TextView> HEAD_R = new ArrayList<TextView>(); // в этом массиве храним объекты типа View - поля отображения нормативных проходов

    TextView HEAD40_R;
    TextView HEAD20_R;
    TextView HEAD15_R;
    TextView HEAD10_R;
    TextView HEAD5_R;
    TextView HEAD2_5_R;
    TextView HEAD1_25_R;
    TextView HEAD0_63_R;
    TextView HEAD0_315_R;
    TextView HEAD0_16_R;
    TextView HEAD0_071_R;


    // Строка с целевым зерновым составом

    ArrayList<Button> PP_R_Target = new ArrayList<Button>(); // в этом массиве храним объекты типа View - поля отображения нормативных проходов

    Button PP40_R_Target;
    Button PP20_R_Target;
    Button PP15_R_Target;
    Button PP10_R_Target;
    Button PP5_R_Target;
    Button PP2_5_R_Target;
    Button PP1_25_R_Target;
    Button PP0_63_R_Target;
    Button PP0_315_R_Target;
    Button PP0_16_R_Target;
    Button PP0_071_R_Target;

    Button AutoPodbor;


    // кнопки добавить убавить содержание компонента

    Button SOD1Minus;
    Button SOD1Plus;

    Button SOD2Minus;
    Button SOD2Plus;

    Button SOD3Minus;
    Button SOD3Plus;

    Button SOD4Minus;
    Button SOD4Plus;

    Button SOD5Minus;
    Button SOD5Plus;

    Button SOD6Minus;
    Button SOD6Plus;

    Button SODMPMinus;
    Button SODMPPlus;

    Button SODSZMinus;
    Button SODSZPlus;

    Button SOD1;
    Button SOD2;
    Button SOD3;
    Button SOD4;
    Button SOD5;
    Button SOD6;
    Button SODMP;
    Button SODSZ;

    EditText NameOfMaterial1;
    EditText NameOfMaterial2;
    EditText NameOfMaterial3;
    EditText NameOfMaterial4;
    EditText NameOfMaterial5;
    EditText NameOfMaterial6;

    TextView HeadPP_R_1;
    TextView HeadPP_R_2;
    TextView HeadPP_R_3;
    TextView HeadPP_R_4;
    TextView HeadPP_R_5;
    TextView HeadPP_R_6;

    Button GoToWorkCompose;

    TextView SummaCHOG1;
    TextView SummaCHOG2;
    TextView SummaCHOG3;
    TextView SummaCHOG4;
    TextView SummaCHOG5;
    TextView SummaCHOG6;
    TextView SummaCHOG7;
    TextView SummaCHOG8;

    int NumberOfMix = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FindById(); // Связываем View xml и объекты


        for (int i = 0; i < 12; i++) {

            CHOG.get(i).setOnClickListener(this);
            CHOG2.get(i).setOnClickListener(this);
            CHOG3.get(i).setOnClickListener(this);
            CHOG4.get(i).setOnClickListener(this);
            CHOG50.get(i).setOnClickListener(this);
            CHOG6.get(i).setOnClickListener(this);
            if (i < 11) PP_R_Target.get(i).setOnClickListener(this);


            if (i < 6) {

                CHOG_MP.get(i).setOnClickListener(this);
                CHOG_SZ.get(i).setOnClickListener(this);

            }


        }

        DatabaseReader(1);


        Probezhka();

    }


    private void Probezhka() {

        //Считываем пределы зернового состава для выбранной смеси

        recept.SetMix(NumberOfMix);

        SpinnerMixType.setText(recept.NameOfMix);

        for (int i = 0; i < 11; i++) {

            PP_R_OT.get(i).setText(recept.MixUp[i]);
            PP_R_DO.get(i).setText(recept.MixDown[i]);
            HEAD_R.get(10-i).setText(recept.Sita[i]);

        }


        //Собираем названия бункеров и вписываем их в поля рецепта

        if (NameOfMaterial1.getText().length() < 10) {
            HeadPP_R_1.setText(NameOfMaterial1.getText());
            recept.NameOfMaterial1 = NameOfMaterial1.getText().toString();
        } else {
            HeadPP_R_1.setText(NameOfMaterial1.getText().toString().substring(0, 10));
            recept.NameOfMaterial1 = NameOfMaterial1.getText().toString().substring(0, 10);
        }

        if (NameOfMaterial2.getText().length() < 10) {
            HeadPP_R_2.setText(NameOfMaterial2.getText());
            recept.NameOfMaterial2 = NameOfMaterial2.getText().toString();
        } else {
            HeadPP_R_2.setText(NameOfMaterial2.getText().toString().substring(0, 10));
            recept.NameOfMaterial2 = NameOfMaterial2.getText().toString().substring(0, 10);
        }

        if (NameOfMaterial3.getText().length() < 10) {
            HeadPP_R_3.setText(NameOfMaterial3.getText());
            recept.NameOfMaterial3 = NameOfMaterial3.getText().toString();
        } else {
            HeadPP_R_3.setText(NameOfMaterial3.getText().toString().substring(0, 10));
            recept.NameOfMaterial3 = NameOfMaterial3.getText().toString().substring(0, 10);
        }

        if (NameOfMaterial4.getText().length() < 10) {
            HeadPP_R_4.setText(NameOfMaterial4.getText());
            recept.NameOfMaterial4 = NameOfMaterial4.getText().toString();
        } else {
            HeadPP_R_4.setText(NameOfMaterial4.getText().toString().substring(0, 10));
            recept.NameOfMaterial4 = NameOfMaterial4.getText().toString().substring(0, 10);
        }

        if (NameOfMaterial5.getText().length() < 10) {
            HeadPP_R_5.setText(NameOfMaterial5.getText());
            recept.NameOfMaterial5 = NameOfMaterial5.getText().toString();
        } else {
            HeadPP_R_5.setText(NameOfMaterial5.getText().toString().substring(0, 10));
            recept.NameOfMaterial5 = NameOfMaterial5.getText().toString().substring(0, 10);
        }

        if (NameOfMaterial6.getText().length() < 10) {
            HeadPP_R_6.setText(NameOfMaterial6.getText());
            recept.NameOfMaterial6 = NameOfMaterial6.getText().toString();
        } else {
            HeadPP_R_6.setText(NameOfMaterial6.getText().toString().substring(0, 10));
            recept.NameOfMaterial6 = NameOfMaterial6.getText().toString().substring(0, 10);
        }

        //Собираем значения ввода из таблиц зернового состава из записываем их в объекты

        Double SCHOG1 = 0.0;
        Double SCHOG2 = 0.0;
        Double SCHOG3 = 0.0;
        Double SCHOG4 = 0.0;
        Double SCHOG5 = 0.0;
        Double SCHOG6 = 0.0;
        Double SCHOG7 = 0.0;
        Double SCHOG8 = 0.0;


        for (int i = 0; i < 12; i++) {

            MatBunker1.CHOG[i] = Double.parseDouble(CHOG.get(i).getText().toString());
            SCHOG1 = SCHOG1 + MatBunker1.CHOG[i];
            MatBunker2.CHOG[i] = Double.parseDouble(CHOG2.get(i).getText().toString());
            SCHOG2 = SCHOG2 + MatBunker2.CHOG[i];
            MatBunker3.CHOG[i] = Double.parseDouble(CHOG3.get(i).getText().toString());
            SCHOG3 = SCHOG3 + MatBunker3.CHOG[i];
            MatBunker4.CHOG[i] = Double.parseDouble(CHOG4.get(i).getText().toString());
            SCHOG4 = SCHOG4 + MatBunker4.CHOG[i];
            MatBunker5.CHOG[i] = Double.parseDouble(CHOG50.get(i).getText().toString());
            SCHOG5 = SCHOG5 + MatBunker5.CHOG[i];
            MatBunker6.CHOG[i] = Double.parseDouble(CHOG6.get(i).getText().toString());
            SCHOG6 = SCHOG6 + MatBunker6.CHOG[i];
            if (i < 6) {
                MatBunkerMP.CHOG[i] = Double.parseDouble(CHOG_MP.get(i).getText().toString());
                SCHOG7 = SCHOG7 + MatBunkerMP.CHOG[i];
                MatBunkerSZ.CHOG[i] = Double.parseDouble(CHOG_SZ.get(i).getText().toString());
                SCHOG8 = SCHOG8 + MatBunkerSZ.CHOG[i];
            }

        }

        SummaCHOG1.setText(BigDecimal.valueOf(SCHOG1).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        SummaCHOG2.setText(BigDecimal.valueOf(SCHOG2).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        SummaCHOG3.setText(BigDecimal.valueOf(SCHOG3).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        SummaCHOG4.setText(BigDecimal.valueOf(SCHOG4).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        SummaCHOG5.setText(BigDecimal.valueOf(SCHOG5).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        SummaCHOG6.setText(BigDecimal.valueOf(SCHOG6).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        SummaCHOG7.setText(BigDecimal.valueOf(SCHOG7).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        SummaCHOG8.setText(BigDecimal.valueOf(SCHOG8).setScale(0, BigDecimal.ROUND_HALF_UP).toString());


        //Производим расчет зернового состава по каждому материалу

        MatBunker1.CHOP();
        MatBunker1.FullPr();
        MatBunker1.FullOst();

        MatBunker2.CHOP();
        MatBunker2.FullPr();
        MatBunker2.FullOst();

        MatBunker3.CHOP();
        MatBunker3.FullPr();
        MatBunker3.FullOst();

        MatBunker4.CHOP();
        MatBunker4.FullPr();
        MatBunker4.FullOst();

        MatBunker5.CHOP();
        MatBunker5.FullPr();
        MatBunker5.FullOst();

        MatBunker6.CHOP();
        MatBunker6.FullPr();
        MatBunker6.FullOst();

        for (int i = 6; i < 11; i++) {

            MatBunkerMP.CHOG[i] = 0;
            MatBunkerSZ.CHOG[i] = 0;
        }

        MatBunkerMP.CHOP();
        MatBunkerMP.FullPr();
        MatBunkerMP.FullOst();

        MatBunkerSZ.CHOP();
        MatBunkerSZ.FullPr();
        MatBunkerSZ.FullOst();

        //Заполняем таблицы зернового состава по каждому материалу

        for (int i = 0; i < 12; i++) {

            int yourScale1 = 1;

            CHOP.get(i).setText(BigDecimal.valueOf(MatBunker1.CHOP[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PO.get(i).setText(BigDecimal.valueOf(MatBunker1.FullOst[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP.get(i).setText(BigDecimal.valueOf(MatBunker1.FullPr[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());

            CHOP2.get(i).setText(BigDecimal.valueOf(MatBunker2.CHOP[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PO2.get(i).setText(BigDecimal.valueOf(MatBunker2.FullOst[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP2.get(i).setText(BigDecimal.valueOf(MatBunker2.FullPr[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());

            CHOP3.get(i).setText(BigDecimal.valueOf(MatBunker3.CHOP[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PO3.get(i).setText(BigDecimal.valueOf(MatBunker3.FullOst[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP3.get(i).setText(BigDecimal.valueOf(MatBunker3.FullPr[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());

            CHOP4.get(i).setText(BigDecimal.valueOf(MatBunker4.CHOP[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PO4.get(i).setText(BigDecimal.valueOf(MatBunker4.FullOst[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP4.get(i).setText(BigDecimal.valueOf(MatBunker4.FullPr[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());

            CHOP50.get(i).setText(BigDecimal.valueOf(MatBunker5.CHOP[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PO50.get(i).setText(BigDecimal.valueOf(MatBunker5.FullOst[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP50.get(i).setText(BigDecimal.valueOf(MatBunker5.FullPr[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());

            CHOP6.get(i).setText(BigDecimal.valueOf(MatBunker6.CHOP[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PO6.get(i).setText(BigDecimal.valueOf(MatBunker6.FullOst[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP6.get(i).setText(BigDecimal.valueOf(MatBunker6.FullPr[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());

            if (i < 6) {
                CHOP_MP.get(i).setText(BigDecimal.valueOf(MatBunkerMP.CHOP[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
                PO_MP.get(i).setText(BigDecimal.valueOf(MatBunkerMP.FullOst[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
                PP_MP.get(i).setText(BigDecimal.valueOf(MatBunkerMP.FullPr[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());

                CHOP_SZ.get(i).setText(BigDecimal.valueOf(MatBunkerSZ.CHOP[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
                PO_SZ.get(i).setText(BigDecimal.valueOf(MatBunkerSZ.FullOst[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
                PP_SZ.get(i).setText(BigDecimal.valueOf(MatBunkerSZ.FullPr[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            }
        }

        //Собираем из полей введнный зерновой состав целевой кривой

        for (int i = 0; i < 11; i++) {

            recept.PP_Target[i] = Double.parseDouble(PP_R_Target.get(i).getText().toString());

        }

        //Собираем из полей введнные дозировки и записываем их в переменные объекта recept

        recept.SOD1 = Double.parseDouble(SOD1.getText().toString());
        recept.SOD2 = Double.parseDouble(SOD2.getText().toString());
        recept.SOD3 = Double.parseDouble(SOD3.getText().toString());
        recept.SOD4 = Double.parseDouble(SOD4.getText().toString());
        recept.SOD5 = Double.parseDouble(SOD5.getText().toString());
        recept.SOD6 = Double.parseDouble(SOD6.getText().toString());
        recept.SODMP = Double.parseDouble(SODMP.getText().toString());
        recept.SODSZ = Double.parseDouble(SODSZ.getText().toString());

        //передаем полные проходы по каждому материалу в объект recept для расчета полных проходов с учетом дозировки и расчета результирующей кривой

        for (int i = 0; i < 11; i++) {
            recept.PP1[i] = Double.parseDouble(PP.get(i + 1).getText().toString());
            recept.PP2[i] = Double.parseDouble(PP2.get(i + 1).getText().toString());
            recept.PP3[i] = Double.parseDouble(PP3.get(i + 1).getText().toString());
            recept.PP4[i] = Double.parseDouble(PP4.get(i + 1).getText().toString());
            recept.PP5[i] = Double.parseDouble(PP50.get(i + 1).getText().toString());
            recept.PP6[i] = Double.parseDouble(PP6.get(i + 1).getText().toString());
        }
        for (int i = 0; i < 5; i++) {
            recept.PPMP[i] = Double.parseDouble(PP_MP.get(i + 1).getText().toString());
            recept.PPSZ[i] = Double.parseDouble(PP_SZ.get(i + 1).getText().toString());
        }

        for (int i = 5; i < 11; i++) {

            recept.PPMP[i] = MatBunkerMP.FullPr[i + 1];
            recept.PPSZ[i] = MatBunkerSZ.FullPr[i + 1];

        }


        //рассчитываем полные проходы с учетом дозировки и результирующую кривую

        recept.Calculate();

        for (int i = 0; i < 11; i++) {
            int yourScale1 = 1;
            PP_R_1.get(i).setText(BigDecimal.valueOf(recept.PP1R[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP_R_2.get(i).setText(BigDecimal.valueOf(recept.PP2R[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP_R_3.get(i).setText(BigDecimal.valueOf(recept.PP3R[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP_R_4.get(i).setText(BigDecimal.valueOf(recept.PP4R[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP_R_5.get(i).setText(BigDecimal.valueOf(recept.PP5R[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP_R_6.get(i).setText(BigDecimal.valueOf(recept.PP6R[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP_R_Result.get(i).setText(BigDecimal.valueOf(recept.PP_Result[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());

            PP_Summa.setText(BigDecimal.valueOf(recept.SOD_Summa).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
        }
        for (int i = 0; i < 11; i++) {
            int yourScale1 = 1;
            PP_R_MP.get(i).setText(BigDecimal.valueOf(recept.PPMPR[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
            PP_R_SZ.get(i).setText(BigDecimal.valueOf(recept.PPSZR[i]).setScale(yourScale1, BigDecimal.ROUND_HALF_UP).toString());
        }

        DatabaseWriter(1); //Запуск процедуры сбора данных их полей активити и их запись в базу данных

    }

    @Override
    public void onClick(View v) {

        Double number;
        int UniversalID = 0;
        String Value = "";
        boolean UniversalPushDetector = false;

        switch (v.getId()) {


            //Обработка переходов на MixChooser

            case R.id.SpinnerMixType:

                Intent intent = new Intent(this, MixChooser.class);
                intent.putExtra("NUMBER_OF_MIX", NumberOfMix);
                startActivityForResult(intent, 1);

                break;
            //Обработка переходов на UniversalKeyboard

            case R.id.GoToWorkCompose:
                Probezhka();
                Intent intent2 = new Intent(this, WorkComposeActivity.class);
                intent2.putExtra("OBJECT", recept);
                //intent2.putExtra("CHOGID", ChogID);
                //intent2.putExtra("BUNKERID", BunkerID);
                startActivityForResult(intent2, 1);

                break;

            case R.id.SOD1:
                UniversalPushDetector = true;
                UniversalID = 1;
                Value = String.valueOf(SOD1.getText());
                break;
            case R.id.SOD2:
                UniversalPushDetector = true;
                UniversalID = 2;
                Value = String.valueOf(SOD2.getText());
                break;
            case R.id.SOD3:
                UniversalPushDetector = true;
                UniversalID = 3;
                Value = String.valueOf(SOD3.getText());
                break;
            case R.id.SOD4:
                UniversalPushDetector = true;
                UniversalID = 4;
                Value = String.valueOf(SOD4.getText());
                break;
            case R.id.SOD5:
                UniversalPushDetector = true;
                UniversalID = 5;
                Value = String.valueOf(SOD5.getText());
                break;
            case R.id.SOD6:
                UniversalPushDetector = true;
                UniversalID = 6;
                Value = String.valueOf(SOD6.getText());
                break;
            case R.id.SODMP:
                UniversalPushDetector = true;
                UniversalID = 7;
                Value = String.valueOf(SODMP.getText());
                break;
            case R.id.SODSZ:
                UniversalPushDetector = true;
                UniversalID = 8;
                Value = String.valueOf(SODSZ.getText());
                break;

            case R.id.PP40_R_TargetCurve:
                UniversalPushDetector = true;
                UniversalID = 21;
                Value = String.valueOf(PP40_R_Target.getText());
                break;
            case R.id.PP20_R_TargetCurve:
                UniversalPushDetector = true;
                UniversalID = 22;
                Value = String.valueOf(PP20_R_Target.getText());
                break;
            case R.id.PP15_R_TargetCurve:
                UniversalPushDetector = true;
                UniversalID = 23;
                Value = String.valueOf(PP15_R_Target.getText());
                break;
            case R.id.PP10_R_TargetCurve:
                UniversalPushDetector = true;
                UniversalID = 24;
                Value = String.valueOf(PP10_R_Target.getText());
                break;
            case R.id.PP5_R_TargetCurve:
                UniversalPushDetector = true;
                UniversalID = 25;
                Value = String.valueOf(PP5_R_Target.getText());
                break;
            case R.id.PP2_5_R_TargetCurve:
                UniversalPushDetector = true;
                UniversalID = 26;
                Value = String.valueOf(PP2_5_R_Target.getText());
                break;
            case R.id.PP1_25_R_TargetCurve:
                UniversalPushDetector = true;
                UniversalID = 27;
                Value = String.valueOf(PP1_25_R_Target.getText());
                break;
            case R.id.PP0_63_R_TargetCurve:
                UniversalPushDetector = true;
                UniversalID = 28;
                Value = String.valueOf(PP0_63_R_Target.getText());
                break;
            case R.id.PP0_315_R_TargetCurve:
                UniversalPushDetector = true;
                UniversalID = 29;
                Value = String.valueOf(PP0_315_R_Target.getText());
                break;
            case R.id.PP0_16_R_TargetCurve:
                UniversalPushDetector = true;
                UniversalID = 30;
                Value = String.valueOf(PP0_16_R_Target.getText());
                break;
            case R.id.PP0_071_R_TargetCurve:
                UniversalPushDetector = true;
                UniversalID = 31;
                Value = String.valueOf(PP0_071_R_Target.getText());
                break;

            case R.id.AutoPodbor:


               /* for (int i = 0; i < 11; i++) {
                    PP_R_Target.get(i).setText(BigDecimal.valueOf(recept.PP_Target[i]).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                }
                recept.AutoPodbor();

                if (recept.Koridor == 0) {
                    Toast toast = Toast.makeText(this, "Не подходящие материалы", Toast.LENGTH_LONG);

                    toast.show();

                } else {
                    Toast toast = Toast.makeText(this, "Вошли в коридор плюс минус " + recept.Koridor, Toast.LENGTH_LONG);

                    toast.show();
                }

                SOD1.setText(BigDecimal.valueOf(recept.SOD1).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                SOD2.setText(BigDecimal.valueOf(recept.SOD2).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                SOD3.setText(BigDecimal.valueOf(recept.SOD3).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                SOD4.setText(BigDecimal.valueOf(recept.SOD4).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                SOD5.setText(BigDecimal.valueOf(recept.SOD5).setScale(1, BigDecimal.ROUND_HALF_UP).toString());*/


                Probezhka();


                break;


            case R.id.SOD1Minus:

                number = Double.parseDouble(SOD1.getText().toString()) - 1;
                if (number >= 0) SOD1.setText(number.toString());
                Probezhka();

                break;

            case R.id.SOD1Plus:

                number = Double.parseDouble(SOD1.getText().toString()) + 1;
                SOD1.setText(number.toString());
                Probezhka();

                break;

            case R.id.SOD2Minus:

                number = Double.parseDouble(SOD2.getText().toString()) - 1;
                if (number >= 0) SOD2.setText(number.toString());
                Probezhka();
                break;

            case R.id.SOD2Plus:

                number = Double.parseDouble(SOD2.getText().toString()) + 1;
                SOD2.setText(number.toString());
                Probezhka();
                break;

            case R.id.SOD3Minus:

                number = Double.parseDouble(SOD3.getText().toString()) - 1;
                if (number >= 0) SOD3.setText(number.toString());
                Probezhka();
                break;

            case R.id.SOD3Plus:

                number = Double.parseDouble(SOD3.getText().toString()) + 1;
                SOD3.setText(number.toString());
                Probezhka();
                break;

            case R.id.SOD4Minus:

                number = Double.parseDouble(SOD4.getText().toString()) - 1;
                if (number >= 0) SOD4.setText(number.toString());
                Probezhka();
                break;

            case R.id.SOD4Plus:

                number = Double.parseDouble(SOD4.getText().toString()) + 1;
                SOD4.setText(number.toString());
                Probezhka();
                break;

            case R.id.SOD5Minus:

                number = Double.parseDouble(SOD5.getText().toString()) - 1;
                if (number >= 0) SOD5.setText(number.toString());
                Probezhka();
                break;

            case R.id.SOD5Plus:

                number = Double.parseDouble(SOD5.getText().toString()) + 1;
                SOD5.setText(number.toString());
                Probezhka();
                break;

            case R.id.SOD6Minus:

                number = Double.parseDouble(SOD6.getText().toString()) - 1;
                if (number >= 0) SOD6.setText(number.toString());
                Probezhka();
                break;

            case R.id.SOD6Plus:

                number = Double.parseDouble(SOD6.getText().toString()) + 1;
                SOD6.setText(number.toString());
                Probezhka();
                break;

            case R.id.SODMPMinus:

                number = Double.parseDouble(SODMP.getText().toString()) - 1;
                if (number >= 0) SODMP.setText(number.toString());
                Probezhka();
                break;

            case R.id.SODMPPlus:

                number = Double.parseDouble(SODMP.getText().toString()) + 1;
                SODMP.setText(number.toString());
                Probezhka();
                break;

            case R.id.SODSZMinus:

                number = Double.parseDouble(SODSZ.getText().toString()) - 1;
                if (number >= 0) SODSZ.setText(number.toString());
                Probezhka();
                break;

            case R.id.SODSZPlus:

                number = Double.parseDouble(SODSZ.getText().toString()) + 1;
                SODSZ.setText(number.toString());
                Probezhka();
                break;


        }

        boolean PushChogDetector = false;
        Material TransMaterial = MatBunker1;
        int ChogID = 0;
        int BunkerID = 0;

        if (v.getId() == R.id.CHOG40 || v.getId() == R.id.CHOG20 || v.getId() == R.id.CHOG15 || v.getId() == R.id.CHOG10 || v.getId() == R.id.CHOG5 || v.getId() == R.id.CHOG2_5 || v.getId() == R.id.CHOG1_25
                || v.getId() == R.id.CHOG0_63 || v.getId() == R.id.CHOG0_315 || v.getId() == R.id.CHOG0_16 || v.getId() == R.id.CHOG0_071 || v.getId() == R.id.CHOGDNO) {
            PushChogDetector = true;
            BunkerID = 1;
            TransMaterial = MatBunker1;
        }
        if (v.getId() == R.id.CHOG40_2 || v.getId() == R.id.CHOG20_2 || v.getId() == R.id.CHOG15_2 || v.getId() == R.id.CHOG10_2 || v.getId() == R.id.CHOG5_2 || v.getId() == R.id.CHOG2_5_2 || v.getId() == R.id.CHOG1_25_2
                || v.getId() == R.id.CHOG0_63_2 || v.getId() == R.id.CHOG0_315_2 || v.getId() == R.id.CHOG0_16_2 || v.getId() == R.id.CHOG0_071_2 || v.getId() == R.id.CHOGDNO_2) {
            PushChogDetector = true;
            BunkerID = 2;
            TransMaterial = MatBunker2;
        }
        if (v.getId() == R.id.CHOG40_3 || v.getId() == R.id.CHOG20_3 || v.getId() == R.id.CHOG15_3 || v.getId() == R.id.CHOG10_3 || v.getId() == R.id.CHOG5_3 || v.getId() == R.id.CHOG2_5_3 || v.getId() == R.id.CHOG1_25_3
                || v.getId() == R.id.CHOG0_63_3 || v.getId() == R.id.CHOG0_315_3 || v.getId() == R.id.CHOG0_16_3 || v.getId() == R.id.CHOG0_071_3 || v.getId() == R.id.CHOGDNO_3) {
            PushChogDetector = true;
            BunkerID = 3;
            TransMaterial = MatBunker3;
        }
        if (v.getId() == R.id.CHOG40_4 || v.getId() == R.id.CHOG20_4 || v.getId() == R.id.CHOG15_4 || v.getId() == R.id.CHOG10_4 || v.getId() == R.id.CHOG5_4 || v.getId() == R.id.CHOG2_5_4 || v.getId() == R.id.CHOG1_25_4
                || v.getId() == R.id.CHOG0_63_4 || v.getId() == R.id.CHOG0_315_4 || v.getId() == R.id.CHOG0_16_4 || v.getId() == R.id.CHOG0_071_4 || v.getId() == R.id.CHOGDNO_4) {
            PushChogDetector = true;
            BunkerID = 4;
            TransMaterial = MatBunker4;
        }
        if (v.getId() == R.id.CHOG40_5 || v.getId() == R.id.CHOG20_5 || v.getId() == R.id.CHOG15_5 || v.getId() == R.id.CHOG10_5 || v.getId() == R.id.CHOG5_5 || v.getId() == R.id.CHOG2_5_5 || v.getId() == R.id.CHOG1_25_5
                || v.getId() == R.id.CHOG0_63_5 || v.getId() == R.id.CHOG0_315_5 || v.getId() == R.id.CHOG0_16_5 || v.getId() == R.id.CHOG0_071_5 || v.getId() == R.id.CHOGDNO_5) {
            PushChogDetector = true;
            BunkerID = 5;
            TransMaterial = MatBunker5;
        }
        if (v.getId() == R.id.CHOG40_6 || v.getId() == R.id.CHOG20_6 || v.getId() == R.id.CHOG15_6 || v.getId() == R.id.CHOG10_6 || v.getId() == R.id.CHOG5_6 || v.getId() == R.id.CHOG2_5_6 || v.getId() == R.id.CHOG1_25_6
                || v.getId() == R.id.CHOG0_63_6 || v.getId() == R.id.CHOG0_315_6 || v.getId() == R.id.CHOG0_16_6 || v.getId() == R.id.CHOG0_071_6 || v.getId() == R.id.CHOGDNO_6) {
            PushChogDetector = true;
            BunkerID = 6;
            TransMaterial = MatBunker6;
        }
        if (v.getId() == R.id.CHOG1_25_MP || v.getId() == R.id.CHOG0_63_MP || v.getId() == R.id.CHOG0_315_MP || v.getId() == R.id.CHOG0_16_MP || v.getId() == R.id.CHOG0_071_MP || v.getId() == R.id.CHOGDNO_MP) {

            PushChogDetector = true;
            BunkerID = 7;
            TransMaterial = MatBunkerMP;
        }
        if (v.getId() == R.id.CHOG1_25_SZ || v.getId() == R.id.CHOG0_63_SZ || v.getId() == R.id.CHOG0_315_SZ || v.getId() == R.id.CHOG0_16_SZ || v.getId() == R.id.CHOG0_071_SZ || v.getId() == R.id.CHOGDNO_SZ) {

            PushChogDetector = true;
            BunkerID = 8;
            TransMaterial = MatBunkerSZ;
        }


        if (v.getId() == R.id.CHOG40 || v.getId() == R.id.CHOG40_2 || v.getId() == R.id.CHOG40_3 || v.getId() == R.id.CHOG40_4 || v.getId() == R.id.CHOG40_5 || v.getId() == R.id.CHOG40_6) {

            ChogID = TransMaterial.CHOG.length - 1;
        }
        if (v.getId() == R.id.CHOG20 || v.getId() == R.id.CHOG20_2 || v.getId() == R.id.CHOG20_3 || v.getId() == R.id.CHOG20_4 || v.getId() == R.id.CHOG20_5 || v.getId() == R.id.CHOG20_6) {

            ChogID = TransMaterial.CHOG.length - 2;
        }
        if (v.getId() == R.id.CHOG15 || v.getId() == R.id.CHOG15_2 || v.getId() == R.id.CHOG15_3 || v.getId() == R.id.CHOG15_4 || v.getId() == R.id.CHOG15_5 || v.getId() == R.id.CHOG15_6) {

            ChogID = TransMaterial.CHOG.length - 3;
        }
        if (v.getId() == R.id.CHOG10 || v.getId() == R.id.CHOG10_2 || v.getId() == R.id.CHOG10_3 || v.getId() == R.id.CHOG10_4 || v.getId() == R.id.CHOG10_5 || v.getId() == R.id.CHOG10_6) {

            ChogID = TransMaterial.CHOG.length - 4;
        }
        if (v.getId() == R.id.CHOG5 || v.getId() == R.id.CHOG5_2 || v.getId() == R.id.CHOG5_3 || v.getId() == R.id.CHOG5_4 || v.getId() == R.id.CHOG5_5 || v.getId() == R.id.CHOG5_6) {

            ChogID = TransMaterial.CHOG.length - 5;
        }
        if (v.getId() == R.id.CHOG2_5 || v.getId() == R.id.CHOG2_5_2 || v.getId() == R.id.CHOG2_5_3 || v.getId() == R.id.CHOG2_5_4 || v.getId() == R.id.CHOG2_5_5 || v.getId() == R.id.CHOG2_5_6) {

            ChogID = TransMaterial.CHOG.length - 6;
        }
        if (v.getId() == R.id.CHOG1_25 || v.getId() == R.id.CHOG1_25_2 || v.getId() == R.id.CHOG1_25_3 || v.getId() == R.id.CHOG1_25_4 || v.getId() == R.id.CHOG1_25_5 || v.getId() == R.id.CHOG1_25_6 || v.getId() == R.id.CHOG1_25_MP || v.getId() == R.id.CHOG1_25_SZ) {

            ChogID = TransMaterial.CHOG.length - 7;
        }
        if (v.getId() == R.id.CHOG0_63 || v.getId() == R.id.CHOG0_63_2 || v.getId() == R.id.CHOG0_63_3 || v.getId() == R.id.CHOG0_63_4 || v.getId() == R.id.CHOG0_63_5 || v.getId() == R.id.CHOG0_63_6 || v.getId() == R.id.CHOG0_63_MP || v.getId() == R.id.CHOG0_63_SZ) {

            ChogID = TransMaterial.CHOG.length - 8;
        }
        if (v.getId() == R.id.CHOG0_315 || v.getId() == R.id.CHOG0_315_2 || v.getId() == R.id.CHOG0_315_3 || v.getId() == R.id.CHOG0_315_4 || v.getId() == R.id.CHOG0_315_5 || v.getId() == R.id.CHOG0_315_6 || v.getId() == R.id.CHOG0_315_MP || v.getId() == R.id.CHOG0_315_SZ) {

            ChogID = TransMaterial.CHOG.length - 9;
        }
        if (v.getId() == R.id.CHOG0_16 || v.getId() == R.id.CHOG0_16_2 || v.getId() == R.id.CHOG0_16_3 || v.getId() == R.id.CHOG0_16_4 || v.getId() == R.id.CHOG0_16_5 || v.getId() == R.id.CHOG0_16_6 || v.getId() == R.id.CHOG0_16_MP || v.getId() == R.id.CHOG0_16_SZ) {

            ChogID = TransMaterial.CHOG.length - 10;
        }
        if (v.getId() == R.id.CHOG0_071 || v.getId() == R.id.CHOG0_071_2 || v.getId() == R.id.CHOG0_071_3 || v.getId() == R.id.CHOG0_071_4 || v.getId() == R.id.CHOG0_071_5 || v.getId() == R.id.CHOG0_071_6 || v.getId() == R.id.CHOG0_071_MP || v.getId() == R.id.CHOG0_071_SZ) {

            ChogID = TransMaterial.CHOG.length - 11;
        }
        if (v.getId() == R.id.CHOGDNO || v.getId() == R.id.CHOGDNO_2 || v.getId() == R.id.CHOGDNO_3 || v.getId() == R.id.CHOGDNO_4 || v.getId() == R.id.CHOGDNO_5 || v.getId() == R.id.CHOGDNO_6 || v.getId() == R.id.CHOGDNO_MP || v.getId() == R.id.CHOGDNO_SZ) {

            ChogID = TransMaterial.CHOG.length - 12;
        }

        if (PushChogDetector) {
            PushChogDetector = false;
            Intent intent = new Intent(this, Keyboard.class);
            intent.putExtra("OBJECT", TransMaterial);
            intent.putExtra("CHOGID", ChogID);
            intent.putExtra("BUNKERID", BunkerID);
            startActivityForResult(intent, 1);
        }

        if (UniversalPushDetector) {
            UniversalPushDetector = false;
            Intent intent = new Intent(this, UniversalKeyboard.class);
            intent.putExtra("UniversalID", UniversalID);
            intent.putExtra("Value", Value);
            startActivityForResult(intent, 1);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data == null) {
            return;
        }

        //Тут ловим номер смеси из MixChoosera

        NumberOfMix = data.getIntExtra("NUMBER_OF_MIX_BACK", 1);

        //Блок обработки возврата из KeyboardActivity
        int BunkerID;

        BunkerID = data.getIntExtra("BUNKERID_BACK", 0);

        if (BunkerID == 1) {
            MatBunker1 = (Material) data.getSerializableExtra("OBJECT_BACK");

            for (int i = 0; i < CHOG.size(); i++) {
                CHOG.get(i).setText(BigDecimal.valueOf(MatBunker1.CHOG[i]).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                //CHOG.get(i).setText(String.format(Double.toString(MatBunker1.CHOG[i]), "%.3f"));
            }
        }
        if (BunkerID == 2) {
            MatBunker2 = (Material) data.getSerializableExtra("OBJECT_BACK");
            for (int i = 0; i < CHOG2.size(); i++) {
                CHOG2.get(i).setText(BigDecimal.valueOf(MatBunker2.CHOG[i]).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
            }
        }
        if (BunkerID == 3) {
            MatBunker3 = (Material) data.getSerializableExtra("OBJECT_BACK");

            for (int i = 0; i < CHOG3.size(); i++) {
                CHOG3.get(i).setText(BigDecimal.valueOf(MatBunker3.CHOG[i]).setScale(1, BigDecimal.ROUND_HALF_UP).toString());

            }

        }
        if (BunkerID == 4) {
            MatBunker4 = (Material) data.getSerializableExtra("OBJECT_BACK");
            for (int i = 0; i < CHOG4.size(); i++) {
                CHOG4.get(i).setText(BigDecimal.valueOf(MatBunker4.CHOG[i]).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
            }
        }
        if (BunkerID == 5) {
            MatBunker5 = (Material) data.getSerializableExtra("OBJECT_BACK");
            for (int i = 0; i < CHOG50.size(); i++) {
                CHOG50.get(i).setText(BigDecimal.valueOf(MatBunker5.CHOG[i]).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
            }
        }
        if (BunkerID == 6) {
            MatBunker6 = (Material) data.getSerializableExtra("OBJECT_BACK");
            for (int i = 0; i < CHOG6.size(); i++) {
                CHOG6.get(i).setText(BigDecimal.valueOf(MatBunker6.CHOG[i]).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
            }
        }
        if (BunkerID == 7) {
            MatBunkerMP = (Material) data.getSerializableExtra("OBJECT_BACK");
            for (int i = 0; i < 6; i++) {
                CHOG_MP.get(i).setText(BigDecimal.valueOf(MatBunkerMP.CHOG[i]).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
            }
        }
        if (BunkerID == 8) {
            MatBunkerSZ = (Material) data.getSerializableExtra("OBJECT_BACK");
            for (int i = 0; i < 6; i++) {
                CHOG_SZ.get(i).setText(BigDecimal.valueOf(MatBunkerSZ.CHOG[i]).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
            }
        }

        //Блок обработки возврата из UniversalKeyboardActivity

        int UniversalID;
        UniversalID = data.getIntExtra("UNIVERSALID_BACK", 0);

        if (UniversalID == 1) SOD1.setText(data.getStringExtra("VALUE_BACK"));
        if (UniversalID == 2) SOD2.setText(data.getStringExtra("VALUE_BACK"));
        if (UniversalID == 3) SOD3.setText(data.getStringExtra("VALUE_BACK"));
        if (UniversalID == 4) SOD4.setText(data.getStringExtra("VALUE_BACK"));
        if (UniversalID == 5) SOD5.setText(data.getStringExtra("VALUE_BACK"));
        if (UniversalID == 6) SOD6.setText(data.getStringExtra("VALUE_BACK"));
        if (UniversalID == 7) SODMP.setText(data.getStringExtra("VALUE_BACK"));
        if (UniversalID == 8) SODSZ.setText(data.getStringExtra("VALUE_BACK"));

        if (UniversalID == 21) {
            if (data.getStringExtra("VALUE_BACK").equals("100.0")) PP40_R_Target.setText("100");
            else PP40_R_Target.setText(data.getStringExtra("VALUE_BACK"));
        }
        if (UniversalID == 22) {
            if (data.getStringExtra("VALUE_BACK").equals("100.0")) PP20_R_Target.setText("100");
            else PP20_R_Target.setText(data.getStringExtra("VALUE_BACK"));
        }
        if (UniversalID == 23) {
            if (data.getStringExtra("VALUE_BACK").equals("100.0")) PP15_R_Target.setText("100");
            else PP15_R_Target.setText(data.getStringExtra("VALUE_BACK"));
        }
        if (UniversalID == 24) {
            if (data.getStringExtra("VALUE_BACK").equals("100.0")) PP10_R_Target.setText("100");
            else PP10_R_Target.setText(data.getStringExtra("VALUE_BACK"));
        }
        if (UniversalID == 25) {
            if (data.getStringExtra("VALUE_BACK").equals("100.0")) PP5_R_Target.setText("100");
            else PP5_R_Target.setText(data.getStringExtra("VALUE_BACK"));
        }
        if (UniversalID == 26) {
            if (data.getStringExtra("VALUE_BACK").equals("100.0")) PP2_5_R_Target.setText("100");
            else PP2_5_R_Target.setText(data.getStringExtra("VALUE_BACK"));
        }
        if (UniversalID == 27) {
            if (data.getStringExtra("VALUE_BACK").equals("100.0")) PP1_25_R_Target.setText("100");
            else PP1_25_R_Target.setText(data.getStringExtra("VALUE_BACK"));
        }
        if (UniversalID == 28) {
            if (data.getStringExtra("VALUE_BACK").equals("100.0")) PP0_63_R_Target.setText("100");
            else PP0_63_R_Target.setText(data.getStringExtra("VALUE_BACK"));
        }
        if (UniversalID == 29) {
            if (data.getStringExtra("VALUE_BACK").equals("100.0")) PP0_315_R_Target.setText("100");
            else PP0_315_R_Target.setText(data.getStringExtra("VALUE_BACK"));
        }
        if (UniversalID == 30) {
            if (data.getStringExtra("VALUE_BACK").equals("100.0")) PP0_16_R_Target.setText("100");
            else PP0_16_R_Target.setText(data.getStringExtra("VALUE_BACK"));
        }
        if (UniversalID == 31) {
            if (data.getStringExtra("VALUE_BACK").equals("100.0")) PP0_071_R_Target.setText("100");
            else PP0_071_R_Target.setText(data.getStringExtra("VALUE_BACK"));
        }

        Probezhka();


    }

    public void FindById() {

        SpinnerMixType = (Button) findViewById(R.id.SpinnerMixType);

        //Связываем объекты с полями xml формы - Таблица зернового состава №1

        CHOG40 = (TextView) findViewById(R.id.CHOG40);
        CHOG20 = (TextView) findViewById(R.id.CHOG20);
        CHOG15 = (TextView) findViewById(R.id.CHOG15);
        CHOG10 = (TextView) findViewById(R.id.CHOG10);
        CHOG5 = (TextView) findViewById(R.id.CHOG5);
        CHOG2_5 = (TextView) findViewById(R.id.CHOG2_5);
        CHOG1_25 = (TextView) findViewById(R.id.CHOG1_25);
        CHOG0_63 = (TextView) findViewById(R.id.CHOG0_63);
        CHOG0_315 = (TextView) findViewById(R.id.CHOG0_315);
        CHOG0_16 = (TextView) findViewById(R.id.CHOG0_16);
        CHOG0_071 = (TextView) findViewById(R.id.CHOG0_071);
        CHOGDNO = (TextView) findViewById(R.id.CHOGDNO);

        // Кладем подготовленные и связанные объекты в массив

        CHOG.add(0, CHOGDNO);
        CHOG.add(1, CHOG0_071);
        CHOG.add(2, CHOG0_16);
        CHOG.add(3, CHOG0_315);
        CHOG.add(4, CHOG0_63);
        CHOG.add(5, CHOG1_25);
        CHOG.add(6, CHOG2_5);
        CHOG.add(7, CHOG5);
        CHOG.add(8, CHOG10);
        CHOG.add(9, CHOG15);
        CHOG.add(10, CHOG20);
        CHOG.add(11, CHOG40);

        CHOP40 = (TextView) findViewById(R.id.CHOP40);
        CHOP20 = (TextView) findViewById(R.id.CHOP20);
        CHOP15 = (TextView) findViewById(R.id.CHOP15);
        CHOP10 = (TextView) findViewById(R.id.CHOP10);
        CHOP5 = (TextView) findViewById(R.id.CHOP5);
        CHOP2_5 = (TextView) findViewById(R.id.CHOP2_5);
        CHOP1_25 = (TextView) findViewById(R.id.CHOP1_25);
        CHOP0_63 = (TextView) findViewById(R.id.CHOP0_63);
        CHOP0_315 = (TextView) findViewById(R.id.CHOP0_315);
        CHOP0_16 = (TextView) findViewById(R.id.CHOP0_16);
        CHOP0_071 = (TextView) findViewById(R.id.CHOP0_071);
        CHOPDNO = (TextView) findViewById(R.id.CHOPDNO);

        CHOP.add(0, CHOPDNO);
        CHOP.add(1, CHOP0_071);
        CHOP.add(2, CHOP0_16);
        CHOP.add(3, CHOP0_315);
        CHOP.add(4, CHOP0_63);
        CHOP.add(5, CHOP1_25);
        CHOP.add(6, CHOP2_5);
        CHOP.add(7, CHOP5);
        CHOP.add(8, CHOP10);
        CHOP.add(9, CHOP15);
        CHOP.add(10, CHOP20);
        CHOP.add(11, CHOP40);

        PO40 = (TextView) findViewById(R.id.PO40);
        PO20 = (TextView) findViewById(R.id.PO20);
        PO15 = (TextView) findViewById(R.id.PO15);
        PO10 = (TextView) findViewById(R.id.PO10);
        PO5 = (TextView) findViewById(R.id.PO5);
        PO2_5 = (TextView) findViewById(R.id.PO2_5);
        PO1_25 = (TextView) findViewById(R.id.PO1_25);
        PO0_63 = (TextView) findViewById(R.id.PO0_63);
        PO0_315 = (TextView) findViewById(R.id.PO0_315);
        PO0_16 = (TextView) findViewById(R.id.PO0_16);
        PO0_071 = (TextView) findViewById(R.id.PO0_071);
        PODNO = (TextView) findViewById(R.id.PODNO);

        PO.add(0, PODNO);
        PO.add(1, PO0_071);
        PO.add(2, PO0_16);
        PO.add(3, PO0_315);
        PO.add(4, PO0_63);
        PO.add(5, PO1_25);
        PO.add(6, PO2_5);
        PO.add(7, PO5);
        PO.add(8, PO10);
        PO.add(9, PO15);
        PO.add(10, PO20);
        PO.add(11, PO40);

        PP40 = (TextView) findViewById(R.id.PP40);
        PP20 = (TextView) findViewById(R.id.PP20);
        PP15 = (TextView) findViewById(R.id.PP15);
        PP10 = (TextView) findViewById(R.id.PP10);
        PP5 = (TextView) findViewById(R.id.PP5);
        PP2_5 = (TextView) findViewById(R.id.PP2_5);
        PP1_25 = (TextView) findViewById(R.id.PP1_25);
        PP0_63 = (TextView) findViewById(R.id.PP0_63);
        PP0_315 = (TextView) findViewById(R.id.PP0_315);
        PP0_16 = (TextView) findViewById(R.id.PP0_16);
        PP0_071 = (TextView) findViewById(R.id.PP0_071);
        PPDNO = (TextView) findViewById(R.id.PPDNO);

        PP.add(0, PPDNO);
        PP.add(1, PP0_071);
        PP.add(2, PP0_16);
        PP.add(3, PP0_315);
        PP.add(4, PP0_63);
        PP.add(5, PP1_25);
        PP.add(6, PP2_5);
        PP.add(7, PP5);
        PP.add(8, PP10);
        PP.add(9, PP15);
        PP.add(10, PP20);
        PP.add(11, PP40);

        //Связываем объекты с полями xml формы - Таблица зернового состава №2

        CHOG40_2 = (TextView) findViewById(R.id.CHOG40_2);
        CHOG20_2 = (TextView) findViewById(R.id.CHOG20_2);
        CHOG15_2 = (TextView) findViewById(R.id.CHOG15_2);
        CHOG10_2 = (TextView) findViewById(R.id.CHOG10_2);
        CHOG5_2 = (TextView) findViewById(R.id.CHOG5_2);
        CHOG2_5_2 = (TextView) findViewById(R.id.CHOG2_5_2);
        CHOG1_25_2 = (TextView) findViewById(R.id.CHOG1_25_2);
        CHOG0_63_2 = (TextView) findViewById(R.id.CHOG0_63_2);
        CHOG0_315_2 = (TextView) findViewById(R.id.CHOG0_315_2);
        CHOG0_16_2 = (TextView) findViewById(R.id.CHOG0_16_2);
        CHOG0_071_2 = (TextView) findViewById(R.id.CHOG0_071_2);
        CHOGDNO_2 = (TextView) findViewById(R.id.CHOGDNO_2);

        // Кладем подготовленные и связанные объекты в массив
        CHOG2.add(0, CHOGDNO_2);
        CHOG2.add(1, CHOG0_071_2);
        CHOG2.add(2, CHOG0_16_2);
        CHOG2.add(3, CHOG0_315_2);
        CHOG2.add(4, CHOG0_63_2);
        CHOG2.add(5, CHOG1_25_2);
        CHOG2.add(6, CHOG2_5_2);
        CHOG2.add(7, CHOG5_2);
        CHOG2.add(8, CHOG10_2);
        CHOG2.add(9, CHOG15_2);
        CHOG2.add(10, CHOG20_2);
        CHOG2.add(11, CHOG40_2);

        CHOP40_2 = (TextView) findViewById(R.id.CHOP40_2);
        CHOP20_2 = (TextView) findViewById(R.id.CHOP20_2);
        CHOP15_2 = (TextView) findViewById(R.id.CHOP15_2);
        CHOP10_2 = (TextView) findViewById(R.id.CHOP10_2);
        CHOP5_2 = (TextView) findViewById(R.id.CHOP5_2);
        CHOP2_5_2 = (TextView) findViewById(R.id.CHOP2_5_2);
        CHOP1_25_2 = (TextView) findViewById(R.id.CHOP1_25_2);
        CHOP0_63_2 = (TextView) findViewById(R.id.CHOP0_63_2);
        CHOP0_315_2 = (TextView) findViewById(R.id.CHOP0_315_2);
        CHOP0_16_2 = (TextView) findViewById(R.id.CHOP0_16_2);
        CHOP0_071_2 = (TextView) findViewById(R.id.CHOP0_071_2);
        CHOPDNO_2 = (TextView) findViewById(R.id.CHOPDNO_2);

        CHOP2.add(0, CHOPDNO_2);
        CHOP2.add(1, CHOP0_071_2);
        CHOP2.add(2, CHOP0_16_2);
        CHOP2.add(3, CHOP0_315_2);
        CHOP2.add(4, CHOP0_63_2);
        CHOP2.add(5, CHOP1_25_2);
        CHOP2.add(6, CHOP2_5_2);
        CHOP2.add(7, CHOP5_2);
        CHOP2.add(8, CHOP10_2);
        CHOP2.add(9, CHOP15_2);
        CHOP2.add(10, CHOP20_2);
        CHOP2.add(11, CHOP40_2);

        PO40_2 = (TextView) findViewById(R.id.PO40_2);
        PO20_2 = (TextView) findViewById(R.id.PO20_2);
        PO15_2 = (TextView) findViewById(R.id.PO15_2);
        PO10_2 = (TextView) findViewById(R.id.PO10_2);
        PO5_2 = (TextView) findViewById(R.id.PO5_2);
        PO2_5_2 = (TextView) findViewById(R.id.PO2_5_2);
        PO1_25_2 = (TextView) findViewById(R.id.PO1_25_2);
        PO0_63_2 = (TextView) findViewById(R.id.PO0_63_2);
        PO0_315_2 = (TextView) findViewById(R.id.PO0_315_2);
        PO0_16_2 = (TextView) findViewById(R.id.PO0_16_2);
        PO0_071_2 = (TextView) findViewById(R.id.PO0_071_2);
        PODNO_2 = (TextView) findViewById(R.id.PODNO_2);

        PO2.add(0, PODNO_2);
        PO2.add(1, PO0_071_2);
        PO2.add(2, PO0_16_2);
        PO2.add(3, PO0_315_2);
        PO2.add(4, PO0_63_2);
        PO2.add(5, PO1_25_2);
        PO2.add(6, PO2_5_2);
        PO2.add(7, PO5_2);
        PO2.add(8, PO10_2);
        PO2.add(9, PO15_2);
        PO2.add(10, PO20_2);
        PO2.add(11, PO40_2);

        PP40_2 = (TextView) findViewById(R.id.PP40_2);
        PP20_2 = (TextView) findViewById(R.id.PP20_2);
        PP15_2 = (TextView) findViewById(R.id.PP15_2);
        PP10_2 = (TextView) findViewById(R.id.PP10_2);
        PP5_2 = (TextView) findViewById(R.id.PP5_2);
        PP2_5_2 = (TextView) findViewById(R.id.PP2_5_2);
        PP1_25_2 = (TextView) findViewById(R.id.PP1_25_2);
        PP0_63_2 = (TextView) findViewById(R.id.PP0_63_2);
        PP0_315_2 = (TextView) findViewById(R.id.PP0_315_2);
        PP0_16_2 = (TextView) findViewById(R.id.PP0_16_2);
        PP0_071_2 = (TextView) findViewById(R.id.PP0_071_2);
        PPDNO_2 = (TextView) findViewById(R.id.PPDNO_2);

        PP2.add(0, PPDNO_2);
        PP2.add(1, PP0_071_2);
        PP2.add(2, PP0_16_2);
        PP2.add(3, PP0_315_2);
        PP2.add(4, PP0_63_2);
        PP2.add(5, PP1_25_2);
        PP2.add(6, PP2_5_2);
        PP2.add(7, PP5_2);
        PP2.add(8, PP10_2);
        PP2.add(9, PP15_2);
        PP2.add(10, PP20_2);
        PP2.add(11, PP40_2);

        //Связываем объекты с полями xml формы - Таблица зернового состава №3

        CHOG40_3 = (TextView) findViewById(R.id.CHOG40_3);
        CHOG20_3 = (TextView) findViewById(R.id.CHOG20_3);
        CHOG15_3 = (TextView) findViewById(R.id.CHOG15_3);
        CHOG10_3 = (TextView) findViewById(R.id.CHOG10_3);
        CHOG5_3 = (TextView) findViewById(R.id.CHOG5_3);
        CHOG2_5_3 = (TextView) findViewById(R.id.CHOG2_5_3);
        CHOG1_25_3 = (TextView) findViewById(R.id.CHOG1_25_3);
        CHOG0_63_3 = (TextView) findViewById(R.id.CHOG0_63_3);
        CHOG0_315_3 = (TextView) findViewById(R.id.CHOG0_315_3);
        CHOG0_16_3 = (TextView) findViewById(R.id.CHOG0_16_3);
        CHOG0_071_3 = (TextView) findViewById(R.id.CHOG0_071_3);
        CHOGDNO_3 = (TextView) findViewById(R.id.CHOGDNO_3);

        // Кладем подготовленные и связанные объекты в массив
        CHOG3.add(0, CHOGDNO_3);
        CHOG3.add(1, CHOG0_071_3);
        CHOG3.add(2, CHOG0_16_3);
        CHOG3.add(3, CHOG0_315_3);
        CHOG3.add(4, CHOG0_63_3);
        CHOG3.add(5, CHOG1_25_3);
        CHOG3.add(6, CHOG2_5_3);
        CHOG3.add(7, CHOG5_3);
        CHOG3.add(8, CHOG10_3);
        CHOG3.add(9, CHOG15_3);
        CHOG3.add(10, CHOG20_3);
        CHOG3.add(11, CHOG40_3);

        CHOP40_3 = (TextView) findViewById(R.id.CHOP40_3);
        CHOP20_3 = (TextView) findViewById(R.id.CHOP20_3);
        CHOP15_3 = (TextView) findViewById(R.id.CHOP15_3);
        CHOP10_3 = (TextView) findViewById(R.id.CHOP10_3);
        CHOP5_3 = (TextView) findViewById(R.id.CHOP5_3);
        CHOP2_5_3 = (TextView) findViewById(R.id.CHOP2_5_3);
        CHOP1_25_3 = (TextView) findViewById(R.id.CHOP1_25_3);
        CHOP0_63_3 = (TextView) findViewById(R.id.CHOP0_63_3);
        CHOP0_315_3 = (TextView) findViewById(R.id.CHOP0_315_3);
        CHOP0_16_3 = (TextView) findViewById(R.id.CHOP0_16_3);
        CHOP0_071_3 = (TextView) findViewById(R.id.CHOP0_071_3);
        CHOPDNO_3 = (TextView) findViewById(R.id.CHOPDNO_3);

        CHOP3.add(0, CHOPDNO_3);
        CHOP3.add(1, CHOP0_071_3);
        CHOP3.add(2, CHOP0_16_3);
        CHOP3.add(3, CHOP0_315_3);
        CHOP3.add(4, CHOP0_63_3);
        CHOP3.add(5, CHOP1_25_3);
        CHOP3.add(6, CHOP2_5_3);
        CHOP3.add(7, CHOP5_3);
        CHOP3.add(8, CHOP10_3);
        CHOP3.add(9, CHOP15_3);
        CHOP3.add(10, CHOP20_3);
        CHOP3.add(11, CHOP40_3);

        PO40_3 = (TextView) findViewById(R.id.PO40_3);
        PO20_3 = (TextView) findViewById(R.id.PO20_3);
        PO15_3 = (TextView) findViewById(R.id.PO15_3);
        PO10_3 = (TextView) findViewById(R.id.PO10_3);
        PO5_3 = (TextView) findViewById(R.id.PO5_3);
        PO2_5_3 = (TextView) findViewById(R.id.PO2_5_3);
        PO1_25_3 = (TextView) findViewById(R.id.PO1_25_3);
        PO0_63_3 = (TextView) findViewById(R.id.PO0_63_3);
        PO0_315_3 = (TextView) findViewById(R.id.PO0_315_3);
        PO0_16_3 = (TextView) findViewById(R.id.PO0_16_3);
        PO0_071_3 = (TextView) findViewById(R.id.PO0_071_3);
        PODNO_3 = (TextView) findViewById(R.id.PODNO_3);

        PO3.add(0, PODNO_3);
        PO3.add(1, PO0_071_3);
        PO3.add(2, PO0_16_3);
        PO3.add(3, PO0_315_3);
        PO3.add(4, PO0_63_3);
        PO3.add(5, PO1_25_3);
        PO3.add(6, PO2_5_3);
        PO3.add(7, PO5_3);
        PO3.add(8, PO10_3);
        PO3.add(9, PO15_3);
        PO3.add(10, PO20_3);
        PO3.add(11, PO40_3);

        PP40_3 = (TextView) findViewById(R.id.PP40_3);
        PP20_3 = (TextView) findViewById(R.id.PP20_3);
        PP15_3 = (TextView) findViewById(R.id.PP15_3);
        PP10_3 = (TextView) findViewById(R.id.PP10_3);
        PP5_3 = (TextView) findViewById(R.id.PP5_3);
        PP2_5_3 = (TextView) findViewById(R.id.PP2_5_3);
        PP1_25_3 = (TextView) findViewById(R.id.PP1_25_3);
        PP0_63_3 = (TextView) findViewById(R.id.PP0_63_3);
        PP0_315_3 = (TextView) findViewById(R.id.PP0_315_3);
        PP0_16_3 = (TextView) findViewById(R.id.PP0_16_3);
        PP0_071_3 = (TextView) findViewById(R.id.PP0_071_3);
        PPDNO_3 = (TextView) findViewById(R.id.PPDNO_3);

        PP3.add(0, PPDNO_3);
        PP3.add(1, PP0_071_3);
        PP3.add(2, PP0_16_3);
        PP3.add(3, PP0_315_3);
        PP3.add(4, PP0_63_3);
        PP3.add(5, PP1_25_3);
        PP3.add(6, PP2_5_3);
        PP3.add(7, PP5_3);
        PP3.add(8, PP10_3);
        PP3.add(9, PP15_3);
        PP3.add(10, PP20_3);
        PP3.add(11, PP40_3);

        //Связываем объекты с полями xml формы - Таблица зернового состава №4

        CHOG40_4 = (TextView) findViewById(R.id.CHOG40_4);
        CHOG20_4 = (TextView) findViewById(R.id.CHOG20_4);
        CHOG15_4 = (TextView) findViewById(R.id.CHOG15_4);
        CHOG10_4 = (TextView) findViewById(R.id.CHOG10_4);
        CHOG5_4 = (TextView) findViewById(R.id.CHOG5_4);
        CHOG2_5_4 = (TextView) findViewById(R.id.CHOG2_5_4);
        CHOG1_25_4 = (TextView) findViewById(R.id.CHOG1_25_4);
        CHOG0_63_4 = (TextView) findViewById(R.id.CHOG0_63_4);
        CHOG0_315_4 = (TextView) findViewById(R.id.CHOG0_315_4);
        CHOG0_16_4 = (TextView) findViewById(R.id.CHOG0_16_4);
        CHOG0_071_4 = (TextView) findViewById(R.id.CHOG0_071_4);
        CHOGDNO_4 = (TextView) findViewById(R.id.CHOGDNO_4);

        // Кладем подготовленные и связанные объекты в массив
        CHOG4.add(0, CHOGDNO_4);
        CHOG4.add(1, CHOG0_071_4);
        CHOG4.add(2, CHOG0_16_4);
        CHOG4.add(3, CHOG0_315_4);
        CHOG4.add(4, CHOG0_63_4);
        CHOG4.add(5, CHOG1_25_4);
        CHOG4.add(6, CHOG2_5_4);
        CHOG4.add(7, CHOG5_4);
        CHOG4.add(8, CHOG10_4);
        CHOG4.add(9, CHOG15_4);
        CHOG4.add(10, CHOG20_4);
        CHOG4.add(11, CHOG40_4);

        CHOP40_4 = (TextView) findViewById(R.id.CHOP40_4);
        CHOP20_4 = (TextView) findViewById(R.id.CHOP20_4);
        CHOP15_4 = (TextView) findViewById(R.id.CHOP15_4);
        CHOP10_4 = (TextView) findViewById(R.id.CHOP10_4);
        CHOP5_4 = (TextView) findViewById(R.id.CHOP5_4);
        CHOP2_5_4 = (TextView) findViewById(R.id.CHOP2_5_4);
        CHOP1_25_4 = (TextView) findViewById(R.id.CHOP1_25_4);
        CHOP0_63_4 = (TextView) findViewById(R.id.CHOP0_63_4);
        CHOP0_315_4 = (TextView) findViewById(R.id.CHOP0_315_4);
        CHOP0_16_4 = (TextView) findViewById(R.id.CHOP0_16_4);
        CHOP0_071_4 = (TextView) findViewById(R.id.CHOP0_071_4);
        CHOPDNO_4 = (TextView) findViewById(R.id.CHOPDNO_4);

        CHOP4.add(0, CHOPDNO_4);
        CHOP4.add(1, CHOP0_071_4);
        CHOP4.add(2, CHOP0_16_4);
        CHOP4.add(3, CHOP0_315_4);
        CHOP4.add(4, CHOP0_63_4);
        CHOP4.add(5, CHOP1_25_4);
        CHOP4.add(6, CHOP2_5_4);
        CHOP4.add(7, CHOP5_4);
        CHOP4.add(8, CHOP10_4);
        CHOP4.add(9, CHOP15_4);
        CHOP4.add(10, CHOP20_4);
        CHOP4.add(11, CHOP40_4);

        PO40_4 = (TextView) findViewById(R.id.PO40_4);
        PO20_4 = (TextView) findViewById(R.id.PO20_4);
        PO15_4 = (TextView) findViewById(R.id.PO15_4);
        PO10_4 = (TextView) findViewById(R.id.PO10_4);
        PO5_4 = (TextView) findViewById(R.id.PO5_4);
        PO2_5_4 = (TextView) findViewById(R.id.PO2_5_4);
        PO1_25_4 = (TextView) findViewById(R.id.PO1_25_4);
        PO0_63_4 = (TextView) findViewById(R.id.PO0_63_4);
        PO0_315_4 = (TextView) findViewById(R.id.PO0_315_4);
        PO0_16_4 = (TextView) findViewById(R.id.PO0_16_4);
        PO0_071_4 = (TextView) findViewById(R.id.PO0_071_4);
        PODNO_4 = (TextView) findViewById(R.id.PODNO_4);

        PO4.add(0, PODNO_4);
        PO4.add(1, PO0_071_4);
        PO4.add(2, PO0_16_4);
        PO4.add(3, PO0_315_4);
        PO4.add(4, PO0_63_4);
        PO4.add(5, PO1_25_4);
        PO4.add(6, PO2_5_4);
        PO4.add(7, PO5_4);
        PO4.add(8, PO10_4);
        PO4.add(9, PO15_4);
        PO4.add(10, PO20_4);
        PO4.add(11, PO40_4);

        PP40_4 = (TextView) findViewById(R.id.PP40_4);
        PP20_4 = (TextView) findViewById(R.id.PP20_4);
        PP15_4 = (TextView) findViewById(R.id.PP15_4);
        PP10_4 = (TextView) findViewById(R.id.PP10_4);
        PP5_4 = (TextView) findViewById(R.id.PP5_4);
        PP2_5_4 = (TextView) findViewById(R.id.PP2_5_4);
        PP1_25_4 = (TextView) findViewById(R.id.PP1_25_4);
        PP0_63_4 = (TextView) findViewById(R.id.PP0_63_4);
        PP0_315_4 = (TextView) findViewById(R.id.PP0_315_4);
        PP0_16_4 = (TextView) findViewById(R.id.PP0_16_4);
        PP0_071_4 = (TextView) findViewById(R.id.PP0_071_4);
        PPDNO_4 = (TextView) findViewById(R.id.PPDNO_4);

        PP4.add(0, PPDNO_4);
        PP4.add(1, PP0_071_4);
        PP4.add(2, PP0_16_4);
        PP4.add(3, PP0_315_4);
        PP4.add(4, PP0_63_4);
        PP4.add(5, PP1_25_4);
        PP4.add(6, PP2_5_4);
        PP4.add(7, PP5_4);
        PP4.add(8, PP10_4);
        PP4.add(9, PP15_4);
        PP4.add(10, PP20_4);
        PP4.add(11, PP40_4);


        //Связываем объекты с полями xml формы - Таблица зернового состава №5

        CHOG40_5 = (TextView) findViewById(R.id.CHOG40_5);
        CHOG20_5 = (TextView) findViewById(R.id.CHOG20_5);
        CHOG15_5 = (TextView) findViewById(R.id.CHOG15_5);
        CHOG10_5 = (TextView) findViewById(R.id.CHOG10_5);
        CHOG5_5 = (TextView) findViewById(R.id.CHOG5_5);
        CHOG2_5_5 = (TextView) findViewById(R.id.CHOG2_5_5);
        CHOG1_25_5 = (TextView) findViewById(R.id.CHOG1_25_5);
        CHOG0_63_5 = (TextView) findViewById(R.id.CHOG0_63_5);
        CHOG0_315_5 = (TextView) findViewById(R.id.CHOG0_315_5);
        CHOG0_16_5 = (TextView) findViewById(R.id.CHOG0_16_5);
        CHOG0_071_5 = (TextView) findViewById(R.id.CHOG0_071_5);
        CHOGDNO_5 = (TextView) findViewById(R.id.CHOGDNO_5);

        // Кладем подготовленные и связанные объекты в массив
        CHOG50.add(0, CHOGDNO_5);
        CHOG50.add(1, CHOG0_071_5);
        CHOG50.add(2, CHOG0_16_5);
        CHOG50.add(3, CHOG0_315_5);
        CHOG50.add(4, CHOG0_63_5);
        CHOG50.add(5, CHOG1_25_5);
        CHOG50.add(6, CHOG2_5_5);
        CHOG50.add(7, CHOG5_5);
        CHOG50.add(8, CHOG10_5);
        CHOG50.add(9, CHOG15_5);
        CHOG50.add(10, CHOG20_5);
        CHOG50.add(11, CHOG40_5);

        CHOP40_5 = (TextView) findViewById(R.id.CHOP40_5);
        CHOP20_5 = (TextView) findViewById(R.id.CHOP20_5);
        CHOP15_5 = (TextView) findViewById(R.id.CHOP15_5);
        CHOP10_5 = (TextView) findViewById(R.id.CHOP10_5);
        CHOP5_5 = (TextView) findViewById(R.id.CHOP5_5);
        CHOP2_5_5 = (TextView) findViewById(R.id.CHOP2_5_5);
        CHOP1_25_5 = (TextView) findViewById(R.id.CHOP1_25_5);
        CHOP0_63_5 = (TextView) findViewById(R.id.CHOP0_63_5);
        CHOP0_315_5 = (TextView) findViewById(R.id.CHOP0_315_5);
        CHOP0_16_5 = (TextView) findViewById(R.id.CHOP0_16_5);
        CHOP0_071_5 = (TextView) findViewById(R.id.CHOP0_071_5);
        CHOPDNO_5 = (TextView) findViewById(R.id.CHOPDNO_5);

        CHOP50.add(0, CHOPDNO_5);
        CHOP50.add(1, CHOP0_071_5);
        CHOP50.add(2, CHOP0_16_5);
        CHOP50.add(3, CHOP0_315_5);
        CHOP50.add(4, CHOP0_63_5);
        CHOP50.add(5, CHOP1_25_5);
        CHOP50.add(6, CHOP2_5_5);
        CHOP50.add(7, CHOP5_5);
        CHOP50.add(8, CHOP10_5);
        CHOP50.add(9, CHOP15_5);
        CHOP50.add(10, CHOP20_5);
        CHOP50.add(11, CHOP40_5);

        PO40_5 = (TextView) findViewById(R.id.PO40_5);
        PO20_5 = (TextView) findViewById(R.id.PO20_5);
        PO15_5 = (TextView) findViewById(R.id.PO15_5);
        PO10_5 = (TextView) findViewById(R.id.PO10_5);
        PO5_5 = (TextView) findViewById(R.id.PO5_5);
        PO2_5_5 = (TextView) findViewById(R.id.PO2_5_5);
        PO1_25_5 = (TextView) findViewById(R.id.PO1_25_5);
        PO0_63_5 = (TextView) findViewById(R.id.PO0_63_5);
        PO0_315_5 = (TextView) findViewById(R.id.PO0_315_5);
        PO0_16_5 = (TextView) findViewById(R.id.PO0_16_5);
        PO0_071_5 = (TextView) findViewById(R.id.PO0_071_5);
        PODNO_5 = (TextView) findViewById(R.id.PODNO_5);

        PO50.add(0, PODNO_5);
        PO50.add(1, PO0_071_5);
        PO50.add(2, PO0_16_5);
        PO50.add(3, PO0_315_5);
        PO50.add(4, PO0_63_5);
        PO50.add(5, PO1_25_5);
        PO50.add(6, PO2_5_5);
        PO50.add(7, PO5_5);
        PO50.add(8, PO10_5);
        PO50.add(9, PO15_5);
        PO50.add(10, PO20_5);
        PO50.add(11, PO40_5);

        PP40_5 = (TextView) findViewById(R.id.PP40_5);
        PP20_5 = (TextView) findViewById(R.id.PP20_5);
        PP15_5 = (TextView) findViewById(R.id.PP15_5);
        PP10_5 = (TextView) findViewById(R.id.PP10_5);
        PP5_5 = (TextView) findViewById(R.id.PP5_5);
        PP2_5_5 = (TextView) findViewById(R.id.PP2_5_5);
        PP1_25_5 = (TextView) findViewById(R.id.PP1_25_5);
        PP0_63_5 = (TextView) findViewById(R.id.PP0_63_5);
        PP0_315_5 = (TextView) findViewById(R.id.PP0_315_5);
        PP0_16_5 = (TextView) findViewById(R.id.PP0_16_5);
        PP0_071_5 = (TextView) findViewById(R.id.PP0_071_5);
        PPDNO_5 = (TextView) findViewById(R.id.PPDNO_5);

        PP50.add(0, PPDNO_5);
        PP50.add(1, PP0_071_5);
        PP50.add(2, PP0_16_5);
        PP50.add(3, PP0_315_5);
        PP50.add(4, PP0_63_5);
        PP50.add(5, PP1_25_5);
        PP50.add(6, PP2_5_5);
        PP50.add(7, PP5_5);
        PP50.add(8, PP10_5);
        PP50.add(9, PP15_5);
        PP50.add(10, PP20_5);
        PP50.add(11, PP40_5);

        //Связываем объекты с полями xml формы - Таблица зернового состава №6

        CHOG40_6 = (TextView) findViewById(R.id.CHOG40_6);
        CHOG20_6 = (TextView) findViewById(R.id.CHOG20_6);
        CHOG15_6 = (TextView) findViewById(R.id.CHOG15_6);
        CHOG10_6 = (TextView) findViewById(R.id.CHOG10_6);
        CHOG5_6 = (TextView) findViewById(R.id.CHOG5_6);
        CHOG2_5_6 = (TextView) findViewById(R.id.CHOG2_5_6);
        CHOG1_25_6 = (TextView) findViewById(R.id.CHOG1_25_6);
        CHOG0_63_6 = (TextView) findViewById(R.id.CHOG0_63_6);
        CHOG0_315_6 = (TextView) findViewById(R.id.CHOG0_315_6);
        CHOG0_16_6 = (TextView) findViewById(R.id.CHOG0_16_6);
        CHOG0_071_6 = (TextView) findViewById(R.id.CHOG0_071_6);
        CHOGDNO_6 = (TextView) findViewById(R.id.CHOGDNO_6);

        // Кладем подготовленные и связанные объекты в массив
        CHOG6.add(0, CHOGDNO_6);
        CHOG6.add(1, CHOG0_071_6);
        CHOG6.add(2, CHOG0_16_6);
        CHOG6.add(3, CHOG0_315_6);
        CHOG6.add(4, CHOG0_63_6);
        CHOG6.add(5, CHOG1_25_6);
        CHOG6.add(6, CHOG2_5_6);
        CHOG6.add(7, CHOG5_6);
        CHOG6.add(8, CHOG10_6);
        CHOG6.add(9, CHOG15_6);
        CHOG6.add(10, CHOG20_6);
        CHOG6.add(11, CHOG40_6);

        CHOP40_6 = (TextView) findViewById(R.id.CHOP40_6);
        CHOP20_6 = (TextView) findViewById(R.id.CHOP20_6);
        CHOP15_6 = (TextView) findViewById(R.id.CHOP15_6);
        CHOP10_6 = (TextView) findViewById(R.id.CHOP10_6);
        CHOP5_6 = (TextView) findViewById(R.id.CHOP5_6);
        CHOP2_5_6 = (TextView) findViewById(R.id.CHOP2_5_6);
        CHOP1_25_6 = (TextView) findViewById(R.id.CHOP1_25_6);
        CHOP0_63_6 = (TextView) findViewById(R.id.CHOP0_63_6);
        CHOP0_315_6 = (TextView) findViewById(R.id.CHOP0_315_6);
        CHOP0_16_6 = (TextView) findViewById(R.id.CHOP0_16_6);
        CHOP0_071_6 = (TextView) findViewById(R.id.CHOP0_071_6);
        CHOPDNO_6 = (TextView) findViewById(R.id.CHOPDNO_6);

        CHOP6.add(0, CHOPDNO_6);
        CHOP6.add(1, CHOP0_071_6);
        CHOP6.add(2, CHOP0_16_6);
        CHOP6.add(3, CHOP0_315_6);
        CHOP6.add(4, CHOP0_63_6);
        CHOP6.add(5, CHOP1_25_6);
        CHOP6.add(6, CHOP2_5_6);
        CHOP6.add(7, CHOP5_6);
        CHOP6.add(8, CHOP10_6);
        CHOP6.add(9, CHOP15_6);
        CHOP6.add(10, CHOP20_6);
        CHOP6.add(11, CHOP40_6);

        PO40_6 = (TextView) findViewById(R.id.PO40_6);
        PO20_6 = (TextView) findViewById(R.id.PO20_6);
        PO15_6 = (TextView) findViewById(R.id.PO15_6);
        PO10_6 = (TextView) findViewById(R.id.PO10_6);
        PO5_6 = (TextView) findViewById(R.id.PO5_6);
        PO2_5_6 = (TextView) findViewById(R.id.PO2_5_6);
        PO1_25_6 = (TextView) findViewById(R.id.PO1_25_6);
        PO0_63_6 = (TextView) findViewById(R.id.PO0_63_6);
        PO0_315_6 = (TextView) findViewById(R.id.PO0_315_6);
        PO0_16_6 = (TextView) findViewById(R.id.PO0_16_6);
        PO0_071_6 = (TextView) findViewById(R.id.PO0_071_6);
        PODNO_6 = (TextView) findViewById(R.id.PODNO_6);

        PO6.add(0, PODNO_6);
        PO6.add(1, PO0_071_6);
        PO6.add(2, PO0_16_6);
        PO6.add(3, PO0_315_6);
        PO6.add(4, PO0_63_6);
        PO6.add(5, PO1_25_6);
        PO6.add(6, PO2_5_6);
        PO6.add(7, PO5_6);
        PO6.add(8, PO10_6);
        PO6.add(9, PO15_6);
        PO6.add(10, PO20_6);
        PO6.add(11, PO40_6);

        PP40_6 = (TextView) findViewById(R.id.PP40_6);
        PP20_6 = (TextView) findViewById(R.id.PP20_6);
        PP15_6 = (TextView) findViewById(R.id.PP15_6);
        PP10_6 = (TextView) findViewById(R.id.PP10_6);
        PP5_6 = (TextView) findViewById(R.id.PP5_6);
        PP2_5_6 = (TextView) findViewById(R.id.PP2_5_6);
        PP1_25_6 = (TextView) findViewById(R.id.PP1_25_6);
        PP0_63_6 = (TextView) findViewById(R.id.PP0_63_6);
        PP0_315_6 = (TextView) findViewById(R.id.PP0_315_6);
        PP0_16_6 = (TextView) findViewById(R.id.PP0_16_6);
        PP0_071_6 = (TextView) findViewById(R.id.PP0_071_6);
        PPDNO_6 = (TextView) findViewById(R.id.PPDNO_6);

        PP6.add(0, PPDNO_6);
        PP6.add(1, PP0_071_6);
        PP6.add(2, PP0_16_6);
        PP6.add(3, PP0_315_6);
        PP6.add(4, PP0_63_6);
        PP6.add(5, PP1_25_6);
        PP6.add(6, PP2_5_6);
        PP6.add(7, PP5_6);
        PP6.add(8, PP10_6);
        PP6.add(9, PP15_6);
        PP6.add(10, PP20_6);
        PP6.add(11, PP40_6);

        //Связываем объекты с полями xml формы - Таблица зернового состава МИНЕРАЛЬНОГО ПОРОШКА

        CHOG1_25_MP = (TextView) findViewById(R.id.CHOG1_25_MP);
        CHOG0_63_MP = (TextView) findViewById(R.id.CHOG0_63_MP);
        CHOG0_315_MP = (TextView) findViewById(R.id.CHOG0_315_MP);
        CHOG0_16_MP = (TextView) findViewById(R.id.CHOG0_16_MP);
        CHOG0_071_MP = (TextView) findViewById(R.id.CHOG0_071_MP);
        CHOGDNO_MP = (TextView) findViewById(R.id.CHOGDNO_MP);

        // Кладем подготовленные и связанные объекты в массив
        CHOG_MP.add(0, CHOGDNO_MP);
        CHOG_MP.add(1, CHOG0_071_MP);
        CHOG_MP.add(2, CHOG0_16_MP);
        CHOG_MP.add(3, CHOG0_315_MP);
        CHOG_MP.add(4, CHOG0_63_MP);
        CHOG_MP.add(5, CHOG1_25_MP);

        CHOP1_25_MP = (TextView) findViewById(R.id.CHOP1_25_MP);
        CHOP0_63_MP = (TextView) findViewById(R.id.CHOP0_63_MP);
        CHOP0_315_MP = (TextView) findViewById(R.id.CHOP0_315_MP);
        CHOP0_16_MP = (TextView) findViewById(R.id.CHOP0_16_MP);
        CHOP0_071_MP = (TextView) findViewById(R.id.CHOP0_071_MP);
        CHOPDNO_MP = (TextView) findViewById(R.id.CHOPDNO_MP);

        CHOP_MP.add(0, CHOPDNO_MP);
        CHOP_MP.add(1, CHOP0_071_MP);
        CHOP_MP.add(2, CHOP0_16_MP);
        CHOP_MP.add(3, CHOP0_315_MP);
        CHOP_MP.add(4, CHOP0_63_MP);
        CHOP_MP.add(5, CHOP1_25_MP);

        PO1_25_MP = (TextView) findViewById(R.id.PO1_25_MP);
        PO0_63_MP = (TextView) findViewById(R.id.PO0_63_MP);
        PO0_315_MP = (TextView) findViewById(R.id.PO0_315_MP);
        PO0_16_MP = (TextView) findViewById(R.id.PO0_16_MP);
        PO0_071_MP = (TextView) findViewById(R.id.PO0_071_MP);
        PODNO_MP = (TextView) findViewById(R.id.PODNO_MP);

        PO_MP.add(0, PODNO_MP);
        PO_MP.add(1, PO0_071_MP);
        PO_MP.add(2, PO0_16_MP);
        PO_MP.add(3, PO0_315_MP);
        PO_MP.add(4, PO0_63_MP);
        PO_MP.add(5, PO1_25_MP);

        PP1_25_MP = (TextView) findViewById(R.id.PP1_25_MP);
        PP0_63_MP = (TextView) findViewById(R.id.PP0_63_MP);
        PP0_315_MP = (TextView) findViewById(R.id.PP0_315_MP);
        PP0_16_MP = (TextView) findViewById(R.id.PP0_16_MP);
        PP0_071_MP = (TextView) findViewById(R.id.PP0_071_MP);
        PPDNO_MP = (TextView) findViewById(R.id.PPDNO_MP);

        PP_MP.add(0, PPDNO_MP);
        PP_MP.add(1, PP0_071_MP);
        PP_MP.add(2, PP0_16_MP);
        PP_MP.add(3, PP0_315_MP);
        PP_MP.add(4, PP0_63_MP);
        PP_MP.add(5, PP1_25_MP);

        //Связываем объекты с полями xml формы - Таблица зернового состава СОБСТВЕННОГО ЗАПОЛНИТЕЛЯ

        CHOG1_25_SZ = (TextView) findViewById(R.id.CHOG1_25_SZ);
        CHOG0_63_SZ = (TextView) findViewById(R.id.CHOG0_63_SZ);
        CHOG0_315_SZ = (TextView) findViewById(R.id.CHOG0_315_SZ);
        CHOG0_16_SZ = (TextView) findViewById(R.id.CHOG0_16_SZ);
        CHOG0_071_SZ = (TextView) findViewById(R.id.CHOG0_071_SZ);
        CHOGDNO_SZ = (TextView) findViewById(R.id.CHOGDNO_SZ);

        // Кладем подготовленные и связанные объекты в массив
        CHOG_SZ.add(0, CHOGDNO_SZ);
        CHOG_SZ.add(1, CHOG0_071_SZ);
        CHOG_SZ.add(2, CHOG0_16_SZ);
        CHOG_SZ.add(3, CHOG0_315_SZ);
        CHOG_SZ.add(4, CHOG0_63_SZ);
        CHOG_SZ.add(5, CHOG1_25_SZ);

        CHOP1_25_SZ = (TextView) findViewById(R.id.CHOP1_25_SZ);
        CHOP0_63_SZ = (TextView) findViewById(R.id.CHOP0_63_SZ);
        CHOP0_315_SZ = (TextView) findViewById(R.id.CHOP0_315_SZ);
        CHOP0_16_SZ = (TextView) findViewById(R.id.CHOP0_16_SZ);
        CHOP0_071_SZ = (TextView) findViewById(R.id.CHOP0_071_SZ);
        CHOPDNO_SZ = (TextView) findViewById(R.id.CHOPDNO_SZ);

        CHOP_SZ.add(0, CHOPDNO_SZ);
        CHOP_SZ.add(1, CHOP0_071_SZ);
        CHOP_SZ.add(2, CHOP0_16_SZ);
        CHOP_SZ.add(3, CHOP0_315_SZ);
        CHOP_SZ.add(4, CHOP0_63_SZ);
        CHOP_SZ.add(5, CHOP1_25_SZ);

        PO1_25_SZ = (TextView) findViewById(R.id.PO1_25_SZ);
        PO0_63_SZ = (TextView) findViewById(R.id.PO0_63_SZ);
        PO0_315_SZ = (TextView) findViewById(R.id.PO0_315_SZ);
        PO0_16_SZ = (TextView) findViewById(R.id.PO0_16_SZ);
        PO0_071_SZ = (TextView) findViewById(R.id.PO0_071_SZ);
        PODNO_SZ = (TextView) findViewById(R.id.PODNO_SZ);

        PO_SZ.add(0, PODNO_SZ);
        PO_SZ.add(1, PO0_071_SZ);
        PO_SZ.add(2, PO0_16_SZ);
        PO_SZ.add(3, PO0_315_SZ);
        PO_SZ.add(4, PO0_63_SZ);
        PO_SZ.add(5, PO1_25_SZ);

        PP1_25_SZ = (TextView) findViewById(R.id.PP1_25_SZ);
        PP0_63_SZ = (TextView) findViewById(R.id.PP0_63_SZ);
        PP0_315_SZ = (TextView) findViewById(R.id.PP0_315_SZ);
        PP0_16_SZ = (TextView) findViewById(R.id.PP0_16_SZ);
        PP0_071_SZ = (TextView) findViewById(R.id.PP0_071_SZ);
        PPDNO_SZ = (TextView) findViewById(R.id.PPDNO_SZ);

        PP_SZ.add(0, PPDNO_SZ);
        PP_SZ.add(1, PP0_071_SZ);
        PP_SZ.add(2, PP0_16_SZ);
        PP_SZ.add(3, PP0_315_SZ);
        PP_SZ.add(4, PP0_63_SZ);
        PP_SZ.add(5, PP1_25_SZ);

        SOD1Minus = (Button) findViewById(R.id.SOD1Minus);
        SOD1Plus = (Button) findViewById(R.id.SOD1Plus);

        SOD2Minus = (Button) findViewById(R.id.SOD2Minus);
        SOD2Plus = (Button) findViewById(R.id.SOD2Plus);

        SOD3Minus = (Button) findViewById(R.id.SOD3Minus);
        SOD3Plus = (Button) findViewById(R.id.SOD3Plus);

        SOD4Minus = (Button) findViewById(R.id.SOD4Minus);
        SOD4Plus = (Button) findViewById(R.id.SOD4Plus);

        SOD5Minus = (Button) findViewById(R.id.SOD5Minus);
        SOD5Plus = (Button) findViewById(R.id.SOD5Plus);

        SOD6Minus = (Button) findViewById(R.id.SOD6Minus);
        SOD6Plus = (Button) findViewById(R.id.SOD6Plus);

        SODMPMinus = (Button) findViewById(R.id.SODMPMinus);
        SODMPPlus = (Button) findViewById(R.id.SODMPPlus);

        SODSZMinus = (Button) findViewById(R.id.SODSZMinus);
        SODSZPlus = (Button) findViewById(R.id.SODSZPlus);

        SOD1 = (Button) findViewById(R.id.SOD1);
        SOD2 = (Button) findViewById(R.id.SOD2);
        SOD3 = (Button) findViewById(R.id.SOD3);
        SOD4 = (Button) findViewById(R.id.SOD4);
        SOD5 = (Button) findViewById(R.id.SOD5);
        SOD6 = (Button) findViewById(R.id.SOD6);
        SODMP = (Button) findViewById(R.id.SODMP);
        SODSZ = (Button) findViewById(R.id.SODSZ);

        PP40_R_OT = (TextView) findViewById(R.id.PP40_R_OT);
        PP20_R_OT = (TextView) findViewById(R.id.PP20_R_OT);
        ;
        PP15_R_OT = (TextView) findViewById(R.id.PP15_R_OT);
        PP10_R_OT = (TextView) findViewById(R.id.PP10_R_OT);
        PP5_R_OT = (TextView) findViewById(R.id.PP5_R_OT);
        PP2_5_R_OT = (TextView) findViewById(R.id.PP2_5_R_OT);
        PP1_25_R_OT = (TextView) findViewById(R.id.PP1_25_R_OT);
        PP0_63_R_OT = (TextView) findViewById(R.id.PP0_63_R_OT);
        PP0_315_R_OT = (TextView) findViewById(R.id.PP0_315_R_OT);
        PP0_16_R_OT = (TextView) findViewById(R.id.PP0_16_R_OT);
        PP0_071_R_OT = (TextView) findViewById(R.id.PP0_071_R_OT);


        PP_R_OT.add(0, PP0_071_R_OT);
        PP_R_OT.add(1, PP0_16_R_OT);
        PP_R_OT.add(2, PP0_315_R_OT);
        PP_R_OT.add(3, PP0_63_R_OT);
        PP_R_OT.add(4, PP1_25_R_OT);
        PP_R_OT.add(5, PP2_5_R_OT);
        PP_R_OT.add(6, PP5_R_OT);
        PP_R_OT.add(7, PP10_R_OT);
        PP_R_OT.add(8, PP15_R_OT);
        PP_R_OT.add(9, PP20_R_OT);
        PP_R_OT.add(10, PP40_R_OT);

        PP40_R_DO = (TextView) findViewById(R.id.PP40_R_DO);
        PP20_R_DO = (TextView) findViewById(R.id.PP20_R_DO);
        ;
        PP15_R_DO = (TextView) findViewById(R.id.PP15_R_DO);
        PP10_R_DO = (TextView) findViewById(R.id.PP10_R_DO);
        PP5_R_DO = (TextView) findViewById(R.id.PP5_R_DO);
        PP2_5_R_DO = (TextView) findViewById(R.id.PP2_5_R_DO);
        PP1_25_R_DO = (TextView) findViewById(R.id.PP1_25_R_DO);
        PP0_63_R_DO = (TextView) findViewById(R.id.PP0_63_R_DO);
        PP0_315_R_DO = (TextView) findViewById(R.id.PP0_315_R_DO);
        PP0_16_R_DO = (TextView) findViewById(R.id.PP0_16_R_DO);
        PP0_071_R_DO = (TextView) findViewById(R.id.PP0_071_R_DO);


        PP_R_DO.add(0, PP0_071_R_DO);
        PP_R_DO.add(1, PP0_16_R_DO);
        PP_R_DO.add(2, PP0_315_R_DO);
        PP_R_DO.add(3, PP0_63_R_DO);
        PP_R_DO.add(4, PP1_25_R_DO);
        PP_R_DO.add(5, PP2_5_R_DO);
        PP_R_DO.add(6, PP5_R_DO);
        PP_R_DO.add(7, PP10_R_DO);
        PP_R_DO.add(8, PP15_R_DO);
        PP_R_DO.add(9, PP20_R_DO);
        PP_R_DO.add(10, PP40_R_DO);

        //Связываем объекты TextView  и ячейки строки полных проходов материала №1 с учетом его дозировки

        PP40_R_1 = (TextView) findViewById(R.id.PP40_R_1);
        PP20_R_1 = (TextView) findViewById(R.id.PP20_R_1);
        ;
        PP15_R_1 = (TextView) findViewById(R.id.PP15_R_1);
        PP10_R_1 = (TextView) findViewById(R.id.PP10_R_1);
        PP5_R_1 = (TextView) findViewById(R.id.PP5_R_1);
        PP2_5_R_1 = (TextView) findViewById(R.id.PP2_5_R_1);
        PP1_25_R_1 = (TextView) findViewById(R.id.PP1_25_R_1);
        PP0_63_R_1 = (TextView) findViewById(R.id.PP0_63_R_1);
        PP0_315_R_1 = (TextView) findViewById(R.id.PP0_315_R_1);
        PP0_16_R_1 = (TextView) findViewById(R.id.PP0_16_R_1);
        PP0_071_R_1 = (TextView) findViewById(R.id.PP0_071_R_1);

        //Загоняем объекты-ячейки в массив

        PP_R_1.add(0, PP0_071_R_1);
        PP_R_1.add(1, PP0_16_R_1);
        PP_R_1.add(2, PP0_315_R_1);
        PP_R_1.add(3, PP0_63_R_1);
        PP_R_1.add(4, PP1_25_R_1);
        PP_R_1.add(5, PP2_5_R_1);
        PP_R_1.add(6, PP5_R_1);
        PP_R_1.add(7, PP10_R_1);
        PP_R_1.add(8, PP15_R_1);
        PP_R_1.add(9, PP20_R_1);
        PP_R_1.add(10, PP40_R_1);

        //Связываем объекты TextView  и ячейки строки полных проходов материала №2 с учетом его дозировки

        PP40_R_2 = (TextView) findViewById(R.id.PP40_R_2);
        PP20_R_2 = (TextView) findViewById(R.id.PP20_R_2);
        ;
        PP15_R_2 = (TextView) findViewById(R.id.PP15_R_2);
        PP10_R_2 = (TextView) findViewById(R.id.PP10_R_2);
        PP5_R_2 = (TextView) findViewById(R.id.PP5_R_2);
        PP2_5_R_2 = (TextView) findViewById(R.id.PP2_5_R_2);
        PP1_25_R_2 = (TextView) findViewById(R.id.PP1_25_R_2);
        PP0_63_R_2 = (TextView) findViewById(R.id.PP0_63_R_2);
        PP0_315_R_2 = (TextView) findViewById(R.id.PP0_315_R_2);
        PP0_16_R_2 = (TextView) findViewById(R.id.PP0_16_R_2);
        PP0_071_R_2 = (TextView) findViewById(R.id.PP0_071_R_2);

        //Загоняем объекты-ячейки в массив

        PP_R_2.add(0, PP0_071_R_2);
        PP_R_2.add(1, PP0_16_R_2);
        PP_R_2.add(2, PP0_315_R_2);
        PP_R_2.add(3, PP0_63_R_2);
        PP_R_2.add(4, PP1_25_R_2);
        PP_R_2.add(5, PP2_5_R_2);
        PP_R_2.add(6, PP5_R_2);
        PP_R_2.add(7, PP10_R_2);
        PP_R_2.add(8, PP15_R_2);
        PP_R_2.add(9, PP20_R_2);
        PP_R_2.add(10, PP40_R_2);

        //Связываем объекты TextView  и ячейки строки полных проходов материала №3 с учетом его дозировки

        PP40_R_3 = (TextView) findViewById(R.id.PP40_R_3);
        PP20_R_3 = (TextView) findViewById(R.id.PP20_R_3);
        ;
        PP15_R_3 = (TextView) findViewById(R.id.PP15_R_3);
        PP10_R_3 = (TextView) findViewById(R.id.PP10_R_3);
        PP5_R_3 = (TextView) findViewById(R.id.PP5_R_3);
        PP2_5_R_3 = (TextView) findViewById(R.id.PP2_5_R_3);
        PP1_25_R_3 = (TextView) findViewById(R.id.PP1_25_R_3);
        PP0_63_R_3 = (TextView) findViewById(R.id.PP0_63_R_3);
        PP0_315_R_3 = (TextView) findViewById(R.id.PP0_315_R_3);
        PP0_16_R_3 = (TextView) findViewById(R.id.PP0_16_R_3);
        PP0_071_R_3 = (TextView) findViewById(R.id.PP0_071_R_3);

        //Загоняем объекты-ячейки в массив

        PP_R_3.add(0, PP0_071_R_3);
        PP_R_3.add(1, PP0_16_R_3);
        PP_R_3.add(2, PP0_315_R_3);
        PP_R_3.add(3, PP0_63_R_3);
        PP_R_3.add(4, PP1_25_R_3);
        PP_R_3.add(5, PP2_5_R_3);
        PP_R_3.add(6, PP5_R_3);
        PP_R_3.add(7, PP10_R_3);
        PP_R_3.add(8, PP15_R_3);
        PP_R_3.add(9, PP20_R_3);
        PP_R_3.add(10, PP40_R_3);

        //Связываем объекты TextView  и ячейки строки полных проходов материала №4 с учетом его дозировки

        PP40_R_4 = (TextView) findViewById(R.id.PP40_R_4);
        PP20_R_4 = (TextView) findViewById(R.id.PP20_R_4);
        ;
        PP15_R_4 = (TextView) findViewById(R.id.PP15_R_4);
        PP10_R_4 = (TextView) findViewById(R.id.PP10_R_4);
        PP5_R_4 = (TextView) findViewById(R.id.PP5_R_4);
        PP2_5_R_4 = (TextView) findViewById(R.id.PP2_5_R_4);
        PP1_25_R_4 = (TextView) findViewById(R.id.PP1_25_R_4);
        PP0_63_R_4 = (TextView) findViewById(R.id.PP0_63_R_4);
        PP0_315_R_4 = (TextView) findViewById(R.id.PP0_315_R_4);
        PP0_16_R_4 = (TextView) findViewById(R.id.PP0_16_R_4);
        PP0_071_R_4 = (TextView) findViewById(R.id.PP0_071_R_4);

        //Загоняем объекты-ячейки в массив

        PP_R_4.add(0, PP0_071_R_4);
        PP_R_4.add(1, PP0_16_R_4);
        PP_R_4.add(2, PP0_315_R_4);
        PP_R_4.add(3, PP0_63_R_4);
        PP_R_4.add(4, PP1_25_R_4);
        PP_R_4.add(5, PP2_5_R_4);
        PP_R_4.add(6, PP5_R_4);
        PP_R_4.add(7, PP10_R_4);
        PP_R_4.add(8, PP15_R_4);
        PP_R_4.add(9, PP20_R_4);
        PP_R_4.add(10, PP40_R_4);

        //Связываем объекты TextView  и ячейки строки полных проходов материала №5 с учетом его дозировки

        PP40_R_5 = (TextView) findViewById(R.id.PP40_R_5);
        PP20_R_5 = (TextView) findViewById(R.id.PP20_R_5);
        ;
        PP15_R_5 = (TextView) findViewById(R.id.PP15_R_5);
        PP10_R_5 = (TextView) findViewById(R.id.PP10_R_5);
        PP5_R_5 = (TextView) findViewById(R.id.PP5_R_5);
        PP2_5_R_5 = (TextView) findViewById(R.id.PP2_5_R_5);
        PP1_25_R_5 = (TextView) findViewById(R.id.PP1_25_R_5);
        PP0_63_R_5 = (TextView) findViewById(R.id.PP0_63_R_5);
        PP0_315_R_5 = (TextView) findViewById(R.id.PP0_315_R_5);
        PP0_16_R_5 = (TextView) findViewById(R.id.PP0_16_R_5);
        PP0_071_R_5 = (TextView) findViewById(R.id.PP0_071_R_5);

        //Загоняем объекты-ячейки в массив

        PP_R_5.add(0, PP0_071_R_5);
        PP_R_5.add(1, PP0_16_R_5);
        PP_R_5.add(2, PP0_315_R_5);
        PP_R_5.add(3, PP0_63_R_5);
        PP_R_5.add(4, PP1_25_R_5);
        PP_R_5.add(5, PP2_5_R_5);
        PP_R_5.add(6, PP5_R_5);
        PP_R_5.add(7, PP10_R_5);
        PP_R_5.add(8, PP15_R_5);
        PP_R_5.add(9, PP20_R_5);
        PP_R_5.add(10, PP40_R_5);

        //Связываем объекты TextView  и ячейки строки полных проходов материала №6 с учетом его дозировки

        PP40_R_6 = (TextView) findViewById(R.id.PP40_R_6);
        PP20_R_6 = (TextView) findViewById(R.id.PP20_R_6);
        ;
        PP15_R_6 = (TextView) findViewById(R.id.PP15_R_6);
        PP10_R_6 = (TextView) findViewById(R.id.PP10_R_6);
        PP5_R_6 = (TextView) findViewById(R.id.PP5_R_6);
        PP2_5_R_6 = (TextView) findViewById(R.id.PP2_5_R_6);
        PP1_25_R_6 = (TextView) findViewById(R.id.PP1_25_R_6);
        PP0_63_R_6 = (TextView) findViewById(R.id.PP0_63_R_6);
        PP0_315_R_6 = (TextView) findViewById(R.id.PP0_315_R_6);
        PP0_16_R_6 = (TextView) findViewById(R.id.PP0_16_R_6);
        PP0_071_R_6 = (TextView) findViewById(R.id.PP0_071_R_6);

        //Загоняем объекты-ячейки в массив

        PP_R_6.add(0, PP0_071_R_6);
        PP_R_6.add(1, PP0_16_R_6);
        PP_R_6.add(2, PP0_315_R_6);
        PP_R_6.add(3, PP0_63_R_6);
        PP_R_6.add(4, PP1_25_R_6);
        PP_R_6.add(5, PP2_5_R_6);
        PP_R_6.add(6, PP5_R_6);
        PP_R_6.add(7, PP10_R_6);
        PP_R_6.add(8, PP15_R_6);
        PP_R_6.add(9, PP20_R_6);
        PP_R_6.add(10, PP40_R_6);

        //Связываем объекты TextView  и ячейки строки полных проходов материала минеарльного порошка с учетом его дозировки

        PP40_R_MP = (TextView) findViewById(R.id.PP40_R_MP);
        PP20_R_MP = (TextView) findViewById(R.id.PP20_R_MP);
        ;
        PP15_R_MP = (TextView) findViewById(R.id.PP15_R_MP);
        PP10_R_MP = (TextView) findViewById(R.id.PP10_R_MP);
        PP5_R_MP = (TextView) findViewById(R.id.PP5_R_MP);
        PP2_5_R_MP = (TextView) findViewById(R.id.PP2_5_R_MP);
        PP1_25_R_MP = (TextView) findViewById(R.id.PP1_25_R_MP);
        PP0_63_R_MP = (TextView) findViewById(R.id.PP0_63_R_MP);
        PP0_315_R_MP = (TextView) findViewById(R.id.PP0_315_R_MP);
        PP0_16_R_MP = (TextView) findViewById(R.id.PP0_16_R_MP);
        PP0_071_R_MP = (TextView) findViewById(R.id.PP0_071_R_MP);

        //Загоняем объекты-ячейки в массив

        PP_R_MP.add(0, PP0_071_R_MP);
        PP_R_MP.add(1, PP0_16_R_MP);
        PP_R_MP.add(2, PP0_315_R_MP);
        PP_R_MP.add(3, PP0_63_R_MP);
        PP_R_MP.add(4, PP1_25_R_MP);
        PP_R_MP.add(5, PP2_5_R_MP);
        PP_R_MP.add(6, PP5_R_MP);
        PP_R_MP.add(7, PP10_R_MP);
        PP_R_MP.add(8, PP15_R_MP);
        PP_R_MP.add(9, PP20_R_MP);
        PP_R_MP.add(10, PP40_R_MP);

        //Связываем объекты TextView  и ячейки строки полных проходов материала №6 с учетом его дозировки

        PP40_R_SZ = (TextView) findViewById(R.id.PP40_R_SZ);
        PP20_R_SZ = (TextView) findViewById(R.id.PP20_R_SZ);
        ;
        PP15_R_SZ = (TextView) findViewById(R.id.PP15_R_SZ);
        PP10_R_SZ = (TextView) findViewById(R.id.PP10_R_SZ);
        PP5_R_SZ = (TextView) findViewById(R.id.PP5_R_SZ);
        PP2_5_R_SZ = (TextView) findViewById(R.id.PP2_5_R_SZ);
        PP1_25_R_SZ = (TextView) findViewById(R.id.PP1_25_R_SZ);
        PP0_63_R_SZ = (TextView) findViewById(R.id.PP0_63_R_SZ);
        PP0_315_R_SZ = (TextView) findViewById(R.id.PP0_315_R_SZ);
        PP0_16_R_SZ = (TextView) findViewById(R.id.PP0_16_R_SZ);
        PP0_071_R_SZ = (TextView) findViewById(R.id.PP0_071_R_SZ);

        //Загоняем объекты-ячейки в массив

        PP_R_SZ.add(0, PP0_071_R_SZ);
        PP_R_SZ.add(1, PP0_16_R_SZ);
        PP_R_SZ.add(2, PP0_315_R_SZ);
        PP_R_SZ.add(3, PP0_63_R_SZ);
        PP_R_SZ.add(4, PP1_25_R_SZ);
        PP_R_SZ.add(5, PP2_5_R_SZ);
        PP_R_SZ.add(6, PP5_R_SZ);
        PP_R_SZ.add(7, PP10_R_SZ);
        PP_R_SZ.add(8, PP15_R_SZ);
        PP_R_SZ.add(9, PP20_R_SZ);
        PP_R_SZ.add(10, PP40_R_SZ);

        //Связываем объекты TextView  и ячейки строки полных проходов результирующей кривой

        PP40_R_Result = (TextView) findViewById(R.id.PP40_R_ResCurve);
        PP20_R_Result = (TextView) findViewById(R.id.PP20_R_ResCurve);
        PP15_R_Result = (TextView) findViewById(R.id.PP15_R_ResCurve);
        PP10_R_Result = (TextView) findViewById(R.id.PP10_R_ResCurve);
        PP5_R_Result = (TextView) findViewById(R.id.PP5_R_ResCurve);
        PP2_5_R_Result = (TextView) findViewById(R.id.PP2_5_R_ResCurve);
        PP1_25_R_Result = (TextView) findViewById(R.id.PP1_25_R_ResCurve);
        PP0_63_R_Result = (TextView) findViewById(R.id.PP0_63_R_ResCurve);
        PP0_315_R_Result = (TextView) findViewById(R.id.PP0_315_R_ResCurve);
        PP0_16_R_Result = (TextView) findViewById(R.id.PP0_16_R_ResCurve);
        PP0_071_R_Result = (TextView) findViewById(R.id.PP0_071_R_ResCurve);

        PP_Summa = (TextView) findViewById(R.id.summa);

        //Загоняем объекты-ячейки в массив

        PP_R_Result.add(0, PP0_071_R_Result);
        PP_R_Result.add(1, PP0_16_R_Result);
        PP_R_Result.add(2, PP0_315_R_Result);
        PP_R_Result.add(3, PP0_63_R_Result);
        PP_R_Result.add(4, PP1_25_R_Result);
        PP_R_Result.add(5, PP2_5_R_Result);
        PP_R_Result.add(6, PP5_R_Result);
        PP_R_Result.add(7, PP10_R_Result);
        PP_R_Result.add(8, PP15_R_Result);
        PP_R_Result.add(9, PP20_R_Result);
        PP_R_Result.add(10, PP40_R_Result);

        //Связываем объекты TextView  и ячейки строки полных проходов целевой кривой

        PP40_R_Target = (Button) findViewById(R.id.PP40_R_TargetCurve);
        PP20_R_Target = (Button) findViewById(R.id.PP20_R_TargetCurve);
        PP15_R_Target = (Button) findViewById(R.id.PP15_R_TargetCurve);
        PP10_R_Target = (Button) findViewById(R.id.PP10_R_TargetCurve);
        PP5_R_Target = (Button) findViewById(R.id.PP5_R_TargetCurve);
        PP2_5_R_Target = (Button) findViewById(R.id.PP2_5_R_TargetCurve);
        PP1_25_R_Target = (Button) findViewById(R.id.PP1_25_R_TargetCurve);
        PP0_63_R_Target = (Button) findViewById(R.id.PP0_63_R_TargetCurve);
        PP0_315_R_Target = (Button) findViewById(R.id.PP0_315_R_TargetCurve);
        PP0_16_R_Target = (Button) findViewById(R.id.PP0_16_R_TargetCurve);
        PP0_071_R_Target = (Button) findViewById(R.id.PP0_071_R_TargetCurve);

        AutoPodbor = (Button) findViewById(R.id.AutoPodbor);

        //Загоняем объекты-ячейки в массив

        PP_R_Target.add(0, PP0_071_R_Target);
        PP_R_Target.add(1, PP0_16_R_Target);
        PP_R_Target.add(2, PP0_315_R_Target);
        PP_R_Target.add(3, PP0_63_R_Target);
        PP_R_Target.add(4, PP1_25_R_Target);
        PP_R_Target.add(5, PP2_5_R_Target);
        PP_R_Target.add(6, PP5_R_Target);
        PP_R_Target.add(7, PP10_R_Target);
        PP_R_Target.add(8, PP15_R_Target);
        PP_R_Target.add(9, PP20_R_Target);
        PP_R_Target.add(10, PP40_R_Target);


        HEAD40_R = (TextView) findViewById(R.id.Head40_R);
        HEAD20_R = (TextView) findViewById(R.id.Head20_R);
        HEAD15_R = (TextView) findViewById(R.id.Head15_R);
        HEAD10_R = (TextView) findViewById(R.id.Head10_R);
        HEAD5_R = (TextView) findViewById(R.id.Head5_R);;
        HEAD2_5_R = (TextView) findViewById(R.id.Head2_5_R);
        HEAD1_25_R = (TextView) findViewById(R.id.Head1_25_R);
        HEAD0_63_R = (TextView) findViewById(R.id.Head0_63_R);
        HEAD0_315_R = (TextView) findViewById(R.id.Head0_315_R);
        HEAD0_16_R = (TextView) findViewById(R.id.Head0_16_R);
        HEAD0_071_R = (TextView) findViewById(R.id.Head0_071_R);

        HEAD_R.add(0, HEAD40_R);
        HEAD_R.add(1, HEAD20_R);
        HEAD_R.add(2, HEAD15_R);
        HEAD_R.add(3, HEAD10_R);
        HEAD_R.add(4, HEAD5_R);
        HEAD_R.add(5, HEAD2_5_R);
        HEAD_R.add(6, HEAD1_25_R);
        HEAD_R.add(7, HEAD0_63_R);
        HEAD_R.add(8, HEAD0_315_R);
        HEAD_R.add(9, HEAD0_16_R);
        HEAD_R.add(10, HEAD0_071_R);

        NameOfMaterial1 = (EditText) findViewById(R.id.NameOfMaterial1);
        NameOfMaterial2 = (EditText) findViewById(R.id.NameOfMaterial2);
        NameOfMaterial3 = (EditText) findViewById(R.id.NameOfMaterial3);
        NameOfMaterial4 = (EditText) findViewById(R.id.NameOfMaterial4);
        NameOfMaterial5 = (EditText) findViewById(R.id.NameOfMaterial5);
        NameOfMaterial6 = (EditText) findViewById(R.id.NameOfMaterial6);

        HeadPP_R_1 = (TextView) findViewById(R.id.HeadPP_R_1);
        HeadPP_R_2 = (TextView) findViewById(R.id.HeadPP_R_2);
        HeadPP_R_3 = (TextView) findViewById(R.id.HeadPP_R_3);
        HeadPP_R_4 = (TextView) findViewById(R.id.HeadPP_R_4);
        HeadPP_R_5 = (TextView) findViewById(R.id.HeadPP_R_5);
        HeadPP_R_6 = (TextView) findViewById(R.id.HeadPP_R_6);

        GoToWorkCompose = (Button) findViewById(R.id.GoToWorkCompose);

        SummaCHOG1 = (TextView) findViewById(R.id.SummaCHOG1);
        SummaCHOG2 = (TextView) findViewById(R.id.SummaCHOG2);
        SummaCHOG3 = (TextView) findViewById(R.id.SummaCHOG3);
        SummaCHOG4 = (TextView) findViewById(R.id.SummaCHOG4);
        SummaCHOG5 = (TextView) findViewById(R.id.SummaCHOG5);
        SummaCHOG6 = (TextView) findViewById(R.id.SummaCHOG6);
        SummaCHOG7 = (TextView) findViewById(R.id.SummaCHOG7);
        SummaCHOG8 = (TextView) findViewById(R.id.SummaCHOG8);

        SOD1Minus.setOnClickListener(this);
        SOD1Plus.setOnClickListener(this);
        SOD1.setOnClickListener(this);


        SOD2Minus.setOnClickListener(this);
        SOD2Plus.setOnClickListener(this);
        SOD2.setOnClickListener(this);


        SOD3Minus.setOnClickListener(this);
        SOD3Plus.setOnClickListener(this);
        SOD3.setOnClickListener(this);

        SOD4Minus.setOnClickListener(this);
        SOD4Plus.setOnClickListener(this);
        SOD4.setOnClickListener(this);

        SOD5Minus.setOnClickListener(this);
        SOD5Plus.setOnClickListener(this);
        SOD5.setOnClickListener(this);

        SOD6Minus.setOnClickListener(this);
        SOD6Plus.setOnClickListener(this);
        SOD6.setOnClickListener(this);

        SODMPMinus.setOnClickListener(this);
        SODMPPlus.setOnClickListener(this);
        SODMP.setOnClickListener(this);

        SODSZMinus.setOnClickListener(this);
        SODSZPlus.setOnClickListener(this);
        SODSZ.setOnClickListener(this);

        AutoPodbor.setOnClickListener(this);

        GoToWorkCompose.setOnClickListener(this);

        SpinnerMixType.setOnClickListener(this);


    }

    private void DatabaseReader(int id) {

        SQLiteOpenHelper abzDatabaseHelper = new ABZDatabaseHelper(this);

        try {
            //Получение ссылки на базу данных
            SQLiteDatabase MyDatabase = abzDatabaseHelper.getReadableDatabase();
            //Создаем курсор содержащий все столбцы одной строки, строки id которой равен 1
            Cursor cursor = MyDatabase.query("ZERN_SOSTAV",
                    new String[]{"_id",
                            "BUNKER1SITO1", "BUNKER1SITO2", "BUNKER1SITO3", "BUNKER1SITO4", "BUNKER1SITO5", "BUNKER1SITO6", "BUNKER1SITO7", "BUNKER1SITO8",
                            "BUNKER1SITO9", "BUNKER1SITO10", "BUNKER1SITO11", "BUNKER1SITO12",

                            "BUNKER2SITO1", "BUNKER2SITO2", "BUNKER2SITO3", "BUNKER2SITO4", "BUNKER2SITO5", "BUNKER2SITO6", "BUNKER2SITO7", "BUNKER2SITO8",
                            "BUNKER2SITO9", "BUNKER2SITO10", "BUNKER2SITO11", "BUNKER2SITO12",

                            "BUNKER3SITO1", "BUNKER3SITO2", "BUNKER3SITO3", "BUNKER3SITO4", "BUNKER3SITO5", "BUNKER3SITO6", "BUNKER3SITO7", "BUNKER3SITO8",
                            "BUNKER3SITO9", "BUNKER3SITO10", "BUNKER3SITO11", "BUNKER3SITO12",

                            "BUNKER4SITO1", "BUNKER4SITO2", "BUNKER4SITO3", "BUNKER4SITO4", "BUNKER4SITO5", "BUNKER4SITO6", "BUNKER4SITO7", "BUNKER4SITO8",
                            "BUNKER4SITO9", "BUNKER4SITO10", "BUNKER4SITO11", "BUNKER4SITO12",

                            "BUNKER5SITO1", "BUNKER5SITO2", "BUNKER5SITO3", "BUNKER5SITO4", "BUNKER5SITO5", "BUNKER5SITO6", "BUNKER5SITO7", "BUNKER5SITO8",
                            "BUNKER5SITO9", "BUNKER5SITO10", "BUNKER5SITO11", "BUNKER5SITO12",

                            "BUNKER6SITO1", "BUNKER6SITO2", "BUNKER6SITO3", "BUNKER6SITO4", "BUNKER6SITO5", "BUNKER6SITO6", "BUNKER6SITO7", "BUNKER6SITO8",
                            "BUNKER6SITO9", "BUNKER6SITO10", "BUNKER6SITO11", "BUNKER6SITO12",

                            "BUNKER7SITO1", "BUNKER7SITO2", "BUNKER7SITO3", "BUNKER7SITO4", "BUNKER7SITO5", "BUNKER7SITO6",

                            "BUNKER8SITO1", "BUNKER8SITO2", "BUNKER8SITO3", "BUNKER8SITO4", "BUNKER8SITO5", "BUNKER8SITO6",

                            "DOZA1", "DOZA2", "DOZA3", "DOZA4", "DOZA5", "DOZA6", "DOZAMP", "DOZASZ",

                            "TARGETSITO1", "TARGETSITO2", "TARGETSITO3", "TARGETSITO4", "TARGETSITO5", "TARGETSITO6",
                            "TARGETSITO7", "TARGETSITO8", "TARGETSITO9", "TARGETSITO10", "TARGETSITO11",

                            "NAMEOFMATERIAL1", "NAMEOFMATERIAL2", "NAMEOFMATERIAL3", "NAMEOFMATERIAL4", "NAMEOFMATERIAL5", "NAMEOFMATERIAL6"


                            // "SOD1"//, "SOD2", "SOD3", "SOD4", "SOD5", "SOD6", "SODMP","SODSZ"

                            //"TARGETSITO1", "TARGETSITO2", "TARGETSITO3","TARGETSITO4","TARGETSITO5","TARGETSITO6",
                            //"TARGETSITO7","TARGETSITO8","TARGETSITO9","TARGETSITO10", "TARGETSITO11"

                    },
                    "_id = ?",
                    new String[]{Integer.toString(id)}, null, null, null);

            if (cursor.moveToFirst()) { //обязательное перемещение курсора

                //Достаем данные из курсора, в формате Double, и заполняем этими данными строку частных остатков для Бункера горячих материалов №1

                for (int i = 0; i < CHOG.size(); i++) {
                    CHOG.get(i).setText(BigDecimal.valueOf(cursor.getDouble(i + 1)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                    CHOG2.get(i).setText(BigDecimal.valueOf(cursor.getDouble(i + 1 + 12)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                    CHOG3.get(i).setText(BigDecimal.valueOf(cursor.getDouble(i + 1 + 12 + 12)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                    CHOG4.get(i).setText(BigDecimal.valueOf(cursor.getDouble(i + 1 + 12 + 12 + 12)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                    CHOG50.get(i).setText(BigDecimal.valueOf(cursor.getDouble(i + 1 + 12 + 12 + 12 + 12)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                    CHOG6.get(i).setText(BigDecimal.valueOf(cursor.getDouble(i + 1 + 12 + 12 + 12 + 12 + 12)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());

                    if (i < CHOG_SZ.size()) {
                        CHOG_MP.get(i).setText(BigDecimal.valueOf(cursor.getDouble(i + 1 + 12 + 12 + 12 + 12 + 12 + 12)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                        CHOG_SZ.get(i).setText(BigDecimal.valueOf(cursor.getDouble(i + 1 + 12 + 12 + 12 + 12 + 12 + 12 + 6)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                    }
                }

                SOD1.setText(BigDecimal.valueOf(cursor.getDouble(85)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                SOD2.setText(BigDecimal.valueOf(cursor.getDouble(86)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                SOD3.setText(BigDecimal.valueOf(cursor.getDouble(87)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                SOD4.setText(BigDecimal.valueOf(cursor.getDouble(88)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                SOD5.setText(BigDecimal.valueOf(cursor.getDouble(89)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                SOD6.setText(BigDecimal.valueOf(cursor.getDouble(90)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                SODMP.setText(BigDecimal.valueOf(cursor.getDouble(91)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
                SODSZ.setText(BigDecimal.valueOf(cursor.getDouble(92)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());

                NameOfMaterial1.setText(cursor.getString(104));
                NameOfMaterial2.setText(cursor.getString(105));
                NameOfMaterial3.setText(cursor.getString(106));
                NameOfMaterial4.setText(cursor.getString(107));
                NameOfMaterial5.setText(cursor.getString(108));
                NameOfMaterial6.setText(cursor.getString(109));

                for (int i = 93; i < 104; i++) {

                    PP_R_Target.get(i - 93).setText(BigDecimal.valueOf(cursor.getDouble(i)).setScale(1, BigDecimal.ROUND_HALF_UP).toString());

                }

            }

            cursor.close(); //Закрываем курсор и базу данных
            MyDatabase.close();

        } catch (SQLiteException e) {

            Toast toast = Toast.makeText(this, "Database unavailable READ", Toast.LENGTH_SHORT);

            toast.show();

        }


    }

    public void DatabaseWriter(int id) {

        ContentValues values = new ContentValues();
        values.put("BUNKER1SITO1", Double.parseDouble(CHOG.get(0).getText().toString()));
        values.put("BUNKER1SITO2", Double.parseDouble(CHOG.get(1).getText().toString()));
        values.put("BUNKER1SITO3", Double.parseDouble(CHOG.get(2).getText().toString()));
        values.put("BUNKER1SITO4", Double.parseDouble(CHOG.get(3).getText().toString()));
        values.put("BUNKER1SITO5", Double.parseDouble(CHOG.get(4).getText().toString()));
        values.put("BUNKER1SITO6", Double.parseDouble(CHOG.get(5).getText().toString()));
        values.put("BUNKER1SITO7", Double.parseDouble(CHOG.get(6).getText().toString()));
        values.put("BUNKER1SITO8", Double.parseDouble(CHOG.get(7).getText().toString()));
        values.put("BUNKER1SITO9", Double.parseDouble(CHOG.get(8).getText().toString()));
        values.put("BUNKER1SITO10", Double.parseDouble(CHOG.get(9).getText().toString()));
        values.put("BUNKER1SITO11", Double.parseDouble(CHOG.get(10).getText().toString()));
        values.put("BUNKER1SITO12", Double.parseDouble(CHOG.get(11).getText().toString()));

        values.put("BUNKER2SITO1", Double.parseDouble(CHOG2.get(0).getText().toString()));
        values.put("BUNKER2SITO2", Double.parseDouble(CHOG2.get(1).getText().toString()));
        values.put("BUNKER2SITO3", Double.parseDouble(CHOG2.get(2).getText().toString()));
        values.put("BUNKER2SITO4", Double.parseDouble(CHOG2.get(3).getText().toString()));
        values.put("BUNKER2SITO5", Double.parseDouble(CHOG2.get(4).getText().toString()));
        values.put("BUNKER2SITO6", Double.parseDouble(CHOG2.get(5).getText().toString()));
        values.put("BUNKER2SITO7", Double.parseDouble(CHOG2.get(6).getText().toString()));
        values.put("BUNKER2SITO8", Double.parseDouble(CHOG2.get(7).getText().toString()));
        values.put("BUNKER2SITO9", Double.parseDouble(CHOG2.get(8).getText().toString()));
        values.put("BUNKER2SITO10", Double.parseDouble(CHOG2.get(9).getText().toString()));
        values.put("BUNKER2SITO11", Double.parseDouble(CHOG2.get(10).getText().toString()));
        values.put("BUNKER2SITO12", Double.parseDouble(CHOG2.get(11).getText().toString()));

        values.put("BUNKER3SITO1", Double.parseDouble(CHOG3.get(0).getText().toString()));
        values.put("BUNKER3SITO2", Double.parseDouble(CHOG3.get(1).getText().toString()));
        values.put("BUNKER3SITO3", Double.parseDouble(CHOG3.get(2).getText().toString()));
        values.put("BUNKER3SITO4", Double.parseDouble(CHOG3.get(3).getText().toString()));
        values.put("BUNKER3SITO5", Double.parseDouble(CHOG3.get(4).getText().toString()));
        values.put("BUNKER3SITO6", Double.parseDouble(CHOG3.get(5).getText().toString()));
        values.put("BUNKER3SITO7", Double.parseDouble(CHOG3.get(6).getText().toString()));
        values.put("BUNKER3SITO8", Double.parseDouble(CHOG3.get(7).getText().toString()));
        values.put("BUNKER3SITO9", Double.parseDouble(CHOG3.get(8).getText().toString()));
        values.put("BUNKER3SITO10", Double.parseDouble(CHOG3.get(9).getText().toString()));
        values.put("BUNKER3SITO11", Double.parseDouble(CHOG3.get(10).getText().toString()));
        values.put("BUNKER3SITO12", Double.parseDouble(CHOG3.get(11).getText().toString()));

        values.put("BUNKER4SITO1", Double.parseDouble(CHOG4.get(0).getText().toString()));
        values.put("BUNKER4SITO2", Double.parseDouble(CHOG4.get(1).getText().toString()));
        values.put("BUNKER4SITO3", Double.parseDouble(CHOG4.get(2).getText().toString()));
        values.put("BUNKER4SITO4", Double.parseDouble(CHOG4.get(3).getText().toString()));
        values.put("BUNKER4SITO5", Double.parseDouble(CHOG4.get(4).getText().toString()));
        values.put("BUNKER4SITO6", Double.parseDouble(CHOG4.get(5).getText().toString()));
        values.put("BUNKER4SITO7", Double.parseDouble(CHOG4.get(6).getText().toString()));
        values.put("BUNKER4SITO8", Double.parseDouble(CHOG4.get(7).getText().toString()));
        values.put("BUNKER4SITO9", Double.parseDouble(CHOG4.get(8).getText().toString()));
        values.put("BUNKER4SITO10", Double.parseDouble(CHOG4.get(9).getText().toString()));
        values.put("BUNKER4SITO11", Double.parseDouble(CHOG4.get(10).getText().toString()));
        values.put("BUNKER4SITO12", Double.parseDouble(CHOG4.get(11).getText().toString()));

        values.put("BUNKER5SITO1", Double.parseDouble(CHOG50.get(0).getText().toString()));
        values.put("BUNKER5SITO2", Double.parseDouble(CHOG50.get(1).getText().toString()));
        values.put("BUNKER5SITO3", Double.parseDouble(CHOG50.get(2).getText().toString()));
        values.put("BUNKER5SITO4", Double.parseDouble(CHOG50.get(3).getText().toString()));
        values.put("BUNKER5SITO5", Double.parseDouble(CHOG50.get(4).getText().toString()));
        values.put("BUNKER5SITO6", Double.parseDouble(CHOG50.get(5).getText().toString()));
        values.put("BUNKER5SITO7", Double.parseDouble(CHOG50.get(6).getText().toString()));
        values.put("BUNKER5SITO8", Double.parseDouble(CHOG50.get(7).getText().toString()));
        values.put("BUNKER5SITO9", Double.parseDouble(CHOG50.get(8).getText().toString()));
        values.put("BUNKER5SITO10", Double.parseDouble(CHOG50.get(9).getText().toString()));
        values.put("BUNKER5SITO11", Double.parseDouble(CHOG50.get(10).getText().toString()));
        values.put("BUNKER5SITO12", Double.parseDouble(CHOG50.get(11).getText().toString()));

        values.put("BUNKER6SITO1", Double.parseDouble(CHOG6.get(0).getText().toString()));
        values.put("BUNKER6SITO2", Double.parseDouble(CHOG6.get(1).getText().toString()));
        values.put("BUNKER6SITO3", Double.parseDouble(CHOG6.get(2).getText().toString()));
        values.put("BUNKER6SITO4", Double.parseDouble(CHOG6.get(3).getText().toString()));
        values.put("BUNKER6SITO5", Double.parseDouble(CHOG6.get(4).getText().toString()));
        values.put("BUNKER6SITO6", Double.parseDouble(CHOG6.get(5).getText().toString()));
        values.put("BUNKER6SITO7", Double.parseDouble(CHOG6.get(6).getText().toString()));
        values.put("BUNKER6SITO8", Double.parseDouble(CHOG6.get(7).getText().toString()));
        values.put("BUNKER6SITO9", Double.parseDouble(CHOG6.get(8).getText().toString()));
        values.put("BUNKER6SITO10", Double.parseDouble(CHOG6.get(9).getText().toString()));
        values.put("BUNKER6SITO11", Double.parseDouble(CHOG6.get(10).getText().toString()));
        values.put("BUNKER6SITO12", Double.parseDouble(CHOG6.get(11).getText().toString()));

        values.put("BUNKER7SITO1", Double.parseDouble(CHOG_MP.get(0).getText().toString()));
        values.put("BUNKER7SITO2", Double.parseDouble(CHOG_MP.get(1).getText().toString()));
        values.put("BUNKER7SITO3", Double.parseDouble(CHOG_MP.get(2).getText().toString()));
        values.put("BUNKER7SITO4", Double.parseDouble(CHOG_MP.get(3).getText().toString()));
        values.put("BUNKER7SITO5", Double.parseDouble(CHOG_MP.get(4).getText().toString()));
        values.put("BUNKER7SITO6", Double.parseDouble(CHOG_MP.get(5).getText().toString()));

        values.put("BUNKER8SITO1", Double.parseDouble(CHOG_SZ.get(0).getText().toString()));
        values.put("BUNKER8SITO2", Double.parseDouble(CHOG_SZ.get(1).getText().toString()));
        values.put("BUNKER8SITO3", Double.parseDouble(CHOG_SZ.get(2).getText().toString()));
        values.put("BUNKER8SITO4", Double.parseDouble(CHOG_SZ.get(3).getText().toString()));
        values.put("BUNKER8SITO5", Double.parseDouble(CHOG_SZ.get(4).getText().toString()));
        values.put("BUNKER8SITO6", Double.parseDouble(CHOG_SZ.get(5).getText().toString()));

        values.put("DOZA1", Double.parseDouble(SOD1.getText().toString()));
        values.put("DOZA2", Double.parseDouble(SOD2.getText().toString()));
        values.put("DOZA3", Double.parseDouble(SOD3.getText().toString()));
        values.put("DOZA4", Double.parseDouble(SOD4.getText().toString()));
        values.put("DOZA5", Double.parseDouble(SOD5.getText().toString()));
        values.put("DOZA6", Double.parseDouble(SOD6.getText().toString()));
        values.put("DOZAMP", Double.parseDouble(SODMP.getText().toString()));
        values.put("DOZASZ", Double.parseDouble(SODSZ.getText().toString()));


        values.put("TARGETSITO1", Double.parseDouble(PP_R_Target.get(0).getText().toString()));
        values.put("TARGETSITO2", Double.parseDouble(PP_R_Target.get(1).getText().toString()));
        values.put("TARGETSITO3", Double.parseDouble(PP_R_Target.get(2).getText().toString()));
        values.put("TARGETSITO4", Double.parseDouble(PP_R_Target.get(3).getText().toString()));
        values.put("TARGETSITO5", Double.parseDouble(PP_R_Target.get(4).getText().toString()));
        values.put("TARGETSITO6", Double.parseDouble(PP_R_Target.get(5).getText().toString()));
        values.put("TARGETSITO7", Double.parseDouble(PP_R_Target.get(6).getText().toString()));
        values.put("TARGETSITO8", Double.parseDouble(PP_R_Target.get(7).getText().toString()));
        values.put("TARGETSITO9", Double.parseDouble(PP_R_Target.get(8).getText().toString()));
        values.put("TARGETSITO10", Double.parseDouble(PP_R_Target.get(9).getText().toString()));
        values.put("TARGETSITO11", Double.parseDouble(PP_R_Target.get(10).getText().toString()));

        values.put("NAMEOFMATERIAL1", NameOfMaterial1.getText().toString());
        values.put("NAMEOFMATERIAL2", NameOfMaterial2.getText().toString());
        values.put("NAMEOFMATERIAL3", NameOfMaterial3.getText().toString());
        values.put("NAMEOFMATERIAL4", NameOfMaterial4.getText().toString());
        values.put("NAMEOFMATERIAL5", NameOfMaterial5.getText().toString());
        values.put("NAMEOFMATERIAL6", NameOfMaterial6.getText().toString());


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
}
