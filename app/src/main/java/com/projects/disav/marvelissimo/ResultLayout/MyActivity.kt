package com.projects.disav.marvelissimo.ResultLayout

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.projects.disav.marvelissimo.R
import kotlinx.android.synthetic.main.recyclerview.*


class MyActivity : Activity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (charactersList.size == 0) {
            requestCharacter()
        }
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
