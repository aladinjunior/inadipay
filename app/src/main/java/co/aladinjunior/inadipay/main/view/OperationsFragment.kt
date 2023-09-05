package co.aladinjunior.inadipay.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.main.model.CostumerContainer

class OperationsFragment : Fragment() {

    val list = mutableListOf<CostumerContainer>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_operations, container, false)

        val name = requireArguments().getString("firstName")
        val amount = requireArguments().getString("amount")


        list.add(
            CostumerContainer(name ?: "Usuario Padrão", amount ?: "Valor Padrão"))

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)











        val adapter = MainAdapter(R.layout.container_costumer, list, requireContext())

        val rv = view.findViewById<RecyclerView>(R.id.main_rv)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter



    }
}