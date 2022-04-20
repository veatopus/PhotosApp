package kg.ruslan.photosapp

import android.app.Application
import kg.ruslan.core.di.coreModule
import kg.ruslan.data.local.di.dataModule
import kg.ruslan.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    domainModule,
                    dataModule,
                    coreModule,
                )
            )
        }
    }
}