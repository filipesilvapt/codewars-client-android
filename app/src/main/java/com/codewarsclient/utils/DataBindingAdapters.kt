package com.codewarsclient.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("bind:listAdapter")
fun RecyclerView.setListAdapter(adapter: RecyclerView.Adapter<*>) {
    this.setHasFixedSize(true)
    this.adapter = adapter
}