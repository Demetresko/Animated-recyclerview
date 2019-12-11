package com.example.animatedrecyclerview.ui

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.math.abs


class MiddleItemFinder(
    private val context: Context,
    private val layoutManager: LinearLayoutManager,
    private val callback: MiddleItemCallback,
    private val controlState: Int
) : RecyclerView.OnScrollListener() {


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val firstVisible = layoutManager.findFirstVisibleItemPosition()
        val lastVisible = layoutManager.findLastVisibleItemPosition()
        val itemsCount = lastVisible - firstVisible + 1

        val screenCenter = context.resources.displayMetrics.heightPixels / 2

        Log.d("HEIGHT" , context.resources.displayMetrics.heightPixels.toString() )
        var minCenterOffset = Integer.MAX_VALUE

        var middleItemIndex = 0

        for (index in firstVisible..lastVisible ) {

            val listItem = layoutManager.findViewByPosition(index) ?: return

            val topOffset = listItem.top   // current absolute position of top
            val bottomOffset = listItem.bottom

            val centerOffset = abs(screenCenter - (bottomOffset - listItem.height / 2))


            if (minCenterOffset > centerOffset) {
                minCenterOffset = centerOffset
                middleItemIndex = index
            }
        }
        callback.scrollFinished(middleItemIndex, minCenterOffset )
    }


    interface MiddleItemCallback {

        fun scrollFinished(middleElement: Int, centerOffset: Int)
    }

    companion object {

        const val ALL_STATES = 10
    }
}