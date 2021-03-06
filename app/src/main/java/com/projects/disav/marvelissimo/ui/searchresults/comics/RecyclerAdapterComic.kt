package com.projects.disav.marvelissimo.ui.searchresults.comics

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.inflate
import com.projects.disav.marvelissimo.network.api.dto.comics.Comic
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_for_list_comic.view.*
import kotlinx.android.synthetic.main.card_for_list_character.view.*

class RecyclerAdapterComic (var comics: MutableList<Comic> = mutableListOf(), val clickListener: (Comic) -> Unit): RecyclerView.Adapter<RecyclerAdapterComic.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterComic.ItemHolder {

        val inflatedView = parent.inflate(R.layout.card_for_list_comic, false)
        return ItemHolder(inflatedView)
    }



    override fun getItemCount(): Int = comics.size


    override fun onBindViewHolder(holder: RecyclerAdapterComic.ItemHolder, position: Int) {
        val itemComic = comics[position]
        holder.bindComic(itemComic,clickListener)
    }


    class ItemHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var comic: Comic? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {

            //onClick
        }

        companion object {
            //5
            private val COMIC_KEY = "Comic"
        }

        fun bindComic(comic: Comic, clickListener: (Comic) -> Unit) {
            this.comic = comic
            Picasso.with(view.context).load(comic.thumbnail.path+"."+comic.thumbnail.extension ).into(view.comicImage, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    if (view.progress_comic_image != null) {
                        view.progress_comic_image.setVisibility(View.GONE)
                    }
                }

                override fun onError() {

                }
            })
            view.comicTitle.text = comic.title
            view.setOnClickListener { clickListener(comic)}
        }
    }
}