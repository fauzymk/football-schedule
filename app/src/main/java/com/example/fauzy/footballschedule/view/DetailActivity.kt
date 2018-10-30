package com.example.fauzy.footballschedule.view

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.example.fauzy.footballschedule.R
import com.example.fauzy.footballschedule.adapter.HighlightAdapter
import com.example.fauzy.footballschedule.api.EventApi
import com.example.fauzy.footballschedule.api.EventApiClient
import com.example.fauzy.footballschedule.model.Event
import com.example.fauzy.footballschedule.model.Team
import com.example.fauzy.footballschedule.presenter.DetailPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.notification_template_part_time.*
import org.jetbrains.anko.indeterminateProgressDialog

class DetailActivity : AppCompatActivity(), MatchView {

    private lateinit var presenter: DetailPresenter
    private lateinit var progress: ProgressDialog
    private lateinit var adapter: HighlightAdapter
    private var highlights: ArrayList<DetailPresenter.Timeline> = arrayListOf()
    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val apiClient = EventApiClient.getClient().create(EventApi::class.java)
        event = intent.extras.getParcelable(EVENT_DATA)

        presenter = DetailPresenter(this, apiClient)

        progress = indeterminateProgressDialog("Fetching Team Details...")

        presenter.getTeamInfo(event)

        highlight_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        adapter = HighlightAdapter(this, highlights)

        highlight_list.adapter = adapter
    }

    override fun showLoading() {
        progress.show()
    }

    override fun hideLoading() {
        progress.dismiss()
    }

    override fun showError(e: Exception) {
    }

    override fun loadEvents(events: ArrayList<Event>) {
    }

    override fun loadTeamDetail(team: ArrayList<Team>, timelines: ArrayList<DetailPresenter.Timeline>) {
        Log.d("TEAM_DETAIL", "THE TEAM $team")

        val homeTeam: Team = team.get(0)
        val awayTeam: Team = team.get(1)

        Picasso.get().load(homeTeam.logo).resize(300,300).into(home_logo)
        Picasso.get().load(awayTeam.logo).resize(300,300).into(away_logo)

        home_text.text = homeTeam.name
        away_text.text = awayTeam.name
        score_text.text = "${event.home_score} : ${event.away_score}"

        highlights.clear()
        highlights.addAll(timelines)
        adapter.notifyDataSetChanged()
    }
}
