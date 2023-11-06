package ru.marat.feature_root

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.marat.api.HomeRoute
import ru.marat.core_navigation.NavController
import ru.marat.core_navigation.Route

class RootFragment: Fragment(R.layout.home_container) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bnv = view.findViewById<BottomNavigationView>(R.id.bottom_nav_bar)
        val select = { itemId: Int ->
            when(itemId){
                R.id.home -> {
                    navigate<HomeRoute>()
                }
                else ->{
                    NavController.navigate<HomeRoute>(null, childFragmentManager, isReplace = true, root = false) //fixme
                }
            }
            true
        }
        select(bnv.selectedItemId)
        bnv?.setOnItemSelectedListener{select(it.itemId)}
    }
    private inline fun <reified T: Route> navigate(){
        NavController.navigate(T::class, childFragmentManager, isReplace = true, root = false)
    }
}