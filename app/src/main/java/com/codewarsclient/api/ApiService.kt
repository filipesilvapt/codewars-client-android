package com.codewarsclient.api

import com.codewarsclient.models.AuthoredChallengesModel
import com.codewarsclient.models.CompletedChallengesModel
import com.codewarsclient.models.MemberModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v1/users/{id_or_username}")
    suspend fun getMemberByName(@Path(ApiConstants.PATH_ID_OR_USERNAME) idOrUsername: String): MemberModel

    @GET("v1/users/{id_or_username}/code-challenges/completed")
    suspend fun getMemberCompletedChallenges(
        @Path(ApiConstants.PATH_ID_OR_USERNAME) idOrUsername: String,
        @Query(ApiConstants.QUERY_PAGE_NUMBER) pageNumber: Int
    ): CompletedChallengesModel

    @GET("v1/users/{id_or_username}/code-challenges/authored")
    suspend fun getMemberAuthoredChallenges(
        @Path(ApiConstants.PATH_ID_OR_USERNAME) idOrUsername: String
    ): AuthoredChallengesModel

}