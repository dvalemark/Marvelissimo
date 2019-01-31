package com.projects.disav.marvelissimo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.projects.disav.marvelissimo.ResultLayout.MyActivity
import com.projects.disav.marvelissimo.ResultLayout.FragmentListComicCharacters
import com.projects.disav.marvelissimo.network.api.MarvelHandler
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* MarvelHandler.service.getAllCharacters()
           .subscribeOn(Schedulers.io())
           .subscribe{wrapper-> println("it worked")}*/


        button_click.setOnClickListener {
            // Handler code here.
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_content, FragmentListComicCharacters())
            transaction.commit()
        }


    }

}
