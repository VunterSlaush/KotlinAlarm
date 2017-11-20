package mota.dev.internetalarm.receivers

/**
 * Created by Slaush on 18/11/2017.
 */
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import mota.dev.internetalarm.constants.Keys
import mota.dev.internetalarm.schedulers.InternetDetectorJobCreator
import mota.dev.internetalarm.services.DetectInternetService
import mota.dev.internetalarm.utils.Functions
import mota.dev.internetalarm.utils.PreferencesHelper

class StartReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (!Functions.haveInternet() && PreferencesHelper.readBoolean(Keys.ALARM_IS_ACTIVE,false))
            DetectInternetService.scheduleJob()
    }
}