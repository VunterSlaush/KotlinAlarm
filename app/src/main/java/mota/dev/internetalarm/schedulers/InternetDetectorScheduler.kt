package mota.dev.internetalarm.schedulers

import android.app.job.JobScheduler
import android.app.job.JobInfo
import android.content.ComponentName
import android.content.Context
import android.os.Build
import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator
import mota.dev.internetalarm.services.DetectInternetService
import android.content.ContentValues.TAG
import android.R.attr.tag




/**
 * Created by Slaush on 18/11/2017.
 */
class InternetDetectorJobCreator : JobCreator
{
    override fun create(tag: String): Job? {
        when (tag) {
            DetectInternetService.TAG -> return DetectInternetService()
            else -> return null
        }
    }

}