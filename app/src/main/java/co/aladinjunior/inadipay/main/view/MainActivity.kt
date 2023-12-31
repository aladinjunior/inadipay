package co.aladinjunior.inadipay.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.data.db.AppDatabase
import co.aladinjunior.inadipay.util.App
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val navController = findNavController(R.id.nav_host_fragment_container)
        findViewById<BottomNavigationView>(R.id.main_bottom_navigation).setupWithNavController(navController)




    }
}