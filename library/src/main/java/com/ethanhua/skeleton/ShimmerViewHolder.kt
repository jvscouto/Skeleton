package com.ethanhua.skeleton

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ShimmerViewHolder(inflater: LayoutInflater, parent: ViewGroup?, innerViewResId: Int) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.shimmer_layout, parent, false)) {

    init {
        val layout = itemView as ViewGroup
        val view = inflater.inflate(innerViewResId, layout, false)
        view.layoutParams?.let {
            layout.layoutParams = it
        }
        layout.addView(view)
    }
}