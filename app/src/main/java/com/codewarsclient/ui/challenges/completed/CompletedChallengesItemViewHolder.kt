package com.codewarsclient.ui.challenges.completed

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.database.entities.CompletedChallengeEntity
import com.codewarsclient.utils.DateTimeUtils
import com.codewarsclient.utils.Utils
import kotlinx.android.synthetic.main.layout_row_item_completed_challenge.view.*

class CompletedChallengesItemViewHolder(private val challengeItemView: View) :
    RecyclerView.ViewHolder(challengeItemView) {

    fun bind(item: CompletedChallengeEntity) {
        // Set the challenge name
        challengeItemView.text_title.text = item.challengeName

        // Set the completed at date
        challengeItemView.text_completed_date.text =
            DateTimeUtils.convertApiDateTimeToDisplay(item.completedAt)

        // Set the languages list
        challengeItemView.text_languages.text =
            Utils.capitalizeAndJoinLanguagesList(item.languagesList)
    }

}