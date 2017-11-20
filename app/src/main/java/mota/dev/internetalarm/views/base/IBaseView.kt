package mota.dev.internetalarm.views.base

import android.content.Context

/**
 * Created by user on 20/11/2017.
 */
interface IBaseView {

    fun getContext() : Context
    fun toast(id : Int)
    fun toast(msg : String)
    fun close()
}