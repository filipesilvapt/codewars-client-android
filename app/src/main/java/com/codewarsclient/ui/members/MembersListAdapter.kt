package com.codewarsclient.ui.members

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.R
import com.codewarsclient.models.MemberModel

class MembersListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var membersList: MutableList<MemberModel> = ArrayList()

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

    fun addItem(memberToAdd: MemberModel) {
        membersList.add(memberToAdd)

        notifyDataSetChanged()
    }

    fun updateItems(receivedMembersList: List<MemberModel>) {

    }

}