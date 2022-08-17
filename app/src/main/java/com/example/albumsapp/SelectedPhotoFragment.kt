package com.example.albumsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.albumsapp.databinding.FragmentSelectedPhotoBinding
import com.example.albumsapp.parser.PhotoColorParser
import javax.inject.Inject

class SelectedPhotoFragment @Inject constructor(
    private val colorParser: PhotoColorParser
) : Fragment() {
    private var _binding: FragmentSelectedPhotoBinding? = null
    private val binding get() = _binding!!

    private val args: SelectedPhotoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectedPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val url = args.photo.url
        binding.itemImage.setBackgroundColor(colorParser.parse(url))
        binding.itemTitle.text = args.photo.title
        (activity as AppCompatActivity).supportActionBar?.title =
            args.photo.title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
