package com.example.fauzy.footballschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fauzy.footballschedule.R
import com.example.fauzy.footballschedule.model.Event
import com.example.fauzy.footballschedule.ui.ListUI
import org.jetbrains.anko.AnkoContext

class LastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val home = view.findViewById<TextView>(R.id.home_team)
    private val away = view.findViewById<TextView>(R.id.away_team)
    private val score = view.findViewById<TextView>(R.id.score)

    fun bindItem(event: Event) {
        home.text = event.home_team
        away.text = event.away_team
        score.text = "${event.home_score} : ${event.away_score}"
    }
}

class LastMatchAdapter(val context: Context, var events: ArrayList<Event>) : RecyclerView.Adapter<LastMatchViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LastMatchViewHolder {
        Log.d("DI ADAPTER", "DATA $events")
        return LastMatchViewHolder(ListUI().createView(AnkoContext.create(context, p0)))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(p0: LastMatchViewHolder, p1: Int) {
        val event: Event = events[p1]
        p0.bindItem(event)
    }
}