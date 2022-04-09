package com.example.profile.activities.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.profile.databinding.ActivityMainBinding
import com.example.profile.models.Profile
import com.example.profile.models.Users
import com.example.profile.utils.hideView
import com.example.profile.utils.showView
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by inject()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        with(mainViewModel) {
            usersMutableLiveData.observe(this@MainActivity, usersObserver)
            errorMutableLiveData.observe(this@MainActivity, errorObserver)
            getUserList()
        }
    }

    private fun setupUI() {
        binding.progressBar.showView()
        with(binding.btnNext) {
            hideView()
            setOnClickListener {
                updateItem()
            }
        }
    }

    private fun updateItem() {
        binding.progressBar.showView()
        mainViewModel.getNextUser()?.let { adapter.updateItems(it) }
        binding.progressBar.hideView()
    }

    private val usersObserver = Observer<Pair<Profile, Users>> {
        binding.progressBar.hideView()
        binding.btnNext.showView()
        adapter = MainAdapter(this@MainActivity, it.first.profile, it.second.users[0])
        binding.rv.adapter = adapter
    }

    private val errorObserver = Observer<String> {
        binding.btnNext.hideView()
        Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()

    }
}