package com.bulkapedia.views.temps.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.R
import com.bulkapedia.databinding.TagCardBinding
import com.bulkapedia.data.heroes.HeroList
import com.bulkapedia.views.temps.models.TagModel

class HeroTagRecyclerAdapter (private val tags: List<TagModel>,
                              private val heroAdapter: HeroRecyclerAdapter
) : RecyclerView.Adapter<HeroTagRecyclerAdapter.HeroTagRecyclerHolder>() {

    private var displayHeroes = heroAdapter.heroes
    private val holders = mutableListOf<HeroTagRecyclerHolder>()
    private lateinit var clickedTag: TagModel

    class HeroTagRecyclerHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bind = TagCardBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroTagRecyclerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tag_card, parent, false)
        val holder = HeroTagRecyclerHolder(view)
        holders.add(holder)
        return holder
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: HeroTagRecyclerHolder, position: Int) {
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
                displayHeroes = HeroList.HEROES
            } else {
                clickedTag.selected = true
                holder.bind.radioButton.visibility = View.VISIBLE
                holders.forEach {
                    if (it != holder) it.bind.radioButton.visibility = View.INVISIBLE
                }
                tags.forEach {
                    if (it != clickedTag) it.selected = false
                }
                displayHeroes = HeroList.HEROES.filter { it.getType() == clickedTag.text }
            }
            heroAdapter.heroes = displayHeroes
            heroAdapter.notifyDataSetChanged()
        }
        holder.itemView.setOnClickListener(listener)
        holder.bind.radioButton.setOnClickListener(listener)
    }

    override fun getItemCount(): Int {
        return tags.size
    }


}