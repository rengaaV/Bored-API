package com.vagner.bored.ui.bored

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vagner.bored.R
import com.vagner.bored.databinding.ActivityBoredBinding
import com.vagner.bored.util.Constants.ERROR

class BoredActivity : AppCompatActivity(), View.OnClickListener,
    AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityBoredBinding
    private lateinit var viewModel: BoredViewModel
    private val type = arrayOf(
        "random", "recreational", "social", "diy", "charity", "cooking",
        "relaxation", "music", "busywork", "education"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBoredBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(BoredViewModel::class.java)

        binding.spType.adapter =
            ArrayAdapter(this@BoredActivity, android.R.layout.simple_spinner_item, type)

        binding.btStartActivities.setOnClickListener(this)
        binding.btNewActivities.setOnClickListener(this)

        binding.spType.onItemSelectedListener = this

        observer()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btNewActivities -> {
                val selectedItem = binding.spType.selectedItem
                if (selectedItem == getString(R.string.item_random_bored_activity)) {
                    viewModel.getAllTypes(type.random())
                } else {
                    viewModel.getAllTypes(selectedItem.toString())
                }
            }
            R.id.btStartActivities -> {
                viewModel.insert()
                Toast.makeText(
                    this,
                    getString(R.string.toast_btStartActivities),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    private fun observer() {
        listSuccess()
        listError()
    }

    private fun listSuccess() {
        viewModel.callSuccess.observe(this) {
            if (it != null) {
                binding.btStartActivities.visibility = View.VISIBLE
            }
            binding.txActivity.text = it.activity
            binding.txAccessibility.text = it.accessibility.toString()
            binding.txPrice.text = it.price.toString()
            binding.txType.text = it.type
            binding.txParticipants.text = it.participants.toString()
        }
    }

    private fun listError() {
        viewModel.callError.observe(this) {
            Toast.makeText(this, ERROR, Toast.LENGTH_LONG).show()
        }
    }
}