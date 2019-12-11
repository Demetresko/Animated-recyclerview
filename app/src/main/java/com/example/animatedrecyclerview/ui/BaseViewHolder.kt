package com.example.animatedrecyclerview.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bindItem(t: T)

    abstract fun bindItem(t: T, payload: List<Any>)
}
