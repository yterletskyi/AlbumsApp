package com.example.albumsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.R
import com.example.albumsapp.model.Photo


class PhotosAdapter(
    private val listOfPhotos: List<Photo>,
    private val onPhotosClicked: (Photo) -> Unit
) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    class PhotosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return PhotosViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val item = listOfPhotos[position]
        holder.button.setOnClickListener {
            onPhotosClicked(item)
        }
    }

    override fun getItemCount(): Int = listOfPhotos.size
}