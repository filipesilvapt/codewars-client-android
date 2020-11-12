package com.codewarsclient.ui.members

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.R
import com.codewarsclient.database.entities.MemberEntity

class MembersListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var membersList: MutableList<MemberEntity> = ArrayList()

    private var currentSortOption: MembersSortOption = MembersSortOption.SEARCH_DESC

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MembersItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_row_item_member, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return membersList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as MembersItemViewHolder).bind(membersList[position])
    }

    fun updateMembersList(receivedMembersList: List<MemberEntity>) {
        membersList.clear()
        membersList.addAll(receivedMembersList)
        sortMembersList(currentSortOption)
        notifyDataSetChanged()
    }

    fun sortMembersList(sortOption: MembersSortOption) {
        currentSortOption = sortOption

        when (currentSortOption) {
            MembersSortOption.SEARCH_DESC -> membersList.sortByDescending { it.timeOfSearch }
            MembersSortOption.RANK_DESC -> membersList.sortByDescending { it.rank }
        }

        notifyDataSetChanged()
    }

}