package com.projects.disav.marvelissimo.ui.searchresults.comics

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
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

}