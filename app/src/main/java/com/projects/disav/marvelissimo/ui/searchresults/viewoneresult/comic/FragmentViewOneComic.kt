package com.projects.disav.marvelissimo.ui.searchresults.viewoneresult.comic

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.Toast
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.network.api.MarvelHandler
import com.projects.disav.marvelissimo.network.api.dto.comics.Comic
import com.projects.disav.marvelissimo.network.api.dto.comics.ComicsDataContainer
import com.projects.disav.marvelissimo.network.api.dto.comics.ComicsDataWrapper
import com.projects.disav.marvelissimo.ui.searchresults.viewoneresult.AdapterExpandableListView
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.one_comic_view.view.*

class FragmentViewOneComic: Fragment(){

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
        val expandableListView = view.expandableListView

        MarvelHandler.service.getOneComic(id)
            .subscribeOn(Schedulers.io())
            .retry(10)
            .onErrorReturn {
                println("error : ${it.message}")
                ComicsDataWrapper(ComicsDataContainer(emptyList()))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper -> val comic = wrapper.data.results.get(0)
                view.comic_title.text = comic.title
                view.comic_summary.text = comic.description
                Picasso.with(view.context).load(comic.thumbnail.path+"."+comic.thumbnail.extension)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(view.comic_image, object : com.squareup.picasso.Callback{
                        override fun onSuccess() {
                            if(view.progress_view_one_comic_image!= null){
                                view.progress_view_one_comic_image.setVisibility(View.GONE)
                            }
                        }

                        override fun onError() {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }
                    })
                url = comic.urls.get(0).url
                createExpandableLv(getData(comic),expandableListView, view)
                view.invalidate()
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


    private fun getData(comic: Comic): HashMap<String, MutableList<String>>{
        var characterList: MutableList<String> = mutableListOf()
        var authorList: MutableList<String> = mutableListOf()
        for (item in comic.characters.items) {
            characterList.add(item.name)
        }
        for (item in comic.creators.items){
            authorList.add(item.name)
        }

        var mapOfCharacterExpLv = HashMap<String, MutableList<String>>()
        mapOfCharacterExpLv["Creators"]=authorList
        mapOfCharacterExpLv["Characters"]=characterList



        return mapOfCharacterExpLv
    }



    private fun createExpandableLv(listData : HashMap<String, MutableList<String>>, expandableListView : ExpandableListView, view : View) {
        if (expandableListView != null) {

            val titleList = ArrayList(listData.keys)
            var adapter = AdapterExpandableListView(
                view.context,
                titleList as ArrayList<String>,
                listData
            )

            expandableListView!!.setAdapter(adapter)

            expandableListView!!.setOnGroupExpandListener { groupPosition ->

            }

            expandableListView!!.setOnGroupCollapseListener { groupPosition ->

            }

            expandableListView!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->

              true
            }
        }
    }

}

