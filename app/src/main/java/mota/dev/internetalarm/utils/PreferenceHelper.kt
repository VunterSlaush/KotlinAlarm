package mota.dev.internetalarm.utils

/**
 * Created by Slaush on 18/11/2017.
 */

import android.content.Context
import android.content.SharedPreferences
import mota.dev.internetalarm.App


object PreferencesHelper {

    fun deleteKey(key: String) {

            getPreferences().remove(key)
    }


    fun writeBoolean(key: String, value: Boolean) {
            getPreferences().putBoolean(key, value)
    }

    fun readBoolean(key: String,
                    defValue: Boolean): Boolean {
        return  getPreferences().getBoolean(key, defValue)
    }


    fun writeInteger(key: String, value: Int) {

            getPreferences().putInt(key, value)

    }

    fun readInteger(key: String, defValue: Int): Int {
        return getPreferences().getInt(key, defValue)
    }


    fun writeString(key: String, value: String) {

            getPreferences().putString(key, value)
    }

    fun readString(key: String, defValue: String): String {
        return getPreferences().getString(key, defValue)
    }

    fun writeDouble(key: String, value: Double?) {
            getPreferences().putDouble(key, value)
    }

    fun readDouble(key: String, defValue: Double?): Double? {
        return  getPreferences().getDouble(key, defValue)
    }

    private fun getPreferences(): SecurePreferences {
        return SecurePreferences(App.instance, "5c0d3349dc674fe9d0")

    }

}


