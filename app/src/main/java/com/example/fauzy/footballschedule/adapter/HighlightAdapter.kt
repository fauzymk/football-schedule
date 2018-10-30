package com.example.fauzy.footballschedule.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.fauzy.footballschedule.R
import com.example.fauzy.footballschedule.presenter.DetailPresenter
import com.squareup.picasso.Picasso

class HighlightViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val homeTime = view.findViewById<TextView>(R.id.home_highlight_time_text)
    private val homePlayer = view.findViewById<TextView>(R.id.home_highlight_player_text)
    private val homeDrawable = view.findViewById<ImageView>(R.id.home_highlight_type)

    private val awayTime = view.findViewById<TextView>(R.id.away_highlight_time_text)
    private val awayPlayer = view.findViewById<TextView>(R.id.away_highlight_player_text)
    private val awayDrawable = view.findViewById<ImageView>(R.id.away_highlight_type)

    fun bindItem(timeline: DetailPresenter.Timeline) {
        if(timeline.side == DetailPresenter.SIDE.HOME) {
            homeTime.text = itemView.context.getString(R.string.highlights_time_text, timeline.time)
            homePlayer.text = timeline.name

            val drawable = when(timeline.type) {
                DetailPresenter.TYPE.YELLOW_CARD -> R.drawable.yellow_card
                DetailPresenter.TYPE.RED_CARD -> R.drawable.red_card
                DetailPresenter.TYPE.GOAL -> R.drawable.goal
            }

            homeDrawable.setImageDrawable(ContextCompat.getDrawable(itemView.context, drawable))
        } else if(timeline.side == DetailPresenter.SIDE.AWAY) {
            awayTime.text = itemView.context.getString(R.string.highlights_time_text, timeline.time)
            awayPlayer.text = timeline.name

            val drawable = when(timeline.type) {
                DetailPresenter.TYPE.YELLOW_CARD -> R.drawable.yellow_card
                DetailPresenter.TYPE.RED_CARD -> R.drawable.red_card
                DetailPresenter.TYPE.GOAL -> R.drawable.goal
            }

            awayDrawable.setImageDrawable(ContextCompat.getDrawable(itemView.context, drawable))
        }
    }
}

class HighlightAdapter(val context: Context, var timelines: ArrayList<DetailPresenter.Timeline>)
    : RecyclerView.Adapter<HighlightViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HighlightViewHolder {
        Log.d("DATA DI HIGHLIGHT", "DATA ${timelines.size}")
        return HighlightViewHolder(LayoutInflater.from(context).inflate(R.layout.hightlight_list_item, p0, false))
    }

    override fun getItemCount(): Int = timelines.size

    override fun onBindViewHolder(p0: HighlightViewHolder, p1: Int) {
        Log.d("HIGHLIGHT", "HIGH ${timelines[p1]}")
        p0.bindItem(timelines[p1])
    }
}