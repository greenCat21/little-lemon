package ro.pyo.littlelemon

import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName


@Serializable
class MenuItemNetwork(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("price") var price: String,
    @SerializedName("image") var image: String,
    @SerializedName("category") var category: String
) {
    fun toMenuItemRoom() = Menu(
        id,
        title,
        description,
        price,
        image,
        category
    )

}

@Serializable
data class MenuNetworkData(
    @SerializedName("menu") var menu: ArrayList<MenuItemNetwork> = arrayListOf()
)

class JsonUrl() {
    companion object {
        val url =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    }
}

//sample json:

/*
{
  "menu": [
    {
      "id": 1,
      "title": "Greek Salad",
      "description": "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
      "price": "10",
      "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
      "category": "starters"
    },
    {
      "id": 2,
      "title": "Lemon Desert",
      "description": "Traditional homemade Italian Lemon Ricotta Cake.",
      "price": "10",
      "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/lemonDessert%202.jpg?raw=true",
      "category": "desserts"
    },
    {
      "id": 3,
      "title": "Grilled Fish",
      "description": "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.",
      "price": "10",
      "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/grilledFish.jpg?raw=true",
      "category": "mains"
    },
    {
      "id": 4,
      "title": "Pasta",
      "description": "Penne with fried aubergines, cherry tomatoes, tomato sauce, fresh chili, garlic, basil & salted ricotta cheese.",
      "price": "10",
      "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
      "category": "mains"
    },
    {
      "id": 5,
      "title": "Bruschetta",
      "description": "Oven-baked bruschetta stuffed with tomatoes and herbs.",
      "price": "10",
      "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/bruschetta.jpg?raw=true",
      "category": "starters"
    }
  ]
}

 */