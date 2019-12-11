package com.example.animatedrecyclerview.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animatedrecyclerview.R
import com.example.animatedrecyclerview.entity.ArtDirection
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.math.abs


class DirectionAdapter(
    val data: List<ArtDirection>,
    private val context: Context,
    private val layoutManager: LinearLayoutManager
) : RecyclerView.Adapter<DirectionViewHolder>() {

    private var lastPosition = -1
    var firstVisible = 0
    var lastVisible = 0
    var middle = 0
    var visibleGap = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DirectionViewHolder(layoutInflater.inflate(R.layout.vh_layout, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DirectionViewHolder, position: Int) {
        val artDirection = data[position]
        holder.bindItem(artDirection)

//        firstVisible = layoutManager.findFirstVisibleItemPosition()
//        lastVisible = layoutManager.findLastVisibleItemPosition()
//
//        visibleGap = lastVisible - firstVisible + 1
//        middle = firstVisible + visibleGap / 2
//
//        val coaeff = when {
//            position > middle -> abs( 1 - 2 * (position - middle) / (visibleGap.toFloat()))
//            position < middle -> abs(1 - 2 * (middle - position) / (visibleGap.toFloat()))
//            else -> 1f
//        }
//
//        val offset : Int = when {
//            position > middle -> ((position - middle) / (visibleGap.toFloat() ) * DEFAULT_OFFSET).toInt()
//            position < middle -> ((middle - position) / (visibleGap.toFloat() ) * DEFAULT_OFFSET).toInt()
//            else -> 0
//        }
//
//        holder.setAlpha(coaeff)
//        holder.setOffset(offset)
    }




    override fun onBindViewHolder(
        holder: DirectionViewHolder, position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)

        if (payloads.isNotEmpty()) {
            holder.setAlpha(1f)
//            holder.setOffset(0)

            val list = payloads[0] as List<*>
            firstVisible = list[0] as Int
            lastVisible = list[1] as Int

            visibleGap = lastVisible - firstVisible
            middle = position

        }


        // When payload list is empty,
        // or we don't have logic to handle a given type,
        // default to full bind:

        val artDirection = data[position]
        holder.bindItem(artDirection)

        lastPosition = position
    }


    companion object {
        private const val DEFAULT_OFFSET = 140
    }

}