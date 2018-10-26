package com.example.fauzy.footballschedule.presenter

import android.util.Log
import com.example.fauzy.footballschedule.api.EventApi
import com.example.fauzy.footballschedule.model.Event
import com.example.fauzy.footballschedule.model.EventResponse
import com.example.fauzy.footballschedule.view.MatchView
import kotlinx.coroutines.experimental.launch
import java.lang.Exception

class LastMatchPresenter(private val view: MatchView,
                         private val apiClient: EventApi) {

    fun getEventList(id: String?) {
        view.showLoading()

        launch {
            val requestEvent = apiClient.getPastEvents(id)

            try {
                val responseEvent: EventResponse = requestEvent.await()

                val events: ArrayList<Event> = responseEvent.events

                view.loadEvents(events)

                view.hideLoading()
            } catch (e: Exception) {
                Log.d("ERROR", "Something went wrong di lastmatch ${e.stackTrace}")
            }

        }
    }
}