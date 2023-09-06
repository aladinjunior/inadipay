package co.aladinjunior.inadipay.main.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.data.db.entities.Costumer
import co.aladinjunior.inadipay.detailed.view.DetailedCostumerInfoActivity
import co.aladinjunior.inadipay.util.DateUtil
import co.aladinjunior.inadipay.util.OnLongClickListener


class MainAdapter(private val containerList: List<Costumer>,
                  private val context: Context,
                  private val listener: OnLongClickListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.container_costumer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = containerList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return containerList.size
    }

     inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         fun bind(customer: Costumer){
             val strDate = DateUtil.getCurrentDate()
             val currentDate = DateUtil.toDate(strDate)
             val paymentDate = DateUtil.toDate(customer.paymentDay)
             itemView.findViewById<TextView>(R.id.main_text_costumer_name).text = customer.firstName
             itemView.findViewById<TextView>(R.id.main_text_costumer_bill).text = customer.amountReleased
             val notifier = itemView.findViewById<ProgressBar>(R.id.main_button_notifier)
             val costumerContainer = itemView as FrameLayout
             if (paymentDate == currentDate){
                 notifier.visibility = View.VISIBLE
             } else {
                 notifier.visibility = View.GONE
             }
             costumerContainer.setOnLongClickListener {
                 listener.onLongClick(adapterPosition, customer, notifier)
                 true
             }
             costumerContainer.setOnClickListener {
                 val i = Intent(context, DetailedCostumerInfoActivity::class.java)
                     .putExtra("id", customer.id)
                 context.startActivity(i)
             }


         }

    }
}