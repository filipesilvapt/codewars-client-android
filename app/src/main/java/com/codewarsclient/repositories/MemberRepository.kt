package com.codewarsclient.repositories

import com.codewarsclient.api.ApiClient
import com.codewarsclient.api.ApiService

class MemberRepository {
    private var apiService: ApiService = ApiClient.apiClient().create(ApiService::class.java)

    suspend fun getMemberByName(idOrUsername: String) = apiService.getMemberByName(idOrUsername)
}