package ro.pyo.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import ro.pyo.littlelemon.data.UserData
import ro.pyo.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }
    //private val onboardingDoneLiveData = MutableLiveData<Boolean>()

    /*private val sharedPrefsListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == ONBOARD_KEY) {
                onboardingDoneLiveData.value = sharedPreferences.getBoolean(key, false)
            }
        }
    private val registerClick: (first: String, last: String, email: String) -> Unit =
        fun(first: String, last: String, email: String) {
            if (first.isNotEmpty() && last.isNotEmpty() && email.isNotEmpty()) {
                val user = UserData(
                    id = UUID.randomUUID().toString(),
                    email = email,
                    firstName = first,
                    lastName = last
                )
                Log.d("DB----", "this is the user: $user")
                val database = UserDatabase.getDatabase(applicationContext)

                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        database.userDao().saveUser(user)
                        Log.d("DB----", "Insert into database")
                        sharedPreferences.edit(commit = true) {
                            putBoolean(ONBOARD_KEY, true)
                        }
                    }
                }
            } else {
                Toast.makeText(
                    this,
                    "Please fill all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
*/

    private val firstNameLiveData = MutableLiveData<String>()
    private val lastNameLiveData = MutableLiveData<String>()
    private val emailLiveData = MutableLiveData<String>()

    private val sharedPrefsListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == SharedPrefsKeys.firstName) {
                firstNameLiveData.value = sharedPreferences.getString(key, "")
            }
            if (key == SharedPrefsKeys.lastName) {
                lastNameLiveData.value = sharedPreferences.getString(key, "")
            }
            if (key == SharedPrefsKeys.email) {
                emailLiveData.value = sharedPreferences.getString(key, "")
            }

            Log.d(
                "main activity:",
                "shared prefs listener : ${firstNameLiveData.value} , ${lastNameLiveData.value}, ${emailLiveData.value}"
            )
        }

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firstNameLiveData.value = sharedPreferences.getString(SharedPrefsKeys.firstName, "")
        lastNameLiveData.value = sharedPreferences.getString(SharedPrefsKeys.lastName, "")
        emailLiveData.value = sharedPreferences.getString(SharedPrefsKeys.email, "")

        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPrefsListener)

        lifecycleScope.launch {
            getMenu()
        }

        setContent {
            LittleLemonTheme {
                AppScreen()
            }
        }
    }

    private suspend fun getMenu() {
        val response:MenuNetworkData =
            client.get(JsonUrl.url)
                .body()
        //return listOf()
        //Log.d("Main activity", "network data: $response")
        for(menu in response.menu)
        {
            Log.d("little lemon main","menu: ${menu.title}")
        }
    }

    @Composable
    fun MyNavigation() {
        val navController = rememberNavController()
        //val skipOnboard = firstNameLiveData.observeAsState(false)
        NavHost(
            navController = navController,
            startDestination = if (firstNameLiveData.value!!.isNotBlank()) Home.route else Onboard.route
        ) {
            composable(Onboard.route) {
                Onboarding(
                    navController = navController
                    //, registerClick
                    , sharedPreferences
                )
            }
            composable(Home.route) {
                HomeScreen(navController = navController)
            }
            composable(Profile.route) {
                ProfileScreen(
                    navController = navController,
                    UserData(
                        "id",
                        firstNameLiveData.value!!,
                        lastNameLiveData.value!!,
                        emailLiveData.value!!
                    )
                )
            }

        }
    }

    @Composable
    private fun AppScreen() {
        Scaffold(
            topBar = {
                //TopAppBar()
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                MyNavigation()
            }
        }
    }
}





