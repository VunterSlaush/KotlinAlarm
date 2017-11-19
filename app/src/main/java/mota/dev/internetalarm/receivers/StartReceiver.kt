package mota.dev.internetalarm.receivers

/**
 * Created by Slaush on 18/11/2017.
 */
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import mota.dev.internetalarm.schedulers.InternetDetectorJobCreator
import mota.dev.internetalarm.services.DetectInternetService

class StartReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        DetectInternetService.scheduleJob()
    }
}