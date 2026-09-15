package ru.asphaltica.ABZ.view_group;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.asphaltica.ABZ.enumerated.GrainTable;
import ru.asphaltica.ABZ.enumerated.Sito;
import ru.asphaltica.ABZ.helpers.ViewElementsGenerator;

@NoArgsConstructor
@Getter
@Setter
public class GrainTableViewGroup {

    private Context context;
    private String number;
    private int primaryColor;
    private int color;
    private GrainTable grainTableName;

    //Поле редактирования в котором пользователь может записать фракцию хранимую в этом бункер
    private EditText bunkerName;

    private Map<Sito, TextView> chogViews = new HashMap<>();
    private Map<Sito, TextView> chopViews = new HashMap<>();
    private Map<Sito, TextView> poViews = new HashMap<>();
    private Map<Sito, TextView> ppViews = new HashMap<>();

    private TextView headerTextView;
    private TextView minusButton;
    private TextView percentageTextView;
    private TextView plusButton;

    private LinearLayout linearLayoutForInfo;
    private LinearLayout linearLayoutForHeader;
    private LinearLayout linearLayoutForChog;
    private LinearLayout linearLayoutForChop;
    private LinearLayout linearLayoutForPo;
    private LinearLayout linearLayoutForPp;

    private LinearLayout rootGrainLayout;
    private TextView chogSumma;

    public GrainTableViewGroup(GrainTable grainTableName, String number, Context context,
                               LinearLayout rootGrainLayout, int primaryColor, int color) {
        this.grainTableName = grainTableName;
        this.context = context;
        this.rootGrainLayout = rootGrainLayout;
        this.number = number;
        this.primaryColor = primaryColor;
        this.color = color;

        linearLayoutForInfo = ViewElementsGenerator.createLinearLayoutForInfoRow(context);

        linearLayoutForHeader = ViewElementsGenerator.createLinearLayoutForRow(context);
        linearLayoutForChog = ViewElementsGenerator.createLinearLayoutForRow(context);
        linearLayoutForChop = ViewElementsGenerator.createLinearLayoutForRow(context);
        linearLayoutForPo = ViewElementsGenerator.createLinearLayoutForRow(context);
        linearLayoutForPp = ViewElementsGenerator.createLinearLayoutForRow(context);
    }

//    public void deleteAllViews(){
//        chogViews = new HashMap<>();
//        chopViews = new HashMap<>();
//    }


}
