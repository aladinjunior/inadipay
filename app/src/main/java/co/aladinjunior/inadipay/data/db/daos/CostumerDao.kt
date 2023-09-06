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

    @Query("SELECT * FROM Costumer WHERE strftime('%d-%m-%Y', 'now') > paymentDay")
    fun getAllDefaults(): List<Costumer>

    @Query("UPDATE Costumer SET paymentDay = :updatedDate WHERE id = :id")
    fun updateDateWithOneMonthAdded(id: Int, updatedDate: String)


}