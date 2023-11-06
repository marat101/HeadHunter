package ru.marat.impl

import androidx.lifecycle.LifecycleOwner
import ru.marat.api.HomeRoute
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class HomeRouteImpl: HomeRoute {
    override fun create(): KClass<LifecycleOwner> = HomeFragment::class as KClass<LifecycleOwner>
}