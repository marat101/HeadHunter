package ru.marat.headhunter

import android.app.Application
import ru.marat.core_navigation.FragmentFactory
import ru.marat.feature_root.R
import ru.marat.headhunter.di.AppComponent
import ru.marat.headhunter.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().dependencies(this).build()
        FragmentFactory.init(createRouter(), R.id.root_container, R.id.home_container)
    }
}