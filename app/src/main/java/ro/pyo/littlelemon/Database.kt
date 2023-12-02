package ro.pyo.littlelemon

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "menu")
data class Menu(
    @PrimaryKey//(autoGenerate = true)
    var id: Int,
    var title: String,
    var description: String,
    var price: String,
    var image: String,
    var category: String,
)

fun MenuNetworkToRoom(menuNetwork: MenuItemNetwork): Menu {
    return Menu(
        id = menuNetwork.id,
        title = menuNetwork.title,
        description = menuNetwork.description,
        price = menuNetwork.price,
        image = menuNetwork.image,
        category = menuNetwork.category
    )
}

@Dao
interface MenuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(menu: Menu)

    @Query("SELECT * FROM menu")
    fun getMenus(): LiveData<List<Menu>>

}

@Database(entities = [Menu::class], version = 1, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao

    companion object {
        @Volatile
        private var INSTANCE: MenuDatabase? = null

        fun getDatabase(context: Context): MenuDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MenuDatabase::class.java,
                    "userDB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}