package com.codewarsclient.api

import com.codewarsclient.models.MemberModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("v1/users/{id_or_username}")
    suspend fun getMemberByName(@Path(ApiConstants.PATH_ID_OR_USERNAME) idOrUsername: String): MemberModel
}