package com.example.albumsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.AlbumsListFragmentDirections
import com.example.albumsapp.R
import com.example.albumsapp.model.Album


class AlbumAdapter(
    private val listOfAlbums: List<Album>,
    private val onAlbumClicked: (Album) -> Unit,
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return AlbumViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = listOfAlbums[position]
        holder.button.text = item.title

        holder.button.setOnClickListener {
            onAlbumClicked(item)
            val action = AlbumsListFragmentDirections.actionListOfAlbumsToListOfPhotos(item.id)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = listOfAlbums.size
}