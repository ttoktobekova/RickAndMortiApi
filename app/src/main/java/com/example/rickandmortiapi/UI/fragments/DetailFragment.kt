package com.example.rickandmortiapi.UI.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.rickandmortiapi.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val binding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val character = args.character
        binding.apply {
            tvId.text = character.id
            tvStatus.text = character.status
            imgCharacterDetail.load(character.image)
            tvName.text = character.species
            tvGender.text = character.gender
//            tvNumEpisodes.text = character.episode.size.toString()
            tvOriginDic.text = character.origin.name
            tvLocationDic.text = character.location.name

            imgBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }


}