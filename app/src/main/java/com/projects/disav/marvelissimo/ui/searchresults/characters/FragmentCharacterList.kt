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
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.network.api.MarvelHandler
import com.projects.disav.marvelissimo.network.api.dto.characters.Character
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.recyclerview.view.*

class FragmentCharacterList: Fragment(){

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapterCharacter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        MarvelHandler.service.getAllCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper -> adapter.characters = wrapper.data.results
                adapter.notifyDataSetChanged()}


        (activity as AppCompatActivity).supportActionBar!!.show()

        val view = inflater.inflate(R.layout.recyclerview, container, false)

        linearLayoutManager = LinearLayoutManager(activity)
        view.my_recycler_view.layoutManager = linearLayoutManager

        adapter = RecyclerAdapterCharacter()
        view.my_recycler_view.adapter = adapter

        return view
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.search)
        if(searchItem != null){
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {

                return true
                }

                override fun onQueryTextChange(newQuery: String?): Boolean {
                    if(newQuery != null){
                        MarvelHandler.service.getCharactersByNameStartingWith(newQuery.toLowerCase())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { wrapper -> adapter.characters = wrapper.data.results
                                adapter.notifyDataSetChanged()}
                    }
                return true
                }
            })

        }



        super.onPrepareOptionsMenu(menu)
    }


}