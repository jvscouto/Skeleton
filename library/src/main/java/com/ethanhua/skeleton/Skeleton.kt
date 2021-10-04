package com.ethanhua.skeleton

import android.view.View
import androidx.recyclerview.widget.RecyclerView

object Skeleton {

    @JvmStatic
    fun bind(recyclerView: RecyclerView): RecyclerViewSkeletonScreen.Builder {
        return RecyclerViewSkeletonScreen.Builder(recyclerView)
    }

    @JvmStatic
    fun bind(view: View): ViewSkeletonScreen.Builder {
        return ViewSkeletonScreen.Builder(view)
    }
}