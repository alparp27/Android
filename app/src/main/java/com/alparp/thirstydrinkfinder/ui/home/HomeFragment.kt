package com.alparp.thirstydrinkfinder.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alparp.thirstydrinkfinder.R
import com.alparp.thirstydrinkfinder.model.Drink
import com.alparp.thirstydrinkfinder.ui.RecyclerAdapter
import com.alparp.thirstydrinkfinder.ui.details.DrinkDetailsViewModel

class HomeFragment : Fragment() {

    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val searchBox: EditText = root.findViewById(R.id.search)
        searchBox.addTextChangedListener {
            homeViewModel.search(it.toString())
        }
        val recyclerView : RecyclerView = root.findViewById(R.id.recycler_view)
        recyclerAdapter = RecyclerAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter
        recyclerAdapter.onItemClick =  { drink ->
            val viewModel = ViewModelProvider(requireActivity()).get(DrinkDetailsViewModel::class.java)
            viewModel.item = drink
            Navigation.findNavController(root).navigate(R.id.navigation_drink_details) }
        homeViewModel.results.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.setListItems(it)
        })
        return root
    }

    override fun onPause() {
        homeViewModel.cancel()
        super.onPause()
    }
}