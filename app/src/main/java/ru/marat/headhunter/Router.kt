package ru.marat.headhunter

import ru.marat.api.HomeRoute
import ru.marat.core_navigation.Route
import ru.marat.core_navigation.Router
import ru.marat.impl.HomeRouteImpl
import kotlin.reflect.KClass

fun createRouter() = object : Router {
    override fun <T : Route> provide(clazz: KClass<T>): Route {
        return when(clazz) {
            HomeRoute::class -> HomeRouteImpl()
            else -> throw NullPointerException() //todo
        }
    }
}