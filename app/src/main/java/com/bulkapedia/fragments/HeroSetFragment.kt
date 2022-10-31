package com.bulkapedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.HeroSetFragmentBinding
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.HeroList
import com.bulkapedia.models.HeroModel
import com.bulkapedia.sets.GearCell
import com.bulkapedia.sets.UserSet
import com.bulkapedia.utils.TripleButtonUtils

class HeroSetFragment (
    private val set: UserSet,
    private val navController: NavController
) : Fragment() {

    private lateinit var bind: HeroSetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = HeroSetFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ivGears = listOf(
            bind.setInclude.ivHead, bind.setInclude.ivBody,
            bind.setInclude.ivArm, bind.setInclude.ivLeg,
            bind.setInclude.ivDecor, bind.setInclude.ivDevice
        )
        val gears = getGears()
        val cells = listOf(
            GearCell.HEAD, GearCell.BODY,
            GearCell.ARM, GearCell.LEG,
            GearCell.DECOR, GearCell.DEVICE,
        )
        cells.forEachIndexed { i, cell ->
            if (gears[cell] != null) {
                ivGears[i].setImageResource(gears[cell]!!.icon)
            }
        }

        if (set.from != MAIN.prefs.getNickname() || !MAIN.prefs.getSigned()) {
            bind.tripleBtnInclude.apply {
                deleteButton.visibility = View.INVISIBLE
                deleteButton.isClickable = false
                // profile
                editProfileButton.setImageResource(R.drawable.person)
                editProfileButton.setOnClickListener {
                    Database().retrieveUserByNickname(set.from) {
                        val action = HeroFragmentDirections.actionHeroFragmentToUserClientFragment(it, true)
                        navController.navigate(action)
                    }
                }
            }
        } else {
            bind.tripleBtnInclude.apply {
                editProfileButton.setImageResource(R.drawable.edit)
                editProfileButton.setOnClickListener {
                    if (!MAIN.prefs.getSigned()) return@setOnClickListener
                    val hero = HeroList.getHeroByBigImage(set.hero)
                    val model = HeroModel(hero, hero.getBigIcon(), hero.getName(), hero.getCounterpicks())
                    val action = HeroFragmentDirections.actionHeroFragmentToCreateUserSetFragment(model, set)
                    navController.navigate(action)
                }
                deleteButton.visibility = View.VISIBLE
                deleteButton.isClickable = true
                deleteButton.setOnClickListener {
                    TripleButtonUtils.onClickDelete(mutableListOf(set), set) {}
                }
            }
        }

        bind.tripleBtnInclude.commentButton.setOnClickListener {
            val action = HeroFragmentDirections.actionHeroFragmentToCommentsFragment(set)
            navController.navigate(action)
        }

        if (containsId())
            bind.likesBox.setImageResource(R.drawable.liked)

        bind.likesCount.text = "${set.likes}"
        bind.likesBox.setOnClickListener {
            if (set.from == MAIN.prefs.getNickname()) return@setOnClickListener
            if (containsId()) {
                set.likes -= 1
                removeId()
                Database().addUserSet(set)
                bind.likesCount.text = "${set.likes}"
                bind.likesBox.setImageResource(R.drawable.unliked)
            } else {
                set.likes += 1
                set.userLikeIds.add(MAIN.prefs.getEmail()!!)
                Database().addUserSet(set)
                bind.likesCount.text = "${set.likes}"
                bind.likesBox.setImageResource(R.drawable.liked)
            }
        }
    }

    private fun getGears(): Map<GearCell, Gear?> {
        return set.gears.map { gs ->
            val index = GearsList.allGears.map{ it.icon }.indexOf(gs.value)
            if (index == -1)
                gs.key to null
            else
                gs.key to GearsList.allGears[index]
        }.toMap()
    }

    private fun containsId() : Boolean {
        for (id in set.userLikeIds)
            if (id == MAIN.prefs.getEmail())
                return true
        return false
    }

    private fun removeId() {
        for ((index, id) in set.userLikeIds.withIndex()) {
            if (id == MAIN.prefs.getEmail()) {
                set.userLikeIds.removeAt(index)
            }
        }
    }

}