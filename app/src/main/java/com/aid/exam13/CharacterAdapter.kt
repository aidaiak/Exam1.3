package com.aid.exam13

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aid.exam13.database.Character
import com.aid.exam13.databinding.ItemCharacterslistBinding
import com.bumptech.glide.Glide

class CharacterAdapter(
    private val onItemClicked: (Long) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_characterslist, parent, false)
        return ViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder -> holder.bind(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

    fun setItems(it: List<Item>) {
        data.clear()
        data.addAll(it)
        notifyDataSetChanged()
    }
}

private class ViewHolder(
    view: View,
    private val onItemClicked: (Long) -> Unit
): RecyclerView.ViewHolder(view) {
    private val binding = ItemCharacterslistBinding.bind(view)

    fun bind(character: Item) = with(binding) {
        Glide.with(itemView)
            .load(character.image)
            .into(imageView)
        textView.text = character.name
        statusTextView.text = character.status
        speciesTextView.text = character.species
        locationTextView.text = character.location.name
        itemView.setOnClickListener {
            onItemClicked(character.id)
        }
    }
}