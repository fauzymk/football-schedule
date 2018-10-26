package com.example.fauzy.footballschedule.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fauzy.footballschedule.R

import com.example.fauzy.footballschedule.api.EventApi
import com.example.fauzy.footballschedule.api.EventApiClient
import com.example.fauzy.footballschedule.model.Event
import com.example.fauzy.footballschedule.presenter.NextMatchPresenter

class NextMatchFragment : Fragment(), MatchView {

    private lateinit var presenter: NextMatchPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val apiClient = EventApiClient.getClient().create(EventApi::class.java)

        presenter = NextMatchPresenter(this, apiClient)

        presenter.getEventList("4328")

        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun showLoading() {
        Log.d("LOADING", "ShowLoading")
    }

    override fun hideLoading() {
        Log.d("LOADING", "HideLoading")
    }

    override fun loadEvents(events: ArrayList<Event>) {
        Log.d("DATA DI FRAGMENT NEXT", "DATANYA $events")
    }

    companion object {
        fun newInstance() = NextMatchFragment()
    }
}
