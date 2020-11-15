package com.codewarsclient.ui.challenges.completed

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.api.models.CompletedChallengeDetailsModel
import kotlinx.android.synthetic.main.layout_row_item_completed_challenge.view.*

class CompletedChallengesItemViewHolder(private val challengeItemView: View) :
    RecyclerView.ViewHolder(challengeItemView) {

    fun bind(item: CompletedChallengeDetailsModel) {
        // Set the challenge name
        challengeItemView.text_title.text = item.challengeName

        // Set the completed at date
        challengeItemView.text_completed_date.text = item.completedAt

        // Set the languages list
        challengeItemView.text_languages.text = item.languagesList.joinToString { it }
    }

}