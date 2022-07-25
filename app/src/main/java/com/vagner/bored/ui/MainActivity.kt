package com.vagner.bored.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.vagner.bored.R
import com.vagner.bored.databinding.ActivityMainBinding
import com.vagner.bored.ui.bored.BoredActivity
import com.vagner.bored.ui.my_activities.MyActivitiesActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btNewActivities.setOnClickListener(this)
        binding.btMyActivities.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btNewActivities -> {
                startActivity(Intent(this, BoredActivity::class.java))
            }
            R.id.btMyActivities -> {
                startActivity(Intent(this, MyActivitiesActivity::class.java))
            }
        }
    }
}