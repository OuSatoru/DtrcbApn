package com.patchouligo.dtrcbapn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        String result = "";
        try {
            Log.d("=place=", "under try");
            java.lang.Process p = Runtime.getRuntime().exec("ping -c 1 -w 5 " + "32.185.22.129");
            InputStream input = p.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            StringBuilder stringBuilder = new StringBuilder();
            String content;
            while ((content = in.readLine()) != null) {
                Log.d("=place=", "under while");
                stringBuilder.append(content);
            }
            Log.d("======", stringBuilder.toString());
            tv.setText(stringBuilder.toString());
            int status = p.waitFor();
            if (status == 0) {
                result = "succ";
            } else {
                result = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("++++", result);
        //tv.setText(result);
    }
}
