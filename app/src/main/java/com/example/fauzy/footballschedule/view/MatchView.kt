package com.example.fauzy.footballschedule.view

import com.example.fauzy.footballschedule.model.Event
import com.example.fauzy.footballschedule.model.Team
import com.example.fauzy.footballschedule.presenter.DetailPresenter

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showError(e: Exception)
    fun loadEvents(events: ArrayList<Event>)
    fun loadTeamDetail(team: ArrayList<Team>, timelines: ArrayList<DetailPresenter.Timeline>)
}