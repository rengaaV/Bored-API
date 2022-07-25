package com.vagner.bored.ui.my_activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vagner.bored.R
import com.vagner.bored.adapter.MyActivitiesAdapter
import com.vagner.bored.databinding.ActivityMyactivitiesBinding
import com.vagner.bored.model.BoredModel
import java.util.*

class MyActivitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyactivitiesBinding
    private val adapter = MyActivitiesAdapter {
        listener(it)
    }
    private lateinit var viewModel: MyActivitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMyactivitiesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MyActivitiesViewModel::class.java)
        binding.rvMyActivities.adapter = adapter
        binding.rvMyActivities.layoutManager = LinearLayoutManager(this)
        observe()
    }

    private fun listener(bored: BoredModel) {
        val date = Calendar.getInstance().time
        val data = date.toString()
        val progress = R.array.Progress
        val dialog = AlertDialog.Builder(this@MyActivitiesActivity)
            .setTitle(getString(R.string.menssagem_progresso))
            .setSingleChoiceItems(progress, -1) { dialog, position ->
                bored.progress = position.toString()
                when (position) {
                    0 -> {
                        bored.progress = getString(R.string.finalizada_dialog_my_activities)
                        bored.end = data
                    }
                    1 -> {
                        bored.progress = getString(R.string.cancelada_dialog_my_activities)
                        bored.end = getString(R.string.dialog_myactiviti_cancelado)
                    }
                    2 -> {
                        viewModel.delete(bored)
                    }
                }
            }.setPositiveButton(getString(R.string.dialog_myactivity_sim)) { _, _ ->
                viewModel.update(bored)
                viewModel.getAll()
            }.setNegativeButton(getString(R.string.dialog_myactivity_nao)) { _, _ ->
                viewModel.getAll()
            }
        dialog.show()
    }

    private fun observe() {
        viewModel.listAllBored.observe(this) {
            adapter.listMyActivities = it
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }
}