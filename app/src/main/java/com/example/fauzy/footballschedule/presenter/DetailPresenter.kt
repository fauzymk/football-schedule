package com.example.fauzy.footballschedule.presenter

import android.util.Log
import com.example.fauzy.footballschedule.api.EventApi
import com.example.fauzy.footballschedule.model.Event
import com.example.fauzy.footballschedule.model.Team
import com.example.fauzy.footballschedule.model.TeamResponse
import com.example.fauzy.footballschedule.view.MatchView
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import java.lang.Exception

class DetailPresenter(private val view: MatchView,
                      private val apiClient: EventApi) {

    enum class TYPE { GOAL, RED_CARD, YELLOW_CARD }
    enum class SIDE { HOME, AWAY }

    data class Timeline(var time: Int, var name: String, var side: SIDE, var type: TYPE)

    fun getTeamInfo(event: Event) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val requestHomeTeam = apiClient.getTeamDetails(event.id_home_team)
            val requestAwayTeam = apiClient.getTeamDetails(event.id_away_team)

            try {
                val responseHomeTeam: TeamResponse = requestHomeTeam.await()
                val responseAwayTeam: TeamResponse = requestAwayTeam.await()

                val homeGoals : ArrayList<Timeline> = generateTimeline(event.home_goal_details, SIDE.HOME, TYPE.GOAL)
                val homeRedCards : ArrayList<Timeline> = generateTimeline(event.home_red_cards, SIDE.HOME, TYPE.RED_CARD)
                val homeYellowCards : ArrayList<Timeline> = generateTimeline(event.home_yellow_cards, SIDE.HOME, TYPE.YELLOW_CARD)

                val awayGoals : ArrayList<Timeline> = generateTimeline(event.away_goal_details, SIDE.AWAY, TYPE.GOAL)
                val awayRedCards : ArrayList<Timeline> = generateTimeline(event.away_red_cards, SIDE.AWAY, TYPE.RED_CARD)
                val awayYellowCards : ArrayList<Timeline> = generateTimeline(event.away_yellow_cards, SIDE.AWAY, TYPE.YELLOW_CARD)

                val timeline : ArrayList<Timeline> = arrayListOf()

                timeline.addAll(homeGoals)
                timeline.addAll(homeRedCards)
                timeline.addAll(homeYellowCards)
                timeline.addAll(awayGoals)
                timeline.addAll(awayRedCards)
                timeline.addAll(awayYellowCards)
                timeline.sortByDescending { it.time }

                Log.d("GOALS", "goals $timeline")

                val team: ArrayList<Team> = arrayListOf(responseHomeTeam.teams[0], responseAwayTeam.teams[0])

                view.loadTeamDetail(team, timeline)

                view.hideLoading()

            } catch (e: Exception) {
                Log.d("ERROR", "Something went wrong di detail ${e.message}")
                view.showError(e)
            }
        }
    }

    private fun generateTimeline(timeline: String?, side: SIDE, type: TYPE) : ArrayList<Timeline> {
        var timelineArr : ArrayList<Timeline> = arrayListOf()

        timeline?.split(";")?.forEach {
            if(!it.isNullOrEmpty()) {
                val str = it.split("':")
                val time : Int = str[0].toInt()
                val name : String = str[1]

                timelineArr.add(Timeline(time, name, side, type))
            }
        }

        return timelineArr
    }
}