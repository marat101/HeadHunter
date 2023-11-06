package ru.marat.impl

import androidx.lifecycle.LifecycleOwner
import ru.marat.api.ProfileRoute
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class ProfileRouteImpl: ProfileRoute {
    override fun create(): KClass<LifecycleOwner> = ProfileFragment::class as KClass<LifecycleOwner>
}