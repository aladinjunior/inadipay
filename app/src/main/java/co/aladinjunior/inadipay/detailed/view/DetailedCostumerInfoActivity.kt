package co.aladinjunior.inadipay.detailed.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.detailed.model.DetailedCostumerInfoContainer
import co.aladinjunior.inadipay.main.view.DetailedAdapter
import co.aladinjunior.inadipay.util.Labels


class DetailedCostumerInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_costumer_info)
        val list = mutableListOf<DetailedCostumerInfoContainer>()

        list.add(
            DetailedCostumerInfoContainer(Labels.NAME, "Aladin Bento Ferreira Júnior")
        )

        list.add(
            DetailedCostumerInfoContainer(Labels.CPF, "142.161.214-38")
        )

        list.add(
            DetailedCostumerInfoContainer(Labels.CELLPHONE, "(81) 984349138")
        )

        list.add(
            DetailedCostumerInfoContainer(Labels.PAYMENT_DAY, "25/08/2023")
        )

        list.add(
            DetailedCostumerInfoContainer(Labels.DELAYED_DAYS, "5")
        )

        list.add(
            DetailedCostumerInfoContainer(Labels.AMOUNT_RELEASED, "1000")
        )



        val adapter = DetailedAdapter(list)
        val rv = findViewById<RecyclerView>(R.id.detailed_rv)
        rv.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rv.adapter = adapter


    }
}