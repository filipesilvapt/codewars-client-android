package com.codewarsclient.repositories

import androidx.lifecycle.LiveData
import com.codewarsclient.api.ApiService
import com.codewarsclient.database.dao.MemberDao
import com.codewarsclient.database.entities.MemberEntity
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

    fun getLastSearchedMembers(): LiveData<List<MemberEntity>> {
        return dao.getLastSearchedMembers()
    }

    suspend fun searchMemberByName(idOrUsername: String): Boolean {
        val memberSearchApiResponse = safeApiCall(dispatcher) {
            apiService.getMemberByName(idOrUsername)
        }

        return when (memberSearchApiResponse) {
            is RepositoryResultWrapper.NetworkError -> false
            is RepositoryResultWrapper.Failure -> false
            is RepositoryResultWrapper.Success -> {
                // _isToShowError.value = false
                // _memberSearchResult.value = memberSearchApiResponse.value

                dao.insertUser(memberSearchApiResponse.value.toMemberEntity())

                true
            }
        }
    }
}