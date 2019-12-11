package com.example.animatedrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animatedrecyclerview.entity.ArtDirection
import com.example.animatedrecyclerview.ui.DirectionAdapter
import kotlinx.android.synthetic.main.activity_main.*
import com.example.animatedrecyclerview.ui.MiddleItemFinder
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        rv_directions.layoutManager = layoutManager
        val adapterDirections = DirectionAdapter(provideDirections(), this, layoutManager)
        rv_directions.adapter = adapterDirections

        val callback = object : MiddleItemFinder.MiddleItemCallback {
            override fun scrollFinished(middleElement: Int, centerOffset: Int) {
                // interaction with middle item
                Log.d("OFFSET", centerOffset.toString() )
                val firstVisible = layoutManager.findFirstVisibleItemPosition()
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                val visibleGap = lastVisible - firstVisible + 1

                for ( position in firstVisible..lastVisible) {
                    val view = layoutManager.findViewByPosition(position)

                    val coeff = when {
                        position > middleElement -> abs( 1 - 2 * (position - middleElement) / (visibleGap.toFloat()))
                        position < middleElement -> abs( 1 - 2 * (middleElement - position) / (visibleGap.toFloat()))
                        else -> 1f
                    }
                    val coeff2 = visibleGap * view.height /
                    Log.d("COEFF", coeff.toString())

                    var height = 1

                    view?.let {
                       height = it.top - it.bottom
                    }


                    var offset = ((1 - coeff)*200).toInt()
//                    offset =
//                        when {
//                            position > middleElement ->
//                                ( (position - middleElement) / (visibleGap.toFloat() ) * 200
//                                        + abs(centerOffset.toFloat() / height.toFloat()) * 200  ).toInt()
//                            position < middleElement ->
//                                ( (middleElement - position) / (visibleGap.toFloat() ) * 200
//                                        - abs(centerOffset.toFloat() / height.toFloat()) * 200  ).toInt()
//                            else -> 10
//                        }



                    Log.d("OFFSET", centerOffset.toString())
                    view?.alpha = coeff
                    view?.setPadding(offset,0,0,0)
                }

            }
        }

        val firstVisible = layoutManager.findFirstVisibleItemPosition()
        val lastVisible = layoutManager.findLastVisibleItemPosition()
        val visibleGap = lastVisible - firstVisible
        val middle = firstVisible + visibleGap / 2

        rv_directions.addOnScrollListener(
            MiddleItemFinder(
                this, layoutManager,
                callback, RecyclerView.SCROLL_STATE_DRAGGING
            )
        )


    }


    private fun provideDirections(): List<ArtDirection> =
        listOf(
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract"),
            ArtDirection("Abstract")
        )

}

/*
   TODO
   1. scroll listener for recyclerview
   2. viewHolder with payload
   3. in scrollListener notifyItemChanged with payload
   4. payload = intensity from 0 to 1
   5. alphga for color from 128 to 255
 */
