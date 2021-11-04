/*
 * Copyright (c) 2017 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.favoritemovies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Get the list of movies from the JSON file
    val movies = MovieHelper.getMoviesFromJson("movies.json", this)
    var moviesIndex = 0

    replaceFragment(movies, moviesIndex)

    val prevButton = findViewById<Button>(R.id.prevButton)
    val nextButton = findViewById<Button>(R.id.nextButton)

    // Disable the Prev Button when showing the first movie
    prevButton.isEnabled = false

    prevButton.setOnClickListener {
      if (moviesIndex > 0) {
        moviesIndex--

        replaceFragment(movies, moviesIndex)

        if (moviesIndex == 0) {
          prevButton.isEnabled = false
        }
        if (moviesIndex == movies.size - 2) {
          nextButton.isEnabled = true
        }
      }
    }

    nextButton.setOnClickListener {
      if (moviesIndex < movies.size - 1) {
        moviesIndex++

        replaceFragment(movies, moviesIndex)

        if (moviesIndex == movies.size - 1) {
          nextButton.isEnabled = false
        }
        if (moviesIndex == 1) {
          prevButton.isEnabled = true
        }
      }
    }
  }

  private fun replaceFragment(movies: ArrayList<Movie>, moviesIndex: Int) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container, MovieFragment.newInstance(movies[moviesIndex]))
        .commit()
  }
}
