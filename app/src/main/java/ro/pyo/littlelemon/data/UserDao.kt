package ro.pyo.littlelemon.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao
{
    @Insert
    fun saveUser(user:UserData)

    @Delete
    fun deleteUser(user:UserData)

    @Query("SELECT * FROM UserData")
    fun getUser():LiveData<UserData>
}
