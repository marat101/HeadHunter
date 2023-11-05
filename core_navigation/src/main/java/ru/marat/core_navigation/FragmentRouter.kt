package ru.marat.core_navigation

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import kotlin.reflect.KClass

abstract class FragmentRouter {

    fun <T : Route> navigate(
        clazz: KClass<T>,
        manager: FragmentManager,
        isReplace: Boolean = false,
        addToBackStack: Boolean = true,
        hide: Boolean = true,
        args: Bundle? = null,
        root: Boolean = true
    ) {
        manager.commit {
            setReorderingAllowed(true)

            val fragment = FragmentFactory.createFragment(clazz, args)

            if (hide) manager.fragments.lastOrNull { !it.isHidden }?.let(::hide)

            val containerId = if (root) R.id.root_container else R.id.home_container
            if (isReplace) replace(containerId, fragment) else add(containerId, fragment)

            if (addToBackStack) addToBackStack(fragment::class.toString())
        }
    }
}