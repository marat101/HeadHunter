package ru.marat.feature_root

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.marat.api.HomeRoute
import ru.marat.api.ProfileRoute
import ru.marat.api.SearchRoute
import ru.marat.core_navigation.NavController
import ru.marat.core_navigation.Route

class RootFragment: Fragment(R.layout.home_container) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bnv = view.findViewById<BottomNavigationView>(R.id.bottom_nav_bar)
        val select = { itemId: Int ->
            when(itemId){
                R.id.home -> navigate<HomeRoute>()
                R.id.search -> navigate<SearchRoute>()
                R.id.profile -> navigate<ProfileRoute>()
            }
            true
        }
        select(bnv.selectedItemId)
        bnv?.setOnItemSelectedListener{
            Log.e("TAGTAG", it.title.toString())
            select(it.itemId)}
        bnv?.setOnItemReselectedListener {}
    }
    private inline fun <reified T: Route> navigate(){
        NavController.navigate(T::class, childFragmentManager, isReplace = true, root = false)
    }
}