package com.example.fauzy.footballschedule.presenter

import android.util.Log
import com.example.fauzy.footballschedule.api.EventApi
import com.example.fauzy.footballschedule.model.Event
import com.example.fauzy.footballschedule.model.EventResponse
import com.example.fauzy.footballschedule.view.MatchView
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import java.lang.Exception

class NextMatchPresenter(private val view: MatchView,
                         private val apiClient: EventApi
) {

    fun getEventList(id: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val requestEvent = apiClient.getNextEvents(id)

            try {
                val responseEvent: EventResponse = requestEvent.await()

                val events: ArrayList<Event> = responseEvent.events

                view.loadEvents(events)

                view.hideLoading()
            } catch (e: Exception) {
                Log.d("ERROR", "Something went wrong nextmatch ${e.stackTrace}")
                view.showError(e)
            }

        }
    }
}