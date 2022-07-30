package com.example.albumsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.ListOfAlbumsDirections
import com.example.albumsapp.R
import com.example.albumsapp.viewmodel.AlbumViewModel
import com.example.albumsapp.model.Album


class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private val viewModel : AlbumViewModel = AlbumViewModel()
    private val listOfAlbums : List<Album>? = viewModel.album.value

    class AlbumViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return AlbumViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = listOfAlbums?.get(position)
        holder.button.text = item?.title

        holder.button.setOnClickListener {
            val action = ListOfAlbumsDirections.actionListOfAlbumsToListOfPhotos()
            holder.view.findNavController().navigate(action)
        }
    }
    override fun getItemCount(): Int = listOfAlbums?.size ?: 0
}