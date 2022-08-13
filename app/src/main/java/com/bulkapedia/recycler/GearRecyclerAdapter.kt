package com.bulkapedia.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.R
import com.bulkapedia.databinding.GearRecyclerItemBinding
import com.bulkapedia.gears.Effect
import com.bulkapedia.gears.Gear

class GearRecyclerAdapter (
    private val gears: List<Gear>,
    private val onClick: RecyclerHolderClick,
    private val toStringEffects: (List<Effect>) -> String
) : RecyclerView.Adapter<GearRecyclerAdapter.GearPlaceHolder>() {

    class GearPlaceHolder(item: View) : RecyclerView.ViewHolder(item) {
        val bind = GearRecyclerItemBinding.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GearPlaceHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.gear_recycler_item, parent, false)
        return GearPlaceHolder(item)
    }

    override fun onBindViewHolder(holder: GearPlaceHolder, position: Int) {
        val gear = gears[position]
        holder.bind.gearImagePattern.setImageResource(gear.icon)
        holder.bind.textEffects.text = toStringEffects(gear.effects)
        holder.itemView.setOnClickListener {
            onClick.onClick(position)
        }
    }

    override fun getItemCount(): Int = gears.size

    interface RecyclerHolderClick {
        fun onClick(position: Int)
    }

}