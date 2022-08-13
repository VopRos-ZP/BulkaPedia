package com.bulkapedia.fragments

import android.os.Bundle
import android.view.*
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.UserClientFragmentBinding
import com.bulkapedia.heroes.HeroList
import com.bulkapedia.models.HeroModel
import com.bulkapedia.recycler.TextRecyclerAdapter
import com.bulkapedia.recycler.UserSetsAdapter
import com.bulkapedia.sets.UserSet
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
        if (args.see) {
            val text = if (MAIN.prefs.getLanguage() == "ru")
                "Сеты ${user.nickname}"
            else "${user.nickname} sets"
            bind.setsTitle.text = text
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
        }
        // Получаем сеты из БД
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        bind.setsRecycler.layoutManager = manager
        Database().getSets { sets ->
            val newSets = sets.filter { it.from == user.nickname }.toMutableList()
            val edit: (UserSet) -> View.OnClickListener = { uSet ->
                View.OnClickListener {
                    val hero = HeroList.getHeroByBigImage(uSet.hero)
                    val heroModel = HeroModel(hero, hero.getBigIcon(), hero.getName())
                    val action = UserClientFragmentDirections.actionUserClientFragmentToCreateUserSetFragment(heroModel, uSet)
                    findNavController().navigate(action)
                }
            }
            bind.setsRecycler.adapter = if (newSets.isEmpty())
                TextRecyclerAdapter(listOf(getString(R.string.empty_sets)))
            else UserSetsAdapter(edit, newSets, findNavController())
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