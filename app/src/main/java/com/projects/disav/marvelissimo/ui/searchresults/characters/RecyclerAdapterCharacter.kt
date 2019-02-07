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

class RecyclerAdapterCharacter( var characters: MutableList<Character> = mutableListOf(), val clickListener: (Character) -> Unit): RecyclerView.Adapter<RecyclerAdapterCharacter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterCharacter.ItemHolder {

        val inflatedView = parent.inflate(R.layout.card_for_list_character, false)
        return ItemHolder(inflatedView)
    }


    override fun getItemCount(): Int = characters.size


    override fun onBindViewHolder(holder: RecyclerAdapterCharacter.ItemHolder, position: Int) {
        val itemCharacter = characters[position]

        if(characters.isEmpty()){
            holder.noResults()
        }

        holder.bindCharacter(itemCharacter, clickListener)
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

        fun bindCharacter(character: Character, clickListener: (Character) -> Unit ) {
            this.character = character
            var uri = character.thumbnail.path
            uri+="."
            uri+=character.thumbnail.extension
            Picasso.with(view.context).load(uri).placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(view.character_image_recyclerview)
            view.characterName.text = character.name
            view.setOnClickListener { clickListener(character)}
        }

        fun noResults(){
            Picasso.with(view.context).load(R.mipmap.ic_launcher_round)
                .into(view.character_image_recyclerview)
            view.characterName.text = "No results available, please try again!"

        }
    }
}