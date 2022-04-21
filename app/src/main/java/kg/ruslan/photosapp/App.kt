package kg.ruslan.photosapp

import android.app.Application
import kg.ruslan.core.di.coreModule
import kg.ruslan.data.local.di.dataModule
import kg.ruslan.domain.domainModule
import kg.ruslan.feature_greeting.di.featureGreetingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.Forest.plant


class App: Application() {
    override fun onCreate() {
        super.onCreate()

        // init Timber logger
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }

        // init koin
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    domainModule,
                    dataModule,
                    coreModule,
                    featureGreetingModule
                )
            )
        }
    }
}