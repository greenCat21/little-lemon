package ro.pyo.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(
    navController: NavHostController,
    firstName:String,
    lastName:String,
    email:String
) {
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
                    navController.navigate(Onboard.route){
                        popUpTo(0)
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
                    text = stringResource(id = R.string.logout),
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        RectangleShape
                    )
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 40.dp, start = 10.dp, end = 10.dp),
                text = stringResource(id = R.string.on_board_pers_info),
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.karla, FontWeight.ExtraBold)),
                color = Color.Black
            )
            UserFields(stringResource(id = R.string.first_name), userInfo = firstName)
            UserFields(stringResource(id = R.string.last_name), userInfo = lastName)
            UserFields(stringResource(id = R.string.email), userInfo = email)
        }
    }
}

@Composable
fun UserFields(caption: String, userInfo: String) {
    val textInfo = Modifier
        .background(
            color = Color.White,
            RectangleShape
        )
        .fillMaxWidth()
        .padding(top = 10.dp, bottom = 0.dp, start = 10.dp, end = 10.dp)
    val textContent = Modifier
        .padding(top = 5.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
        .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
        .fillMaxWidth()
    Text(
        modifier = textInfo,
        text = caption,
        fontSize = 14.sp,
        textAlign = TextAlign.Start,
        fontFamily = FontFamily(Font(R.font.karla, FontWeight.Light)),
        color = Color.Black
    )
    Text(
        modifier = textContent,
        text = userInfo,
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        fontFamily = FontFamily(Font(R.font.karla, FontWeight.Light)),
        color = Color.Black
    )
}

@Preview
@Composable
fun ProfileScreenPreview() {
    //ProfileScreen(UserData("000", "mail@domain.com", "First", "Last"));
}