package co.aladinjunior.inadipay.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.aladinjunior.inadipay.data.db.daos.CostumerDao
import co.aladinjunior.inadipay.data.db.entities.Costumer

@Database(entities = [Costumer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun costumerDao() : CostumerDao

    companion object{
        private var INSTANCE: AppDatabase? = null

         fun getDb(context: Context) : AppDatabase{
           return if(INSTANCE == null){
               synchronized(this){
                   INSTANCE = Room.databaseBuilder(
                       context.applicationContext,
                       AppDatabase::class.java,
                       "inadipay"
                   ).build()
               }
               INSTANCE as AppDatabase
            } else {
               INSTANCE as AppDatabase
            }

        }
    }
}