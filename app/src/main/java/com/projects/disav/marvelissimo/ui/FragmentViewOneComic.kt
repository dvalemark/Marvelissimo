package com.projects.disav.marvelissimo.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.network.api.MarvelHandler
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.one_comic_view.view.*

class FragmentViewOneComic(): Fragment(){

    companion object {
        fun newInstance(id : Int): Fragment {
            val fragment = FragmentViewOneComic()
            val bundle = Bundle()
            bundle.putInt("index", id)
            fragment.arguments=bundle
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val id = arguments!!.getInt("index")
        var url = ""

        (activity as AppCompatActivity).supportActionBar!!.hide()

        val view = inflater.inflate(R.layout.one_comic_view, container, false)

        MarvelHandler.service.getOneComic(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { wrapper -> val comic = wrapper.data.results.get(0)
                view.comic_title.text = comic.title
                view.comic_summary.text = comic.description
                Picasso.with(view.context).load(comic.thumbnail.path+"."+comic.thumbnail.extension)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(view.comic_image)
                url = comic.urls.get(0).url
            }, {fun Context.toast(message: CharSequence) =
                Toast.makeText(this, "Nu such comic, try again!", Toast.LENGTH_SHORT).show()
            })

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