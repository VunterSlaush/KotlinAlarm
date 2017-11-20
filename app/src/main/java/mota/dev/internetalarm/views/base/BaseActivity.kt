package mota.dev.internetalarm.views.base

import android.content.Context
import android.support.v7.app.AppCompatActivity

/**
 * Created by user on 20/11/2017.
 */
open class BaseActivity : AppCompatActivity(), IBaseView {

    override fun getContext(): Context {
        return this
    }

    override fun toast(id: Int) {
        toast(id)
    }

    override fun toast(msg: String) {
        toast(msg)
    }

    override fun close()
    {
        finish()
    }
}