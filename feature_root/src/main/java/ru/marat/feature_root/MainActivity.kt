package ru.marat.feature_root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_container)
        supportFragmentManager.beginTransaction().replace(R.id.root_container, RootFragment()).commit()
        //todo
    }
}