package com.codewarsclient.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codewarsclient.database.dao.MemberDao
import com.codewarsclient.database.entities.MemberEntity

@Database(
    entities = arrayOf(
        MemberEntity::class
    ), version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao

    companion object {
        fun getInstance(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "codewars-database"
            ).build()
        }
    }
}