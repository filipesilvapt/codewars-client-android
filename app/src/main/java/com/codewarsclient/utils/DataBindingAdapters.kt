package com.codewarsclient.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("bind:listAdapter")
fun RecyclerView.setListAdapter(adapter: RecyclerView.Adapter<*>) {
    this.setHasFixedSize(true)
    this.adapter = adapter
}

@BindingAdapter("bind:errorTextResId")
fun TextInputLayout.setErrorTextResId(resource: Int?) {
    resource?.let {
        this.error = this.context.getString(it)
    } ?: run {
        this.error = null
    }
}

@BindingAdapter("bind:textResourceId")
fun TextView.setTextResourceId(resource: Int?) {
    resource?.let { this.setText(it) }
}