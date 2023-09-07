package co.aladinjunior.inadipay.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.aladinjunior.inadipay.data.db.daos.ActiveWalletDao
import co.aladinjunior.inadipay.data.db.daos.CostumerDao
import co.aladinjunior.inadipay.data.db.entities.ActiveWallet
import co.aladinjunior.inadipay.data.db.entities.Costumer

@Database(entities = [Costumer::class, ActiveWallet::class],  version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun costumerDao(): CostumerDao
    abstract fun activeWalletDao() : ActiveWalletDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDb(context: Context): AppDatabase {
            return if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "inadipay"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
                INSTANCE as AppDatabase
            } else {
                INSTANCE as AppDatabase
            }

        }

        fun deleteDatabase(context: Context) {
            INSTANCE?.close()
            val databaseFile = context.getDatabasePath("inadipay")
            if (databaseFile.exists()) {
                context.deleteDatabase("inadipay")

            }
        }
    }
}