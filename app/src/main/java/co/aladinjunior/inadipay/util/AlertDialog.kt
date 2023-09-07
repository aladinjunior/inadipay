package co.aladinjunior.inadipay.util

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.data.db.entities.Costumer
import co.aladinjunior.inadipay.main.view.MainAdapter
import java.util.*

class AlertDialog {

    companion object {
        fun buildDialog(customer: Costumer, context: Context, adapter: MainAdapter) {
            val editText = EditText(context)
            val errorText = context.getString(R.string.this_field_cant_be_null)
            editText.hint = context.getString(R.string.type_payment_amount)
            editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

            AlertDialog.Builder(context)
                .setMessage(context.getString(R.string.update_message))
                .setView(editText)
                .setNegativeButton(android.R.string.cancel) { dialog, which ->
                }
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    val inputText = editText.text.toString().trim()
                    if (inputText.isEmpty() ) {
                        Toast.makeText(context, "valor do pagamento não pode ser igual a zero", Toast.LENGTH_SHORT).show()
                    } else {
                        Thread {
                            val id = customer.id
                            val date = DateUtil.toDate(customer.paymentDay)
                            val app = context.applicationContext as App
                            val dao = app.db.costumerDao()
                            val aw = app.db.activeWalletDao()

                            dao.getInstallmentValue(id, inputText.toDouble())
                            val currentWallet = aw.getActiveWallet()

                            val newAmount =   currentWallet.wallet - inputText.toDouble()
                            if(newAmount < 0){
                                Handler(Looper.getMainLooper())
                                    .post { Toast.makeText(context, context.getString(R.string.payment_cant_be_bigger), Toast.LENGTH_LONG).show() }

                            } else {
                                val calendar = Calendar.getInstance()
                                calendar.time = date
                                calendar.add(Calendar.MONTH, 1)
                                val newDate = calendar.time
                                val strDate = DateUtil.fromDate(newDate)
                                dao.updatePaymentDay(id, strDate)
                                aw.updateTotalAmount(newAmount)


                                Handler(Looper.getMainLooper()).post {
                                    adapter.notifyDataSetChanged()

                                }

                            }

                        }.start()
                    }
                }
                .create()
                .show()
        }
    }
}
