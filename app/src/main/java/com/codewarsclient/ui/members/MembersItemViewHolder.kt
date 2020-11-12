package com.codewarsclient.ui.members

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.database.entities.MemberEntity
import kotlinx.android.synthetic.main.layout_row_item_member.view.*

class MembersItemViewHolder(private val memberItemView: View) :
    RecyclerView.ViewHolder(memberItemView) {

    fun bind(item: MemberEntity) {
        // Set the rank
        memberItemView.text_rank.text = item.rankName

        // Set the member name
        memberItemView.text_name.text = item.username

        // Set the language name with highest score
        memberItemView.text_language.text = "${item.bestLanguage} (${item.bestLanguagePoints})"
    }

}