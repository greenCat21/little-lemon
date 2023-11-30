package ro.pyo.littlelemon

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ro.pyo.littlelemon.composables.Onboarding
import ro.pyo.littlelemon.data.UserData
import ro.pyo.littlelemon.data.UserDatabase
import ro.pyo.littlelemon.ui.theme.LittleLemonTheme
import java.util.UUID

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var registerClick: (first:String,last:String,email:String) -> Unit = fun(first:String, last:String, email:String){
                        if (first.isNotEmpty() && last.isNotEmpty() && email.isNotEmpty()) {
                            val user = UserData(
                                id = UUID.randomUUID().toString(),
                                email = email,
                                firstName = first,
                                lastName = last
                            )
                            Log.d("DB----","this is the user: $user")
                            val database = UserDatabase.getDatabase(applicationContext)

                            lifecycleScope.launch {
                                withContext(Dispatchers.IO) {
                                    database.userDao().saveUser(user)
                                    Log.d("DB----","Insert into database" )
                                }
                            }
                        } else {
                            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
                        }
                    }
                    Onboarding(registerClick)
                }
            }
        }
    }


}



