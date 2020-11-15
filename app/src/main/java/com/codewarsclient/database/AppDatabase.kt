package com.codewarsclient.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codewarsclient.database.dao.CompletedChallengeDao
import com.codewarsclient.database.dao.MemberDao
import com.codewarsclient.database.entities.CompletedChallengesEntity
import com.codewarsclient.database.entities.MemberEntity

@Database(
    entities = [
        MemberEntity::class,
        CompletedChallengesEntity::class
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
    abstract fun completedChallengeDao(): CompletedChallengeDao

    companion object {
        fun getInstance(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "codewars-database"
            ).build()
        }
    }
}