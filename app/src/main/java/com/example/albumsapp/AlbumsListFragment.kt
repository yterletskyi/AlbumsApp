package com.example.albumsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.adapter.AlbumAdapter
import com.example.albumsapp.databinding.FragmentListOfAlbumsBinding
import com.example.albumsapp.model.Album
import com.example.albumsapp.viewmodel.AlbumViewModel

class AlbumsListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentListOfAlbumsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AlbumViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfAlbumsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.album
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.albums.observe(viewLifecycleOwner) {
            recyclerView.adapter = AlbumAdapter(it, ::onAlbumClicked)
        }
    }

    private fun onAlbumClicked(album: Album) {
        // TODO: implement onAlbumClicked
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
