package com.example.dogbreedinfo.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dogbreedinfo.R
import com.example.dogbreedinfo.ui.constants.Constants
import com.example.dogbreedinfo.ui.viewmodel.DogViewModel

class BreedDetailFragment : Fragment() {
    private lateinit var viewModel: DogViewModel
    private var imageId: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_breed_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity())[DogViewModel::class.java]
        imageId = arguments?.getString(Constants.IMAGE_ID)
        if (imageId != null) {
            viewModel.fetchBreedDetails(imageId!!)
            viewModel.breedDetails.observe(viewLifecycleOwner) {breedDetails ->
                view.findViewById<TextView>(R.id.breedName).text = breedDetails.breeds[0].name
                view.findViewById<TextView>(R.id.breedInfo).text = breedDetails.breeds[0].description
                view.findViewById<TextView>(R.id.breedSize).text =
                    breedDetails.breeds[0].weight?.imperial ?: ""
                view.findViewById<TextView>(R.id.temperament).text = breedDetails.breeds[0].temperament
                view.findViewById<TextView>(R.id.lifeSpan).text = breedDetails.breeds[0].life_span

                val imageView = view.findViewById<ImageView>(R.id.breedImage)
                Glide.with(this).load(breedDetails.url).into(imageView)
            }
        }
    }
}