package com.example.mycocktailebar.presentation.creation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mycocktailebar.R
import com.example.mycocktailebar.databinding.FragmentCreationBinding
import com.example.mycocktailebar.domain.models.Cocktail
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreationFragment : Fragment() {

    private lateinit var _binding: FragmentCreationBinding
    private val viewModel: CreationViewModel by viewModel()
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
    ): View {
        _binding = FragmentCreationBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (cocktailId > 0) {
            viewModel.getCocktail(cocktailId)
            viewModel.cocktail.observe(viewLifecycleOwner) {
                bindViews(it)
            }
            _binding.buttonSave.setOnClickListener { saveCocktail(cocktailId) }

        } else {
            _binding.buttonSave.setOnClickListener { saveCocktail(cocktailId) }
        }

        _binding.buttonCancel.setOnClickListener { findNavController().navigateUp() }

        clearErrorMessage(_binding.editCocktailIngredients)
        clearErrorMessage(_binding.editCocktailName)

    }

    private fun isInputValid(): Boolean {
        return viewModel.isInputIsValid(
            name = _binding.editCocktailName.text.toString(),
            ingredients = _binding.editCocktailIngredients.text.toString()
        )
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(_binding.scrollView.windowToken, 0)
    }

    private fun showErrorMessage(view: TextInputEditText) {

        if (view.text?.isBlank() == true) {
            when (view) {
                _binding.editCocktailName -> _binding.layoutCocktailName.error =
                    getString(R.string.add_title)

                else -> _binding.layoutCocktailIngredients.error = getString(R.string.add_title)
            }
            _binding.scrollView.smoothScrollTo(0, 0)
            hideKeyboard()
        }
    }

    private fun clearErrorMessage(view: TextInputEditText) {

        view.addTextChangedListener {
            if (it?.isNotBlank() == true) {
                when (view) {
                    _binding.editCocktailName -> _binding.layoutCocktailName.error = null
                    else -> _binding.layoutCocktailIngredients.error = null
                }
            }
        }
    }

    private fun bindViews(cocktail: Cocktail) {

        _binding.apply {
            editCocktailName.setText(cocktail.name, TextView.BufferType.SPANNABLE)
            editCocktailDescription.setText(cocktail.description, TextView.BufferType.SPANNABLE)
            editCocktailIngredients.setText(cocktail.ingredients, TextView.BufferType.SPANNABLE)
            editCocktailRecipe.setText(cocktail.recipe)
        }
    }


    companion object {
        const val ID = "id"
    }

    private fun saveCocktail(cocktailId: Int) {

        if (isInputValid()) {

            if (cocktailId > 0) {
                viewModel.editCocktail(
                    id = cocktailId,
                    name = _binding.editCocktailName.text.toString(),
                    description = _binding.editCocktailDescription.text.toString(),
                    ingredients = _binding.editCocktailIngredients.text.toString(),
                    recipe = _binding.editCocktailRecipe.text.toString()
                )

                cocktail = Cocktail(
                    id = cocktailId,
                    name = _binding.editCocktailName.text.toString(),
                    description = _binding.editCocktailDescription.text.toString(),
                    ingredients = _binding.editCocktailIngredients.text.toString(),
                    recipe = _binding.editCocktailRecipe.text.toString(),
                    imageSrc = 0
                )

                val action =
                    CreationFragmentDirections.actionCreationFragmentToDetailsFragment(cocktail)
                findNavController().navigate(action)

            } else {
                viewModel.addNewCocktail(
                    name = _binding.editCocktailName.text.toString(),
                    description = _binding.editCocktailDescription.text.toString(),
                    ingredients = _binding.editCocktailIngredients.text.toString(),
                    recipe = _binding.editCocktailRecipe.text.toString()
                )
                val action = CreationFragmentDirections.actionCreationFragmentToCocktailsFragment()
                findNavController().navigate(action)
            }


        } else {
            showErrorMessage(_binding.editCocktailName)
            showErrorMessage(_binding.editCocktailIngredients)

        }

    }
}