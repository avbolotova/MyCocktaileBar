package com.example.mycocktailebar.presentation.cocktails.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycocktailebar.R
import com.example.mycocktailebar.databinding.CocktailItemBinding
import com.example.mycocktailebar.domain.models.Cocktail

class CocktailsAdapter(
    private val context: Context,
    private val actionListener: CocktailActionListener
) : ListAdapter<Cocktail, CocktailsAdapter.CocktailViewHolder>(DiffCallBack), View.OnClickListener {

    class CocktailViewHolder(val binding: CocktailItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CocktailItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.cocktailCard.setOnClickListener(this)
        return CocktailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            cocktailImage.setImageResource(R.drawable.mohito_image)
            cocktailName.text = item.name
            root.tag = item
            cocktailCard.tag = item
        }
    }

    override fun onClick(v: View?) {
        val cocktail = v?.tag as Cocktail
        when (v.id) {
            R.id.cocktail_card -> actionListener.onClickItem(cocktail)
            else -> actionListener.onClickItem(cocktail)
        }
    }

    interface CocktailActionListener {
        fun onClickItem(cocktail: Cocktail)
    }

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<Cocktail>() {

            override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
                return oldItem == newItem
            }
        }
    }

}