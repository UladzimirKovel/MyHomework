package com.example.myhomework.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myhomework.data.model.UserEntity

@Dao
interface UserDao {
    //CRUD (Create(Insert), Read(Query), Update, Delete)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(user: UserEntity)

    @Query("SELECT * FROM users WHERE _email = :email AND _password = :password " )
    fun getUser(email:String, password:String): UserEntity?

    @Query("SELECT * FROM users WHERE _email = :email")
    fun getUserByEmail(email: String): UserEntity?
}