package co.aladinjunior.inadipay.data.db.daos

import androidx.room.*
import co.aladinjunior.inadipay.data.db.entities.Costumer
import co.aladinjunior.inadipay.main.model.CostumerContainer

@Dao
interface CostumerDao {

    @Insert
    fun insert(costumer: Costumer)

    @Query("SELECT * FROM Costumer")
    fun getAllContainers() : List<Costumer>

    @Delete
    fun delete(costumer: Costumer) : Int

    @Query("SELECT id, secondName, firstName, cpf, paymentDay, amountReleased, cellPhone FROM Costumer WHERE id = :id")
    fun getCustomerInfo(id: Int) : Costumer

    @Query("SELECT * FROM Costumer WHERE strftime('%Y-%m-%d', 'now') > strftime('%Y-%m-%d', substr(paymentDay, 7, 4) || '-' || substr(paymentDay, 4, 2) || '-' || substr(paymentDay, 1, 2))")
    fun getAllDefaults(): List<Costumer>

    @Query("UPDATE Costumer SET paymentDay = :updatedDate WHERE id = :id")
    fun updatePaymentDay(id: Int, updatedDate: String)


}