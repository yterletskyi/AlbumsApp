package com.example.albumsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.R
import com.example.albumsapp.model.Photo
import com.example.albumsapp.parser.PhotoColorParser

class PhotoAdapter(private val photo: Photo) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private val colorParser: PhotoColorParser = PhotoColorParser()

    class PhotoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.item_image)
        val title: TextView = view.findViewById(R.id.item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_view, parent, false)

        return PhotoViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.image.setBackgroundColor(colorParser.parse(photo.url))
        holder.title.text = photo.title
    }

    override fun getItemCount(): Int = 1
}
