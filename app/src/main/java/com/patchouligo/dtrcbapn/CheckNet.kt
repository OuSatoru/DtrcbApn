package com.patchouligo.dtrcbapn

/**
 * Created by Kasumi on 2017/2/23.
 */
class CheckNet {

    fun canBaidu(): Boolean {
        val p = Runtime.getRuntime().exec("ping -c 1 -w 5 " + "www.baidu.com")
        val status = p.waitFor()
        return status == 0
    }

    fun canIntra(): Boolean {
        val p = Runtime.getRuntime().exec("ping -c 1 -w 5 " + "32.185.22.129")
        val status = p.waitFor()
        return status == 0
    }

    fun blocked(): Boolean {
        return !canBaidu() && !canIntra()
    }

}