package ru.marat.core_navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

object NavController {

    @IdRes
    internal var rootContainer: Int? = null

    @IdRes
    internal var mainContainer: Int? = null

    fun <T : Route> navigate(
        clazz: KClass<T>? =null, //fixme это потом убрать
        manager: FragmentManager,
        isReplace: Boolean = false,
        addToBackStack: Boolean = true,
        hide: Boolean = true,
        args: Bundle? = null,
        root: Boolean = true
    ) {
        manager.commit {
            setReorderingAllowed(true)

            val fragment = if (clazz == null) Fragment() else FragmentFactory.createFragment(clazz, args)

            if (hide) manager.fragments.lastOrNull { !it.isHidden }?.let(::hide)

            val containerId = (if (root) rootContainer else mainContainer)
                ?: throw IllegalArgumentException("Container not specified\nroot=${rootContainer}\nmain=${mainContainer}")
            if (isReplace) replace(containerId, fragment) else add(containerId, fragment)

            if (addToBackStack) addToBackStack(fragment::class.toString())
        }
    }
}