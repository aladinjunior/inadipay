package co.aladinjunior.inadipay.util

import co.aladinjunior.inadipay.data.db.entities.Costumer

interface OnLongClickListener {



    fun onLongClick(position: Int, customer: Costumer)
}