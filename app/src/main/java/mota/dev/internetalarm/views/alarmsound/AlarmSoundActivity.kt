package mota.dev.internetalarm.views.alarmsound

import android.media.MediaPlayer
import android.os.Bundle
import mota.dev.internetalarm.R

import kotlinx.android.synthetic.main.activity_alarm_sound.*
import mota.dev.internetalarm.views.base.BaseActivity


class AlarmSoundActivity : BaseActivity(), IAlarmSound  {

    private lateinit var mp : MediaPlayer
    private lateinit var presenter : AlarmSoundPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_sound)

        presenter = AlarmSoundPresenter(this)

        shut_up_alarm_buttom.setOnClickListener({presenter.onClickStopSound()})
    }


    override fun playSound() {
        mp = MediaPlayer.create(this,R.raw.alarm)
        mp.isLooping = true
        mp.start()
    }

    override fun stopSound() {
        mp.stop()
    }
}
