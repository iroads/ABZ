package ru.asphaltica.ABZ.view_group;

import android.widget.TextView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.asphaltica.ABZ.enumerated.ResultTableRow;

@Getter
@AllArgsConstructor
public class ResultTableColumn {

    TextView sieveSizeTextView;
    TextView resultValueTextView;
    TextView targetValueTextView;
    TextView upRequirementTextView;
    TextView downRequirementTextView;


    public TextView getTextViewByRowName(ResultTableRow rowName) {
        switch (rowName) {
            case RESULT_ROW:
                return resultValueTextView;
            case TARGET_ROW:
                return targetValueTextView;
            default:
                return null;
        }
    }
}
