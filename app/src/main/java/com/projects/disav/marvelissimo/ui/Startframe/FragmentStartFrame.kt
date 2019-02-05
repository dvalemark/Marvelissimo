package com.projects.disav.marvelissimo.ui.Startframe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.disav.marvelissimo.R
import com.projects.disav.marvelissimo.ui.searchresults.characters.FragmentCharacterList
import com.projects.disav.marvelissimo.ui.searchresults.comics.FragmentComicList
import kotlinx.android.synthetic.main.start_view.view.*
import android.support.v7.app.AppCompatActivity
import com.projects.disav.marvelissimo.MainActivity


class FragmentStartFrame: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(com.projects.disav.marvelissimo.R.layout.start_view, container, false)

        (activity as AppCompatActivity).supportActionBar!!.hide()

        view.character_bg.setOnClickListener {
           var activity = activity as MainActivity

            activity.navigateToFragment(FragmentCharacterList())
        }

        view.comic_bg.setOnClickListener{
            var activity = activity as MainActivity

            activity.navigateToFragment(FragmentComicList())
        }


        return view
    }


}