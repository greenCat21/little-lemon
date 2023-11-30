package ro.pyo.littlelemon

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
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
                            val database = UserDatabase.getDatabase(applicationContext)

                            lifecycleScope.launch {
                                withContext(Dispatchers.IO) {
                                    database.userDao().saveUser(user)
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



