package com.example.albumsapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.R
import com.example.albumsapp.model.Photo
import com.example.albumsapp.parser.PhotoColorParser
import com.squareup.picasso.Picasso


class PhotosAdapter(
    private val listOfPhotos: List<Photo>,
    private val onPhotoClicked: (Photo) -> Unit
) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    private val colorParser: PhotoColorParser = PhotoColorParser()

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
        Picasso.with(holder.itemView.context).load(item.thumbnailUrl).into(holder.imageView)
        holder.imageView.setBackgroundColor(colorParser.parse(item.thumbnailUrl))
        holder.imageView.setOnClickListener {
            onPhotoClicked(item)
        }
    }

    override fun getItemCount(): Int = listOfPhotos.size
}
