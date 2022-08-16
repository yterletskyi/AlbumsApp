package com.example.albumsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.albumsapp.adapter.PhotosAdapter
import com.example.albumsapp.databinding.FragmentListOfPhotosBinding
import com.example.albumsapp.model.Photo
import com.example.albumsapp.network.ApiDataSource
import com.example.albumsapp.parser.PhotoColorParser
import com.example.albumsapp.viewmodel.PhotoViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PhotosListFragment @Inject constructor(apiDataSource: ApiDataSource, private val colorParser: PhotoColorParser ) : Fragment() {
    private var _binding: FragmentListOfPhotosBinding? = null
    private val binding get() = _binding!!

    private val args: PhotosListFragmentArgs by navArgs()

    private val viewModel: PhotoViewModel by viewModels {
        PhotoViewModel.MyViewModelFactory(args.id, apiDataSource)
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
        binding.photos.layoutManager = GridLayoutManager(context, 4)
        viewModel.photos.observe(viewLifecycleOwner) {
            binding.photos.adapter = PhotosAdapter(it, ::onPhotoClicked, colorParser)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            showErrorAlertDialog(it)
        }
    }

    private fun onPhotoClicked(photo: Photo) {
        val action = PhotosListFragmentDirections.actionListOfPhotosToSelectedPhoto(photo)
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
