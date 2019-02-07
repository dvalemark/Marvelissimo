package com.projects.disav.marvelissimo.ui.searchresults.comics

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.projects.disav.marvelissimo.MainActivity
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.network.api.MarvelHandler
import com.projects.disav.marvelissimo.network.api.dto.comics.Comic
import com.projects.disav.marvelissimo.ui.searchresults.displayoneresult.FragmentViewOneComic
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.recyclerview.view.*


class FragmentComicList : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapterComic
    private var searchString = ""
    private var positionWhenClick = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar!!.show()

        if (savedInstanceState != null) {
            var oldQuery = savedInstanceState.getString("search")
            searchString = oldQuery
        }

        if (searchString.isEmpty()) {
            getAllComics()
        } else {
            getComicsByName(searchString)
        }


        val view = inflater.inflate(R.layout.recyclerview, container, false)

        linearLayoutManager = LinearLayoutManager(activity)
        view.my_recycler_view.layoutManager = linearLayoutManager

        adapter = RecyclerAdapterComic(clickListener = { comic: Comic, int: Int -> itemClicked(comic, int) })
        view.my_recycler_view.adapter = adapter


        view.my_recycler_view.layoutManager.scrollToPosition(positionWhenClick -1)


        view.my_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)


                if (!recyclerView.canScrollVertically(1)&& adapter.comics.size>=20) {
                    if(searchString.isEmpty()){
                        getAllComics(adapter.comics.size)
                    }
                    else{
                        getComicsByName(searchString, adapter.comics.size)
                    }

                }
            }
        })



        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val searchItem = menu.findItem(com.projects.disav.marvelissimo.R.id.search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        searchString = query
                        adapter.comics.clear()

                        getComicsByName(query)
                    }
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newQuery: String?): Boolean {

                    return true
                }
            })

        }

        super.onPrepareOptionsMenu(menu)
    }

    private fun itemClicked(comic: Comic, position: Int) {
        positionWhenClick = position
        var activity = activity as MainActivity
        activity.navigateToFragment(FragmentViewOneComic.newInstance(comic.id))

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("search", searchString)
    }

    fun getComicsByName(query: String, offset: Int=0){
        MarvelHandler.service.getComicsByTitleStartingWith(searchString.toLowerCase(), offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ wrapper ->
                adapter.comics.addAll( wrapper.data.results)
                adapter.notifyDataSetChanged()
            }
    }

    fun getAllComics(offset: Int = 0){
        MarvelHandler.service.getAllComic(offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper ->
                adapter.comics.addAll( wrapper.data.results)
                adapter.notifyDataSetChanged()
            }
    }
}