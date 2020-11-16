package com.codewarsclient.ui.challenges.completed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.R
import com.codewarsclient.database.entities.CompletedChallengeEntity
import com.codewarsclient.ui.challenges.ChallengesListAdapter

class CompletedChallengesListAdapter : ChallengesListAdapter<CompletedChallengeEntity>() {

    private var challengesList: MutableList<CompletedChallengeEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CompletedChallengesItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_row_item_completed_challenge, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return challengesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CompletedChallengesItemViewHolder).bind(challengesList[position])

        // Set the click listener for each item
        holder.itemView.setOnClickListener {
            selectedChallenge.value = challengesList[position]
            // Reset value in live data object
            selectedChallenge.value = null
        }
    }

    /**
     * Updates the challenges list with the received content
     */
    fun appendToChallengesList(receivedChallengesList: List<CompletedChallengeEntity>) {
        challengesList.addAll(receivedChallengesList)
        notifyDataSetChanged()
    }

    /**
     * Returns the completed date time of the last challenge of the list
     */
    fun getLastChallengeCompletedAt(): String? {
        return if (challengesList.isNullOrEmpty()) {
            null
        } else {
            challengesList.last().completedAt
        }
    }

}