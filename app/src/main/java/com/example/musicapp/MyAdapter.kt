package com.example.musicapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter (val context : Activity, val dataList : List<Data>) :
RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        // create the view i case layout manager fails to create view for the data

        val itemView = LayoutInflater.from(context).inflate(R.layout.eath_item, parent , false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
       // populate the data into the view

        val currentData = dataList[position]
        val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())
        holder.title.text = currentData.title

        Picasso.get().load(currentData.album.cover).into(holder.image)

        holder.play.setOnClickListener {
            mediaPlayer.start()
        }

        holder.pause.setOnClickListener{
            mediaPlayer.pause()
        }

    }

    override fun getItemCount(): Int {
        // count the size of data
        return dataList.size
    }

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView
        val title : TextView
        val play : ImageButton
        val pause : ImageButton

        init {
            image = itemView.findViewById(R.id.song_image)
            title = itemView.findViewById(R.id.song_Title)
            play = itemView.findViewById(R.id.btn_play)
            pause = itemView.findViewById(R.id.btn_pause)
        }

    }
}