package mota.dev.internetalarm.receivers

/**
 * Created by Slaush on 18/11/2017.
 */
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log


/**
 * Created by user on 23/05/2017.
 */
class NetworkReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val conMan = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conMan.activeNetworkInfo
        Log.i("MOTA","Network Change>"+netInfo)
    }
};