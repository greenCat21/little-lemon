package ro.pyo.littlelemon

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavHostController


@Composable
fun Onboarding(
    navController: NavHostController,
    //clickRegister: (first: String, last: String, email: String) -> Unit,
    sharedPreferencesImport: SharedPreferences
) {

    val context = LocalContext.current
    var firstName by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    var lastName by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    var email by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxHeight(fraction = 0.1f)
                        .padding(vertical = 20.dp),
                    painter = painterResource(id = R.drawable.logo),

                    contentScale = ContentScale.FillHeight,
                    contentDescription = stringResource(id = R.string.logo_description)
                )
            }
        },
        bottomBar = {
            Button(
                onClick = {
                    if (firstName.text.isNotBlank()
                        && lastName.text.isNotBlank()
                        && email.text.isNotBlank())
                    {
                        sharedPreferencesImport.edit(commit = true) {
                            putString(SharedPrefsKeys.firstName, firstName.text)
                            putString(SharedPrefsKeys.lastName, lastName.text)
                            putString(SharedPrefsKeys.email, email.text)
                        }
                        Log.d("onboard","shared prefs write : ${firstName.text} , ${ lastName.text}, ${email.text}")
                        navController.navigate(Home.route)
                        {
                            popUpTo(0)
                        }

                    }
                    else
                    {
                        Toast.makeText(context,"Registration unsuccessful. Please enter all data.",Toast.LENGTH_SHORT).show()
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.primary_2)
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.register),
                    color = Color.Black
                )
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding.calculateTopPadding())
                .background(Color.White)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.primary_1),
                        RectangleShape
                    )
                    .fillMaxWidth()
                    .padding(vertical = 40.dp),
                text = stringResource(id = R.string.on_board_title),
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.karla, FontWeight.ExtraBold)),
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        RectangleShape
                    )
                    .fillMaxWidth()
                    .padding(vertical = 40.dp, horizontal = 20.dp),
                text = stringResource(id = R.string.on_board_pers_info),
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.karla, FontWeight.ExtraBold)),
                color = Color.Black
            )
            val textFieldColor = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.LightGray,
                placeholderColor = Color.Black,
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = colorResource(id = R.color.primary_1)
            )
            val textInputModifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 10.dp)
                /* .border(
                width = 1.dp,
                color = colorResource(id = R.color.primary_1),
                shape = RoundedCornerShape(8.dp)
            )*/
                .padding(0.dp)
                .fillMaxWidth()
            OutlinedTextField(value = firstName,
                onValueChange = { it -> firstName = it },
                modifier = textInputModifier,
                shape = RoundedCornerShape(12.dp),
                colors = textFieldColor,
                label = { Text(text = stringResource(id = R.string.first_name)) }
            )
            OutlinedTextField(value = lastName,
                onValueChange = { it -> lastName = it },
                modifier = textInputModifier,
                shape = RoundedCornerShape(12.dp),
                colors = textFieldColor,
                label = { Text(text = stringResource(id = R.string.last_name)) }
            )
            OutlinedTextField(value = email,
                onValueChange = { it -> email = it },
                modifier = textInputModifier,
                shape = RoundedCornerShape(12.dp),
                colors = textFieldColor,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text(text = stringResource(id = R.string.email)) }
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    //Onboarding(rememberNavController(),)
}