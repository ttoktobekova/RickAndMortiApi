package com.example.rickandmortiapi.UI.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortiapi.UI.fragments.ListFragmentDirections
import com.example.rickandmortiapi.databinding.ItemListBinding
import com.example.rickandmortiapi.model.Character

@Suppress("UNUSED_EXPRESSION")
class CharacterAdapter :
    ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))

    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setCharacter(characters: List<Character>) {
//        characters
//        notifyDataSetChanged()
//    }

    class CharacterViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.apply {
                tvIndex.text = character.id.toString()
                tvNameCharacter.text = character.name
                imgRick.load(character.image)
                tvStatus.text = character.status

                itemView.setOnClickListener { view ->
                    val action =
                        ListFragmentDirections.actionListFragmentToDetailFragment(character)
                    view.findNavController().navigate(action)
                }
            }
        }
    }

    class CharacterItemCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }
}