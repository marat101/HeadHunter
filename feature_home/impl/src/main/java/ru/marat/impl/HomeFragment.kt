package ru.marat.impl

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.marat.core_view.diagram.Diagram
import ru.marat.core_view.diagram.DiagramItem

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val diagram = view.findViewById<Diagram>(R.id.diagram)
        diagram.items = listOf(
            DiagramItem(
                color = Color.BLUE,
                value = 15f
            ),
            DiagramItem(
                color = Color.RED,
                value = 5f
            ),
            DiagramItem(
                color = Color.YELLOW,
                value = 15f
            ),
            DiagramItem(
                color = Color.CYAN,
                value = 5f
            ),
            DiagramItem(
                color = Color.BLACK,
                value = 85f
            ),
            DiagramItem(
                color = Color.GREEN,
                value = 35f
            )
        )
    }
}