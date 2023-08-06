package com.alikamran.newsapp.presentation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alikamran.newsapp.R
import com.alikamran.newsapp.databinding.ActivityMainBinding
import com.alikamran.newsapp.presentation.adapter.NewsAdapter
import com.alikamran.newsapp.presentation.viewmodel.NewsViewModel
import com.alikamran.newsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity  : AppCompatActivity() {

    @Inject
    lateinit var factory: NewsViewModelFactory
    lateinit var viewModel: NewsViewModel
    @Inject
    lateinit var newsAdapter: NewsAdapter
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment

        val navController = navHostFragment.navController



        binding.bnvNews.setupWithNavController(
            navController
        )

        viewModel = ViewModelProvider(this,factory)[NewsViewModel::class.java]
    }
}