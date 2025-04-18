package com.example.dogbreedinfo.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogbreedinfo.R
import com.example.dogbreedinfo.ui.adapter.BreedAdapter
import com.example.dogbreedinfo.ui.constants.Constants
import com.example.dogbreedinfo.ui.viewmodel.DogViewModel

class BreedListFragment : Fragment() {
    private lateinit var viewModel: DogViewModel
    private lateinit var adapter: BreedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_breed_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity())[DogViewModel::class.java]
        adapter = BreedAdapter {
            val navParams = Bundle()
            navParams.putString(Constants.IMAGE_ID, it.reference_image_id)
            findNavController().navigate(R.id.breedDetailFragment, navParams)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.breedRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val searchEditText = view.findViewById<EditText>(R.id.searchText)
        searchEditText.addTextChangedListener {
            viewModel.searchBreeds(it.toString())
        }

        viewModel.filteredBreeds.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}