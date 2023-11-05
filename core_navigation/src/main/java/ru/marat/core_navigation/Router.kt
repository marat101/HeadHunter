package ru.marat.core_navigation

import kotlin.reflect.KClass

interface Router {
    fun <T : Route> provide(clazz: KClass<T>): Route
}