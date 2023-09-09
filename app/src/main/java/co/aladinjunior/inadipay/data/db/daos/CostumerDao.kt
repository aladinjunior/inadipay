package co.aladinjunior.inadipay.data.db.daos

import androidx.room.*
import co.aladinjunior.inadipay.data.db.entities.Costumer


@Dao
interface CostumerDao {


    @Insert
    fun insert(costumer: Costumer)

    @Query("SELECT * FROM Costumer")
    fun getAllContainers() : List<Costumer>

    @Delete
    fun delete(costumer: Costumer) : Int

    @Query("SELECT * FROM Costumer WHERE id = :id")
    fun getCustomerInfo(id: Int) : Costumer

    @Query("SELECT * FROM Costumer WHERE strftime('%Y-%m-%d', 'now') > strftime('%Y-%m-%d', substr(paymentDay, 7, 4) || '-' || substr(paymentDay, 4, 2) || '-' || substr(paymentDay, 1, 2))")
    fun getAllDefaults(): List<Costumer>

    @Query("UPDATE Costumer SET paymentDay = :updatedDate WHERE id = :id")
    fun updatePaymentDay(id: Int, updatedDate: String)

    @Query("SELECT SUM(CAST(amountReleased AS REAL)) FROM Costumer")
    fun getSum() : Double



    @Query("UPDATE Costumer SET installmentValue = :installmentValue WHERE id =:id ")
    fun getInstallmentValue(id: Int, installmentValue: Double)

    @Query("SELECT SUM(amountReleased) FROM Costumer WHERE strftime('%Y-%m-%d', 'now') > strftime('%Y-%m-%d', substr(paymentDay, 7, 4) || '-' || substr(paymentDay, 4, 2) || '-' || substr(paymentDay, 1, 2))")
    fun getAllInstallmentValue() : Double

    @Query("UPDATE COSTUMER SET installmentPaids = :installmentPaids WHERE id = :id")
    fun updateInstallmentPaids(id: Int, installmentPaids: Int) : Int

    @Query("UPDATE Costumer SET remainingValue = :remainingValue WHERE id = :id")
    fun updateRemainingValue(id: Int, remainingValue: Double)

    @Query("SELECT SUM(remainingValue) FROM Costumer")
    fun getAllRemainingValue() : Double

    @Query("UPDATE Costumer SET paidNextDay  = :state WHERE id = :id")
    fun updatePaidNextDay(id: Int, state: Int)








}