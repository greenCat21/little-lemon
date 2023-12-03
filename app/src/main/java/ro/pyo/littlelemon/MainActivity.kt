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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ro.pyo.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    private val firstNameLiveData = MutableLiveData<String>()

    private val sharedPrefsListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == SharedPrefsKeys.firstName) {
                firstNameLiveData.value = sharedPreferences.getString(key, "")
            }
        }

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firstNameLiveData.value = sharedPreferences.getString(SharedPrefsKeys.firstName, "")

        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPrefsListener)

        lifecycleScope.launch {
            val menus = getMenu()
            val database = MenuDatabase.getDatabase(applicationContext)

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    for(menu in menus) {
                        database.menuDao().insert(menu.toMenuItemRoom())
                        Log.d("main activity", "Insert into database ${menu.title}")
                    }
                }
            }
        }

        setContent {
            LittleLemonTheme {
                AppScreen()
            }
        }
    }

    private suspend fun getMenu():List<MenuItemNetwork> {
        val response: MenuNetworkData =
            client.get(JsonUrl.url)
                .body()
        //return listOf()
        //Log.d("Main activity", "network data: $response")
        for (menu in response.menu) {
            Log.d("little lemon main", "menu: ${menu.title}")
        }
        return response.menu
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
                val database = MenuDatabase.getDatabase(applicationContext)
                val menus by database.menuDao().getMenus().observeAsState(emptyList())
                val category by database.menuDao().getCategory().observeAsState(emptyList())
                HomeScreen(navController = navController,menus, category)
            }
            composable(Profile.route) {
                ProfileScreen(
                    navController = navController
                    , sharedPreferences
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





