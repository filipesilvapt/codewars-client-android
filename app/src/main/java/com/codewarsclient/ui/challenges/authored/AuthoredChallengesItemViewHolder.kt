package com.codewarsclient.ui.challenges.authored

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.database.entities.AuthoredChallengeEntity
import com.codewarsclient.utils.Utils
import kotlinx.android.synthetic.main.layout_row_item_authored_challenge.view.*

class AuthoredChallengesItemViewHolder(private val challengeItemView: View) :
    RecyclerView.ViewHolder(challengeItemView) {

    fun bind(item: AuthoredChallengeEntity) {
        // Set the challenge name
        challengeItemView.text_title.text = item.challengeName

        // Set the rank name
        challengeItemView.text_rank.text = item.rankName

        // Set the languages list
        challengeItemView.text_languages.text =
            Utils.capitalizeAndJoinLanguagesList(item.languagesList)

        // Set the tags list
        challengeItemView.text_tags.text = item.tagsList.joinToString { it }
    }

}