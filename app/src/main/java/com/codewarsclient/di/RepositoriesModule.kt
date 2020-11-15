package com.codewarsclient.di

import com.codewarsclient.api.ApiService
import com.codewarsclient.database.dao.MemberDao
import com.codewarsclient.repositories.AuthoredChallengesRepository
import com.codewarsclient.repositories.CompletedChallengesRepository
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

    @Provides
    fun provideCompletedChallengesRepository(
        //completedChallengesDao: CompletedChallengesDao,
        apiService: ApiService
    ): CompletedChallengesRepository {
        return CompletedChallengesRepository(apiService, Dispatchers.IO)
    }

    @Provides
    fun provideAuthoredChallengesRepository(
        //authoredChallengesDao: AuthoredChallengesDao,
        apiService: ApiService
    ): AuthoredChallengesRepository {
        return AuthoredChallengesRepository(apiService, Dispatchers.IO)
    }

}