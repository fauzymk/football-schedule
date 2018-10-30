package com.example.fauzy.footballschedule.api

import com.example.fauzy.footballschedule.model.EventResponse
import com.example.fauzy.footballschedule.model.TeamResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.*

interface EventApi {
    @GET("api/v1/json/1/eventspastleague.php")
    fun getPastEvents(@Query("id") id: String?): Deferred<EventResponse>

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextEvents(@Query("id") id: String?): Deferred<EventResponse>

    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamDetails(@Query("id") id: String): Deferred<TeamResponse>
}