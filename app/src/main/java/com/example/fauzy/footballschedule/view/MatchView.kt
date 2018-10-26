package com.example.fauzy.footballschedule.view

import com.example.fauzy.footballschedule.model.Event

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun loadEvents(events: ArrayList<Event>)
}