package com.ethanhua.skeleton

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ethanhua.skeleton.ShimmerViewHolder
import com.facebook.shimmer.ShimmerFrameLayout

class SkeletonAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemCount = 0
    private var layoutReference = 0
    private var layoutArrayReferences: IntArray? = null
    private var shimmer = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (doesArrayOfLayoutsExist()) {
            layoutReference = viewType
        }
        return if (shimmer) {
            ShimmerViewHolder(inflater, parent, layoutReference)
        } else object : RecyclerView.ViewHolder(inflater.inflate(layoutReference, parent, false)) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (shimmer) {
            val layout = holder.itemView as ShimmerFrameLayout
            layout.startShimmer()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (doesArrayOfLayoutsExist()) {
            getCorrectLayoutItem(position)
        } else super.getItemViewType(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return itemCount
    }

    fun setLayoutReference(layoutReference: Int) {
        this.layoutReference = layoutReference
    }

    fun setArrayOfLayoutReferences(layoutReferences: IntArray?) {
        this.layoutArrayReferences = layoutReferences
    }

    fun setItemCount(itemCount: Int) {
        this.itemCount = itemCount
    }

    fun shimmer(shimmer: Boolean) {
        this.shimmer = shimmer
    }

    fun getCorrectLayoutItem(position: Int): Int {
        return if (doesArrayOfLayoutsExist()) {
            layoutArrayReferences?.let { references->
                references[position % references.size]
            } ?: 0
        } else layoutReference
    }

    private fun doesArrayOfLayoutsExist(): Boolean {
        return layoutArrayReferences != null && layoutArrayReferences?.size != 0
    }
}