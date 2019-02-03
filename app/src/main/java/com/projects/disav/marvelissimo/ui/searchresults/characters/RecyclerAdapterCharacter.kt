package com.projects.disav.marvelissimo.ui.searchresults.characters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.inflate
import com.squareup.picasso.Picasso
import com.projects.disav.marvelissimo.network.api.dto.characters.Character
import kotlinx.android.synthetic.main.card_for_list_character.view.*
import kotlinx.android.synthetic.main.card_for_list_comic.view.*

class RecyclerAdapterCharacter( var characters: List<Character> =listOf()): RecyclerView.Adapter<RecyclerAdapterCharacter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterCharacter.ItemHolder {

        val inflatedView = parent.inflate(R.layout.card_for_list_character, false)
        return ItemHolder(inflatedView)
    }


    override fun getItemCount(): Int = characters.size


    override fun onBindViewHolder(holder: RecyclerAdapterCharacter.ItemHolder, position: Int) {
        val itemCharacter = characters[position]
        holder.bindPhoto(itemCharacter)
    }


    class ItemHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var character: Character? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {

        }

        companion object {
            //5
            private val CHARACTER_KEY = "Character"
        }

        fun bindPhoto(character: Character) {
            this.character = character
            Picasso.with(view.context).load(character.thumbnail.path+"."+character.thumbnail.extension)
                .into(view.characterImage)
            view.characterName.text = character.name
        }
    }
}