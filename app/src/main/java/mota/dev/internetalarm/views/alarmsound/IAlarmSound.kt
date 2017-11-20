package mota.dev.internetalarm.views.alarmsound

import mota.dev.internetalarm.views.base.IBaseView

/**
 * Created by user on 20/11/2017.
 */
interface IAlarmSound : IBaseView {
    fun playSound()
    fun stopSound()
}