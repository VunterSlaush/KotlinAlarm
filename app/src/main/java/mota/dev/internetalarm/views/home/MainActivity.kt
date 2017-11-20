package mota.dev.internetalarm.views.home

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import mota.dev.internetalarm.R
import mota.dev.internetalarm.views.base.BaseActivity


class MainActivity : BaseActivity(), IHomeView {

    lateinit var presenter: HomePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = HomePresenter(this)
        switchAlarm.setOnCheckedChangeListener({ _, active ->  presenter.activeAlarmClick(active)})
    }

    override fun changeAlarmSwitch(active: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeWifiInternetSwitch(active: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeAlarmReactivateSwitch(active: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeDataInternetSwitch(active: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
