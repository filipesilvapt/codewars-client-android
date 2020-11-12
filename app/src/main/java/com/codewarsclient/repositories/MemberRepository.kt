package com.codewarsclient.repositories

import com.codewarsclient.api.ApiClient
import com.codewarsclient.api.ApiService
import com.codewarsclient.models.MemberModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MemberRepository(
    private val apiService: ApiService = ApiClient.apiClient().create(ApiService::class.java),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository() {

    suspend fun getMemberByName(idOrUsername: String): RepositoryResultWrapper<MemberModel> {
        return safeApiCall(dispatcher) {
            apiService.getMemberByName(idOrUsername)
        }
    }
}