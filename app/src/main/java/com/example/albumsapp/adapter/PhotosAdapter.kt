package com.example.albumsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.ListOfPhotosDirections
import com.example.albumsapp.R
import com.example.albumsapp.model.Photo
import com.example.albumsapp.viewmodel.PhotoViewModel

class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    private val viewModel : PhotoViewModel = PhotoViewModel()
    private val listOfPhoto : List<Photo>? = viewModel.photos.value
    class PhotosViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return PhotosViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.button.setOnClickListener {
            val action = ListOfPhotosDirections.actionListOfPhotosToSelectedPhoto()
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = listOfPhoto?.size ?: 0
}