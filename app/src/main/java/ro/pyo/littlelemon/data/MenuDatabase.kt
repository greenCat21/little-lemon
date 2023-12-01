package ro.pyo.littlelemon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
/*
@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {
     abstract fun userDao(): UserDao
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
*/