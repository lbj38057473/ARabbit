package com.androidwind.androidquick.sample.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidwind.androidquick.sample.MainActivity
import com.androidwind.androidquick.sample.util.TimeUtils
import java.lang.Thread.sleep

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calculateStartTime()
        Thread(Runnable {
            //耗时任务，比如加载网络数据
            runOnUiThread { // 这里可以睡几秒钟，如果要放广告的话
                sleep(2000);
                val intent: Intent = Intent().setClass(this, MainActivity::class.java)
                startActivity(intent)
                this@SplashActivity.finish()
            }
        }).start()
    }

    private fun calculateStartTime() {
        val coldStartTime: Long = TimeUtils.getTimeCalculate(TimeUtils.COLD_START)
        // 这里记录的TimeUtils.coldStartTime是指Application启动的时间，最终的冷启动时间等于Application启动时间+热启动时间
        TimeUtils.sColdStartTime = if (coldStartTime > 0) coldStartTime else 0
        TimeUtils.beginTimeCalculate(TimeUtils.HOT_START)
    }
}