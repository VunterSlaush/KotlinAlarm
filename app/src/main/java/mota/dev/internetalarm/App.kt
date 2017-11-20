package mota.dev.internetalarm

import android.app.Application
import com.evernote.android.job.JobManager
import mota.dev.internetalarm.schedulers.InternetDetectorJobCreator
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric




/**
 * Created by Slaush on 18/11/2017.
 */
class App : Application() {

    companion object {
        lateinit var instance : App
    }

    override fun onCreate() {
        super.onCreate()
        JobManager.create(this).addJobCreator(InternetDetectorJobCreator())
        Fabric.with(this, Crashlytics())
        instance = this
    }

}