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
import com.projects.disav.marvelissimo.MainActivity
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.network.api.MarvelHandler
import com.projects.disav.marvelissimo.network.api.dto.characters.Character
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.recyclerview.view.*
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.ViewUtils
import com.projects.disav.marvelissimo.ui.searchresults.viewoneresult.character.FragmentViewOneCharacter





class FragmentCharacterList : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapterCharacter
    private var searchString: String = ""
    private var results = mutableListOf<Character>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view?.my_recycler_view?.clearFocus()

            setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar!!.show()



        val view = inflater.inflate(R.layout.recyclerview, container, false)


        linearLayoutManager = LinearLayoutManager(activity)
        view.my_recycler_view.layoutManager = linearLayoutManager



        if(results.size == 0){
            getAllCharacters()
            adapter = RecyclerAdapterCharacter(clickListener = { character: Character -> itemClicked(character) })
        }
        else if (savedInstanceState != null) {
            searchString = savedInstanceState!!.getString("search")
            getCharactersNameStartBy(searchString, view=view)
            adapter = RecyclerAdapterCharacter(clickListener = { character: Character -> itemClicked(character) })
        }
        else{
            adapter = RecyclerAdapterCharacter(results, clickListener = { character: Character -> itemClicked(character) })
        }

        view.my_recycler_view.adapter = adapter

        view.my_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)


                if (!recyclerView.canScrollVertically(1)&& adapter.characters.size>=20) {
                    if(searchString.isEmpty()){
                        getAllCharacters(adapter.characters.size)
                    }
                    else{
                        getCharactersNameStartBy(searchString, adapter.characters.size, view)
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
        val searchItem = menu.findItem(R.id.search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        searchString = query
                        adapter.characters.clear()
                        results.clear()
                        adapter.notifyDataSetChanged()
                        recyclerVisibility(true)

                        getCharactersNameStartBy(query, view =view)
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

        (activity as MainActivity).navigateToFragment(FragmentViewOneCharacter.newInstance(character.id))

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("search", searchString)
    }

    fun getCharactersNameStartBy( query: String, offset:Int=0, view: View?){
        MarvelHandler.service.getCharactersByNameStartingWith(query.toLowerCase(), offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper ->
                adapter.characters.addAll(wrapper.data.results)
                results.addAll(wrapper.data.results)
                adapter.notifyDataSetChanged()

                if(results.isEmpty()){
                    view?.empty_view?.text="No available results for ${searchString}, try again!"
                    recyclerVisibility(false)

                }

            }


    }

    fun getAllCharacters(offset: Int=0){
        MarvelHandler.service.getAllCharacters(offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper ->
                adapter.characters.addAll(wrapper.data.results)
                results.addAll(wrapper.data.results)
                adapter.notifyDataSetChanged()
            }
    }

    fun recyclerVisibility( bool: Boolean){
        if(bool) view?.my_recycler_view?.visibility = View.VISIBLE else view?.my_recycler_view?.visibility = View.GONE
    }


}