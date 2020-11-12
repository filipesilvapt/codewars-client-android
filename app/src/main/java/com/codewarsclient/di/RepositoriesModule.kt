package com.codewarsclient.di

import com.codewarsclient.api.ApiService
import com.codewarsclient.database.dao.MemberDao
import com.codewarsclient.repositories.MemberRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ApplicationComponent::class)
object RepositoriesModule {

    @Provides
    fun provideMemberRepository(
        memberDao: MemberDao,
        apiService: ApiService
    ): MemberRepository {
        return MemberRepository(memberDao, apiService, Dispatchers.IO)
    }

}