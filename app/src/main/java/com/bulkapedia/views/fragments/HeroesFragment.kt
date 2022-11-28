package com.bulkapedia.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulkapedia.R
import com.bulkapedia.databinding.HeroesMapsContainerBinding
import com.bulkapedia.views.temps.recycler.HeroRecyclerAdapter
import com.bulkapedia.views.temps.recycler.HeroTagRecyclerAdapter
import com.bulkapedia.data.tags.TagsList

class HeroesFragment : Fragment() {

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
        bind.actionBar.actionBar.title = getString(R.string.select_hero)

        val heroesAdapter = HeroRecyclerAdapter(findNavController())

        bind.recyclerHeroesMaps.layoutManager = LinearLayoutManager(context)
        bind.recyclerHeroesMaps.adapter = heroesAdapter

        bind.tagLayoutInclude.tagsRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        bind.tagLayoutInclude.tagsRecycler.adapter = HeroTagRecyclerAdapter(TagsList.HEROES_TYPE_TAGS, heroesAdapter)
    }

}