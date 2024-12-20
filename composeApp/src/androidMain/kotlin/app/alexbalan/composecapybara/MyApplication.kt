package app.alexbalan.composecapybara

import android.app.Application
import app.alexbalan.composecapybara.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApplication)
        }
    }
}