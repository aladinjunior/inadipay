package co.aladinjunior.inadipay.main.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.data.db.entities.Costumer
import co.aladinjunior.inadipay.detailed.model.DetailedCostumerInfoContainer
import co.aladinjunior.inadipay.detailed.view.DetailedCostumerInfoActivity
import co.aladinjunior.inadipay.main.model.CostumerContainer
import co.aladinjunior.inadipay.util.OnLongClickListener

class MainAdapter(@LayoutRes private val layoutId: Int,
                  private val containerList: List<Costumer>,
                  private val context: Context,
                  private val listener: OnLongClickListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
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
         fun bind(container: Costumer){
             itemView.findViewById<TextView>(R.id.main_text_costumer_name).text = container.firstName
             itemView.findViewById<TextView>(R.id.main_text_costumer_bill).text = container.amountReleased
             val costumerContainer = itemView as FrameLayout
             costumerContainer.setOnLongClickListener {
                 listener.onLongClick(adapterPosition, container)
                 true
             }
             costumerContainer.setOnClickListener {
                 val i = Intent(context, DetailedCostumerInfoActivity::class.java)
                     .putExtra("id", container.id)
                 context.startActivity(i)
             }


         }

    }
}