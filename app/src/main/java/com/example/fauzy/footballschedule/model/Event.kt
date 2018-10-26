package com.example.fauzy.footballschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    @SerializedName("idEvent") val id: String,
    @SerializedName("dateEvent") val date: String,
    @SerializedName("strHomeTeam") val home_team: String,
    @SerializedName("strAwayTeam") val away_team: String,
    @SerializedName("idHomeTeam") val id_home_team: String,
    @SerializedName("idAwayTeam") val id_away_team: String,
    @SerializedName("intHomeScore") val home_score: String?,
    @SerializedName("intAwayScore") val away_score: String?,
    @SerializedName("intHomeShots") val home_shots: String?,
    @SerializedName("intAwayShots") val away_shots: String?,
    @SerializedName("strHomeGoalDetails") val home_goal_details: String?,
    @SerializedName("strHomeRedCards") val home_red_cards: String?,
    @SerializedName("strHomeYellowCards") val home_yellow_cards: String?,
    @SerializedName("strHomeLineupGoalkeeper") val home_gk: String?,
    @SerializedName("strHomeLineupDefense") val home_def: String?,
    @SerializedName("strHomeLineupMidfield") val home_mid: String?,
    @SerializedName("strHomeLineupForward") val home_att: String?,
    @SerializedName("strHomeLineupSubstitutes") val home_sub: String?,
    @SerializedName("strAwayGoalDetails") val away_goal_details: String?,
    @SerializedName("strAwayRedCards") val away_red_cards: String?,
    @SerializedName("strAwayYellowCards") val away_yellow_cards: String?,
    @SerializedName("strAwayLineupGoalkeeper") val away_gk: String?,
    @SerializedName("strAwayLineupDefense") val away_def: String?,
    @SerializedName("strAwayLineupMidfield") val away_mid: String?,
    @SerializedName("strAwayLineupForward") val away_att: String?,
    @SerializedName("strAwayLineupSubstitutes") val away_sub: String?
) : Parcelable