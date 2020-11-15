package com.codewarsclient.ui.challenges.authored

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.models.AuthoredChallengeDetailsModel
import kotlinx.android.synthetic.main.layout_row_item_authored_challenge.view.*

class AuthoredChallengesItemViewHolder(private val challengeItemView: View) :
    RecyclerView.ViewHolder(challengeItemView) {

    fun bind(item: AuthoredChallengeDetailsModel) {
        // Set the challenge name
        challengeItemView.text_title.text = item.challengeName

        // Set the rank name
        challengeItemView.text_rank.text = item.rank

        // Set the languages list
        challengeItemView.text_languages.text = item.languagesList.joinToString { it }

        // Set the tags list
        challengeItemView.text_tags.text = item.tagsList.joinToString { it }
    }

}