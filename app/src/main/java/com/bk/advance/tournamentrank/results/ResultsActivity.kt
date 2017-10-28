package com.bk.advance.tournamentrank.results

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.bk.advance.tournamentrank.IntentConstants
import com.bk.advance.tournamentrank.R
import com.bk.advance.tournamentrank.Rank
import com.bk.advance.tournamentrank.databinding.ActivityResultsBinding
import com.bk.advance.tournamentrank.model.Player

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ResultsViewModel()
        val binding: ActivityResultsBinding = DataBindingUtil.setContentView(this, R.layout.activity_results)
        binding.viewModel = viewModel

        val playersCount = intent.getIntExtra(IntentConstants.PLAYERS_COUNT, 0)
        val rank = Rank.valueOf(intent.getStringExtra(IntentConstants.RANK))
        val junior = intent.getBooleanExtra(IntentConstants.JUNIOR, false)

        viewModel.initialize(playersCount, rank, junior)
        setupRecyclerView(binding)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(menu: MenuItem): Boolean {
        if (menu.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(menu)
    }

    private fun setupRecyclerView(binding: ActivityResultsBinding) {
        binding.results.layoutManager = LinearLayoutManager(this)
        binding.results.adapter = object : MyBaseAdapter() {
            override fun getLayoutIdForPosition(position: Int): Int {
                return R.layout.item_player
            }

            override fun getItemCount(): Int {
                return binding.viewModel!!.players.size
            }

            override fun getObjForPosition(position: Int): Player {
                return binding.viewModel!!.players[position]
            }
        }
    }


}
