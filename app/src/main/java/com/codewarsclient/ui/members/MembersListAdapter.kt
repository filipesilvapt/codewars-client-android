package com.codewarsclient.ui.members

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.R
import com.codewarsclient.database.entities.MemberEntity

class MembersListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var membersList: MutableList<MemberEntity> = ArrayList()

    private var currentSortOption: MembersSortOption = MembersSortOption.SEARCH_DESC

    val selectedMember = MutableLiveData<MemberEntity?>()

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
        (holder as MembersItemViewHolder).bind(membersList[position])

        // Set the click listener for each item
        holder.itemView.setOnClickListener {
            selectedMember.value = membersList[position]
            // Reset value in live data object
            selectedMember.value = null
        }
    }

    /**
     * Updates the members list with the received content, making sure its sorted according to the
     * current sorting option
     */
    fun updateMembersList(receivedMembersList: List<MemberEntity>) {
        membersList.clear()
        membersList.addAll(receivedMembersList)
        sortMembersList(currentSortOption)
        notifyDataSetChanged()
    }

    /**
     * Sorts the members list according the given sorting option
     */
    fun sortMembersList(sortOption: MembersSortOption) {
        currentSortOption = sortOption

        when (currentSortOption) {
            MembersSortOption.SEARCH_DESC -> membersList.sortByDescending { it.timeOfSearch }
            MembersSortOption.RANK_DESC -> membersList.sortByDescending { it.rank }
        }

        notifyDataSetChanged()
    }

}