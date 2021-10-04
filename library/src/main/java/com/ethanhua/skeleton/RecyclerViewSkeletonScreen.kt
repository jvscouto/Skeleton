package com.ethanhua.skeleton

import androidx.annotation.ArrayRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewSkeletonScreen private constructor(builder: Builder) : SkeletonScreen {
    private val recyclerView: RecyclerView
    private val actualAdapter: RecyclerView.Adapter<*>
    private val skeletonAdapter: SkeletonAdapter
    private val recyclerViewFrozen: Boolean

    init {
        recyclerView = builder.recyclerView
        actualAdapter = builder.actualAdapter
        skeletonAdapter = SkeletonAdapter().apply {
            itemCount = builder.itemCount
            setLayoutReference(builder.itemResID)
            setArrayOfLayoutReferences(builder.itemsResIDArray)
            shimmer(builder.shimmer)
        }
        recyclerViewFrozen = builder.frozen
    }

    override fun show() {
        recyclerView.adapter = skeletonAdapter
        if (!recyclerView.isComputingLayout && recyclerViewFrozen) {
            recyclerView.suppressLayout(true)
        }
    }

    override fun hide() {
        recyclerView.adapter = actualAdapter
    }

    class Builder(val recyclerView: RecyclerView) {

        lateinit var actualAdapter: RecyclerView.Adapter<*>
            private set
        var shimmer = true
            private set
        var itemCount = 10
            private set
        var itemResID = R.layout.shimmer_item_layout
            private set
        var itemsResIDArray: IntArray? = null
            private set
        var frozen = true
            private set

        fun adapter(adapter: RecyclerView.Adapter<*>) = apply {
            this.actualAdapter = adapter
        }

        fun count(itemCount: Int) = apply {
            this.itemCount = itemCount
        }

        fun shimmer(shimmer: Boolean) = apply {
            this.shimmer = shimmer
        }

        fun load(@LayoutRes skeletonLayoutResID: Int) = apply {
            this.itemResID = skeletonLayoutResID
        }

        fun loadArrayOfLayouts(@ArrayRes skeletonLayoutResIDs: IntArray) = apply {
            this.itemsResIDArray = skeletonLayoutResIDs
        }

        fun frozen(frozen: Boolean) = apply {
            this.frozen = frozen
        }

        fun build(): RecyclerViewSkeletonScreen = RecyclerViewSkeletonScreen(this)
    }
}