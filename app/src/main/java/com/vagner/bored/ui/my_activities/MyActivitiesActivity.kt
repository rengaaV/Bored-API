package com.vagner.bored.ui.my_activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vagner.bored.R
import com.vagner.bored.adapter.MyActivitiesAdapter
import com.vagner.bored.databinding.ActivityMyactivitiesBinding
import com.vagner.bored.model.BoredLocalModel
import com.vagner.bored.model.ProgressColor
import com.vagner.bored.util.Date

class MyActivitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyactivitiesBinding

    private lateinit var adapter: MyActivitiesAdapter

    private lateinit var viewModel: MyActivitiesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMyactivitiesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MyActivitiesViewModel::class.java)

        listStatusChanged()
        setupAdapter()
        observe()
    }

    private fun setupAdapter() {
        adapter = MyActivitiesAdapter(
            onClickListener = { boredToChange ->
                showBoredProgress(boredToChange)
            },
            onClickDelete = { boredToConfirmDelete ->
                showDeleteConfirmation(boredToConfirmDelete) { boredToDelete ->
                    viewModel.delete(boredToDelete)
                    listStatusChanged()
                }
            }
        )
        binding.rvMyActivities.adapter = adapter
    }

    private fun showDeleteConfirmation(
        bored: BoredLocalModel,
        onConfirm: (BoredLocalModel) -> Unit
    ) {

        val builder = AlertDialog.Builder(this)

        builder.apply {
            setTitle(getString(R.string.confirmacao))
            setMessage("Do you want to delete the Activity \"${bored.activity}\"?")
            setPositiveButton(getString(R.string.sim)) { _, _ ->
                onConfirm(bored)
            }
            setNegativeButton(getString(R.string.nao)) { dialog, _ ->
                dialog.dismiss()
            }
        }
        builder.show()
    }

    private fun showBoredProgress(bored: BoredLocalModel) {
        val progress = R.array.Progress
        val dialog = AlertDialog.Builder(this@MyActivitiesActivity)
            .setTitle(getString(R.string.menssagem_progresso))
            .setSingleChoiceItems(progress, -1) { dialog, position ->
                when (position) {
                    0 -> {
                        bored.progress = ProgressColor.FINISHED
                        bored.end = Date.setDate()
                    }
                    1 -> {
                        bored.progress = ProgressColor.CANCELED
                        bored.end = getString(R.string.dialog_myactiviti_cancelado)
                    }
                }
            }.setPositiveButton(getString(R.string.dialog_myactivity_sim)) { _, _ ->
                viewModel.update(bored)
                viewModel.getAll()
            }.setNegativeButton(getString(R.string.dialog_myactivity_nao)) { dialog, _ ->
                dialog.dismiss()
            }
        dialog.show()
    }

    private fun observe() {
        viewModel.listAllBored.observe(this) {
            adapter.listMyActivities = it
        }
    }

    private fun listStatusChanged() {
        viewModel.getAll()
        if (viewModel.listAllBored.value?.isEmpty() == true) {
            binding.rvMyActivities.visibility = View.INVISIBLE
            binding.txNoDataMyActivities.visibility = View.VISIBLE

        } else {
            binding.rvMyActivities.visibility = View.VISIBLE
            binding.txNoDataMyActivities.visibility = View.INVISIBLE
        }
    }

}