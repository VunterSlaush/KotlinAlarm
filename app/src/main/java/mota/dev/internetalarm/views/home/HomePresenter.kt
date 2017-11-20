package mota.dev.internetalarm.views.home


import com.evernote.android.job.JobManager
import mota.dev.internetalarm.constants.Keys
import mota.dev.internetalarm.services.DetectInternetService
import mota.dev.internetalarm.utils.PreferencesHelper
import mota.dev.internetalarm.views.base.BasePresenter

/**
 * Created by user on 20/11/2017.
 */
class HomePresenter(private var iView: IHomeView) : BasePresenter() {

    init {
        init()
    }

    private fun init() {
        iView.changeAlarmReactivateSwitch(PreferencesHelper.readBoolean(Keys.ACTIVATE_ALARM_ON_NOT_INTERNET,false))
        iView.changeAlarmSwitch(PreferencesHelper.readBoolean(Keys.ALARM_IS_ACTIVE,false))
        iView.changeDataInternetSwitch(PreferencesHelper.readBoolean(Keys.ALARM_WIFI,false))
        iView.changeWifiInternetSwitch(PreferencesHelper.readBoolean(Keys.ALARM_DATA,false))
    }

    fun activeAlarmClick(active: Boolean) {
        if (active) {
            DetectInternetService.scheduleJob()
            PreferencesHelper.writeBoolean(Keys.ALARM_IS_ACTIVE, true)
        } else {
            JobManager.instance().cancelAllForTag(DetectInternetService.TAG)
            PreferencesHelper.writeBoolean(Keys.ALARM_IS_ACTIVE, true)
        }
    }

}


