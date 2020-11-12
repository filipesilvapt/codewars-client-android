package com.codewarsclient.ui.members

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.R
import com.codewarsclient.database.entities.MemberEntity

class MembersListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var membersList: MutableList<MemberEntity> = ArrayList()

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

    fun updateItems(receivedMembersList: List<MemberEntity>) {
        membersList.clear()
        membersList.addAll(receivedMembersList)
        notifyDataSetChanged()
    }

}