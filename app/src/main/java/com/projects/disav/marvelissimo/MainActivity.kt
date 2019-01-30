package com.projects.disav.marvelissimo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.projects.disav.marvelissimo.network.api.MarvelHandler
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         MarvelHandler.service.getAllCharacters()
           .subscribeOn(Schedulers.io())
           .subscribe{wrapper-> println("it worked")}
    }
}
