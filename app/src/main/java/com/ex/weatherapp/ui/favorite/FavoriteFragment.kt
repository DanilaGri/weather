package com.ex.weatherapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ex.weatherapp.databinding.FragmentFavoriteBinding
import com.ex.weatherapp.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private val favoriteModel: FavoriteViewModel by viewModels()

    private var _binding: FragmentFavoriteBinding? = null

    private var adapter: CityAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        adapter = CityAdapter(object : CityAdapter.OnCityClickListener {
            override fun onDelete(position: Int) {
                val currentList = adapter?.currentList?.toMutableList()
                currentList?.get(position)?.let { viewModel.deleteFavorite(it) }
                currentList?.removeAt(position)
                adapter?.submitList(currentList)
            }
        })

        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favoriteModel.uiState.collect { uiState ->
                    adapter?.submitList(uiState.list)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}