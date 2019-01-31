package com.projects.disav.marvelissimo.ResultLayout

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.projects.disav.marvelissimo.R
import kotlinx.android.synthetic.main.recyclerview.*
import com.projects.disav.marvelissimo.network.api.dto.characters.Character
import com.projects.disav.marvelissimo.network.api.dto.characters.Thumbnail


class MyActivity : Activity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    var charactersList = mutableListOf<Character>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        charactersList.add(Character(123,"Deadpool", Thumbnail("https://m.media-amazon.com/images/S/aplus-media/sota/589a3469-2183-4eee-9840-05bc57475126._SR285,285_.png")))
        charactersList.add(Character(234,"Marvel", Thumbnail("https://m.media-amazon.com/images/S/aplus-media/sota/589a3469-2183-4eee-9840-05bc57475126._SR285,285_.png")))

        setContentView(R.layout.recyclerview)

        linearLayoutManager = LinearLayoutManager(this)
        my_recycler_view.layoutManager = linearLayoutManager

        adapter = RecyclerAdapter(charactersList)
        my_recycler_view.adapter = adapter

        }

    override fun onStart(){
        super.onStart()

    }


    }
