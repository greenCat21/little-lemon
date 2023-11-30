package ro.pyo.littlelemon.composables

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.pyo.littlelemon.R

@Composable
fun Onboarding() {
    val context = LocalContext.current
    var firstName by remember() {
        mutableStateOf(TextFieldValue(""))
    }
    var lastName by remember() {
        mutableStateOf(TextFieldValue(""))
    }
    var email by remember() {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        modifier = Modifier
            .padding(0.dp)
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight(fraction = 0.1f)
                .padding(vertical = 20.dp),
            painter = painterResource(id = R.drawable.logo),
            contentScale = ContentScale.FillHeight,
            contentDescription = stringResource(id = R.string.logo_description)
        )
        Text(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.primary_1),
                    RectangleShape
                )
                .fillMaxWidth()
                .padding(vertical = 40.dp),
            text = stringResource(id = R.string.on_board_title),
            fontSize = 24.sp,
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
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            fontFamily = FontFamily(Font(R.font.karla, FontWeight.ExtraBold)),
            color = Color.Black
        )
        TextField(value = firstName,
            onValueChange = { it -> firstName = it },
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 40.dp, bottom = 10.dp)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.primary_1),
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.White
            ),
            label = { Text(text = stringResource(id = R.string.first_name)) }
        )
        TextField(value = lastName,
            onValueChange = { it -> lastName = it },
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 10.dp)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.primary_1),
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.White
            ),
            label = { Text(text = stringResource(id = R.string.last_name)) }
        )
        TextField(value = email,
            onValueChange = { it -> email = it },
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 10.dp)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.primary_1),
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text(text = stringResource(id = R.string.email)) }
        )
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            Button(
                onClick = { },
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
    }

}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding()
}