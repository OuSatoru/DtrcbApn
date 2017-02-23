package com.patchouligo.dtrcbapn

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timer

class FloatSv : Service() {

    private var wm: WindowManager? = null
    private var wmParams: WindowManager.LayoutParams? = null
    private var view: View? = null
    private var startTouchViewX = 0f
    private var startTouchViewY = 0f
    private var endTouchX = 0f
    private var endTouchY = 0f

    var flBtn: TextView? = null
    val check = CheckNet()


    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        view = LayoutInflater.from(this).inflate(R.layout.activity_float, null)
        createView()
    }

    override fun onDestroy() {
        super.onDestroy()
        wm!!.removeView(view)
    }

    private fun createView() {
        wm = getSystemService(WINDOW_SERVICE) as WindowManager
        wmParams = WindowManager.LayoutParams()
        wmParams!!.type = WindowManager.LayoutParams.TYPE_PHONE
        wmParams!!.flags = wmParams!!.flags or 8
        wmParams!!.gravity = Gravity.START or Gravity.TOP
        wmParams!!.x = 0
        wmParams!!.y = 0
        wmParams!!.width = WindowManager.LayoutParams.WRAP_CONTENT
        wmParams!!.height = WindowManager.LayoutParams.WRAP_CONTENT
        wmParams!!.format = 1

        wm!!.addView(view, wmParams)

        flBtn = view!!.findViewById(R.id.net_label) as TextView

        val handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    100 -> flBtn?.text = "外网"
                    200 -> flBtn?.text = "东台内网"
                    300 -> flBtn?.text = "无网络"
                    400 -> flBtn?.text = "WIFI"
                    500 -> flBtn?.text = "江苏农信"
                }
            }
        }

        Timer().schedule(object : TimerTask() {
            override fun run() {
                val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val info = connMgr.activeNetworkInfo
                when {
                    info == null -> handler.sendEmptyMessage(300)
                    info.type == ConnectivityManager.TYPE_WIFI -> handler.sendEmptyMessage(400)
                    info.extraInfo == "cmnet" || info.extraInfo == "cmwap" -> handler.sendEmptyMessage(100)
                    info.extraInfo == "ycdtrcb.js" -> handler.sendEmptyMessage(200)
                    info.extraInfo == "jsnx.js" -> handler.sendEmptyMessage(500)
                }
            }
        }, 0, 3000)

        view!!.setOnTouchListener { v, event ->
            endTouchX = event.rawX
            endTouchY = event.rawY - 45
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startTouchViewX = event.x
                    startTouchViewY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    wmParams!!.x = (endTouchX - startTouchViewX).toInt()
                    wmParams!!.y = (endTouchY - startTouchViewY).toInt()
                    wm!!.updateViewLayout(view, wmParams)
                }
                MotionEvent.ACTION_UP -> {
                    startTouchViewX = 0f
                    startTouchViewX = 0f
                }
                else -> {
                }
            }
            false
        }

        view!!.setOnClickListener { v ->
            if (v.id == R.id.net_label) {
                Log.i("======", "clicked")
                val toApn = Intent(Settings.ACTION_APN_SETTINGS)
                startActivity(toApn)
            }
        }

        flBtn?.setOnClickListener { v ->
            if (v.id == R.id.net_label) {
                Log.i("======", "clicked")
                val toApn = Intent(Settings.ACTION_APN_SETTINGS)
                toApn.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(toApn)
            }
        }

        flBtn?.setOnTouchListener { v, event ->
            endTouchX = event.rawX
            endTouchY = event.rawY - 45
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startTouchViewX = event.x
                    startTouchViewY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    wmParams!!.x = (endTouchX - startTouchViewX).toInt()
                    wmParams!!.y = (endTouchY - startTouchViewY).toInt()
                    wm!!.updateViewLayout(view, wmParams)
                }
                MotionEvent.ACTION_UP -> {
                    startTouchViewX = 0f
                    startTouchViewX = 0f
                }
                else -> {
                }
            }
            false
        }
    }

}
