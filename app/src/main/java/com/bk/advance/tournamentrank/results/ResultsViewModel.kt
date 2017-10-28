package com.bk.advance.tournamentrank.results

import android.databinding.BaseObservable
import com.bk.advance.tournamentrank.Rank
import com.bk.advance.tournamentrank.model.Player
import io.reactivex.Observable
import java.text.DecimalFormat

class ResultsViewModel : BaseObservable() {
    var players: List<Player> = emptyList()
    private val format = DecimalFormat("###.##")

    private val tMaxes = mapOf(Pair(Rank.A, 600.0),
            Pair(Rank.B0, 365.0),
            Pair(Rank.B1, 320.0),
            Pair(Rank.B2, 275.0),
            Pair(Rank.B3, 230.0),
            Pair(Rank.B4, 185.0),
            Pair(Rank.B5, 140.0),
            Pair(Rank.C, 100.0))

    fun initialize(playerCount: Int, rank: Rank, junior: Boolean) {
        Observable.range(1, playerCount)
                .map { i -> Player(i, formatDouble(calculatePointsForPosition(i, playerCount, rank, junior))) }
                .toList()
                .subscribe({ t1, _ ->
                    players = t1
                })
    }

    private fun formatDouble(double: Double): String {
        return format.format(double)
    }

    private fun calculatePointsForPosition(position: Int, playerCount: Int, rank: Rank, junior: Boolean): Double {
        return if (junior) {
            calculatePointsForJunior(position, playerCount, rank)
        } else {
            calculatePointsForOpen(position, rank)
        }
    }

    private fun calculatePointsForJunior(position: Int, playerCount: Int, rank: Rank): Double {
        val delta = 4
        val num = calculatePointsForOpen(position, rank)

        return num * (playerCount - position + delta + 1)/(playerCount + delta)
    }

    private fun calculatePointsForOpen(position: Int, rank: Rank): Double {
        val tMax = tMaxes[rank]!!
        val mz = position.toDouble()

        val prePos = 1.0 / Math.sqrt(mz)

        return tMax * (prePos + Math.pow(1.05, (1 - mz))) / 2
    }
}
