package mota.dev.internetalarm.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import mota.dev.internetalarm.views.alarmsound.AlarmSoundActivity

/**
 * Created by Slaush on 18/11/2017.
 */
class TurnOnAlarmReceiver : BroadcastReceiver()
{
    override fun onReceive(context : Context?, intent : Intent?) {
        Log.i("MOTA1","TURN ON ALARM ACTION RECEIVE!")
        val i = Intent(context, AlarmSoundActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.startActivity(i)
    }

}