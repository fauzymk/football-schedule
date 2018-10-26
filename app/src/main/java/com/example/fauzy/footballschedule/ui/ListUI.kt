package com.example.fauzy.footballschedule.ui

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.example.fauzy.footballschedule.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ListUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        cardView {
            lparams(matchParent, wrapContent) {
                margin = dip(10)
            }

            linearLayout {
                lparams(matchParent, wrapContent)

                padding = dip(10)

                textView {
                    id = R.id.home_team
                    textSize = 16f
                    gravity = Gravity.CENTER_VERTICAL
                }

                linearLayout {
                    lparams(wrapContent, wrapContent)

                    textView {
                        id = R.id.score
                    }
                }

                textView {
                    id = R.id.away_team
                    textSize = 16f
                    gravity = Gravity.CENTER_VERTICAL
                }
            }
        }
    }
}