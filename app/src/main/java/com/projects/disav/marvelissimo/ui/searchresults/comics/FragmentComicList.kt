package com.projects.disav.marvelissimo.ui.searchresults.comics

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.Toast
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.network.api.MarvelHandler
import com.projects.disav.marvelissimo.network.api.dto.comics.Comic
import com.projects.disav.marvelissimo.network.api.dto.comics.ComicsDataWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.recyclerview.view.*


class FragmentComicList: Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapterComic

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)


        MarvelHandler.service.getAllComic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper -> adapter.comics = wrapper.data.results
            adapter.notifyDataSetChanged()}


        (activity as AppCompatActivity).supportActionBar!!.show()

        val view = inflater.inflate(R.layout.recyclerview, container, false)

        linearLayoutManager = LinearLayoutManager(activity)
        view.my_recycler_view.layoutManager = linearLayoutManager

        adapter = RecyclerAdapterComic()
        view.my_recycler_view.adapter = adapter

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.search)
        if(searchItem != null){
            val searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(query != null){
                        MarvelHandler.service.getComicsByTitleStartingWith(query.toLowerCase())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe ({wrapper -> adapter.comics = wrapper.data.results
                            adapter.notifyDataSetChanged()},{
                                fun Context.toast(message: CharSequence) =
                                    Toast.makeText(this, "Nu such comic, try again!", Toast.LENGTH_SHORT).show()
                            })
                    }
                    return true
                }

                override fun onQueryTextChange(newQuery: String?): Boolean {

                    return true
                }
            })

        }



        super.onPrepareOptionsMenu(menu)
    }

}