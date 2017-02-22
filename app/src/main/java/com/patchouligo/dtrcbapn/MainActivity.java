package com.patchouligo.dtrcbapn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        String result = "";
        try {
            java.lang.Process p = Runtime.getRuntime().exec("ping -c 1 -w 5" + "www.baidu.com");
            int status = p.waitFor();
            if (status == 0) {
                result = "succ";
            } else {
                result = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv.setText(result);
    }
}
