package com.bulkapedia.views.temps.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bulkapedia.R
import com.bulkapedia.databinding.MainItemBinding
import com.bulkapedia.data.labels.Stats
import com.bulkapedia.views.dialogs.ShowStatsView

class MainsRecyclerAdapter(
    private val mains: MutableMap<String, Stats>
) : RecyclerView.Adapter<MainsRecyclerAdapter.MainsViewHolder>() {

    class MainsViewHolder(item: View) : ViewHolder(item) {
        val bind = MainItemBinding.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainsViewHolder {
        return MainsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.main_item, parent, false))
    }

    override fun onBindViewHolder(holder: MainsViewHolder, position: Int) {
        val pair = mains[mains.keys.elementAt(position)]!!
        holder.bind.mainsText.setText(mains.keys.elementAt(position).toInt())
        holder.itemView.setOnClickListener {
            ShowStatsView(pair).show()
        }
    }

    override fun getItemCount(): Int = mains.size

}