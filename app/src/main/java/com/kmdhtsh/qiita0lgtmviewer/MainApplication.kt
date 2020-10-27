package com.kmdhtsh.qiita0lgtmviewer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

// Applicationクラスには@HiltAndroidAppが必要
@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Debug時のみログ出力
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
