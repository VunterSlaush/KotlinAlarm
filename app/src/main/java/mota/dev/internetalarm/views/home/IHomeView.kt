package mota.dev.internetalarm.views.home

import mota.dev.internetalarm.views.base.IBaseView

/**
 * Created by user on 20/11/2017.
 */
interface IHomeView : IBaseView {
    fun changeAlarmSwitch(active : Boolean)
    fun changeWifiInternetSwitch(active : Boolean)
    fun changeAlarmReactivateSwitch(active : Boolean)
    fun changeDataInternetSwitch(active : Boolean)
}