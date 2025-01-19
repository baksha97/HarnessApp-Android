package com.baksha.harness.app

import android.app.Application
import com.baksha.sample.harness.repository.LogRepository
import com.baksha.sample.harness.viewmodel.AuthViewModel
import com.baksha.sample.harness.viewmodel.HarnessAppViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.*
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import timber.log.Timber

val module: Module
    get() = module {
        single { LogRepository() }
        viewModel { AuthViewModel() }
        viewModel { HarnessAppViewModel(get()) }
    }

class HarnessApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HarnessApp)
            modules(module)
        }
        Timber.plant(Timber.DebugTree())
        Timber.plant(get<LogRepository>())
    }
}
