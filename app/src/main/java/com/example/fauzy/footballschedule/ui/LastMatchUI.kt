package com.example.fauzy.footballschedule.ui

import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import com.example.fauzy.footballschedule.adapter.LastMatchAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class LastMatchUI(private val lastMatchAdapter: LastMatchAdapter) : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        relativeLayout {
            lparams(matchParent, matchParent)

            recyclerView {
                lparams(matchParent, matchParent)
                layoutManager = LinearLayoutManager(ctx)
                adapter = lastMatchAdapter
            }
        }
    }
}