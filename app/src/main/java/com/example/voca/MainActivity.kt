package com.example.voca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.example.voca.core.presentation.navigation.VocaNavGraph
import com.example.voca.ui.theme.VocaTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            0
        )

        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()


            VocaTheme {
                VocaNavGraph(
                    navController = navController
                )
            }
        }
    }
}



