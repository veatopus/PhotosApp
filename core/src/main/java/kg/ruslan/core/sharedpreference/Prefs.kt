package kg.ruslan.core.sharedpreference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import kg.ruslan.core.sharedpreference.Prefs.SPConstants.GREETING

class Prefs(private val context: Context) {
    private fun getSharedPreferences(name: String): SharedPreferences {
        return context.getSharedPreferences(name, MODE_PRIVATE)
    }

    fun getGreetingSharedPreferences() = getSharedPreferences(GREETING)

    private object SPConstants {
        const val GREETING = "greeting"
    }
}