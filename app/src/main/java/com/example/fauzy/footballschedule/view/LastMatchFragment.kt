package com.example.fauzy.footballschedule.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fauzy.footballschedule.R
import com.example.fauzy.footballschedule.adapter.LastMatchAdapter
import com.example.fauzy.footballschedule.api.EventApi
import com.example.fauzy.footballschedule.api.EventApiClient
import com.example.fauzy.footballschedule.model.Event
import com.example.fauzy.footballschedule.presenter.LastMatchPresenter
import com.example.fauzy.footballschedule.ui.LastMatchUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class LastMatchFragment : Fragment(), MatchView {

    private lateinit var presenter: LastMatchPresenter
    private lateinit var adapter: LastMatchAdapter
    private var eventsList: ArrayList<Event> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val apiClient = EventApiClient.getClient().create(EventApi::class.java)

        presenter = LastMatchPresenter(this, apiClient)

        adapter = LastMatchAdapter(inflater.context, eventsList)

        presenter.getEventList("4328")

        return LastMatchUI(adapter).createView(AnkoContext.create(inflater.context, container!!))
    }

    override fun showLoading() {
        Log.d("LOADING", "ShowLoading")
    }

    override fun hideLoading() {
        Log.d("LOADING", "HideLoading")
    }

    override fun loadEvents(events: ArrayList<Event>) {
        Log.d("GOOO", "WOW $events")
        eventsList.clear()
        eventsList.addAll(events)
//        adapter =
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = LastMatchFragment()
    }
}