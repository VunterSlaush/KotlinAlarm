package mota.dev.internetalarm.views.home

import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import com.evernote.android.job.JobManager
import kotlinx.android.synthetic.main.activity_main.*
import mota.dev.internetalarm.R

import mota.dev.internetalarm.services.DetectInternetService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchAlarm.setOnCheckedChangeListener({ button, changed->
            if (changed)
                DetectInternetService.scheduleJob()
            else
                JobManager.instance().cancelAllForTag(DetectInternetService.TAG)
        })
    }
}
