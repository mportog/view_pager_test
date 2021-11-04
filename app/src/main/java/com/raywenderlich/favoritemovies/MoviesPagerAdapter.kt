package com.raywenderlich.favoritemovies

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MoviesPagerAdapter(fragmentManager: FragmentManager, private val movies: ArrayList<Movie>) :
        FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return MovieFragment.newInstance(movies[position])
    }

    override fun getCount(): Int {
        return movies.size
    }
}
