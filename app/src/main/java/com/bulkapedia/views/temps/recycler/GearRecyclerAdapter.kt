package com.bulkapedia.views.temps.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.R
import com.bulkapedia.databinding.GearRecyclerItemBinding
import com.bulkapedia.data.gears.Effect
import com.bulkapedia.data.gears.Gear

class GearRecyclerAdapter (
    private val gears: List<Gear>,
    private val onClick: RecyclerHolderClick,
    private val toStringEffects: (List<Effect>) -> String
) : RecyclerView.Adapter<GearRecyclerAdapter.GearPlaceHolder>() {

    private val holders: MutableList<GearPlaceHolder> = mutableListOf()
    private var clickedHolder: GearPlaceHolder? = null

    class GearPlaceHolder(item: View) : RecyclerView.ViewHolder(item) {
        val bind = GearRecyclerItemBinding.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GearPlaceHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.gear_recycler_item, parent, false)
        val holder = GearPlaceHolder(item)
        holders.add(holder)
        return holder
    }

    override fun onBindViewHolder(holder: GearPlaceHolder, position: Int) {
        val gear = gears[position]
        holder.bind.gearImagePattern.setImageResource(gear.icon)
        holder.bind.textEffects.text = toStringEffects(gear.effects)
        holder.bind.textEffects.visibility = VISIBLE
        holder.bind.selectRankConst.visibility = INVISIBLE
        holder.bind.selectRankConst.children.forEach {
            it.isClickable = false
        }
        holder.bind.backTv.setOnClickListener {
            holder.bind.gearImagePattern.visibility = VISIBLE
            holder.bind.textEffects.visibility = VISIBLE
            holder.bind.selectRankConst.visibility = INVISIBLE
            holder.bind.selectRankConst.children.forEach {
                it.isClickable = false
            }
            clickedHolder = null
        }
        holder.itemView.setOnClickListener { _ ->
            if (position == 0) {
                onClick.onClick(position, 0)
                return@setOnClickListener
            }
            holder.bind.textEffects.visibility = INVISIBLE
            holder.bind.selectRankConst.visibility = VISIBLE
            holder.bind.gearImagePattern.visibility = INVISIBLE
            holder.bind.selectRankConst.children.forEach {
                it.isClickable = true
                if (it.id != holder.bind.backTv.id) {
                    it.setOnClickListener { _ ->
                        onClick.onClick(position, it.id)
                    }
                }
            }
            if (clickedHolder != null) {
                clickedHolder!!.bind.backTv.callOnClick()
            }
            clickedHolder = holder
        }
    }

    override fun getItemCount(): Int = gears.size

    interface RecyclerHolderClick {
        fun onClick(position: Int, rank: Int)
    }

}