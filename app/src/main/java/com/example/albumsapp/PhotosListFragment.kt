package com.example.albumsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.adapter.PhotosAdapter
import com.example.albumsapp.databinding.FragmentListOfPhotosBinding
import com.example.albumsapp.model.Photo
import com.example.albumsapp.viewmodel.PhotoViewModel


class PhotosListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentListOfPhotosBinding? = null
    private val binding get() = _binding!!
    private lateinit var id: String

    val viewModel: PhotoViewModel by viewModels {
        PhotoViewModel.MyViewModelFactory(id)
    }
    val args: PhotosListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = args.id.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.photos
        recyclerView.layoutManager = GridLayoutManager(context, 4)

        viewModel.photos.observe(viewLifecycleOwner) {
            recyclerView.adapter = PhotosAdapter(it, ::onPhotoClicked)
        }

    }

    private fun onPhotoClicked(photo: Photo) {
        val action = PhotosListFragmentDirections.actionListOfPhotosToSelectedPhoto()
        findNavController(binding.photos.findFragment()).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
