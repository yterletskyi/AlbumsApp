package com.example.albumsapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.R
import com.example.albumsapp.model.Photo


class PhotosAdapter(
    private val listOfPhotos: List<Photo>,
    private val onPhotosClicked: (Photo) -> Unit
) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    class PhotosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.album_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_view_photo, parent, false)

        return PhotosViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val item = listOfPhotos[position]
        val queryUrl: Uri = Uri.parse(item.url)
        holder.imageView.setImageURI(queryUrl)
    }

    override fun getItemCount(): Int = listOfPhotos.size
}