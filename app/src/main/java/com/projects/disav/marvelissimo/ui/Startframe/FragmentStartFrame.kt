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


class FragmentStartFrame: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(com.projects.disav.marvelissimo.R.layout.start_view, container, false)


        view.character_bg.setOnClickListener {
            val newfragment = FragmentCharacterList()
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_content, newfragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        view.comic_bg.setOnClickListener{
            val newfragment = FragmentComicList()
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_content, newfragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }


}