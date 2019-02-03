package com.projects.disav.marvelissimo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.projects.disav.marvelissimo.ui.searchresults.characters.FragmentCharacterList
import com.projects.disav.marvelissimo.network.api.MarvelHandler
import com.projects.disav.marvelissimo.ui.searchresults.comics.FragmentComicList
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        character_bg.setOnClickListener {
            // Handler code here.
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_content, FragmentCharacterList())
            transaction.commit()
        }

        comic_bg.setOnClickListener{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_content, FragmentComicList())
            transaction.commit()
        }




    }





}
