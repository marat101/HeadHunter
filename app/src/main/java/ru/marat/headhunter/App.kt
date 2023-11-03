package ru.marat.headhunter

import android.app.Application
import ru.marat.headhunter.di.AppComponent
import ru.marat.headhunter.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().dependencies(this).build()
    }
}