package com.projects.disav.marvelissimo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.projects.disav.marvelissimo.ui.searchresults.characters.FragmentCharacterList
import com.projects.disav.marvelissimo.network.api.MarvelHandler
import com.projects.disav.marvelissimo.ui.Startframe.FragmentStartFrame
import com.projects.disav.marvelissimo.ui.searchresults.comics.FragmentComicList
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_content, FragmentStartFrame())
        transaction.commit()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.comic_menu -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_content, FragmentComicList())
                transaction.commit()
                return true
            }
            R.id.character_menu ->{
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_content, FragmentCharacterList())
                transaction.commit()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}








