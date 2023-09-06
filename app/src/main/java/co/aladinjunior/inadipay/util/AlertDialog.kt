package co.aladinjunior.inadipay.util

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.os.Looper

import android.widget.Toast
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.data.db.entities.Costumer
import co.aladinjunior.inadipay.main.view.MainAdapter
import java.util.*


class AlertDialog {

    companion object{
        fun buildDialog(customer: Costumer, context: Context, adapter: MainAdapter){
            AlertDialog.Builder(context)
                .setMessage(context.getString(R.string.update_message))
                .setNegativeButton(android.R.string.cancel) { dialog, which ->
                }
                .setPositiveButton(android.R.string.ok) { dialog, which ->


                    Toast.makeText(context, "pegou", Toast.LENGTH_SHORT).show()
                    Thread {
                        val id = customer.id
                        val date = DateUtil.toDate(customer.paymentDay)
                        val app = context.applicationContext as App
                        val dao = app.db.costumerDao()

                        val calendar = Calendar.getInstance()
                        calendar.time = date
                        calendar.add(Calendar.MONTH, 1)
                        val newDate = calendar.time
                        val strDate = DateUtil.fromDate(newDate)
                        dao.updatePaymentDay(id, strDate)
                        Handler(Looper.getMainLooper()).post {

                            adapter.notifyDataSetChanged()
                        }

                    }.start()
                }
                .create()
                .show()
        }
    }


}