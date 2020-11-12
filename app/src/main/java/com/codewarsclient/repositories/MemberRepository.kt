package com.codewarsclient.repositories

import com.codewarsclient.api.ApiService
import com.codewarsclient.database.dao.MemberDao
import com.codewarsclient.models.MemberModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemberRepository @Inject constructor(
    private val dao: MemberDao,
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository() {

    suspend fun getMemberByName(idOrUsername: String): RepositoryResultWrapper<MemberModel> {
        return safeApiCall(dispatcher) {
            apiService.getMemberByName(idOrUsername)
        }
    }
}