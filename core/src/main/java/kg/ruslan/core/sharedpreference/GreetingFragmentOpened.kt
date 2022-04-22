package kg.ruslan.core.sharedpreference

import android.content.SharedPreferences

class GreetingFragmentOpened(prefs: SharedPreferences) {

    private var isScreenOpened by BooleanSPDelegate(prefs = prefs)

    fun haveGreetingFragmentOpened() = isScreenOpened

    fun greetingFragmentOpened() {
        isScreenOpened = true
    }
}