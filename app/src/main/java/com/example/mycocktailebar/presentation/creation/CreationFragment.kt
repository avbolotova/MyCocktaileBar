package com.example.mycocktailebar.presentation.creation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.mycocktailebar.R
import com.example.mycocktailebar.databinding.FragmentCreationBinding
import com.example.mycocktailebar.domain.models.Cocktail
import com.google.android.material.textfield.TextInputEditText

class CreationFragment : Fragment() {

    private var _binding: FragmentCreationBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CreationViewModel
    private var cocktailId = -1
    private lateinit var cocktail: Cocktail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cocktailId = it.getInt(ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (cocktailId > 0) {
            viewModel.getCocktail(cocktailId)
            viewModel.cocktail.observe(viewLifecycleOwner) {
                bindViews(it)
            }
            binding.buttonSave.setOnClickListener { saveCocktail(cocktailId) }

        } else {
            binding.buttonSave.setOnClickListener { saveCocktail(cocktailId) }
        }

        binding.buttonCancel.setOnClickListener { findNavController().navigateUp() }

        clearErrorMessage(binding.editCocktailIngredients)
        clearErrorMessage(binding.editCocktailName)

    }

    private fun isInputValid(): Boolean {
        return viewModel.isInputIsValid(
            name = binding.editCocktailName.text.toString(),
            ingredients = binding.editCocktailIngredients.text.toString()
        )
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.scrollView.windowToken, 0)
    }

    private fun showErrorMessage(view: TextInputEditText) {

        if (view.text?.isBlank() == true) {
            when (view) {
                binding.editCocktailName -> binding.layoutCocktailName.error =
                    getString(R.string.add_title)

                else -> binding.layoutCocktailIngredients.error = getString(R.string.add_title)
            }
            binding.scrollView.smoothScrollTo(0, 0)
            hideKeyboard()
        }
    }

    private fun clearErrorMessage(view: TextInputEditText) {

        view.addTextChangedListener {
            if (it?.isNotBlank() == true) {
                when (view) {
                    binding.editCocktailName -> binding.layoutCocktailName.error = null
                    else -> binding.layoutCocktailIngredients.error = null
                }
            }
        }
    }

    private fun bindViews(cocktail: Cocktail) {

        binding.apply {
            editCocktailName.setText(cocktail.name, TextView.BufferType.SPANNABLE)
            editCocktailDescription.setText(cocktail.description, TextView.BufferType.SPANNABLE)
            editCocktailIngredients.setText(cocktail.ingredients, TextView.BufferType.SPANNABLE)
            editCocktailRecipe.setText(cocktail.recipe)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ID = "id"
    }

    private fun saveCocktail(cocktailId: Int) {

        if (isInputValid()) {

            if (cocktailId > 0) {
                viewModel.editCocktail(
                    id = cocktailId,
                    name = binding.editCocktailName.text.toString(),
                    description = binding.editCocktailDescription.text.toString(),
                    ingredients = binding.editCocktailIngredients.text.toString(),
                    recipe = binding.editCocktailRecipe.text.toString()
                )

                cocktail = Cocktail(
                    id = cocktailId,
                    name = binding.editCocktailName.text.toString(),
                    description = binding.editCocktailDescription.text.toString(),
                    ingredients = binding.editCocktailIngredients.text.toString(),
                    recipe = binding.editCocktailRecipe.text.toString(),
                    imageSrc = 0
                )

                val action =
                    CreationFragmentDirections.actionCreationFragmentToDetailsFragment(cocktail)
                findNavController().navigate(action)

            } else {
                viewModel.addNewCocktail(
                    name = binding.editCocktailName.text.toString(),
                    description = binding.editCocktailDescription.text.toString(),
                    ingredients = binding.editCocktailIngredients.text.toString(),
                    recipe = binding.editCocktailRecipe.text.toString()
                )
                val action = CreationFragmentDirections.actionCreationFragmentToCocktailsFragment()
                findNavController().navigate(action)
            }


        } else {
            showErrorMessage(binding.editCocktailName)
            showErrorMessage(binding.editCocktailIngredients)

        }

    }
}