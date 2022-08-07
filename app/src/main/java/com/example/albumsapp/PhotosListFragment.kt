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
import com.example.albumsapp.viewmodel.PhotoViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class PhotosListFragment : Fragment() {
    private var _binding: FragmentListOfPhotosBinding? = null
    private val binding get() = _binding!!

    val args: PhotosListFragmentArgs by navArgs()

    val viewModel: PhotoViewModel by viewModels {
        PhotoViewModel.MyViewModelFactory(args.id)
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
            binding.photos.adapter = PhotosAdapter(it, ::onPhotoClicked)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            viewModel.errorLiveData.value?.let { exp -> showErrorAlertDialog(exp) }
        }
    }

    private fun onPhotoClicked(photo: Photo) {
        val action = PhotosListFragmentDirections.actionListOfPhotosToSelectedPhoto()
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
