package ui.smartpro.a22byte.ui.activity

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import ui.smartpro.a22byte.BuildConfig

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Core.initAppCore(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}