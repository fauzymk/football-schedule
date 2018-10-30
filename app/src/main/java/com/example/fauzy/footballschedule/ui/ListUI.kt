package com.example.fauzy.footballschedule.ui

import android.graphics.Color
import android.graphics.Typeface
import android.text.Layout
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

            verticalLayout {

                textView("hello") {
                    id = R.id.date
                }.lparams {
                    verticalPadding = dip(10)
                    horizontalPadding = dip(20)
                }

                linearLayout {
                    lparams(matchParent, wrapContent)
                    weightSum = 5f
                    verticalPadding = dip(10)
                    horizontalPadding = dip(20)

                    linearLayout {
                        lparams(0, wrapContent) {
                            weight = 2f
                            gravity = Gravity.CENTER_VERTICAL
                        }

                        textView("Placeholder") {
                            id = R.id.home_team
                            textSize = 15f
                            gravity = Gravity.CENTER_VERTICAL
                            textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                        }.lparams(matchParent, wrapContent)
                    }

                    linearLayout {
                        lparams(0, wrapContent) {
                            gravity = Gravity.CENTER_VERTICAL
                            weight = 1f
                        }


                        textView {
                            id = R.id.score
                            textSize = 20f
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            padding = dip(5)
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(matchParent, wrapContent)
                    }

                    linearLayout {
                        lparams(0, wrapContent) {
                            weight = 2f
                            gravity = Gravity.CENTER_VERTICAL
                        }

                        textView("Placeholder") {
                            id = R.id.away_team
                            textSize = 15f
                            gravity = Gravity.CENTER_VERTICAL
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams(matchParent, wrapContent)
                    }
                }
            }
        }
    }
}