package com.example.albumsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.adapter.AlbumAdapter
import com.example.albumsapp.databinding.FragmentListOfAlbumsBinding
import com.example.albumsapp.model.Album
import com.example.albumsapp.viewmodel.AlbumViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            binding.album.adapter = AlbumAdapter(it, ::onAlbumClicked)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            showErrorAlertDialog(it)
        }
    }

    private fun onAlbumClicked(album: Album) {
        val action = AlbumsListFragmentDirections.actionListOfAlbumsToListOfPhotos(album.id)
        findNavController(this).navigate(action)
    }

    private fun showErrorAlertDialog(e: Exception) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage(e.message)
            .setCancelable(true)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
