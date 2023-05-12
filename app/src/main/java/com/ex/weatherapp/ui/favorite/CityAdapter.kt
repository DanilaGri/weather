package com.ex.weatherapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ex.weatherapp.data.models.City
import com.ex.weatherapp.databinding.ItemFavoriteBinding


class CityAdapter(
    private val mOnCityClickListener: OnCityClickListener
) : ListAdapter<City, CityAdapter.ConstructionViewHolder>(DiffCallback)
{

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<City>() {
            override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstructionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteBinding.inflate(inflater, parent, false)
        return ConstructionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConstructionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ConstructionViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City) {
            binding.apply {
                itFavoriteName.text = city.name

                itFavoriteDelete.setOnClickListener {
                    mOnCityClickListener.onDelete(adapterPosition)
                }
            }
        }
    }

    interface OnCityClickListener {
        fun onDelete(position: Int)
    }
}