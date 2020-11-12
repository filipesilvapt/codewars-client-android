package com.codewarsclient.ui.members

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.models.MemberModel
import kotlinx.android.synthetic.main.layout_row_item_member.view.*

class MembersItemViewHolder(private val memberItemView: View) :
    RecyclerView.ViewHolder(memberItemView) {

    fun bind(item: MemberModel) {
        // Set the rank
        memberItemView.text_rank.text = item.ranksList.overallRank.name

        // Set the member name
        memberItemView.text_name.text = item.username

        // Set the language name with highest score
        val highestRankedLanguage = item.ranksList.getRanksByLanguage().maxByOrNull { it.score }
        highestRankedLanguage?.let {
            memberItemView.text_language.text = "${it.language} (${it.score})"
        }
    }

}