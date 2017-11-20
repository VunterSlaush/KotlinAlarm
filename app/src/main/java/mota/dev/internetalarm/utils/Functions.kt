package mota.dev.internetalarm.utils

import mota.dev.internetalarm.constants.Keys
import java.net.InetAddress

/**
 * Created by user on 20/11/2017.
 */
object Functions {
    fun haveInternet() : Boolean
    {
        return !InetAddress.getByName(Keys.PING_URL).equals("")
    }
}