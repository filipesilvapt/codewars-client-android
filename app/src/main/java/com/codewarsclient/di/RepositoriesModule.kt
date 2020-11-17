package com.codewarsclient.di

import com.codewarsclient.api.ApiService
import com.codewarsclient.database.dao.AuthoredChallengeDao
import com.codewarsclient.database.dao.ChallengeDetailsDao
import com.codewarsclient.database.dao.CompletedChallengeDao
import com.codewarsclient.database.dao.MemberDao
import com.codewarsclient.repositories.DefaultAuthoredChallengesRepository
import com.codewarsclient.repositories.DefaultChallengeDetailsRepository
import com.codewarsclient.repositories.DefaultCompletedChallengesRepository
import com.codewarsclient.repositories.DefaultMemberRepository
import com.codewarsclient.repositories.interfaces.AuthoredChallengesRepository
import com.codewarsclient.repositories.interfaces.ChallengeDetailsRepository
import com.codewarsclient.repositories.interfaces.CompletedChallengesRepository
import com.codewarsclient.repositories.interfaces.MemberRepository
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
        return DefaultMemberRepository(memberDao, apiService, Dispatchers.IO)
    }

    @Provides
    fun provideCompletedChallengesRepository(
        completedChallengeDao: CompletedChallengeDao,
        apiService: ApiService
    ): CompletedChallengesRepository {
        return DefaultCompletedChallengesRepository(
            completedChallengeDao,
            apiService,
            Dispatchers.IO
        )
    }

    @Provides
    fun provideAuthoredChallengesRepository(
        authoredChallengesDao: AuthoredChallengeDao,
        apiService: ApiService
    ): AuthoredChallengesRepository {
        return DefaultAuthoredChallengesRepository(
            authoredChallengesDao,
            apiService,
            Dispatchers.IO
        )
    }

    @Provides
    fun provideChallengeDetailsRepository(
        challengeDetailsDao: ChallengeDetailsDao,
        apiService: ApiService
    ): ChallengeDetailsRepository {
        return DefaultChallengeDetailsRepository(challengeDetailsDao, apiService, Dispatchers.IO)
    }

}