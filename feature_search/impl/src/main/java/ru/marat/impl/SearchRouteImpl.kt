package ru.marat.impl

import androidx.lifecycle.LifecycleOwner
import ru.marat.api.SearchRoute
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class SearchRouteImpl: SearchRoute {
    override fun create(): KClass<LifecycleOwner> = SearchFragment::class as KClass<LifecycleOwner>
}