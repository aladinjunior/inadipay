package co.aladinjunior.inadipay.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import co.aladinjunior.inadipay.data.db.entities.Costumer

@Dao
interface CostumerDao {

    @Insert
    fun insert(costumer: Costumer)


}