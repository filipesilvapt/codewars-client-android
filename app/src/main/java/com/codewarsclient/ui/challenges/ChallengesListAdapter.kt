package com.codewarsclient.ui.challenges

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

abstract class ChallengesListAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val selectedChallenge = MutableLiveData<T?>()
}