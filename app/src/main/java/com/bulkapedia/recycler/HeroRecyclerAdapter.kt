package com.bulkapedia.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.HeroCardBinding
import com.bulkapedia.fragments.HeroesFragmentDirections
import com.bulkapedia.heroes.HeroList
import com.bulkapedia.models.HeroModel
import com.bulkapedia.models.TopModel
import com.bulkapedia.sets.UserSet

class HeroRecyclerAdapter(private val navController: NavController) : RecyclerView.Adapter<HeroRecyclerAdapter.HeroHolder>() {

    var heroes = HeroList.HEROES

    class HeroHolder(item: View) : RecyclerView.ViewHolder(item) {
        val bind = HeroCardBinding.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.hero_card, parent, false)
        return HeroHolder(item)
    }

    override fun onBindViewHolder(holder: HeroHolder, position: Int) {
        val hero = heroes[position]
        holder.bind.apply {
            heroIcon.setImageResource(hero.getBigIcon())
            setBtn.setOnClickListener {
                val model = HeroModel(hero, hero.getBigIcon(), hero.getName(), hero.getCounterpicks())
                val action = HeroesFragmentDirections.actionGearsItemToHeroFragment(model)
                navController.navigate(action)
            }
            topStoBtn.setOnClickListener {
                Database().getSets {
                    val newset = it.filter { set -> set.hero == hero.getBigIcon() }
                    val sorted = newset.sortedByDescending(UserSet::likes).take(100).mapIndexed { i, s ->
                        TopModel(i + 1, s.from, s)
                    }
                    val action = HeroesFragmentDirections.actionGearsItemToTopFragment(sorted.toTypedArray())
                    navController.navigate(action)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

}