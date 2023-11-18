package ru.marat.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class SearchFragment : Fragment(R.layout.fragment_search) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = view.findViewById<RecyclerView>(R.id.work_list)
        val rcv = TestRecyclerView()
        list.adapter = rcv
//        rcv.upd()
    }
}

private class TestRecyclerView : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val listik = listOf<TestItem>(
        TestItem,
        TestItem,
        TestItem,
        TestItem,
        TestItem,
        TestItem,
        TestItem,
        TestItem,
        TestItem,
        TestItem
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(ru.marat.core_view.R.layout.view_vacancy_card, parent, false)
        val workName = inflater.findViewById<TextView>(ru.marat.core_view.R.id.work_name)
        val salat = inflater.findViewById<TextView>(ru.marat.core_view.R.id.salary)
        val empl = inflater.findViewById<TextView>(ru.marat.core_view.R.id.employer)
        val loc = inflater.findViewById<TextView>(ru.marat.core_view.R.id.location)
        val exp = inflater.findViewById<TextView>(ru.marat.core_view.R.id.experience)
        workName.text = TestItem.workName
        salat.text = TestItem.salatik
        empl.text = TestItem.company
        loc.text = TestItem.location
        exp.text = TestItem.opitRaboti
        return object : RecyclerView.ViewHolder(inflater.rootView) {
        }
    }

    override fun getItemCount(): Int = listik.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
}

data object TestItem {
    val workName = "Начинающий специалист в сфере IT"
    val salatik = "52000 – 60000 ₽"
    val company = "ООО Частная Охранная организация Восток"
    val location = "Ростов-на-Дону"
    val opitRaboti = "Без опыта"
}