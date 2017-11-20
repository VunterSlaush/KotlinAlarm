package mota.dev.internetalarm.views.alarmsound

import mota.dev.internetalarm.constants.Keys
import mota.dev.internetalarm.utils.PreferencesHelper
import mota.dev.internetalarm.views.base.BasePresenter

/**
 * Created by user on 20/11/2017.
 */
class AlarmSoundPresenter(private var iView: IAlarmSound) : BasePresenter() {

    init {
        iView.playSound()
    }

    fun onClickStopSound()
    {
        PreferencesHelper.writeBoolean(Keys.ALARM_IS_ACTIVE,false)
        iView.stopSound()
        iView.close()
    }
}