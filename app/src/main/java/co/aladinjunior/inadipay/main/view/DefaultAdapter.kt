package co.aladinjunior.inadipay.main.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.data.db.entities.Costumer
import co.aladinjunior.inadipay.detailed.model.DetailedCostumerInfoContainer
import co.aladinjunior.inadipay.detailed.view.DetailedCostumerInfoActivity

import co.aladinjunior.inadipay.util.DateUtil


class DefaultAdapter(
    private val containerList: List<Costumer>,
    private val context: Context) : RecyclerView.Adapter<DefaultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.container_customer, parent, false)
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

            val splitDate = customer.paymentDay.split("/")
            val day = splitDate[0]

            val monthName = DateUtil.getMonthName(customer)

            val date = itemView.findViewById<TextView>(R.id.date)
            date.text = day

            val monthView = itemView.findViewById<TextView>(R.id.month)
            monthView.text = monthName

            val name = itemView.findViewById<TextView>(R.id.name)
            name.text = customer.firstName

            val amount = itemView.findViewById<TextView>(R.id.amount)
            amount.text = customer.amountReleased



            val costumerContainer = itemView as CardView

            costumerContainer.setOnClickListener {
                val i = Intent(context, DetailedCostumerInfoActivity::class.java)
                    .putExtra("id", customer.id)
                context.startActivity(i)
            }


        }

    }
}