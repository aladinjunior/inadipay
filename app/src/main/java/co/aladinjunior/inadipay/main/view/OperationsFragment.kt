package co.aladinjunior.inadipay.main.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.data.db.entities.Costumer
import co.aladinjunior.inadipay.main.model.CostumerContainer
import co.aladinjunior.inadipay.register.view.RegisterActivity
import co.aladinjunior.inadipay.util.App
import co.aladinjunior.inadipay.util.DateUtil
import co.aladinjunior.inadipay.util.OnLongClickListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class OperationsFragment : Fragment(), OnLongClickListener {

    val list = mutableListOf<Costumer>()
    private lateinit var adapter: MainAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
          return inflater.inflate(R.layout.fragment_operations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = MainAdapter(list, requireContext(), this)
        val rv = view.findViewById<RecyclerView>(R.id.main_rv)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        Thread {
            val app = requireActivity().application as App
            val dao = app.db.costumerDao()
            val customers = dao.getAllContainers()

            requireActivity().runOnUiThread {
                list.addAll(customers)
                adapter.notifyDataSetChanged()
            }

        }.start()

        view.findViewById<TextView>(R.id.register).setOnClickListener {
            val i = Intent(requireContext(), RegisterActivity::class.java)
            startActivity(i)
        }


    }

    override fun onLongClick(position: Int, customer: Costumer, notifier: ProgressBar) {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.update_message))
            .setNegativeButton(android.R.string.cancel) { dialog, which ->
            }
            .setPositiveButton(android.R.string.ok) { dialog, which ->


                Toast.makeText(requireContext(), "pegou", Toast.LENGTH_SHORT).show()
                    Thread {
                        val id = customer.id
                        val date = DateUtil.toDate(customer.paymentDay)
                        val app = requireActivity().application as App
                        val dao = app.db.costumerDao()

                        val calendar = Calendar.getInstance()
                        calendar.time = date
                        calendar.add(Calendar.MONTH, 1)
                        val newDate = calendar.time
                        val strDate = DateUtil.fromDate(newDate)
                        dao.updatePaymentDay(id, strDate)

                        requireActivity().runOnUiThread {
                            notifier.visibility = View.GONE

                        }
                    }.start()
            }
            .create()
            .show()
    }
}