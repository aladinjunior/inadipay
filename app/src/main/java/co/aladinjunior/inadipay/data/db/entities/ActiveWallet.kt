package co.aladinjunior.inadipay.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ActiveWallet(
    @PrimaryKey
    val id: Int = 0,
    val wallet : Double
)
