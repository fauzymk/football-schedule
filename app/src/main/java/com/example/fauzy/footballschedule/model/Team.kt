package com.example.fauzy.footballschedule.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam") val id: String,
    @SerializedName("strTeam") val name: String,
    @SerializedName("strTeamBadge") val logo: String
)