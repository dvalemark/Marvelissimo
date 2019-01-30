package com.projects.disav.marvelissimo.ResultLayout

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_for_list_item.view.*

import android.widget.TextView
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.inflate
import com.squareup.picasso.Picasso
import com.projects.disav.marvelissimo.network.api.dto.characters.Character

class RecyclerAdapter(private val characters: List<Character>): RecyclerView.Adapter<RecyclerAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ItemHolder {

        val inflatedView = parent.inflate(R.layout.card_for_list_item, false)
        return ItemHolder(inflatedView)
    }



    override fun getItemCount(): Int = characters.size


    override fun onBindViewHolder(holder: RecyclerAdapter.ItemHolder, position: Int) {
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

            val context = itemView.context
            val showPhotoIntent = Intent(context, ItemActivity::class.java)
            showPhotoIntent.putExtra(CHARACTER_KEY, character)
            context.startActivity(showItemIntent)
        }

        companion object {
            //5
            private val CHARACTER_KEY = "Character"
        }

        fun bindPhoto(character: Character) {
            this.character = character
            Picasso.with(view.context).load(character.thumbnail.path).into(view.itemImage)
            view.itemName.text = character.name
            view.itemDescription.text = character.id
        }
    }
}