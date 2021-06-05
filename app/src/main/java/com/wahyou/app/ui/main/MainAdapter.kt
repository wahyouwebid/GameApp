package com.wahyou.app.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wahyou.app.R
import com.wahyou.app.data.model.Game
import com.wahyou.app.databinding.AdapterMainBinding

class MainAdapter (
    private val showDetail: (Game) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var data = ArrayList<Game>()

    fun setData(movieList: List<Game>?) {
        if (movieList.isNullOrEmpty()) return
        data.clear()
        data.addAll(movieList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val item = data[position]

            Picasso.get().load(R.drawable.thumbnail).into(imgThumbnail)

            root.setOnClickListener {
                showDetail(item)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterMainBinding) : RecyclerView.ViewHolder(view.root)

}