package com.v.nevi.p.sv.android.myapplication

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class PushUpsChangeItemAnimator: DefaultItemAnimator() {
    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder,
        newHolder: RecyclerView.ViewHolder,
        preInfo: ItemHolderInfo,
        postInfo: ItemHolderInfo
    ): Boolean {
        return super.animateChange(oldHolder, newHolder, preInfo, postInfo)
    }
}