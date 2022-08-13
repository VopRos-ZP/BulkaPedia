package com.bulkapedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulkapedia.*
import com.bulkapedia.databinding.HeroesMapsContainerBinding
import com.bulkapedia.recycler.MapRecyclerAdapter
import com.bulkapedia.recycler.MapTagRecyclerAdapter
import com.bulkapedia.tags.TagsList

class MapsFragment : Fragment() {

    lateinit var bind: HeroesMapsContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = HeroesMapsContainerBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.actionBar.actionBar.title = getString(R.string.select_map)
        bind.recyclerHeroesMaps.layoutManager = LinearLayoutManager(context)
        val mapAdapter = MapRecyclerAdapter(findNavController())
        bind.recyclerHeroesMaps.adapter = mapAdapter
        bind.tagLayoutInclude.tagsRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        bind.tagLayoutInclude.tagsRecycler.adapter = MapTagRecyclerAdapter(TagsList.MAPS_MODE_TAGS, mapAdapter)
    }

}