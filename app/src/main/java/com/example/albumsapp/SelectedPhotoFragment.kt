package com.example.albumsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albumsapp.adapter.PhotoAdapter
import com.example.albumsapp.databinding.FragmentSelectedPhotoBinding
import com.example.albumsapp.viewmodel.PhotoViewModel

class SelectedPhotoFragment : Fragment() {
    private var _binding: FragmentSelectedPhotoBinding? = null
    private val binding get() = _binding!!

    private val args: SelectedPhotoFragmentArgs by navArgs()

    private val viewModel: PhotoViewModel by viewModels {
        PhotoViewModel.MyViewModelFactory(args.albumId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectedPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.currentPhoto.layoutManager = LinearLayoutManager(context)
        viewModel.photos.observe(viewLifecycleOwner) {
            binding.currentPhoto.adapter = PhotoAdapter(it[args.photo - 1])
            (activity as AppCompatActivity).supportActionBar?.title = it[args.photo-1].title
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
