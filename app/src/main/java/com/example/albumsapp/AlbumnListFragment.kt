package com.example.albumsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.adapter.AlbumAdapter
import com.example.albumsapp.databinding.FragmentListOfAlbumsBinding


class AlbumnListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentListOfAlbumsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListOfAlbumsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.album
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AlbumAdapter()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}