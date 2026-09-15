package ru.asphaltica.ABZ;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.asphaltica.ABZ.autopodbor.AutoPodbor;
import ru.asphaltica.ABZ.enumerated.ActivityName;
import ru.asphaltica.ABZ.enumerated.GrainTable;
import ru.asphaltica.ABZ.enumerated.MixType;
import ru.asphaltica.ABZ.enumerated.PercentageButtonType;
import ru.asphaltica.ABZ.enumerated.ResultTableRow;
import ru.asphaltica.ABZ.enumerated.Sito;
import ru.asphaltica.ABZ.enumerated.SitoType;
import ru.asphaltica.ABZ.helpers.Calculations;
import ru.asphaltica.ABZ.helpers.ChogCellPosition;
import ru.asphaltica.ABZ.helpers.MixInfoFactory;
import ru.asphaltica.ABZ.helpers.PercenageButtonTag;
import ru.asphaltica.ABZ.helpers.ResultTableCellPositionTag;
import ru.asphaltica.ABZ.helpers.Rounder;
import ru.asphaltica.ABZ.helpers.ViewElementsGenerator;
import ru.asphaltica.ABZ.model.Grain;
import ru.asphaltica.ABZ.model.MixInfo;
import ru.asphaltica.ABZ.view_group.GrainTableViewGroup;
import ru.asphaltica.ABZ.view_group.ResultTableColumn;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Выбор элемента в данном списке будет изменять нормативные полные проходы
    Button mixChooserButton;
    Button autoPodborButton;


    Button GoToWorkCompose;

    Button SaveButton;
    Button LoadButton;

    TextView NameOfProject;

    int NumberOfMix;

    int CurrentDataBaseID;

    SharedPreferences MyPref;

           /* + "BITUMUP100 REAL, " //110
            + "BITUMIN100 REAL, "
            + "SD REAL, "
            + "DD REAL, "
            + "AD REAL, "
            + "MASSAZAMESA REAL, "
            + "SDCHECKED INTEGER, "
            + "DDCHECKED INTEGER, "
            + "ADCHECKED INTEGER, "
            + "PRIMECHANIE TEXT,"*/

    // Переменные для временного хранения данных WorkCompozeActivity
    Double BITUMUP100;
    Double BITUMIN100;
    Double SD;
    Double DD;
    Double AD;
    Double MASSAZAMESA;
    int SDCHECKED;
    int DDCHECKED;
    int ADCHECKED;
    String PRIMECHANIE;


    //Новая часть - 2026

    //Это контейнеры для расчета зернового состава каждого из компонентов
    LinearLayout rootGrainLayout1;
    LinearLayout rootGrainLayout2;
    LinearLayout rootGrainLayout3;
    LinearLayout rootGrainLayout4;
    LinearLayout rootGrainLayout5;
    LinearLayout rootGrainLayout6;
    LinearLayout rootGrainLayoutMP;
    LinearLayout rootGrainLayoutSZ;

    LinearLayout rootDesignLayout;
    TextView summaPercentage;

    //Корневой контейнер для строк таблицы результатов подбора
    LinearLayout rootResultTableLayout;
    //Строка с названиями ячеек сит
    LinearLayout sieveSizesRow;
    //Строка с подобранной кривой зерновго состава
    LinearLayout resultMixRow;
    //Строка с целевой кривой зернового состава
    LinearLayout targetMixRow;
    //Строка верхних пределов требований
    LinearLayout upRequirementsRow;
    //Строка нижних пределеов требований
    LinearLayout downRequirementsRow;

    Map<Sito, ResultTableColumn> resultTableColumnMap;

    //Объект в котором группируется вся информация для создания контейнера для расчета зернового состава компонента
    GrainTableViewGroup grainTableViewGroup1;
    GrainTableViewGroup grainTableViewGroup2;
    GrainTableViewGroup grainTableViewGroup3;
    GrainTableViewGroup grainTableViewGroup4;
    GrainTableViewGroup grainTableViewGroup5;
    GrainTableViewGroup grainTableViewGroup6;
    GrainTableViewGroup grainTableViewGroupMP;
    GrainTableViewGroup grainTableViewGroupSZ;

    //Тут храним названия каждого из ингредиентов и его зерновой состав представленный в виде частных остатков
    Map<GrainTable, Grain> grains = new HashMap<>();
    //Тут храним процентное содержание компонентов
    Map<GrainTable, Double> percentageMap = new HashMap<>();
    //Информация о смеси
    MixInfo mixInfo;

    Map<GrainTable, GrainTableViewGroup> grainViewGroupsMap = new HashMap<>();

    //Содержит список сит по которым определеяется зерновой состав материалов горячего бункера
    List<Sito> actualSitoListOfGrainTables;
    //Тоже самое но без дна
    List<Sito> actualSitoListForResultTable;
    //Содержит список сит по которым определеяется зерновой состав МП и СЗ
    List<Sito> powderSitoListOfGrainTables;
    //Тут хранится целевой зерновой состав
    Map<Sito, Double> targetMixGrain;

    //Блок инициализации, заполняем Map объектами Grain чтобы можно было обращаться к ним по ключу
    // соотвествующему определенному ингредиенту
    {
        //Тут хранятся зерновые составы горячих материалов
        grains.put(GrainTable.GRAIN_TABLE_1, Grain.getDefaultGrain(GrainTable.GRAIN_TABLE_1));
        grains.put(GrainTable.GRAIN_TABLE_2, Grain.getDefaultGrain(GrainTable.GRAIN_TABLE_2));
        grains.put(GrainTable.GRAIN_TABLE_3, Grain.getDefaultGrain(GrainTable.GRAIN_TABLE_3));
        grains.put(GrainTable.GRAIN_TABLE_4, Grain.getDefaultGrain(GrainTable.GRAIN_TABLE_4));
        grains.put(GrainTable.GRAIN_TABLE_5, Grain.getDefaultGrain(GrainTable.GRAIN_TABLE_5));
        grains.put(GrainTable.GRAIN_TABLE_6, Grain.getDefaultGrain(GrainTable.GRAIN_TABLE_6));
        //Тут хранится зерновой состав минерального порошка
        grains.put(GrainTable.GRAIN_TABLE_7, Grain.getDefaultGrain(GrainTable.GRAIN_TABLE_7));
        //Тут хранится зерновой состав собственного заполнителя
        grains.put(GrainTable.GRAIN_TABLE_8, Grain.getDefaultGrain(GrainTable.GRAIN_TABLE_8));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Находим и подключаем view элементы
        findById();

        mixInfo = MixInfoFactory.getMixInfo(MixType.A16VT);

        //Получаем список сит по которым будет рассчитан зерновой состав
        actualSitoListOfGrainTables = mixInfo.getSitoListForGrainTable();

        //Убираем DNO
        actualSitoListForResultTable = mixInfo.getSitoListForResultTable();

        powderSitoListOfGrainTables = Sito.getPowderSitoList();

        //Получаем из БД процентное содержание каждого компонента
        percentageMap.put(GrainTable.GRAIN_TABLE_1, 25.0);
        percentageMap.put(GrainTable.GRAIN_TABLE_2, 10.0);
        percentageMap.put(GrainTable.GRAIN_TABLE_3, 10.0);
        percentageMap.put(GrainTable.GRAIN_TABLE_4, 10.0);
        percentageMap.put(GrainTable.GRAIN_TABLE_5, 15.0);
        percentageMap.put(GrainTable.GRAIN_TABLE_6, 25.0);
        percentageMap.put(GrainTable.GRAIN_TABLE_7, 3.0);
        percentageMap.put(GrainTable.GRAIN_TABLE_8, 2.0);

        //Получаем из БД целевой зерновой состав
        targetMixGrain = new HashMap<>();
        targetMixGrain.put(Sito.S_E0_0063_R0_0071, 5.1);
        targetMixGrain.put(Sito.S_E0_125_R0_16, 7.1);
        targetMixGrain.put(Sito.S_E0_25_R0_315, 9.1);
        targetMixGrain.put(Sito.S_E0_5_R0_63, 12.0);
        targetMixGrain.put(Sito.S_E1_R1_25, 16.0);
        targetMixGrain.put(Sito.S_E2_R2_5, 23.4);
        targetMixGrain.put(Sito.S_E4_R5, 36.0);
        targetMixGrain.put(Sito.S_E5_6, 0.0);
        targetMixGrain.put(Sito.S_E8_R10, 61.0);
        targetMixGrain.put(Sito.S_E11_2_R15, 79.0);
        targetMixGrain.put(Sito.S_E_16_R20, 100.0);
        targetMixGrain.put(Sito.S_E_22_4, 0.0);
        targetMixGrain.put(Sito.S_E_31_5_R40, 0.0);

        createInterface();


        startActionsOne();

        // Связываем View xml и объекты
        // Создаем объект для работы с системой хранения настроек
        // Здесь имя файла Values - должно быть единым для всего приложения
        MyPref = getSharedPreferences("Values", MODE_PRIVATE);

        //Если ячейка настроек не пуста устанавливаем номер строки для загрузки из базы данных
        if (MyPref.getInt("CurrentDataBaseID", 0) != 0) {
            CurrentDataBaseID = MyPref.getInt("CurrentDataBaseID", 0);
        } else CurrentDataBaseID = 1;


        NameOfProject.setText(" Проект:  " + MyPref.getString(Integer.toString(CurrentDataBaseID), ""));

    }

    private void createInterface() {

        mixChooserButton.setText(mixInfo.getMixType().getMixName());

        //Создаем объект мета-данных для каждого из ингредиентов
        grainTableViewGroup1 = new GrainTableViewGroup(
                GrainTable.GRAIN_TABLE_1, "№1",
                this,
                rootGrainLayout1,
                R.color.FirstMaterialTableDark, R.color.FirstMaterialTable);
        grainTableViewGroup2 = new GrainTableViewGroup(
                GrainTable.GRAIN_TABLE_2, "№2",
                this,
                rootGrainLayout2,
                R.color.SecondMaterialTableDark, R.color.SecondMaterialTable);
        grainTableViewGroup3 = new GrainTableViewGroup(
                GrainTable.GRAIN_TABLE_3, "№3",
                this,
                rootGrainLayout3,
                R.color.ThirdMaterialTableDark, R.color.ThirdMaterialTable);
        grainTableViewGroup4 = new GrainTableViewGroup(
                GrainTable.GRAIN_TABLE_4, "№4",
                this,
                rootGrainLayout4,
                R.color.FourthMaterialTableDark, R.color.FourthMaterialTable);
        grainTableViewGroup5 = new GrainTableViewGroup(
                GrainTable.GRAIN_TABLE_5, "№5",
                this,
                rootGrainLayout5,
                R.color.FifthMaterialTableDark, R.color.FifthMaterialTable);
        grainTableViewGroup6 = new GrainTableViewGroup(
                GrainTable.GRAIN_TABLE_6, "№6",
                this,
                rootGrainLayout6,
                R.color.SixthMaterialTableDark, R.color.SixthMaterialTable);
        grainTableViewGroupMP = new GrainTableViewGroup(
                GrainTable.GRAIN_TABLE_7, "№7",
                this,
                rootGrainLayoutMP,
                R.color.SeventhMaterialTableDark, R.color.SeventhMaterialTable);
        grainTableViewGroupSZ = new GrainTableViewGroup(
                GrainTable.GRAIN_TABLE_8, "№8",
                this,
                rootGrainLayoutSZ,
                R.color.EighthMaterialTableDark, R.color.EighthMaterialTable);


        //Кладем объекты с мета-данными в Map для возможности доступа по имени ингредиента
        grainViewGroupsMap.put(GrainTable.GRAIN_TABLE_1, grainTableViewGroup1);
        grainViewGroupsMap.put(GrainTable.GRAIN_TABLE_2, grainTableViewGroup2);
        grainViewGroupsMap.put(GrainTable.GRAIN_TABLE_3, grainTableViewGroup3);
        grainViewGroupsMap.put(GrainTable.GRAIN_TABLE_4, grainTableViewGroup4);
        grainViewGroupsMap.put(GrainTable.GRAIN_TABLE_5, grainTableViewGroup5);
        grainViewGroupsMap.put(GrainTable.GRAIN_TABLE_6, grainTableViewGroup6);
        grainViewGroupsMap.put(GrainTable.GRAIN_TABLE_7, grainTableViewGroupMP);
        grainViewGroupsMap.put(GrainTable.GRAIN_TABLE_8, grainTableViewGroupSZ);

        //Для возможности итерирования кладем объекты мета-данных в List
        //НУЖНО ЛИ ЭТО!!!
        List<GrainTableViewGroup> grainTables = List.of(
                grainTableViewGroup1,
                grainTableViewGroup2,
                grainTableViewGroup3,
                grainTableViewGroup4,
                grainTableViewGroup5,
                grainTableViewGroup6,
                grainTableViewGroupMP,
                grainTableViewGroupSZ);

        //В этом цикле программно создаем строки View элементов и сами ячейки для каждого ингредиента
        //В первом цикле итерируемся по каждому ингредиенту
        for (GrainTableViewGroup grainTableViewGroup : grainTables) {

            //Достаем контейнер в котором будем создавать строки
            LinearLayout rootGrainLayout = grainTableViewGroup.getRootGrainLayout();


            //Создаем переменные для создания строк и кладем в них ссылки из каждого ингредиента
            LinearLayout linearLayoutForInfo = grainTableViewGroup.getLinearLayoutForInfo();
            linearLayoutForInfo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout linearLayoutForHeader = grainTableViewGroup.getLinearLayoutForHeader();
            LinearLayout linearLayoutForChog = grainTableViewGroup.getLinearLayoutForChog();
            LinearLayout linearLayoutForChop = grainTableViewGroup.getLinearLayoutForChop();
            LinearLayout linearLayoutForPo = grainTableViewGroup.getLinearLayoutForPo();
            LinearLayout linearLayoutForPp = grainTableViewGroup.getLinearLayoutForPp();

            //Создание элементов для информационной строки над каждой таблицей зернового состава
            // Общая высота элементов
            int height = dpToPx(48);
            TextView grainTableDescriptionView = ViewElementsGenerator.createDescriptionTextView(
                    this,
                    grainTableViewGroup.getGrainTableName().getDescription(),
                    height);
            EditText bunkerName = ViewElementsGenerator.createBunkerNameEditText(this,
                    grainTableViewGroup.getGrainTableName().getDefaultName(), height);
            //Устанавливаем действие готово на клавиатуре
            bunkerName.setImeOptions(EditorInfo.IME_ACTION_DONE);
            //Устанавливаем однострочный режим
            bunkerName.setSingleLine(true);
            //Помещаем его в объект grainTableViewGroup для возможности доступа
            grainTableViewGroup.setBunkerName(bunkerName);

            TextView labelSumma = ViewElementsGenerator.createLabelTextView(this, "Сумма ЧО: ", height);

            TextView summaCHOG = ViewElementsGenerator.createSummaTextView(this, "1000", height);
            grainTableViewGroup.setChogSumma(summaCHOG);

            TextView labelGramm = ViewElementsGenerator.createLabelTextView(this, " г", height);


            linearLayoutForInfo.addView(grainTableDescriptionView);
            linearLayoutForInfo.addView(bunkerName);
            linearLayoutForInfo.addView(labelSumma);
            linearLayoutForInfo.addView(summaCHOG);
            linearLayoutForInfo.addView(labelGramm);


            //В каждую строку закидываем TextView c заголовком
            linearLayoutForHeader.addView(ViewElementsGenerator.createTextView(this,
                    (grainTableViewGroup.getNumber()),
                    grainTableViewGroup.getPrimaryColor()));

            linearLayoutForChog.addView(ViewElementsGenerator.createTextView(this,
                    getString(R.string.CHOG),
                    grainTableViewGroup.getPrimaryColor()));
            linearLayoutForChop.addView(ViewElementsGenerator.createTextView(this,
                    getString(R.string.CHOP),
                    grainTableViewGroup.getPrimaryColor()));
            linearLayoutForPo.addView(ViewElementsGenerator.createTextView(this,
                    getString(R.string.PO),
                    grainTableViewGroup.getPrimaryColor()));
            linearLayoutForPp.addView(ViewElementsGenerator.createTextView(this,
                    getString(R.string.PP),
                    grainTableViewGroup.getPrimaryColor()));

            //Заполняем строки элементами итерируясь по списку сит
            for (Sito sito : actualSitoListOfGrainTables) {


                //Если отрисовывается таблица минерального порошка или пыли мы пропускаем те сита
                // которые не включены в их список отображения
                if ((grainTableViewGroup.getGrainTableName().equals(GrainTable.GRAIN_TABLE_7)
                        || grainTableViewGroup.getGrainTableName().equals(GrainTable.GRAIN_TABLE_8))
                        && !powderSitoListOfGrainTables.contains(sito)) {
                    continue;
                }
                //Создаем TextView c заголовком таблицы, содержащим название сита
                String sitoSize = mixInfo.getSitoType().equals(SitoType.EURO) ? sito.getEuroSize() : sito.getRussianSize();
                linearLayoutForHeader.addView(ViewElementsGenerator.createTextView(this, sitoSize,
                        grainTableViewGroup.getPrimaryColor()));
                //Создаем TextView для частного остатка в граммах
                TextView chogTextView = ViewElementsGenerator.createTextView(this, "0",
                        grainTableViewGroup.getColor());
                //Вешаем на него тег, чтобы потом понимать по какому именно нажали
                chogTextView.setTag(new ChogCellPosition(grainTableViewGroup.getGrainTableName(), sito));
                //Вешаем слушатель нажатия
                chogTextView.setOnClickListener(this);
                //Кладем его в специальную Map, для возможности доступа
                grainTableViewGroup.getChogViews().put(sito, chogTextView);
                //Добавляем готовый элемент в контейнер
                linearLayoutForChog.addView(chogTextView);

                //Создаем TextView для частного остатка в процентах
                TextView chopTextView = ViewElementsGenerator.createTextView(this, "0",
                        grainTableViewGroup.getColor());
                //Кладем его в специальную Map, для возможности доступа
                grainTableViewGroup.getChopViews().put(sito, chopTextView);
                //Добавляем готовый элемент в контейнер
                linearLayoutForChop.addView(chopTextView);

                //Создаем TextView для полного остатка в процентах
                TextView poTextView = ViewElementsGenerator.createTextView(this, "0",
                        grainTableViewGroup.getColor());
                //Кладем его в специальную Map, для возможности доступа
                grainTableViewGroup.getPoViews().put(sito, poTextView);
                //Добавляем готовый элемент в контейнер
                linearLayoutForPo.addView(poTextView);

                //Создаем TextView для полного прохода в процентах
                TextView ppTextView = ViewElementsGenerator.createTextView(this, "0",
                        grainTableViewGroup.getColor());
                //Кладем его в специальную Map, для возможности доступа
                grainTableViewGroup.getPpViews().put(sito, ppTextView);
                //Добавляем готовый элемент в контейнер
                linearLayoutForPp.addView(ppTextView);
            }

            //Вставляем созданные строки в корневой контейнер
            rootGrainLayout.addView(linearLayoutForInfo);
            rootGrainLayout.addView(linearLayoutForHeader);
            rootGrainLayout.addView(linearLayoutForChog);
            rootGrainLayout.addView(linearLayoutForChop);
            rootGrainLayout.addView(linearLayoutForPo);
            rootGrainLayout.addView(linearLayoutForPp);
        }

        //В этом цикле создаем блок дизайна потом можешь перенести это в общий цикл
        int rowCount = 0;
        //Создаем контейнер - строку в нем будем хранить по два блока
        LinearLayout designBlockRow = ViewElementsGenerator.createLinearLayoutForPercentageRow(this);
        int height = dpToPx(30);
        for (GrainTableViewGroup grainTableViewGroup : grainTables) {
            rowCount++;

            //Создаем TextView c заголовком таблицы, содержащим название сита
            TextView headerTextView = ViewElementsGenerator.createHeaderPercentageTextView(this,
                    grainTableViewGroup.getGrainTableName().getDefaultName(), height, grainTableViewGroup.getPrimaryColor());
            grainTableViewGroup.setHeaderTextView(headerTextView);

            //Создаем кнопку уменьшения дозировки
            Button minusButton = ViewElementsGenerator.createPercentageButton(this, "-",
                    height, grainTableViewGroup.getPrimaryColor());
            //Кладем ее в grainTableViewGroup
            grainTableViewGroup.setMinusButton(minusButton);
            //Вешаем на него тег, чтобы потом понимать по какому именно нажали
            minusButton.setTag(new PercenageButtonTag(grainTableViewGroup.getGrainTableName(), PercentageButtonType.MINUS));
            //Вешаем слушатель
            minusButton.setOnClickListener(this);

            //Создаем TextView для отображения дозировкой
            TextView percentageTextView = ViewElementsGenerator.createPercentageTextView(this,
                    Double.toString(percentageMap.get(grainTableViewGroup.getGrainTableName())), height, grainTableViewGroup.getPrimaryColor());
            //Кладем ее в grainTableViewGroup
            grainTableViewGroup.setPercentageTextView(percentageTextView);
            //Вешаем на него тег, чтобы потом понимать по какому именно нажали
            percentageTextView.setTag(new PercenageButtonTag(grainTableViewGroup.getGrainTableName(), PercentageButtonType.PERCENTAGE_FIELD));
            percentageTextView.setOnClickListener(this);


            //Создаем кнопку увеличения дозировки
            Button plusButton = ViewElementsGenerator.createPercentageButton(this, "+", height, grainTableViewGroup.getPrimaryColor());
            //Кладем ее в grainTableViewGroup
            grainTableViewGroup.setPlusButton(plusButton);
            //Вешаем на него тег, чтобы потом понимать по какому именно нажали
            plusButton.setTag(new PercenageButtonTag(grainTableViewGroup.getGrainTableName(), PercentageButtonType.PLUS));
            //Вешаем слушатель
            plusButton.setOnClickListener(this);

            designBlockRow.addView(headerTextView);
            designBlockRow.addView(minusButton);
            designBlockRow.addView(percentageTextView);
            designBlockRow.addView(plusButton);
            //Через каждые два блока кидаем заполненную строку в контейнер и создаем новую строку
            if (rowCount % 2 == 0) {
                rootDesignLayout.addView(designBlockRow);
                designBlockRow = ViewElementsGenerator.createLinearLayoutForPercentageRow(this);
            }

        }

        resultTableColumnMap = new HashMap<>();
        sieveSizesRow = ViewElementsGenerator.createLinearLayoutForRow(this);
        resultMixRow = ViewElementsGenerator.createLinearLayoutForRow(this);
        targetMixRow = ViewElementsGenerator.createLinearLayoutForRow(this);
        upRequirementsRow = ViewElementsGenerator.createLinearLayoutForRow(this);
        downRequirementsRow = ViewElementsGenerator.createLinearLayoutForRow(this);


        height = dpToPx(25);


        //Заполняем строки таблицы с результатами подбора
        for (Sito sito : actualSitoListForResultTable) {
            String sitoSize = mixInfo.getSitoType().equals(SitoType.EURO) ? sito.getEuroSize() : sito.getRussianSize();
            TextView sieveSizeTextView = ViewElementsGenerator.createResultTableTextView(this, sitoSize, height, R.color.FirstMaterialTableDark);
            sieveSizesRow.addView(sieveSizeTextView);

            TextView resultValueTextView = ViewElementsGenerator.createResultTableTextView(this, "0", height, R.color.ResultTable);
            resultMixRow.addView(resultValueTextView);

            String targetValue = String.valueOf(targetMixGrain.get(sito));
            TextView targetValueTextView = ViewElementsGenerator.createResultTableTextView(this, targetValue, height, R.color.FourthMaterialTable);
            //Вешаем тег
            targetValueTextView.setTag(new ResultTableCellPositionTag(ResultTableRow.TARGET_ROW, sito));
            //Вешаем слушатель нажатия
            targetValueTextView.setOnClickListener(this);
            targetMixRow.addView(targetValueTextView);

            TextView upRequirementsTextView = ViewElementsGenerator.createResultTableTextView(this, "0", height, R.color.FirstMaterialTable);
            upRequirementsRow.addView(upRequirementsTextView);

            TextView downRequirementsTextView = ViewElementsGenerator.createResultTableTextView(this, "0", height, R.color.FirstMaterialTable);
            downRequirementsRow.addView(downRequirementsTextView);

            resultTableColumnMap.put(sito, new ResultTableColumn(
                    sieveSizeTextView,
                    resultValueTextView,
                    targetValueTextView,
                    upRequirementsTextView,
                    downRequirementsTextView
            ));
        }
        rootResultTableLayout.addView(sieveSizesRow);
        rootResultTableLayout.addView(resultMixRow);
        rootResultTableLayout.addView(targetMixRow);
        rootResultTableLayout.addView(upRequirementsRow);
        rootResultTableLayout.addView(downRequirementsRow);

        //Настраиваем дублирование пользовательской подписи каждого кармана и снятие фокуса при завершении редактирования
        for (GrainTableViewGroup grainTableViewGroup : grainViewGroupsMap.values()) {
            setupEditText(grainTableViewGroup.getBunkerName(), grainTableViewGroup.getHeaderTextView());
        }
    }

    private void refreshTables() {

        grainTableViewGroup1.getRootGrainLayout().removeAllViews();
        grainTableViewGroup2.getRootGrainLayout().removeAllViews();
        grainTableViewGroup3.getRootGrainLayout().removeAllViews();
        grainTableViewGroup4.getRootGrainLayout().removeAllViews();
        grainTableViewGroup5.getRootGrainLayout().removeAllViews();
        grainTableViewGroup6.getRootGrainLayout().removeAllViews();
        grainTableViewGroupSZ.getRootGrainLayout().removeAllViews();
        grainTableViewGroupMP.getRootGrainLayout().removeAllViews();

        rootDesignLayout.removeAllViews();
        rootResultTableLayout.removeAllViews();

        createInterface();

        startActionsOne();


        System.out.println();
    }

    private void painter() {

        int backgroundColorRED = ContextCompat.getColor(this, R.color.SecondMaterialTableDark);
        int backgroundColorSUMMA = ContextCompat.getColor(this, R.color.FourthMaterialTableDark);
        int backgroundColorUpDownPositive = ContextCompat.getColor(this, R.color.FirstMaterialTable);
        int backgroundColorResultPositive = ContextCompat.getColor(this, R.color.ResultTable);
        int backgroundColorResultNegative = ContextCompat.getColor(this, R.color.ResultTableDark);


        if (Double.parseDouble(summaPercentage.getText().toString()) != 100.0) {
            summaPercentage.setBackgroundColor(backgroundColorRED);
        } else {
            summaPercentage.setBackgroundColor(backgroundColorSUMMA);
        }

        for (Sito sito : actualSitoListForResultTable) {
            double resultValue = Double.parseDouble(resultTableColumnMap.get(sito)
                    .getResultValueTextView().getText().toString());
            double targetValue = Double.parseDouble(resultTableColumnMap.get(sito)
                    .getTargetValueTextView().getText().toString());

            double startBoundary = Double.parseDouble(resultTableColumnMap.get(sito).getUpRequirementTextView().getText().toString());
            double endBoundary = Double.parseDouble(resultTableColumnMap.get(sito).getDownRequirementTextView().getText().toString());


            if (Math.abs(resultValue-targetValue) > 1.0) {
                resultTableColumnMap.get(sito).getResultValueTextView().setBackgroundColor(backgroundColorResultNegative);
            } else {
                resultTableColumnMap.get(sito).getResultValueTextView().setBackgroundColor(backgroundColorResultPositive);
            }

            if (resultValue < startBoundary && startBoundary != 0.0) {
                resultTableColumnMap.get(sito).getUpRequirementTextView().setBackgroundColor(backgroundColorRED);
            } else resultTableColumnMap.get(sito).getUpRequirementTextView().setBackgroundColor(backgroundColorUpDownPositive);

            if (resultValue > endBoundary && startBoundary != 0.0) {
                resultTableColumnMap.get(sito).getDownRequirementTextView().setBackgroundColor(backgroundColorRED);
            } else resultTableColumnMap.get(sito).getDownRequirementTextView().setBackgroundColor(backgroundColorUpDownPositive);

        }


    }

    //Блок нового кода
    //Вызови этот метод когда развернул актививти и загрузил данные из БД
    private void startActionsOne() {
        //Заполняем таблицы полученными данными
        for (GrainTable grainTableName : GrainTable.values()) {
            if (grainViewGroupsMap.get(grainTableName) != null) { //Убери этот if когда будут все таблицы
                calculateAndShowGrainTableByName(grainTableName);
            }
        }
        //подсчитываем и вписываем сумму дозировок
        summaPercentage.setText(getSummaPercentage());
        calculateAndShowResultMix();
    }

    //Метод рассчитывает зерновой состав и заполняет таблицу по ее имени
    //Вызываем его при первоначальной загрузке приложения для всех таблиц и выборочно при изменении частных остатков
    private void calculateAndShowGrainTableByName(GrainTable grainTableName) {

        List<Sito> orderList = (grainTableName.equals(GrainTable.GRAIN_TABLE_7) || grainTableName.equals(GrainTable.GRAIN_TABLE_8))
                ? powderSitoListOfGrainTables : actualSitoListOfGrainTables;

        Grain grain = grains.get(grainTableName);

        Map<Sito, Double> chogsMap = grain.getChog();
        Map<Sito, Double> chopsMap = Calculations.chopsCalculate(chogsMap);
        Map<Sito, Double> poMap = Calculations.poCalculate(chopsMap, orderList);
        //Если зерновой состав пустой, то полные проходы делаем нулевыми
        Map<Sito, Double> ppMap = grain.isEmpty() ? grain.getChog() : Calculations.ppCalculate(poMap);


        double chop;
        double po;
        double pp;

        //Производим заполнение таблицы полученными значениями
        for (Sito sito : orderList) {
            grainViewGroupsMap.get(grainTableName).getChogViews().get(sito)
                    .setText(chogsMap.get(sito).toString());
            chop = Rounder.roundDouble(1, chopsMap.get(sito));
            grainViewGroupsMap.get(grainTableName).getChopViews().get(sito)
                    .setText(Double.toString(chop));
            po = Rounder.roundDouble(1, poMap.get(sito));
            grainViewGroupsMap.get(grainTableName).getPoViews().get(sito)
                    .setText(Double.toString(po));
            pp = Rounder.roundDouble(1, ppMap.get(sito));
            grainViewGroupsMap.get(grainTableName).getPpViews().get(sito)
                    .setText(Double.toString(pp));
        }

        //Считаем сумму частных остатков и записываем над таблицей
        grainViewGroupsMap.get(grainTableName).getChogSumma()
                .setText(Double.toString(Calculations.chogSumma(chogsMap)));

        fillRequirements();

    }

    //Метод заполняет требования к зерновому составу
    private void fillRequirements() {
        for (Sito sito : actualSitoListForResultTable) {

            switch (mixInfo.getSitoType()) {
                case EURO: {
                    if (targetMixGrain.get(sito) != 0.0) {
                        double targetValue = targetMixGrain.get(sito);
                        if (mixInfo.getDopuskMap().get(sito) != null
                                && mixInfo.getDopuskMap().get(sito) != 0.0) {
                            double dopusk = mixInfo.getDopuskMap().get(sito);

                            double upValue = Rounder.roundDouble(1,
                                    targetValue - dopusk);
                            if (upValue < 0) upValue = 0.0;

                            double downValue = Rounder.roundDouble(1,
                                    targetValue + dopusk);
                            if (downValue > 100) downValue = 100.0;

                            resultTableColumnMap.get(sito)
                                    .getUpRequirementTextView().setText(String.valueOf(upValue));
                            resultTableColumnMap.get(sito)
                                    .getDownRequirementTextView().setText(String.valueOf(downValue));
                        }
                    }
                    break;
                }

                case RUSSIAN: {
                    resultTableColumnMap.get(sito).getUpRequirementTextView()
                            .setText(String.valueOf(mixInfo.getBoundaryDownMap().get(sito)));
                    resultTableColumnMap.get(sito).getDownRequirementTextView()
                            .setText(String.valueOf(mixInfo.getBoundaryUpMap().get(sito)));
                    break;
                }
            }
        }
    }


    private void calculateAndShowResultMix() {
        Map<Sito, Double> resultMix = Calculations.calculateResultMix(
                grains, percentageMap);
        for (Sito sito : actualSitoListForResultTable) {
            resultTableColumnMap.get(sito).getResultValueTextView()
                    .setText(resultMix.get(sito).toString());
        }
        painter();
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {



            //Обработка переходов на ProjectChooser

            int id = v.getId();

            if (id == R.id.autoPodborButton) {

                percentageMap = AutoPodbor.getDosageByMix(
                        grains,
                        targetMixGrain,
                        percentageMap.get(GrainTable.GRAIN_TABLE_8)
                );

                // обновляем процентное содержание компонентов
                for (GrainTable grainTable : GrainTable.values()) {
                    grainViewGroupsMap.get(grainTable)
                            .getPercentageTextView()
                            .setText(Double.toString(percentageMap.get(grainTable)));
                }

                // считаем сумму
                summaPercentage.setText(getSummaPercentage());

                // считаем полученную кривую
                calculateAndShowResultMix();

            } else if (id == R.id.mixChooserButton) {

                Intent intent = new Intent(this, MixChooser.class);
                intent.putExtra("MIX_TYPE", mixInfo.getMixType());
                startActivityForResult(intent, 1);

            } else if (id == R.id.SaveButton) {

                Intent intentPC_Save = new Intent(this, ProjectChooser.class);
                intentPC_Save.putExtra("TYPE_OF_CHOOSE", "SAVE");
                startActivityForResult(intentPC_Save, 2);

            } else if (id == R.id.LoadButton) {

                Intent intentPC_Load = new Intent(this, ProjectChooser.class);
                intentPC_Load.putExtra("TYPE_OF_CHOOSE", "LOAD");
                startActivityForResult(intentPC_Load, 2);

            } else if (id == R.id.GoToWorkCompose) {

                HashMap<GrainTable, Double> percentageMap = new HashMap<>();
                HashMap<GrainTable, String> bunkerNamesMap = new HashMap<>();

                for (GrainTableViewGroup grainTableViewGroup : grainViewGroupsMap.values()) {

                    if (grainTableViewGroup.getBunkerName() != null) {

                        percentageMap.put(
                                grainTableViewGroup.getGrainTableName(),
                                Double.parseDouble(
                                        grainTableViewGroup
                                                .getPercentageTextView()
                                                .getText()
                                                .toString()
                                )
                        );

                        bunkerNamesMap.put(
                                grainTableViewGroup.getGrainTableName(),
                                grainTableViewGroup
                                        .getBunkerName()
                                        .getText()
                                        .toString()
                        );
                    }
                }

                Intent intent2 = new Intent(this, WorkComposeActivity.class);
                intent2.putExtra("PERCENTAGE", percentageMap);
                intent2.putExtra("NAMES", bunkerNamesMap);
                intent2.putExtra("DataBazeID", CurrentDataBaseID);

                startActivityForResult(intent2, 1);
            }

        //Блок нового кода


        //Блок нового кода
        Object tag = v.getTag();

        if (tag instanceof ChogCellPosition) {
            ChogCellPosition chogCellPosition = (ChogCellPosition) tag;


            GrainTable grainTableName = chogCellPosition.getGrainTableName();
            //Проверяем в какой таблице нажали, если это минеральный порошок или пыль то отдаем соотвествующий список сит
            //Если нет то отдаем актуальный набор сит для фракций щебня
            List<Sito> sitoList = grainTableName.equals(GrainTable.GRAIN_TABLE_7)
                    || grainTableName.equals(GrainTable.GRAIN_TABLE_8) ? powderSitoListOfGrainTables : actualSitoListOfGrainTables;

            Intent intent = new Intent(this, ChogKeyboard.class);
            intent.putExtra("GRAIN_TABLE_NAME", grainTableName);
            intent.putExtra("SITO", chogCellPosition.getSito());
            intent.putExtra("TRANSFER_GRAIN", grains.get(grainTableName));
            intent.putExtra("SITO_LIST", (Serializable) sitoList);
            startActivityForResult(intent, 1);
        }

        if (tag instanceof PercenageButtonTag) {
            PercenageButtonTag percenageButtonTag = (PercenageButtonTag) tag;

            GrainTable grainTableName = percenageButtonTag.getGrainTableName();
            PercentageButtonType percentageButtonType = percenageButtonTag.getPercentageButtonType();
            double oldValue = Double.parseDouble(grainViewGroupsMap.get(grainTableName).getPercentageTextView().getText().toString());

            if (percentageButtonType.equals(PercentageButtonType.PERCENTAGE_FIELD)) {
                Intent intent = new Intent(this, SimpleKeyboard.class);
                intent.putExtra("OLD_VALUE", String.valueOf(oldValue));
                intent.putExtra("TAG", percenageButtonTag);
                startActivityForResult(intent, 1);
            } else {

                double newValue = 0;
                if (percentageButtonType.equals(PercentageButtonType.MINUS)) {
                    newValue = Rounder.roundDouble(1, oldValue - 0.1);
                }
                if (percentageButtonType.equals(PercentageButtonType.PLUS)) {
                    newValue = Rounder.roundDouble(1, oldValue + 0.1);

                }
                //Обновляем значение в таблице
                grainViewGroupsMap.get(grainTableName).getPercentageTextView().setText(Double.toString(newValue));
                //Сохраняем значение в Map с дозировками
                percentageMap.put(grainTableName, newValue);
                summaPercentage.setText(getSummaPercentage());
                calculateAndShowResultMix();
            }

        }

        if (tag instanceof ResultTableCellPositionTag) {

            //Получаем тег
            ResultTableCellPositionTag resultTableCellPositionTag = (ResultTableCellPositionTag) tag;
            //Вытаскиваем из тега тип строки
            ResultTableRow rowName = resultTableCellPositionTag.getResultTableRow();
            //Вытаскиваем из тега номер сита
            Sito sito = resultTableCellPositionTag.getSito();
            //По известному ситу вытаскиваем объект столбца
            ResultTableColumn resultTableColumn = resultTableColumnMap.get(sito);


            TextView pressedTextView = resultTableColumn.getTextViewByRowName(rowName);

            String oldValue = pressedTextView.getText().toString();

            Intent intent = new Intent(this, SimpleKeyboard.class);
            intent.putExtra("OLD_VALUE", oldValue);
            intent.putExtra("TAG", resultTableCellPositionTag);
            startActivityForResult(intent, 1);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data == null) {
            return;
        }

        ActivityName activityName = (ActivityName) data.getSerializableExtra("ACTIVITY_NAME");

        switch (activityName) {

            case MIX_CHOOSER:
                MixType mixType = (MixType) data.getSerializableExtra("MIX_TYPE");
                mixInfo = MixInfoFactory.getMixInfo(mixType);
                //Получаем список сит по которым будет рассчитан зерновой состав
                actualSitoListOfGrainTables = mixInfo.getSitoListForGrainTable();
                //Получаем список сит для зернового состава подобранной смеси
                actualSitoListForResultTable = mixInfo.getSitoListForResultTable();

                if (mixType.getMixName().length() >= 10 ){
                    mixChooserButton.setTextSize(12);
                }

                if (mixType.getMixName().length() < 10 ){
                    mixChooserButton.setTextSize(16);
                }

                refreshTables();
                break;

            //Действия при возврате из клавиатуры редактирования частных остатков
            case CHOG_KEYBOARD:
                //Получаем маркер таблицы в которой произошло изменение
                GrainTable responceGrainTableName = (GrainTable) data.getSerializableExtra("GRAIN_TABLE_NAME");
                //Получаем значения и записывавем их в соответствующий объект зернового состава
                grains.put(responceGrainTableName, (Grain) data.getSerializableExtra("OBJECT_BACK"));
                //Рассчитываем зерновой состав и заполняем таблицу в которой произошли изменения
                calculateAndShowGrainTableByName(responceGrainTableName);
                //Далее нужно пересчитать все полные проходы подобранной кривой зернового состава
                calculateAndShowResultMix();

                break;
            case SIMPLE_KEYBOARD:

                Serializable tag = data.getSerializableExtra("TAG");
                String newValue = data.getStringExtra("NEW_VALUE");
                TextView pressedTextView;

                if (tag instanceof ResultTableCellPositionTag) {
                    ResultTableCellPositionTag resultTableCellPositionTag = (ResultTableCellPositionTag) tag;
                    pressedTextView = resultTableColumnMap.get(resultTableCellPositionTag.getSito())
                            .getTextViewByRowName(resultTableCellPositionTag.getResultTableRow());
                    pressedTextView.setText(newValue);
                    targetMixGrain.put(resultTableCellPositionTag.getSito(), Double.parseDouble(newValue));
                    fillRequirements();
                    painter();
                }

                if (tag instanceof PercenageButtonTag) {
                    PercenageButtonTag percenageButtonTag = (PercenageButtonTag) tag;
                    pressedTextView = grainViewGroupsMap
                            .get(percenageButtonTag.getGrainTableName()).getPercentageTextView();
                    pressedTextView.setText(newValue);
                    //Сохраняем значение в Map с дозировками
                    percentageMap.put(percenageButtonTag.getGrainTableName(), Double.parseDouble(newValue));
                    summaPercentage.setText(getSummaPercentage());
                    calculateAndShowResultMix();
                }
                break;
        }
    }

    private void findById() {

        mixChooserButton = (Button) findViewById(R.id.mixChooserButton);
        mixChooserButton.setOnClickListener(this);

        autoPodborButton = (Button) findViewById(R.id.autoPodborButton);
        autoPodborButton.setOnClickListener(this);

        GoToWorkCompose = (Button) findViewById(R.id.GoToWorkCompose);
        SaveButton = (Button) findViewById(R.id.SaveButton);
        LoadButton = (Button) findViewById(R.id.LoadButton);

        GoToWorkCompose.setOnClickListener(this);
        SaveButton.setOnClickListener(this);
        LoadButton.setOnClickListener(this);

        NameOfProject = (TextView) findViewById(R.id.HEADERPROJECT);


        //Находим родительские Layout таблиц зерновго состава
        rootGrainLayout1 = findViewById(R.id.rootLayout1);
        rootGrainLayout2 = findViewById(R.id.rootLayout2);
        rootGrainLayout3 = findViewById(R.id.rootLayout3);
        rootGrainLayout4 = findViewById(R.id.rootLayout4);
        rootGrainLayout5 = findViewById(R.id.rootLayout5);
        rootGrainLayout6 = findViewById(R.id.rootLayout6);
        rootGrainLayoutMP = findViewById(R.id.rootLayoutMP);
        rootGrainLayoutSZ = findViewById(R.id.rootLayoutSZ);

        //Находим родительский Layout для блока управления дизайном кривой
        rootDesignLayout = findViewById(R.id.rootDesignLayout);
        //Находим поле для суммы процентов
        summaPercentage = findViewById(R.id.summaPercentage);
        //Находим родительский Layout для блока результатов подбора
        rootResultTableLayout = findViewById(R.id.resultMixRootContainer);
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density + 0.5f);
    }

    private String getSummaPercentage() {
        double value = Rounder.roundDouble(1, percentageMap.values().stream().reduce(Double::sum).orElse(0.0));
        return String.valueOf(value);
    }

    private void setupEditText(EditText editText, TextView targetTextView) {
        // 1. Дублирование текста при каждом символе
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                targetTextView.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // 2. Потеря фокуса и скрытие клавиатуры по кнопке "Done"
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    editText.clearFocus(); // Снимаем фокус с текущего поля
                    // Скрываем клавиатуру (опционально, но обычно нужно)
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }


}
