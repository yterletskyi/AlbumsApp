package com.example.albumsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.adapter.PhotosAdapter
import com.example.albumsapp.databinding.FragmentListOfPhotosBinding
import com.example.albumsapp.model.Photo
import com.example.albumsapp.viewmodel.PhotoViewModel



class PhotosListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentListOfPhotosBinding? = null
    private val binding get() = _binding!!
    private lateinit var albumId : String

    val viewModel : PhotoViewModel by viewModels()

    companion object {
        const val ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            albumId = it?.get(ID).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfPhotosBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.photos
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.photos.observe(viewLifecycleOwner) {
            recyclerView.adapter = PhotosAdapter(it, ::onPhotosClicked)
        }

    }
    private fun onPhotosClicked(photo : Photo) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}