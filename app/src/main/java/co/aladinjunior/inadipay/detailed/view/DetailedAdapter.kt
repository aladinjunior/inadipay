package co.aladinjunior.inadipay.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.detailed.model.DetailedCostumerInfoContainer


class DetailedAdapter(private val containerList: List<DetailedCostumerInfoContainer>, ) : RecyclerView.Adapter<DetailedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.container_detailed_costumer_info, parent, false)
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
        fun bind(container: DetailedCostumerInfoContainer){
            itemView.findViewById<TextView>(R.id.detailed_text_label).text = container.label
            itemView.findViewById<TextView>(R.id.detailed_text_response).text = container.response


        }

    }
}