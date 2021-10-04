package com.ethanhua.skeleton

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ViewReplacer(private val sourceView: View) {

    var targetView: View? = null
        private set
    private var targetViewResID = DEFAULT_TARGET_VIEW_ID
    private var currentView: View?
        private set
    private var sourceParentView: ViewGroup? = null
    private val sourceViewLayoutParams: ViewGroup.LayoutParams = sourceView.layoutParams
    private var sourceViewIndexInParent = 0
    private val sourceViewId: Int

    init {
        currentView = sourceView
        sourceViewId = sourceView.id
    }

    fun replace(targetViewResID: Int) {
        if (this.targetViewResID == targetViewResID) {
            return
        }
        if (init()) {
            this.targetViewResID = targetViewResID
            replace(
                LayoutInflater.from(sourceView.context)
                    .inflate(this.targetViewResID, sourceParentView, false)
            )
        }
    }

    fun replace(targetView: View) {
        if (currentView === targetView) {
            return
        }
        if (targetView.parent != null) {
            (targetView.parent as ViewGroup).removeView(targetView)
        }
        if (init()) {
            this.targetView = targetView
            sourceParentView?.removeView(currentView)
            targetView.id = sourceViewId
            sourceParentView?.addView(
                this.targetView,
                sourceViewIndexInParent,
                sourceViewLayoutParams
            )
            currentView = this.targetView
        }
    }

    fun restore() {
        sourceParentView?.removeView(currentView)
        sourceParentView?.addView(sourceView, sourceViewIndexInParent, sourceViewLayoutParams)
        currentView = sourceView
        targetView = null
        targetViewResID = DEFAULT_TARGET_VIEW_ID
    }

    private fun init(): Boolean {
        sourceParentView = sourceView.parent as ViewGroup
        if (sourceParentView == null) {
            return false
        }
        val count = sourceParentView?.childCount ?: 0
        for (index in 0 until count) {
            if (sourceView === sourceParentView?.getChildAt(index)) {
                sourceViewIndexInParent = index
                break
            }
        }
        return true
    }

    companion object {
        const val DEFAULT_TARGET_VIEW_ID = -1
    }
}