package ru.marat.headhunter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.marat.core_navigation.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_container)
        supportFragmentManager.beginTransaction().replace(R.id.root_container, Fragment(R.layout.home_container)).commit()
        //todo
    }
}