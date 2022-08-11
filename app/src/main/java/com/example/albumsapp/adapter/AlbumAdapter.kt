package com.example.albumsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.R
import com.example.albumsapp.model.Album

class AlbumAdapter(
    private val listOfAlbums: List<Album>,
    private val onAlbumClicked: (Album) -> Unit,
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTxtView: TextView = view.findViewById(R.id.nameTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return AlbumViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = listOfAlbums[position]

        with(holder) {
            nameTxtView.text = item.title
            itemView.setOnClickListener { onAlbumClicked(item) }
        }
    }

    override fun getItemCount(): Int = listOfAlbums.size
}
