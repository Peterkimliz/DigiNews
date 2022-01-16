package com.example.dignews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dignews.R
import com.example.dignews.db.ArticleDatabase
import com.example.dignews.repository.NewsRepository
import com.example.dignews.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setSupportActionBar(mainToolbar as Toolbar?)
        supportActionBar?.title = "Trending News"
        bottomNavigation.setupWithNavController(navHostFragment.findNavController())
    }
}