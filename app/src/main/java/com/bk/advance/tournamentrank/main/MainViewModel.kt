package com.bk.advance.tournamentrank.main

import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.Observable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.bk.advance.tournamentrank.IntentConstants
import com.bk.advance.tournamentrank.Rank
import com.bk.advance.tournamentrank.results.ResultsActivity

class MainViewModel : BaseObservable() {

    val ranks = Rank.values().asList()
    var selectedRankIndex = 0

    val playersCount = ObservableField<String>("")
    val juniorChecked = ObservableBoolean(false)

    var nextEnabled = ObservableBoolean(false)

    init {
        playersCount.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                nextEnabled.set(inputValid())
            }
        })
    }

    private fun inputValid() = playersCount.get().toIntOrNull() != null

    fun onNextClicked(context: Context) {
        if (!inputValid()) {
            return
        }

        val intent = Intent(context, ResultsActivity::class.java)

        intent.putExtra(IntentConstants.PLAYERS_COUNT, playersCount.get().toInt())
        intent.putExtra(IntentConstants.RANK, ranks[selectedRankIndex].toString())
        intent.putExtra(IntentConstants.JUNIOR, juniorChecked.get())
        context.startActivity(intent)
    }

}
