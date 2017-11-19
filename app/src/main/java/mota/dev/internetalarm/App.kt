package mota.dev.internetalarm

import android.app.Application
import com.evernote.android.job.JobManager
import mota.dev.internetalarm.schedulers.InternetDetectorJobCreator


/**
 * Created by Slaush on 18/11/2017.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        JobManager.create(this).addJobCreator(InternetDetectorJobCreator())
    }

}