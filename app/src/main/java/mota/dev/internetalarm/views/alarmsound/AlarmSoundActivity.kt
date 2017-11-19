package mota.dev.internetalarm.views.alarmsound

import android.media.MediaPlayer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import mota.dev.internetalarm.R

import kotlinx.android.synthetic.main.activity_alarm_sound.*




class AlarmSoundActivity : AppCompatActivity()  {

    lateinit var mp : MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_sound)

        shut_up_alarm_buttom.setOnClickListener({
            view ->
            mp.stop()
            finish()
        })
        playAlarmSound()
    }

    fun playAlarmSound()
    {
        mp = MediaPlayer.create(this,R.raw.alarm)
        mp.isLooping = true
        mp.start()
    }
}
