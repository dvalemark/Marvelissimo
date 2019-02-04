package com.projects.disav.marvelissimo.ui.searchresults.characters

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.inflate
import com.projects.disav.marvelissimo.network.api.dto.characters.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.one_character_view.view.*

class AdapterShowOneCharacter(var character: Character): RecyclerView.Adapter<AdapterShowOneCharacter.CharacterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AdapterShowOneCharacter.CharacterHolder {

        val inflatedView = parent.inflate(R.layout.one_character_view, false)
        return CharacterHolder(inflatedView)
    }

    override fun getItemCount(): Int = 1


    override fun onBindViewHolder(holder: AdapterShowOneCharacter.CharacterHolder, position: Int) {
        val itemCharacter = character

        holder.bindCharacter(itemCharacter)
    }

    class CharacterHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View = v
        private var character: Character? = null

        fun bindCharacter(character: Character) {
            this.character = character
            Picasso.with(view.context).load(character.thumbnail.path+"."+character.thumbnail.extension)
                .into(view.character_image)
            view.character_title.text = character.name
            view.character_summary.text = character.description

            val websiteButton = view.go_to_website as Button

            websiteButton.setOnClickListener{
                val url = character.urls[1].url

                val uris = Uri.parse(url)
                val intents = Intent(Intent.ACTION_VIEW, uris)
                val b = Bundle()
                b.putBoolean("new_window", true)
                intents.putExtras(b)
                view.context.startActivity(intents)
            }
        }
    }

}

