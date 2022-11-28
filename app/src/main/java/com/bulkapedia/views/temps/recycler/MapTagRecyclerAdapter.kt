package com.bulkapedia.views.temps.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.R
import com.bulkapedia.databinding.TagCardBinding
import com.bulkapedia.data.maps.MapList
import com.bulkapedia.views.temps.models.TagModel

class MapTagRecyclerAdapter(private val tags: List<TagModel>,
                            private val mapAdapter: MapRecyclerAdapter
) : RecyclerView.Adapter<MapTagRecyclerAdapter.MapTagRecyclerHolder>() {

    private var displayMaps = mapAdapter.maps
    private val holders = mutableListOf<MapTagRecyclerHolder>()
    private lateinit var clickedTag: TagModel

    class MapTagRecyclerHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bind = TagCardBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapTagRecyclerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tag_card, parent, false)
        val holder = MapTagRecyclerHolder(view)
        holders.add(holder)
        return holder
    }

    override fun onBindViewHolder(holder: MapTagRecyclerHolder, position: Int) {
        val tag = tags[position]
        holder.bind.apply {
            tagText.setText(tag.text)
            val color = ResourcesCompat.getColor(holder.itemView.resources, tag.color, null)
            tagCard.setCardBackgroundColor(color)
        }
        val listener: (View) -> Unit = {
            clickedTag = tags[position]
            if (clickedTag.selected) {
                holder.bind.radioButton.visibility = View.INVISIBLE
                tags.forEach { it.selected = false }
                displayMaps = MapList.maps
            } else {
                clickedTag.selected = true
                holder.bind.radioButton.visibility = View.VISIBLE
                holders.forEach {
                    if (it != holder) it.bind.radioButton.visibility = View.INVISIBLE
                }
                tags.forEach {
                    if (it != clickedTag) it.selected = false
                }
                displayMaps = MapList.maps.filter { it.mapMode == clickedTag.text }
            }
            mapAdapter.maps = displayMaps
            mapAdapter.notifyDataSetChanged()
        }
        holder.itemView.setOnClickListener(listener)
        holder.bind.radioButton.setOnClickListener(listener)
    }

    override fun getItemCount(): Int {
        return tags.size
    }
}