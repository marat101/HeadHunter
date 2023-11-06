package ru.marat.headhunter

import ru.marat.api.HomeRoute
import ru.marat.api.ProfileRoute
import ru.marat.api.SearchRoute
import ru.marat.core_navigation.Route
import ru.marat.core_navigation.Router
import ru.marat.impl.HomeRouteImpl
import ru.marat.impl.ProfileRouteImpl
import ru.marat.impl.SearchRouteImpl
import kotlin.reflect.KClass

fun createRouter() = object : Router {
    override fun <T : Route> provide(clazz: KClass<T>): Route {
        return when(clazz) {
            HomeRoute::class -> HomeRouteImpl()
            SearchRoute::class -> SearchRouteImpl()
            ProfileRoute::class -> ProfileRouteImpl()
            else -> throw IllegalArgumentException(clazz.simpleName)
        }
    }
}