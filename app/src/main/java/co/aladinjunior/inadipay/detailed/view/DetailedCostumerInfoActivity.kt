package co.aladinjunior.inadipay.detailed.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.detailed.model.DetailedCostumerInfoContainer
import co.aladinjunior.inadipay.main.view.DetailedAdapter
import co.aladinjunior.inadipay.util.App
import co.aladinjunior.inadipay.util.DateUtil
import co.aladinjunior.inadipay.util.Labels
import java.lang.NullPointerException


class DetailedCostumerInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_costumer_info)

        val id = intent?.extras?.getInt("id")
        val list = mutableListOf<DetailedCostumerInfoContainer>()


        Thread {

            val app = application as App
            val dao = app.db.costumerDao()

            if(id != null) {
                val customer = dao.getCustomerInfo(id)
                val name = "${customer.firstName} ${customer.secondName}"

                val delayedDays = DateUtil.calculateDelayedDays(customer.paymentDay).toString()

                runOnUiThread {
                    list.clear()

                    list.add(
                        DetailedCostumerInfoContainer(Labels.NAME, name)
                    )
                    list.add(
                        DetailedCostumerInfoContainer(Labels.CPF, customer.cpf)
                    )
                    list.add(
                        DetailedCostumerInfoContainer(Labels.CELLPHONE, "81-98439138")
                    )
                    list.add(
                        DetailedCostumerInfoContainer(Labels.PAYMENT_DAY, customer.paymentDay)
                    )

                    list.add(
                        DetailedCostumerInfoContainer(Labels.DELAYED_DAYS, delayedDays)
                    )
                    list.add(
                        DetailedCostumerInfoContainer(Labels.AMOUNT_RELEASED, customer.amountReleased)
                    )
                }
            }


        }.start()





        val adapter = DetailedAdapter(list)
        val rv = findViewById<RecyclerView>(R.id.detailed_rv)
        rv.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rv.adapter = adapter




    }

}
