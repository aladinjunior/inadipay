package co.aladinjunior.inadipay.main.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.data.db.entities.Costumer
import co.aladinjunior.inadipay.register.view.RegisterActivity
import co.aladinjunior.inadipay.util.App

class DefaultFragment : Fragment() {

    val list = mutableListOf<Costumer>()
    private lateinit var adapter: DefaultAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_default, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DefaultAdapter(list, requireContext())
        val rv = view.findViewById<RecyclerView>(R.id.default_rv)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        Thread {

            val app = requireActivity().application as App
            val dao = app.db.costumerDao()
            val defaults = dao.getAllDefaults()

            requireActivity().runOnUiThread {
                list.addAll(defaults)
                adapter.notifyDataSetChanged()
            }

        }.start()



    }
    }
