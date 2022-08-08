package com.vagner.bored.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vagner.bored.databinding.ItemMyactivitiesBinding
import com.vagner.bored.model.BoredLocalModel

class MyActivitiesAdapter(
    private val onClickListener: (BoredLocalModel) -> Unit,
    private val onClickDelete: (BoredLocalModel) -> Unit
) :
    RecyclerView.Adapter<MyActivitiesAdapter.MyActivitiesViewHolder>() {

    var listMyActivities = listOf<BoredLocalModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = listMyActivities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyActivitiesViewHolder {
        return MyActivitiesViewHolder(
            ItemMyactivitiesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyActivitiesViewHolder, position: Int) {
        holder.bind(listMyActivities[position], onClickListener, onClickDelete)
    }

    inner class MyActivitiesViewHolder(
        private val binding: ItemMyactivitiesBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            bored: BoredLocalModel,
            onClickListener: (BoredLocalModel) -> Unit,
            onClickDelete: (BoredLocalModel) -> Unit
        ) {
            binding.txActivity.text = bored.activity
            binding.txAccessibility.text = bored.accessibility.toString()
            binding.txPrice.text = bored.price.toString()
            binding.txType.text = bored.type
            binding.txParticipants.text = bored.participants.toString()
            binding.txProgress.text = bored.progress.toString()
            binding.txData.text = bored.data
            binding.txEnd.text = bored.end
            binding.txProgress.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    bored.progress.color
                )
            )

            binding.imgDelete.setOnClickListener {
                onClickDelete(bored)
            }

            binding.clMyActivity.setOnClickListener {
                onClickListener(bored)
            }
        }
    }
}