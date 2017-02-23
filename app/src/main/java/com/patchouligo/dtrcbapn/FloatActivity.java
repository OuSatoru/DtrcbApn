package com.patchouligo.dtrcbapn;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FloatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float);
//        Button label = (Button) findViewById(R.id.net_label);
//        label.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent toApn = new Intent(Settings.ACTION_APN_SETTINGS);
//                startActivity(toApn);
//            }
//        });
    }
}
