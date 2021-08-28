package github.ardondev.apuri

import android.app.Application
import github.ardondev.apuri.modules.appModule
import github.ardondev.apuri.modules.retrofitModule
import github.ardondev.apuri.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Apuri: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@Apuri)
            modules(retrofitModule, appModule, viewModelModule)
        }

    }

}