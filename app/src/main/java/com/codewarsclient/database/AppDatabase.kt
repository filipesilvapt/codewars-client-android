package com.codewarsclient.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codewarsclient.database.dao.AuthoredChallengeDao
import com.codewarsclient.database.dao.CompletedChallengeDao
import com.codewarsclient.database.dao.MemberDao
import com.codewarsclient.database.entities.AuthoredChallengeEntity
import com.codewarsclient.database.entities.CompletedChallengeEntity
import com.codewarsclient.database.entities.MemberEntity

@Database(
    entities = [
        MemberEntity::class,
        CompletedChallengeEntity::class,
        AuthoredChallengeEntity::class
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
    abstract fun completedChallengeDao(): CompletedChallengeDao
    abstract fun authoredChallengeDao(): AuthoredChallengeDao

    companion object {
        fun getInstance(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "codewars-database"
            ).build()
        }
    }
}