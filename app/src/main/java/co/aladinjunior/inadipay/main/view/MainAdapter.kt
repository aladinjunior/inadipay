package co.aladinjunior.inadipay.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.main.model.CostumerContainer

class MainAdapter(@LayoutRes private val layoutId: Int,
                  private val containerList: List<CostumerContainer>,
                  private val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

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
         fun bind(container: CostumerContainer){
             itemView.findViewById<TextView>(R.id.main_text_costumer_name).text = container.costumerName
             itemView.findViewById<TextView>(R.id.main_text_costumer_bill).text = container.costumerBill
             val costumerContainer = itemView as FrameLayout
             costumerContainer.setOnClickListener {

             }

         }

    }
}