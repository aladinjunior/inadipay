package co.aladinjunior.inadipay.data.db.entities

import androidx.room.ColumnInfo
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
    val defaultValue: String
)
