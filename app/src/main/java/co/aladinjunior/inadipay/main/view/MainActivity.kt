package co.aladinjunior.inadipay.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import co.aladinjunior.inadipay.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navController = findNavController(R.id.nav_host_fragment_container)
        navController.navigate(R.id.nav_operations)

        findViewById<BottomNavigationView>(R.id.main_bottom_navigation).setupWithNavController(navController)




    }
}