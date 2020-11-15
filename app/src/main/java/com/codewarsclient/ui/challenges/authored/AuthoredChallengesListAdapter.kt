package com.codewarsclient.ui.challenges.authored

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.R
import com.codewarsclient.api.models.AuthoredChallengeDetailsModel

class AuthoredChallengesListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var challengesList: MutableList<AuthoredChallengeDetailsModel> = ArrayList()

    val selectedChallenge = MutableLiveData<AuthoredChallengeDetailsModel?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AuthoredChallengesItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_row_item_authored_challenge, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return challengesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AuthoredChallengesItemViewHolder).bind(challengesList[position])

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
    fun updateChallengesList(receivedChallengesList: List<AuthoredChallengeDetailsModel>) {
        challengesList.clear()
        challengesList.addAll(receivedChallengesList)
        notifyDataSetChanged()
    }

}