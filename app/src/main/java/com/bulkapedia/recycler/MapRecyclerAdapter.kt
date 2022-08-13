package com.bulkapedia.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.R
import com.bulkapedia.databinding.MapCardBinding
import com.bulkapedia.fragments.MapsFragmentDirections
import com.bulkapedia.maps.MapList
import com.bulkapedia.models.MapModel

class MapRecyclerAdapter(private val navController: NavController) : RecyclerView.Adapter<MapRecyclerAdapter.MapHolder>() {

    var maps = MapList.maps

    class MapHolder(item: View) : RecyclerView.ViewHolder(item) {
        val bind = MapCardBinding.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.map_card, parent, false)
        return MapHolder(item)
    }

    override fun onBindViewHolder(holder: MapHolder, position: Int) {
        val map = maps[position]
        holder.bind.mapIcon.setImageResource(map.mapImage)
        holder.bind.mapName.setText(map.mapName)
        holder.itemView.setOnClickListener {
            val model = MapModel(map.mapImage, map.mapImageSpawns, map.mapName)
            val action = MapsFragmentDirections.actionMapsItemToMapFragment(model)
            navController.navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return maps.size
    }


}