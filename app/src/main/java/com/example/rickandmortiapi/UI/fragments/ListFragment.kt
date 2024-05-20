package com.example.rickandmortiapi.UI.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmortiapi.R
import com.example.rickandmortiapi.UI.adapter.CharacterAdapter
import com.example.rickandmortiapi.DI.viewModel.SharedViewModel
import com.example.rickandmortiapi.databinding.FragmentListBinding
import com.example.rickandmortiapi.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val adapter = CharacterAdapter()
    private val binding by lazy { FragmentListBinding.inflate(layoutInflater) }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getCharactersFromViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initClickers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvView.adapter = adapter
    }

    @SuppressLint("StringFormatMatches")
    private fun observe() {
        sharedViewModel.listCharacterLD.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvApiError.visibility = View.GONE
                    binding.rvView.visibility = View.INVISIBLE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.submitList(resource.data.results)
                    binding.tvApiError.visibility = View.GONE
                    binding.rvView.visibility = View.VISIBLE
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvApiError.text = getString(R.string.error, resource.message)
                    binding.tvApiError.visibility = View.VISIBLE
                    binding.rvView.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        sharedViewModel.isFilter.observe(viewLifecycleOwner) { filter ->
            binding.tvActionReset.visibility = if (filter) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun initClickers() {
        binding.apply {
            btnFilter.setOnClickListener {
                findNavController().navigate(R.id.action_listFragment_to_filterFragment)
            }
//            tvTitleCharacter.setOnClickListener {
//                findNavController().navigate(R.id.action_listFragment_to_detailFragment)
//            }
            tvActionReset.setOnClickListener {
                getCharactersFromViewModel()
                sharedViewModel.filterValue.value = arrayOf(0, 0)
            }
        }
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    sharedViewModel.getByName(it)
                    binding.searchView.setQuery("", false)
                    binding.searchView.clearFocus()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun getCharactersFromViewModel() {
        sharedViewModel.getCharacters(1)
    }
}
