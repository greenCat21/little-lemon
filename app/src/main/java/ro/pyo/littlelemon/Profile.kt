package ro.pyo.littlelemon

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController)
{
    Scaffold(modifier = Modifier.fillMaxSize(), backgroundColor = Color.White) { padding ->
        Text(
            modifier = Modifier.padding(padding.calculateTopPadding())
            , text = "profile screen"
        )
    }
}