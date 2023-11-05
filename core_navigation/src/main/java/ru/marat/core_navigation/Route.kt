package ru.marat.core_navigation

import androidx.lifecycle.LifecycleOwner
import kotlin.reflect.KClass

interface Route {
    fun create(): KClass<LifecycleOwner>
}