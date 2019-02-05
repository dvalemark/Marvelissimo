package com.projects.disav.marvelissimo.ui.searchresults.characters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.projects.disav.marvelissimo.MainActivity
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.network.api.MarvelHandler
import com.projects.disav.marvelissimo.network.api.dto.characters.Character
import com.projects.disav.marvelissimo.ui.FragmentViewOneCharacter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.recyclerview.view.*

class FragmentCharacterList : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapterCharacter
    private var searchString = ""


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

            MarvelHandler.service.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { wrapper ->
                    adapter.characters = wrapper.data.results
                    adapter.notifyDataSetChanged()
                }

        } else {
            MarvelHandler.service.getCharactersByNameStartingWith(searchString.toLowerCase())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { wrapper ->
                    adapter.characters = wrapper.data.results
                    adapter.notifyDataSetChanged()
                }
        }


        val view = inflater.inflate(R.layout.recyclerview, container, false)

        linearLayoutManager = LinearLayoutManager(activity)
        view.my_recycler_view.layoutManager = linearLayoutManager

        adapter = RecyclerAdapterCharacter(clickListener = { character: Character -> itemClicked(character) })
        view.my_recycler_view.adapter = adapter

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        searchString = query

                        MarvelHandler.service.getCharactersByNameStartingWith(query.toLowerCase())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { wrapper ->
                                adapter.characters = wrapper.data.results
                                adapter.notifyDataSetChanged()
                            }
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

    private fun itemClicked(character: Character) {
        var activity = activity as MainActivity
        activity.navigateToFragment(FragmentViewOneCharacter.newInstance(character.id))

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("search", searchString)
    }
}