package co.aladinjunior.inadipay.util

import android.app.Application
import co.aladinjunior.inadipay.data.db.AppDatabase

class App : Application() {

    lateinit var db: AppDatabase


    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDb(this)


    }
}