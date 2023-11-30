package ro.pyo.littlelemon.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData(
    @PrimaryKey val id:String,
    val email: String,
    val firstName: String,
    val lastName: String
)
