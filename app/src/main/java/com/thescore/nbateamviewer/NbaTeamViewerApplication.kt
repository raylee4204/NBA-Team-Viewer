package com.thescore.nbateamviewer

import android.app.Application
import com.thescore.nbateamviewer.di.appModule
import com.thescore.nbateamviewer.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Kanghee Lee
 */
class NbaTeamViewerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NbaTeamViewerApplication)
            modules(appModule, networkModule)
        }
    }
}