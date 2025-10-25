package br.com.innovarix.runningclub

import android.app.Application
import br.com.innovarix.runningclub.di.loadModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.fileProperties

class RCRunningClubApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RCRunningClubApplication)
            fileProperties()
            loadModules()
        }
    }
}