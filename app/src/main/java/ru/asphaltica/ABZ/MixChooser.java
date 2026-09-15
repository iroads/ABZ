package ru.asphaltica.ABZ;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ru.asphaltica.ABZ.enumerated.ActivityName;
import ru.asphaltica.ABZ.enumerated.MixType;
import ru.asphaltica.ABZ.helpers.ViewElementsGenerator;

public class MixChooser extends AppCompatActivity implements View.OnClickListener {

    LinearLayout rootMarshallAbsUpLayer;
    LinearLayout rootMarshallAbsDownLayer;
    LinearLayout rootMarshallAbsBaseLayer;

    LinearLayout rootMarshallSHMA;

    LinearLayout rootSuperpaveAbsUpLayer;
    LinearLayout rootSuperpaveAbsDownLayer;
    LinearLayout rootSuperpaveAbsBaseLayer;
    LinearLayout rootSuperpaveAbsTrotuarLayer;

    LinearLayout rootSuperpaveAbsSMALayer;

    LinearLayout root9128UpLayerNS;
    LinearLayout root9128UpLayerPS;

    LinearLayout root9128DownLayerNS;
    LinearLayout root9128DownLayerPS;

    MixType selectedMixType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix_chooser);


        findByid();

        createInterface();

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            selectedMixType = (MixType) arguments.getSerializable("MIX_TYPE");
        }
    }


    @Override
    public void onClick(View v) {
        selectedMixType = (MixType) v.getTag();
        ExitActions();
    }

    public void ExitActions() {
        Intent intent = new Intent();
        intent.putExtra("MIX_TYPE", selectedMixType);
        intent.putExtra("ACTIVITY_NAME", ActivityName.MIX_CHOOSER);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        ExitActions();
    }

    private void createInterface() {
        createRow(MixType.getAbsMarshallUpLayerRow(), rootMarshallAbsUpLayer);
        createRow(MixType.getAbsMarshallDownLayerRow(), rootMarshallAbsDownLayer);
        createRow(MixType.getAbsMarshallBaseLayerRow(), rootMarshallAbsBaseLayer);


        createRow(MixType.getAbsMarshallSHMARow(), rootMarshallSHMA);

        createRow(MixType.getAbsSuperpaveUpLayerRow(), rootSuperpaveAbsUpLayer);
        createRow(MixType.getAbsSuperpaveDownLayerRow(), rootSuperpaveAbsDownLayer);
        createRow(MixType.getAbsSuperpaveBaseLayerRow(), rootSuperpaveAbsBaseLayer);
        createRow(MixType.getAbsSuperpaveTrotuarLayerRow(), rootSuperpaveAbsTrotuarLayer);

        createRow(MixType.getAbsSuperpaveSMALayerRow(), rootSuperpaveAbsSMALayer);

        createRow(MixType.getAbs9128UpLayerNepreryvRow(), root9128UpLayerNS);
        createRow(MixType.getAbs9128UpLayerPreryvRow(), root9128UpLayerPS);

        createRow(MixType.getAbs9128DownLayerNepreryvRow(), root9128DownLayerNS);
        createRow(MixType.getAbs9128DownLayerPreryvRow(), root9128DownLayerPS);

    }

    private void createRow(List<MixType> mixTypes, LinearLayout container) {
        for (MixType mixType : mixTypes) {
            TextView textView = ViewElementsGenerator.createTextViewForMixType(this, mixType.getShortName());
            textView.setTag(mixType);
            textView.setOnClickListener(this);
            container.addView(textView);
        }
    }

    private void findByid() {

        rootMarshallAbsUpLayer = (LinearLayout) findViewById(R.id.rootMarshallAbsUpLayer);
        rootMarshallAbsDownLayer = (LinearLayout) findViewById(R.id.rootMarshallAbsDownLayer);
        rootMarshallAbsBaseLayer = (LinearLayout) findViewById(R.id.rootMarshallAbsBaseLayer);

        rootMarshallSHMA = (LinearLayout) findViewById(R.id.rootMarshallSHMA);

        rootSuperpaveAbsUpLayer = (LinearLayout) findViewById(R.id.rootSuperpaveAbsUpLayer);
        rootSuperpaveAbsDownLayer = (LinearLayout) findViewById(R.id.rootSuperpaveAbsDownLayer);
        rootSuperpaveAbsBaseLayer = (LinearLayout) findViewById(R.id.rootSuperpaveAbsBaseLayer);
        rootSuperpaveAbsTrotuarLayer = (LinearLayout) findViewById(R.id.rootSuperpaveAbsTrotuarLayer);

        rootSuperpaveAbsSMALayer = (LinearLayout) findViewById(R.id.rootSuperpaveAbsSMALayer);

        root9128UpLayerNS = (LinearLayout) findViewById(R.id.root9128UpLayerNS);
        root9128UpLayerPS = (LinearLayout) findViewById(R.id.root9128UpLayerPS);

        root9128DownLayerNS = (LinearLayout) findViewById(R.id.root9128DownLayerNS);
        root9128DownLayerPS = (LinearLayout) findViewById(R.id.root9128DownLayerPS);
    }
}
