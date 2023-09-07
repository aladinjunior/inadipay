package co.aladinjunior.inadipay.data.db.daos

import androidx.room.*
import co.aladinjunior.inadipay.data.db.entities.ActiveWallet


@Dao
interface ActiveWalletDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(activeWallet: ActiveWallet)

    @Query("SELECT * FROM ActiveWallet WHERE id = 0")
    fun getActiveWallet(): ActiveWallet

    @Query("UPDATE ActiveWallet SET wallet = :totalAmount WHERE id = 0")
    fun updateTotalAmount(totalAmount: Double)

}