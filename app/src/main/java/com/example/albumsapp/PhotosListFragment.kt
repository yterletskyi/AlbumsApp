package com.example.albumsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.adapter.PhotosAdapter
import com.example.albumsapp.databinding.FragmentListOfPhotosBinding


class PhotosListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentListOfPhotosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfPhotosBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.photos
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PhotosAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}