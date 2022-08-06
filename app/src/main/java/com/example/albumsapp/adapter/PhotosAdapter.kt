package com.example.albumsapp.adapter


import android.graphics.Color.parseColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.R
import com.example.albumsapp.model.Photo
import com.squareup.picasso.Picasso
import java.util.*


class PhotosAdapter(
    private val listOfPhotos: List<Photo>,
    private val onPhotoClicked: (Photo) -> Unit
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
        var color = "#" + item.thumbnailUrl.takeLastWhile { it != '/' }.uppercase(Locale.ROOT)
        if (color.length < 7) {
            color += "0"
        }
        Picasso.with(holder.itemView.context).load(item.thumbnailUrl).into(holder.imageView)
        holder.imageView.setBackgroundColor(parseColor(color))
        holder.imageView.setOnClickListener {
            onPhotoClicked(item)
        }
    }

    override fun getItemCount(): Int = listOfPhotos.size
}
