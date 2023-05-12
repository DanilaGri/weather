package com.ex.weatherapp.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.ex.weatherapp.R
import com.ex.weatherapp.data.models.CurrentWeather
import com.ex.weatherapp.databinding.FragmentFirstBinding
import com.ex.weatherapp.ui.search.SearchCityBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_FavoriteFragment)
        }


        viewLifecycleOwner.lifecycleScope.launch {
                viewModel.uiState.collect{ uiState ->
                    uiState.weather?.let { setupWeather(it) }
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    setupFavorite(uiState.isFavorite)
                }
            }
        }

        _binding?.imgFavorite?.setOnClickListener{
            viewModel.addDelFavorite()
        }

        binding.btnSearch.setOnClickListener{
            showSearchCityBottomSheetDialog()
        }

    }

    private fun showSearchCityBottomSheetDialog() {
        val searchCityBottomSheetDialog = SearchCityBottomSheetDialog()
        searchCityBottomSheetDialog.show(requireActivity().supportFragmentManager, "searchCityDialog")
    }


    @SuppressLint("SetTextI18n")
    fun setupWeather(weather: CurrentWeather) {
        _binding?.tvCity?.text = weather.name
        _binding?.tvTempeatur?.text = weather.main?.temp.toString() + " \u2103"
        val clouds = weather.clouds?.all
        var drawableIcon: Drawable?
        // Облачность, %
        clouds?.let {
            drawableIcon = when (it) {
                in 0..30 -> {
                    AppCompatResources.getDrawable(requireContext(), R.drawable.sunny)
                }

                in 31..70 -> {
                    AppCompatResources.getDrawable(requireContext(), R.drawable.cloudy)
                }

                else -> {
                    AppCompatResources.getDrawable(requireContext(), R.drawable.rainy)
                }
            }
            _binding?.imIcon?.setImageDrawable(drawableIcon)
            _binding?.tvDescription?.text = weather.weatherItems?.get(0)?.description
        }

        _binding?.tvTempeaturMax?.text = weather.main?.tempMax.toString() + " \u2103"
        _binding?.tvTempeaturMin?.text = weather.main?.tempMin.toString() + " \u2103"

    }

    private fun setupFavorite(isFavorite:Boolean){
        var drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.not_favorite)
        if(isFavorite){
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.favorite)
        }
        _binding?.imgFavorite?.setImageDrawable(drawable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}