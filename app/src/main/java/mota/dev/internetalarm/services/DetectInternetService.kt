package mota.dev.internetalarm.services

import android.app.IntentService
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.util.Log
import com.evernote.android.job.Job
import mota.dev.internetalarm.constants.Keys
import mota.dev.internetalarm.constants.ServiceKeys
import java.net.InetAddress
import android.content.ContentValues.TAG
import com.evernote.android.job.JobManager
import com.evernote.android.job.JobRequest
import mota.dev.internetalarm.constants.Consts
import mota.dev.internetalarm.utils.Functions
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Slaush on 18/11/2017.
 */
// TODO evaluar Estabilidad del internet!
class DetectInternetService : Job() {
    companion object {
        const val TAG = "DETECT INTERNET TAG"

        fun scheduleJob() : Int {
            Log.i("MOTA1","SCHEDULED JOB!>"+Date())

            JobRequest.Builder(TAG).setExact(TimeUnit.MINUTES.toMillis(1)).build().schedule()
            JobRequest.Builder(TAG).setExact(TimeUnit.MINUTES.toMillis(5)).build().schedule()

            return JobRequest.Builder(TAG)
                    .setPeriodic(JobRequest.MIN_INTERVAL, JobRequest.MIN_FLEX)
                    .setRequirementsEnforced(false)
                    .build()
                    .schedule()
        }
    }

    override fun onRunJob(params: Params?): Result {
        detectInternet()
        return Result.SUCCESS
    }


    fun detectInternet() {
        try {

            Log.i("MOTA1", "Finding Internet:"+ Date())
            if (Functions.haveInternet()) {
                sendHaveInternetBroadCast()
                removeSelf()
            }
        } catch (ex: Exception) {
            Log.i("MOTA","EXCEPTION ON FINDING INTERNET?"+ex.message)
        }

    }

    private fun sendHaveInternetBroadCast() {
        Log.i("MOTA1", "sendHaveInternetBroadCast")
        context.sendBroadcast(Intent(Consts.TURN_ON_ALARM_ACTION))
    }

    private fun removeSelf()
    {
        JobManager.instance().cancelAllForTag(TAG)
    }
}