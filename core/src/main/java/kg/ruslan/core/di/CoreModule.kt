package kg.ruslan.core.di

import kg.ruslan.core.halpers.Utils
import kg.ruslan.core.sharedpreference.GreetingFragmentOpened
import kg.ruslan.core.sharedpreference.Prefs
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    // utils
    factory { Utils(androidApplication()) }

    //shared preferences
    factory { Prefs(androidContext()) }
    factory { get<Prefs>().getGreetingSharedPreferences() }

    factory { GreetingFragmentOpened(get()) }
}