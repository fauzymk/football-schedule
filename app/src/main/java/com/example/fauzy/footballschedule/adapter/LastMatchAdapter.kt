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
import com.example.fauzy.footballschedule.view.DetailActivity
import com.example.fauzy.footballschedule.view.EVENT_DATA
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.startActivity

class LastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val home = view.findViewById<TextView>(R.id.home_team)
    private val away = view.findViewById<TextView>(R.id.away_team)
    private val score = view.findViewById<TextView>(R.id.score)
    private val date = view.findViewById<TextView>(R.id.date)

    fun bindItem(event: Event) {
        home.text = event.home_team
        away.text = event.away_team
        score.text = "${event.home_score} : ${event.away_score}"
        date.text = event.date

        itemView.setOnClickListener {
            it.context.startActivity<DetailActivity>(EVENT_DATA to event)
        }

    }

}

class LastMatchAdapter(val context: Context, var events: ArrayList<Event>) : RecyclerView.Adapter<LastMatchViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LastMatchViewHolder {
        Log.d("DI ADAPTER", "DATA $p1")
        return LastMatchViewHolder(ListUI().createView(AnkoContext.create(context, p0)))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(p0: LastMatchViewHolder, p1: Int) {
        Log.d("HELLO LAST", "LAST $p1")
        val event: Event = events[p1]
        p0.bindItem(event)
    }
}