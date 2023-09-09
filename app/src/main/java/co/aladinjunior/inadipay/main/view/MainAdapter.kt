package co.aladinjunior.inadipay.main.view

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
import co.aladinjunior.inadipay.util.AlertDialog
import co.aladinjunior.inadipay.util.App
import co.aladinjunior.inadipay.util.DateUtil
import org.w3c.dom.Text
import java.util.*


class MainAdapter(
    private val containerList: List<Costumer>,
    private val context: Context,
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.container_customer, parent, false)
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

        private var alertDialogShown = false

        fun bind(customer: Costumer) {
            val strDate = DateUtil.getCurrentDate()
            val currentDate = DateUtil.toDate(strDate)
            val paymentDate = DateUtil.toDate(customer.paymentDay)
            val splitDate = customer.paymentDay.split("/")
            val day = splitDate[0]
            val monthName = DateUtil.getMonthName(customer)
            val rememberText = itemView.findViewById<TextView>(R.id.remember)

            val date = itemView.findViewById<TextView>(R.id.date)
            date.text = day

            val monthView = itemView.findViewById<TextView>(R.id.month)
            monthView.text = monthName

            val name = itemView.findViewById<TextView>(R.id.name)
            name.text = customer.firstName

            val amount = itemView.findViewById<TextView>(R.id.amount)
            amount.text = context.getString(R.string.total_amount_cardview, customer.amountReleased)

            val icon = itemView.findViewById<ImageView>(R.id.status)


            val calendar = Calendar.getInstance()
            calendar.time = paymentDate
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            val nextDayDate = calendar.time







            if (paymentDate == currentDate) icon.visibility = View.VISIBLE
            else icon.visibility = View.GONE



           if(currentDate == nextDayDate) rememberText.visibility = View.VISIBLE
            else rememberText.visibility = View.GONE













            val options = itemView.findViewById<ImageView>(R.id.options)
            options.visibility = View.VISIBLE
            options.setOnClickListener {
                val popUpMenu = PopupMenu(context, it)
                popUpMenu.inflate(R.menu.menu_options)

                popUpMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.delete -> {

                            AlertDialog.buildDeleteDialog(
                                customer,
                                context,
                                this@MainAdapter,
                                adapterPosition
                            )

                            true
                        }
                        R.id.confirm -> {

                            AlertDialog.buildDialog(
                                customer,
                                context,
                                this@MainAdapter
                            )

                            true
                        }
                        else -> false
                    }
                }
                popUpMenu.show()
            }

            val notifier = itemView.findViewById<ProgressBar>(R.id.main_button_notifier)
            val costumerContainer = itemView as CardView


            costumerContainer.setOnClickListener {
                val i = Intent(context, DetailedCostumerInfoActivity::class.java)
                    .putExtra("id", customer.id)
                context.startActivity(i)
            }


        }

    }
}