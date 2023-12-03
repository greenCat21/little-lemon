package ro.pyo.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
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
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import java.util.Locale

@Composable
fun TopAppBar(navController: NavHostController) {
    Box(
        //horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight(fraction = 0.1f)
                .padding(vertical = 20.dp)
                .align(alignment = Alignment.Center),
            painter = painterResource(id = R.drawable.logo),

            contentScale = ContentScale.FillHeight,
            contentDescription = stringResource(id = R.string.logo_description)
        )
        Image(
            modifier = Modifier
                .fillMaxHeight(fraction = 0.1f)
                .padding(vertical = 20.dp, horizontal = 10.dp)
                .align(alignment = Alignment.CenterEnd)
                .clickable { navController.navigate(Profile.route) },
            painter = painterResource(id = R.drawable.profile),
            contentScale = ContentScale.FillHeight,
            contentDescription = stringResource(id = R.string.logo_description)
        )
    }
}

@Composable
fun HeroSection() {
    Column(Modifier.background(colorResource(id = R.color.primary_1))) {
        Row()
        {
            Text(
                modifier = Modifier
                    //.padding(top = 5.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
                    //.border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                    .padding(top = 10.dp, bottom = 0.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.restaurant_name),
                fontSize = 46.sp,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.markazi_text, FontWeight.ExtraBold)),
                color = colorResource(id = R.color.primary_2)
            )
        }
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.65f)
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 0.dp, bottom = 1.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(Font(R.font.karla, FontWeight.Bold)),
                    color = Color.White,
                    text = stringResource(id = R.string.city),
                )
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.description),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(Font(R.font.karla, FontWeight.Normal)),
                    color = Color.White
                )
            }
            Image(
                modifier = Modifier
                    //.fillMaxHeight(fraction = 0.1f)
                    .height(150.dp)
                    .padding(start = 0.dp, end = 10.dp, top = 0.dp, bottom = 10.dp)
                    //.align(alignment = Alignment)
                    .clip(RoundedCornerShape(12.dp)),
                painter = painterResource(id = R.drawable.hero_image),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(id = R.string.logo_description)
            )
        }

    }
}

@Composable
fun MenuCategory(category: String) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
        shape = RoundedCornerShape(40),
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = category
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuDishs(menus: List<Menu>) {
    LazyColumn {
        items(
            items = menus,
            itemContent = { menu ->
                Card(
                    contentColor = Color.White, backgroundColor = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                    // .background(Color.White)
                    , elevation = 5.dp, shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .background(Color.White),
                    ) {
                        Column(modifier = Modifier.fillMaxWidth(0.6f)) {
                            Text(
                                modifier = Modifier
                                    .padding(top = 0.dp, bottom = 1.dp, start = 0.dp, end = 10.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                text = menu.title,
                            )
                            Text(
                                text = menu.description,
                                color = Color.Gray,
                                modifier = Modifier
                                    .padding(top = 5.dp, bottom = 5.dp)
                                    .fillMaxWidth()
                            )
                            Text(
                                text = "$ ${menu.price.toDouble()}",
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        GlideImage(
                            model = menu.image,
                            contentDescription = "menu image",
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .fillMaxWidth()
                                .align(CenterVertically),
                            loading = placeholder(ColorPainter(Color.Red))
                        )

                    }
                }
            }
        )
    }

}

@Composable
fun HomeScreen(navController: NavHostController, menus: List<Menu>, categories: List<String>) {

    var searchPhrase by rememberSaveable() { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.White,
        topBar = {
            TopAppBar(navController)
        }
    )
    { paddingValues ->
        val padding = paddingValues
        Column() {
            HeroSection()
            val textFieldColor = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.LightGray,
                placeholderColor = Color.Black,
                leadingIconColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
            val textInputModifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 0.dp, bottom = 15.dp)
                .padding(0.dp)
                .fillMaxWidth()
            Box(Modifier.background(colorResource(id = R.color.primary_1))) {
                TextField(value = searchPhrase,
                    onValueChange = { searchPhrase = it },
                    modifier = textInputModifier,
                    shape = RoundedCornerShape(12.dp),
                    colors = textFieldColor,
                    label = { Text(text = stringResource(id = R.string.search)) },
                    leadingIcon = {
                        Icon(
                            Icons.Rounded.Search,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    }
                )
            }
            LazyRow {
                items(categories) { category ->
                    MenuCategory(category)
                }
            }
            Divider(
                modifier = Modifier.padding(8.dp),
                color = Color.Gray,
                thickness = 1.dp
            )
            if (searchPhrase.isNotEmpty()) {
                MenuDishs(menus.filter {
                    it.title.toLowerCase(Locale.ROOT)
                        .contains(searchPhrase.lowercase())
                            ||
                            it.description.toLowerCase(Locale.ROOT)
                                .contains(searchPhrase.lowercase())
                })
            } else {
                MenuDishs(menus)
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        listOf(Menu(1, "titlu", description = "descr", "10", "image", "cat1")),
        listOf("cat1", "cat2")
    )
}