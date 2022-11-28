package com.bulkapedia.views.temps.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.databinding.CounterpickRecyclerItemBinding
import com.bulkapedia.views.temps.models.CounterpickModel

class CounterpickRecyclerAdapter (
    private val counterpicks: List<CounterpickModel>
) : RecyclerView.Adapter<CounterpickRecyclerAdapter.CounterpickRecyclerHolder>() {

    class CounterpickRecyclerHolder(
        private val bind: CounterpickRecyclerItemBinding
        ) : RecyclerView.ViewHolder(bind.root) {

        fun setData(model: CounterpickModel) {
            bind.counterpickIcon.setImageResource(model.icon)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterpickRecyclerHolder {
        val bind = CounterpickRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CounterpickRecyclerHolder(bind)
    }

    override fun onBindViewHolder(holder: CounterpickRecyclerHolder, position: Int) {
        holder.setData(counterpicks[position])
    }

    override fun getItemCount(): Int {
        return counterpicks.size
    }


}