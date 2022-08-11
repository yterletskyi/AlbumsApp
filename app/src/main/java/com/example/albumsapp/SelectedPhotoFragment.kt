package com.example.albumsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albumsapp.adapter.PhotoAdapter
import com.example.albumsapp.databinding.FragmentSelectedPhotoBinding

class SelectedPhotoFragment : Fragment() {
    private var _binding : FragmentSelectedPhotoBinding? = null
    private val binding get() = _binding!!

    val args: SelectedPhotoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentSelectedPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.selectedPhoto.layoutManager = LinearLayoutManager(context)
        binding.selectedPhoto.adapter = PhotoAdapter(args.photo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
