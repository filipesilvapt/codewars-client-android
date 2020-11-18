package com.codewarsclient.di

import com.codewarsclient.api.ApiClient
import com.codewarsclient.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideApiClient(): Retrofit {
        return ApiClient().getInstance()
    }

    @Provides
    fun provideApiService(client: Retrofit): ApiService {
        return client.create(ApiService::class.java)
    }

}