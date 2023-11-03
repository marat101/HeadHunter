package ru.marat.headhunter.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.marat.headhunter.MainActivity

@Component
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun dependencies(@BindsInstance context: Context) : Builder
        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}