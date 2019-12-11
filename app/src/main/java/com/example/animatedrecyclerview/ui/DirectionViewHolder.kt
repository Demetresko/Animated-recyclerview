package com.example.animatedrecyclerview.ui

import android.view.View
import com.example.animatedrecyclerview.entity.ArtDirection
import kotlinx.android.synthetic.main.vh_layout.view.*

class DirectionViewHolder(itemView: View) : BaseViewHolder<ArtDirection>(itemView) {

    override fun bindItem(t: ArtDirection) {
        with(itemView) {
            tv_direction.text = t.title
        }
    }

    fun setAlpha(coeff : Float) {
        itemView.alpha = coeff
    }

    fun setOffset(offset : Int) {
        itemView.setPadding(offset,0,0,0)
    }

    override fun bindItem(t: ArtDirection, payload: List<Any>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
