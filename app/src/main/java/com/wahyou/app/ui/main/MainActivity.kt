package com.wahyou.app.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahyou.app.data.model.Game
import com.wahyou.app.databinding.ActivityMainBinding
import com.wahyou.app.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter : MainAdapter by lazy {
        MainAdapter {item -> showDetail(item)}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupAdapter()
        setupDummyData()
    }

    private fun setupAdapter(){
        with(binding) {
            rvMain.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(this@MainActivity, 2)
                it.setHasFixedSize(true)
            }
        }
    }

    private fun setupDummyData(){
        val data: ArrayList<Game> = ArrayList()
        val game = Game(
            1,
            "Game Dummy",
            "https://test.pdf",
            "https://game.html",
            "https://www.kibrispdr.org/data/pc-game-cover-19.jpg"
        )
        for (i in 1..4) {
            data.add(game)
        }

        adapter.setData(data)
    }

    private fun showDetail(item: Game) {
        startActivity(Intent(this, DetailActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

}