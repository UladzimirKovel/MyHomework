package com.example.myhomework.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myhomework.data.model.NoteEntity
import com.example.myhomework.data.model.UserEntity

@Database(entities = [UserEntity::class, NoteEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun noteDao(): NoteDao

    companion object {

//        @Volatile // Значение INSTANCE может изменяться несколькими потоками (гарант,что изменения,сделанные одним потоком, будут видны другим)
//        private var INSTANCE: UserDatabase? = null

//            return INSTANCE
//                ?: synchronized(this) { // Блокирует доступ к коду внутри фигурных скобок для других потоков, пока текущий поток создает экземпляр базы данных.
//                    val instance = Room.databaseBuilder(
//                        context,
//                        UserDatabase::class.java,
//                        "MyDatabase"
//                    ).build()
//                    INSTANCE = instance
//                    instance
//                }

        fun getDatabase(context: Context): UserDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "MyDatabase"
            ).build()
        }
    }
}


