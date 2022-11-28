package com.bulkapedia.views.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.database.User
import com.bulkapedia.databinding.UserClientFragmentBinding
import com.bulkapedia.views.temps.listeners.ViewPagerAdapter
import com.bulkapedia.views.temps.recycler.FavoritesAdapter
import com.bulkapedia.views.temps.recycler.MainsRecyclerAdapter
import com.bulkapedia.views.temps.recycler.UserSetsAdapter
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

    private lateinit var user: User
    private lateinit var textTab1: CharSequence
    private lateinit var textTab2: CharSequence

    private var selectedTab: Int = 0

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
        user = args.user
        database = Firebase.firestore
        bind.actionBarInclude.actionBar.title = user.nickname

        textTab1 = getText(R.string.your_sets)
        textTab2 = getText(R.string.favorites)

        if (args.see) {
            textTab1 = if (MAIN.prefs.getLanguage() == "ru")
                "Сеты ${user.nickname}"
            else "${user.nickname} sets"
            bind.tabLayout.getTabAt(1)?.view?.isClickable = false
            bind.viewPager2.isUserInputEnabled = false
            bind.actionBarInclude.actionBar.setNavigationIcon(R.drawable.backspace)
            bind.actionBarInclude.actionBar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        } else {
            bind.viewPager2.isUserInputEnabled = true
            bind.actionBarInclude.actionBar.inflateMenu(R.menu.settings_menu)
            bind.actionBarInclude.actionBar.setOnMenuItemClickListener(this::onSettingsItemSelected)
            val menuItem = getMenuItem()!!
            menuItem.setTitle(R.string.profile)
            menuItem.setIcon(R.drawable.person)
            bind.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    selectedTab = bind.tabLayout.selectedTabPosition
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
        // Инициализируем теги мейнов
        bind.mainsRecycler.layoutManager = LinearLayoutManager(MAIN, LinearLayoutManager.HORIZONTAL, false)
        Database().retrieveUserByEmail(user.email!!) { newU ->
            bind.mainsRecycler.adapter = MainsRecyclerAdapter(newU.mains)
        }
        // Получаем сеты из БД
        bind.viewPager2.orientation = ORIENTATION_HORIZONTAL
        setUserSets()
    }

    override fun onResume() {
        super.onResume()
        textTab1 = getText(R.string.your_sets)
        textTab2 = getText(R.string.favorites)
        setUserSets()
    }

    private fun setUserSets() {
        Database().getSets { sets ->
            val yourSets = sets.filter { it.from == user.nickname }.toMutableList()
            val favSets = sets.filter { it.userLikeIds.contains(user.email) }.toMutableList()
            val yourSetsAdapter = UserSetsAdapter(yourSets,
                mutableListOf(getString(R.string.empty_sets)), findNavController())

            val favoritesAdapter = FavoritesAdapter(favSets,
                mutableListOf(getString(R.string.empty_sets)), findNavController())
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