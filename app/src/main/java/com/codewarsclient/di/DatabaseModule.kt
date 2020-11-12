package com.codewarsclient.di

import android.content.Context
import com.codewarsclient.database.AppDatabase
import com.codewarsclient.database.dao.MemberDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return AppDatabase.getInstance(applicationContext)
    }

    @Provides
    fun provideMemberDao(
        database: AppDatabase
    ): MemberDao {
        return database.memberDao()
    }

}