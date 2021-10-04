package com.ethanhua.skeleton

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnAttachStateChangeListener
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.facebook.shimmer.ShimmerFrameLayout

class ViewSkeletonScreen private constructor(builder: Builder) : SkeletonScreen {
    private val viewReplacer: ViewReplacer
    private val actualView: View
    private val skeletonResID: Int
    private val shimmer: Boolean

    init {
        actualView = builder.view
        skeletonResID = builder.skeletonLayoutResID
        shimmer = builder.shimmer
        viewReplacer = ViewReplacer(builder.view)
    }

    private fun generateShimmerContainerLayout(parentView: ViewGroup): ShimmerFrameLayout {

        val shimmerLayout = LayoutInflater.from(actualView.context)
            .inflate(R.layout.shimmer_layout, parentView, false) as ShimmerFrameLayout
        val innerView =
            LayoutInflater.from(actualView.context).inflate(skeletonResID, shimmerLayout, false)

        innerView.layoutParams?.let {
            shimmerLayout.layoutParams = it
        }

        shimmerLayout.addView(innerView)
        shimmerLayout.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                shimmerLayout.startShimmer()
            }

            override fun onViewDetachedFromWindow(v: View) {
                shimmerLayout.stopShimmer()
            }
        })
        shimmerLayout.startShimmer()
        return shimmerLayout
    }

    private fun generateSkeletonLoadingView(): View? {
        val viewParent = actualView.parent ?: return null
        val parentView = viewParent as ViewGroup
        return if (shimmer) {
            generateShimmerContainerLayout(parentView)
        } else LayoutInflater.from(actualView.context).inflate(skeletonResID, parentView, false)
    }

    override fun show() {
        val skeletonLoadingView = generateSkeletonLoadingView()
        if (skeletonLoadingView != null) {
            viewReplacer.replace(skeletonLoadingView)
        }
    }

    override fun hide() {
        if (viewReplacer.targetView is ShimmerFrameLayout) {
            (viewReplacer.targetView as ShimmerFrameLayout?)!!.stopShimmer()
        }
        viewReplacer.restore()
    }

    class Builder(val view: View) {
        var skeletonLayoutResID = 0
            private set

        var shimmer = true
            private set

        fun load(@LayoutRes skeletonLayoutResID: Int) = apply {
            this.skeletonLayoutResID = skeletonLayoutResID
        }

        fun shimmer(shimmer: Boolean) = apply {
            this.shimmer = shimmer
        }

        fun build(): ViewSkeletonScreen = ViewSkeletonScreen(this)
    }
}