package co.aladinjunior.inadipay.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Costumer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val secondName: String,
    val cpf: String,
    val paymentDay: String,
    val amountReleased: String,
    val cellPhone: String,
    val installmentValue: Double,
    val installments: Int,
    val installmentPaids: Int = 0,
    var remainingValue: Double = amountReleased.toDouble(),
    var paidNextDay: Int = 0
    )







