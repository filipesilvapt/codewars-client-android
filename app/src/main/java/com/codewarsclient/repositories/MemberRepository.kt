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

    /**
     * Returns an observable object for a list of the last searched members.
     */
    fun getLastSearchedMembers(): LiveData<List<MemberEntity>> {
        return dao.getLastSearchedMembers()
    }

    /**
     * Searches a member by a given username giving priority to the api call and falling back to
     * a database entry, which in case will update its time of search.
     *
     * Returns true if it was able to find the member, false otherwise.
     */
    suspend fun searchMemberAndSave(username: String): Boolean {
        val memberSearchApiResponse = safeApiCall(dispatcher) {
            apiService.getMemberByName(username)
        }

        return when (memberSearchApiResponse) {
            is RepositoryResultWrapper.NetworkError -> updateMemberLocalSearchIfExists(username)
            is RepositoryResultWrapper.Failure -> updateMemberLocalSearchIfExists(username)
            is RepositoryResultWrapper.Success -> {
                dao.insertMember(memberSearchApiResponse.value.toMemberEntity())
                true
            }
        }
    }

    /**
     * Searches for a member using its username in the local database and updates its time of search.
     *
     * Returns true if it was able to find the member, false otherwise.
     */
    private suspend fun updateMemberLocalSearchIfExists(username: String): Boolean {
        val memberFound = dao.getMember(username)
        return memberFound?.let {
            it.updateTimeOfSearchWithNow()
            dao.updateMember(it)
            true
        } ?: false
    }
}