package com.example.rickandmortiapi.UI.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmortiapi.DATA.repository.CharacterRepository
import com.example.rickandmortiapi.DI.viewModel.SharedViewModel
import com.example.rickandmortiapi.DI.viewModel.SharedViewModelFactory
import com.example.rickandmortiapi.EXT.getTextButtonChecked
import com.example.rickandmortiapi.EXT.getTextChipChecked
import com.example.rickandmortiapi.EXT.setChipChecked
import com.example.rickandmortiapi.EXT.setTextButtonChecked
import com.example.rickandmortiapi.R
import com.example.rickandmortiapi.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : BottomSheetDialogFragment() {
    private val binding by lazy {
        FragmentFilterBinding.inflate(layoutInflater)
    }
    private val sharedViewModel: SharedViewModel by activityViewModels {
        (SharedViewModelFactory(
            CharacterRepository()
        ))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvClearRadio.setOnClickListener {
                radioBtnGroupGender.clearCheck()
            }
            sharedViewModel.filterValue.observe(viewLifecycleOwner) { item ->
                chipGroupStatus.setChipChecked(item[0])
                radioBtnGroupGender.setTextButtonChecked(item[1])
            }

            btnApplyFilter.setOnClickListener {
                if (chipGroupStatus.getTextChipChecked().isNotEmpty()
                    && radioBtnGroupGender.getTextButtonChecked().isNotEmpty()
                ) {
                    sharedViewModel.getByStatusAndGender(
                        chipGroupStatus.getTextChipChecked(),
                        radioBtnGroupGender.getTextButtonChecked(),
                        1
                    )
                } else {
                    if (chipGroupStatus.getTextChipChecked().isNotEmpty()) {
                        sharedViewModel.getCharactersByStatus(
                            chipGroupStatus.getTextChipChecked(),
                            1
                        )
                    } else {
                        sharedViewModel.getCharactersByGender(
                            radioBtnGroupGender.getTextButtonChecked(),
                            1
                        )
                    }
                }
                sharedViewModel.filterValue.value =
                    arrayOf(chipGroupStatus.checkedChipId, radioBtnGroupGender.checkedRadioButtonId)
                findNavController().popBackStack(R.id.listFragment, false)

            }
        }
    }

}