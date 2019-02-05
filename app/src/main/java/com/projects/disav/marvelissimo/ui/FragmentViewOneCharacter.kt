package com.projects.disav.marvelissimo.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.network.api.MarvelHandler
import com.projects.disav.marvelissimo.network.api.dto.characters.Character
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.one_character_view.view.*

class FragmentViewOneCharacter(): Fragment(){

    companion object {
        fun newInstance(id : Int): Fragment{
       val fragment = FragmentViewOneCharacter()
        val bundle = Bundle()
        bundle.putInt("index", id)
        fragment.arguments=bundle
        return fragment
    }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val id = arguments!!.getInt("index")
        var url = ""


        val view = inflater.inflate(R.layout.one_character_view, container, false)

        MarvelHandler.service.getOneCharacter(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper -> val character = wrapper.data.results.get(0)
                 view.character_title.text = character.name
                view.character_summary.text = character.description
                Picasso.with(view.context).load(character.thumbnail.path+"."+character.thumbnail.extension)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(view.character_image)
                url = character.urls.get(1).url
            }

        val websiteButton = view.go_to_website as Button

        websiteButton.setOnClickListener{
            val uris = Uri.parse(url)
            val intents = Intent(Intent.ACTION_VIEW, uris)
            val b = Bundle()
            b.putBoolean("new_window", true)
            intents.putExtras(b)
            view.context.startActivity(intents)
        }

        return view
    }



}

