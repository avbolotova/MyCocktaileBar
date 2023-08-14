package com.example.mycocktailebar.presentation.cocktails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mycocktailebar.databinding.FragmentCocktailsBinding
import com.example.mycocktailebar.domain.models.Cocktail
import com.example.mycocktailebar.presentation.cocktails.adapters.CocktailsAdapter
import com.example.mycocktailebar.presentation.cocktails.models.ScreenState

class CocktailsFragment : Fragment() {

    private var _binding: FragmentCocktailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CocktailsViewModel
    private lateinit var cocktailsAdapter: CocktailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCocktailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        viewModel.getAllCocktails()

        viewModel.cocktailsList.observe(viewLifecycleOwner) { cocktailsList ->
            cocktailsAdapter.submitList(cocktailsList)
        }

        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->

            when (screenState) {
                ScreenState.Content -> showContent()
                ScreenState.Empty -> showEmptyScreen()
                ScreenState.Error -> showError()
                ScreenState.Loading -> showLoading()
            }
        }

        binding.fabAdd.setOnClickListener {
            val action = CocktailsFragmentDirections.actionCocktailsFragmentToCreationFragment()
            findNavController().navigate(action)
        }

    }

    private fun setRecyclerView() {

        cocktailsAdapter =
            CocktailsAdapter(requireContext(), object : CocktailsAdapter.CocktailActionListener {

                override fun onClickItem(cocktail: Cocktail) {
                    val action =
                        CocktailsFragmentDirections.actionCocktailsFragmentToDetailsFragment(
                            cocktail
                        )
                    findNavController().navigate(action)
                }
            })

        binding.recyclerView.adapter = cocktailsAdapter
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun showContent() {
        with(binding) {
            noContentLayout.visibility = View.GONE
            contentLayout.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            errorLayout.visibility = View.GONE
        }
    }

    private fun showLoading() {
        with(binding) {
            noContentLayout.visibility = View.GONE
            contentLayout.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            errorLayout.visibility = View.GONE
        }
    }

    private fun showEmptyScreen() {
        with(binding) {
            noContentLayout.visibility = View.VISIBLE
            contentLayout.visibility = View.GONE
            progressBar.visibility = View.GONE
            errorLayout.visibility = View.GONE
        }
    }

    private fun showError() {
        with(binding) {
            noContentLayout.visibility = View.GONE
            contentLayout.visibility = View.GONE
            progressBar.visibility = View.GONE
            errorLayout.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}