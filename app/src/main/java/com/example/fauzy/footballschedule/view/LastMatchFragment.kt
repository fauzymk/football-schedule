package com.example.fauzy.footballschedule.view

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.CircularProgressDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fauzy.footballschedule.adapter.LastMatchAdapter
import com.example.fauzy.footballschedule.api.EventApi
import com.example.fauzy.footballschedule.api.EventApiClient
import com.example.fauzy.footballschedule.model.Event
import com.example.fauzy.footballschedule.model.Team
import com.example.fauzy.footballschedule.presenter.DetailPresenter
import com.example.fauzy.footballschedule.presenter.LastMatchPresenter
import com.example.fauzy.footballschedule.ui.LastMatchUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.*

class LastMatchFragment : Fragment(), MatchView {

    private lateinit var presenter: LastMatchPresenter
    private lateinit var adapter: LastMatchAdapter
    private lateinit var progress: ProgressDialog
    private var eventsList: ArrayList<Event> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val apiClient = EventApiClient.getClient().create(EventApi::class.java)

        progress = indeterminateProgressDialog("Loading")

        presenter = LastMatchPresenter(this, apiClient)

        adapter = LastMatchAdapter(inflater.context, eventsList)

        presenter.getEventList("4328")

        return LastMatchUI(adapter).createView(AnkoContext.create(inflater.context, container!!))
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
        fun newInstance() = LastMatchFragment()
    }
}