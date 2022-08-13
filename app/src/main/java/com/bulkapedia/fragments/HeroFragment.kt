package com.bulkapedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.HeroFragmentBinding
import com.bulkapedia.listeners.ViewPagerAdapter
import com.bulkapedia.sets.UserSet
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HeroFragment : Fragment() {

    private lateinit var bind: HeroFragmentBinding

    private val args: HeroFragmentArgs by navArgs()

    private lateinit var database: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = HeroFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = Firebase.firestore
        // Получаем ViewModel и инициализируем содержимое фрагмента
        args.heroModel.apply {
            // Инициализируем ActionBar
            bind.barInclude.actionBar.title = getString(heroName)
            bind.barInclude.actionBar.setNavigationIcon(R.drawable.backspace)
            bind.barInclude.actionBar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            // Инициализируем иконку героя
            bind.heroIcon.setImageResource(heroIcon)
            val dividers = listOf(
                bind.dividerOne,
                bind.dividerTwo,
                bind.dividerThree
            )
            dividers.forEachIndexed { i, d ->
                d.background = ResourcesCompat.getDrawable(resources,
                    hero.getDifficult()[i], null)
            }
            // Инициализируем фрагменты сетов
            Database().getSets {
                val sorted = it.sortedByDescending(UserSet::likes).take(3)
                initSetFragments(sorted)
            }

            // Инициализируем нажатие на создание собственного сета
            if (!MAIN.prefs.getSigned()) {
                bind.addSelfSet.visibility = View.INVISIBLE
                bind.addSelfSet.isClickable = false
            } else {
                bind.addSelfSet.visibility = View.VISIBLE
                bind.addSelfSet.isClickable = true
                bind.addSelfSet.setOnClickListener {
                    val action =
                        HeroFragmentDirections.actionHeroFragmentToCreateUserSetFragment(args.heroModel)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun initSetFragments(sets: List<UserSet>) {
        val fragments = mutableListOf<HeroSetFragment>()
        sets.forEach { fragments.add(HeroSetFragment(it, findNavController())) }
        bind.viewPager.adapter = ViewPagerAdapter(MAIN as AppCompatActivity, fragments)
        TabLayoutMediator(bind.tabLayout, bind.viewPager) { tab, pos ->
            tab.text = "Set №${pos + 1}"
        }.attach()
    }


}