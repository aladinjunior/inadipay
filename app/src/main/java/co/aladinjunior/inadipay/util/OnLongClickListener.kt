package co.aladinjunior.inadipay.util

import android.widget.ProgressBar
import co.aladinjunior.inadipay.data.db.entities.Costumer
import com.google.android.material.floatingactionbutton.FloatingActionButton

interface OnLongClickListener {



    fun onLongClick(position: Int, customer: Costumer, notifier: ProgressBar)
}