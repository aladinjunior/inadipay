package co.aladinjunior.inadipay.main.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.data.db.entities.Costumer
import co.aladinjunior.inadipay.detailed.view.DetailedCostumerInfoActivity
import co.aladinjunior.inadipay.util.DateUtil
import co.aladinjunior.inadipay.util.OnLongClickListener
import java.util.*


class MainAdapter(private val containerList: List<Costumer>,
                  private val context: Context,
                  private val listener: OnLongClickListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

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
             val currentDate = DateUtil.toDate(strDate)
             val paymentDate = DateUtil.toDate(customer.paymentDay)
             val splitDate = customer.paymentDay.split("/")
             val day = splitDate[0]
             val month = splitDate[1]
             val monthName = DateUtil.getMonthName(customer)

             val date = itemView.findViewById<TextView>(R.id.date)
             date.text = day

             val monthView = itemView.findViewById<TextView>(R.id.month)
             monthView.text = monthName

             val name = itemView.findViewById<TextView>(R.id.name)
             name.text = customer.firstName

             val amount = itemView.findViewById<TextView>(R.id.amount)
             amount.text = customer.amountReleased

             val icon = itemView.findViewById<ImageView>(R.id.status)

             if (paymentDate == currentDate) icon.visibility = View.VISIBLE
             else icon.visibility = View.GONE

             val options = itemView.findViewById<ImageView>(R.id.options)
             options.setOnClickListener {
                 val popUpMenu = PopupMenu(context, it)
                 popUpMenu.inflate(R.menu.menu_options)

                 popUpMenu.setOnMenuItemClickListener {
                     when(it.itemId) {
                         R.id.delete -> {

                             true
                         }
                         R.id.confirm -> {

                             co.aladinjunior.inadipay.util.AlertDialog.buildDialog(customer, context, this@MainAdapter)


                             true
                         }
                         else -> false
                     }
                 }
                 popUpMenu.show()
             }

             val notifier = itemView.findViewById<ProgressBar>(R.id.main_button_notifier)
             val costumerContainer = itemView as CardView

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