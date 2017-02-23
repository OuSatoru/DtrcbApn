package com.patchouligo.dtrcbapn

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            val flSetting = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            startActivity(flSetting)
        }
        val check: CheckNet = CheckNet()
        val intent = Intent(this@MainActivity, FloatSv::class.java)
        val start = findViewById(R.id.start) as Button
        val end = findViewById(R.id.end) as Button
        start.setOnClickListener {
            startService(intent)
        }
        end.setOnClickListener {
            stopService(intent)
        }
//        if (check.canBaidu()) {
//            tv.text = "succ"
//        } else {
//            tv.text = "fail"
//        }
//        val result: String
//
//        Log.d("=place=", "under try")
//        val p = Runtime.getRuntime().exec("ping -c 1 -w 5 " + "www.baidu.com")
//        val input = p.inputStream
//        val buf = BufferedReader(InputStreamReader(input))
//        val stringBuilder = StringBuilder()
//        while (buf.readLine() != null) {
//            val content = buf.readLine()
//            Log.d("=place=", "under while")
//            stringBuilder.append(content)
//        }
//        Log.d("======", stringBuilder.toString())
//        tv.text = stringBuilder.toString()
//        val status = p.waitFor()
//        if (status == 0) {
//            result = "succ"
//        } else {
//            result = "fail"
//        }
//
//
//        Log.d("++++", result)
        //tv.setText(result);
    }
}
