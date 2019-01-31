package com.projects.disav.marvelissimo.ResultLayout

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.network.api.dto.characters.Character
import com.projects.disav.marvelissimo.network.api.dto.characters.Thumbnail
import kotlinx.android.synthetic.main.recyclerview.*
import kotlinx.android.synthetic.main.recyclerview.view.*

class FragmentListComicCharacters: Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter
    var charactersList = mutableListOf<Character>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        charactersList.add(Character(123,"Deadpool", Thumbnail("https://m.media-amazon.com/images/S/aplus-media/sota/589a3469-2183-4eee-9840-05bc57475126._SR285,285_.png")))
        charactersList.add(Character(234,"Marvel", Thumbnail("https://m.media-amazon.com/images/S/aplus-media/sota/589a3469-2183-4eee-9840-05bc57475126._SR285,285_.png")))


        val view = inflater.inflate(R.layout.recyclerview, container, false)

        linearLayoutManager = LinearLayoutManager(activity)
        view.my_recycler_view.layoutManager = linearLayoutManager

        adapter = RecyclerAdapter(charactersList)
        view.my_recycler_view.adapter = adapter

        return view
    }

}