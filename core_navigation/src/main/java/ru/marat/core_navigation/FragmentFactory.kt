package ru.marat.core_navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
object FragmentFactory {

    private var provider: Router? = null

    fun init(
        provider: Router,
        @IdRes rootContainerId: Int,
        @IdRes mainContainerId: Int,
    ) {
        this.provider = provider
        NavController.mainContainer = mainContainerId
        NavController.rootContainer = rootContainerId
    }

    fun <T : Route> createFragment(clazz: KClass<T>, bundle: Bundle?): Fragment {
        return getFragmentInstance(getFragmentClass(clazz).java, bundle)
    }

    private fun <T : Route> getFragmentClass(clazz: KClass<T>): KClass<out Fragment> {
        if (provider == null) throw IllegalStateException("Router is not initialized")
        return provider!!.provide(clazz).create() as KClass<out Fragment>
    }


    private fun <T : Fragment> getFragmentInstance(clazz: Class<T>, bundle: Bundle?): Fragment {
        val fragment = clazz.newInstance()
        fragment.arguments = bundle
        return fragment
    }
}