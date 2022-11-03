package com.bulkapedia.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.UserClientFragmentBinding
import com.bulkapedia.heroes.HeroList
import com.bulkapedia.listeners.ViewPagerAdapter
import com.bulkapedia.models.HeroModel
import com.bulkapedia.recycler.FavoritesAdapter
import com.bulkapedia.recycler.TextRecyclerAdapter
import com.bulkapedia.recycler.UserSetsAdapter
import com.bulkapedia.sets.UserSet
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserClientFragment : Fragment() {

    private lateinit var bind: UserClientFragmentBinding

    private val args: UserClientFragmentArgs by navArgs()
    private lateinit var database: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = UserClientFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = args.user
        database = Firebase.firestore
        bind.actionBarInclude.actionBar.title = user.nickname

        val tabListener: OnTabSelectedListener = object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == 1) {
                    bind.tabLayout.selectTab(bind.tabLayout.getTabAt(0)!!)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        }

        var textTab1 = getText(R.string.your_sets)
        val textTab2 = getText(R.string.favorites)

        if (args.see) {
            textTab1 = if (MAIN.prefs.getLanguage() == "ru")
                "Сеты ${user.nickname}"
            else "${user.nickname} sets"
            bind.tabLayout.addOnTabSelectedListener(tabListener)
            bind.actionBarInclude.actionBar.setNavigationIcon(R.drawable.backspace)
            bind.actionBarInclude.actionBar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        } else {
            bind.actionBarInclude.actionBar.inflateMenu(R.menu.settings_menu)
            bind.actionBarInclude.actionBar.setOnMenuItemClickListener(this::onSettingsItemSelected)
            val menuItem = getMenuItem()!!
            menuItem.setTitle(R.string.profile)
            menuItem.setIcon(R.drawable.person)
            bind.tabLayout.removeOnTabSelectedListener(tabListener)
        }
        // Получаем сеты из БД
        bind.viewPager2.orientation = ORIENTATION_HORIZONTAL
        Database().getSets { sets ->
            val newSets = sets.filter { it.from == user.nickname }.toMutableList()
            val edit: (UserSet) -> View.OnClickListener = { uSet ->
                View.OnClickListener {
                    val hero = HeroList.getHeroByBigImage(uSet.hero)
                    val heroModel = HeroModel(hero, hero.getBigIcon(), hero.getName(), hero.getCounterpicks())
                    val action = UserClientFragmentDirections.actionUserClientFragmentToCreateUserSetFragment(heroModel, uSet)
                    findNavController().navigate(action)
                }
            }

            val yourSetsAdapter = if (newSets.isEmpty())
                TextRecyclerAdapter(listOf(getString(R.string.empty_sets)))
            else UserSetsAdapter(edit, newSets, findNavController())

            val favSets = sets.filter { it.userLikeIds.contains(user.email) }.toMutableList()

            val favoritesAdapter = if (favSets.isEmpty())
                TextRecyclerAdapter(listOf(getString(R.string.empty_sets)))
            else FavoritesAdapter(favSets)
            // 2 фрагмента с recycler view
            val fragments = listOf(
                ClientRecyclerFragment(yourSetsAdapter),
                ClientRecyclerFragment(favoritesAdapter)
            )
            bind.viewPager2.adapter = ViewPagerAdapter(MAIN as AppCompatActivity, fragments)

            TabLayoutMediator(bind.tabLayout, bind.viewPager2) { tab, pos ->
                tab.text = when (pos) {
                    0 -> textTab1
                    else -> textTab2
                }
            }.attach()
        }
    }

    private fun onSettingsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settingsItem) {
            val action = UserClientFragmentDirections.actionUserClientFragmentToSettingsFragment()
            findNavController().navigate(action)
            return true
        }
        return false
    }

    private fun getMenuItem(): MenuItem? {
        for (m in MAIN.bind.bottomNav.menu) {
            if (m.itemId == R.id.loginItem)
                return m
        }
        return null
    }

}