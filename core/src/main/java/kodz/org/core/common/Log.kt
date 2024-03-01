package kodz.org.core.common

import android.util.Log
import kodz.org.core.domain.consts.APPLOG

fun AppLog(message: String?) {
    val time = System.currentTimeMillis()
    Log.i(APPLOG, "$time $message")
}