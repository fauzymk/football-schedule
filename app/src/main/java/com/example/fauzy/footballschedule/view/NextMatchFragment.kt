package com.example.fauzy.footballschedule.view

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fauzy.footballschedule.R
import com.example.fauzy.footballschedule.adapter.NextMatchAdapter

import com.example.fauzy.footballschedule.api.EventApi
import com.example.fauzy.footballschedule.api.EventApiClient
import com.example.fauzy.footballschedule.model.Event
import com.example.fauzy.footballschedule.model.Team
import com.example.fauzy.footballschedule.presenter.DetailPresenter
import com.example.fauzy.footballschedule.presenter.NextMatchPresenter
import com.example.fauzy.footballschedule.ui.NextMatchUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.indeterminateProgressDialog

class NextMatchFragment : Fragment(), MatchView {

    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: NextMatchAdapter
    private lateinit var progress: ProgressDialog
    private var eventsList: ArrayList<Event> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val apiClient = EventApiClient.getClient().create(EventApi::class.java)

        presenter = NextMatchPresenter(this, apiClient)

        progress = indeterminateProgressDialog("Loading")

        adapter = NextMatchAdapter(inflater.context, eventsList)

        presenter.getEventList("4328")

        return NextMatchUI(adapter).createView(AnkoContext.create(inflater.context, container!!))
    }

    override fun showLoading() {
        Log.d("LOADING", "ShowLoading")
        progress.show()
    }

    override fun hideLoading() {
        Log.d("LOADING", "HideLoading")
        progress.dismiss()
    }

    override fun showError(e: Exception) {
//        alert(e.message ?: "Something went wrong", "Alert").show()
        Log.d("ERROR", e.message)
    }

    override fun loadEvents(events: ArrayList<Event>) {
        Log.d("GOOO", "WOW $events")
        eventsList.clear()
        eventsList.addAll(events)
        adapter.notifyDataSetChanged()
    }

    override fun loadTeamDetail(team: ArrayList<Team>, timelines: ArrayList<DetailPresenter.Timeline>) {

    }

    companion object {
        fun newInstance() = NextMatchFragment()
    }
}
