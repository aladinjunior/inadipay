package co.aladinjunior.inadipay.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.model.CostumerContainer

class OperationsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_operations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val list = mutableListOf<CostumerContainer>()

        list.add(CostumerContainer("Aladin", "R$250,00"))

        val adapter = Adapter(R.layout.container_costumer, list)

        val rv = view.findViewById<RecyclerView>(R.id.main_rv)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

    }
}