package com.bulkapedia.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.HeroSetFragmentBinding
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.GEARS_LIST
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.data.sets.UserSet
import com.bulkapedia.utils.TripleButtonUtils
import com.bulkapedia.utils.stringToResource

class HeroSetFragment (
    private val uSet: UserSet,
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
        Database().getSet(uSet.setId) { set ->
            val ivGears = listOf(
                bind.setInclude.ivHead, bind.setInclude.ivBody,
                bind.setInclude.ivArm, bind.setInclude.ivLeg,
                bind.setInclude.ivDecor, bind.setInclude.ivDevice
            )
            val gears = getGears(set)
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
                bind.settingsProfileButton.apply {
                    // profile
                    setImageResource(R.drawable.person)
                    setOnClickListener {
                        Database().retrieveUserByNickname(set.from) {
                            val action = HeroFragmentDirections.actionHeroFragmentToUserClientFragment(it, true)
                            navController.navigate(action)
                        }
                    }
                }
            } else {
                bind.settingsProfileButton.apply {
                    setImageResource(R.drawable.settings)
                    setOnClickListener {
                        if (!MAIN.prefs.getSigned()) return@setOnClickListener
                        val wrapper = ContextThemeWrapper(MAIN, R.style.menuStyle_PopupMenu)
                        val popupMenu = PopupMenu(wrapper, it)
                        popupMenu.inflate(R.menu.client_settings_menu)
                        popupMenu.setOnMenuItemClickListener { item ->
                            return@setOnMenuItemClickListener when (item.itemId) {
                                R.id.editItem -> {
                                    TripleButtonUtils.onClickEdit(set) { model ->
                                        val action = HeroFragmentDirections.actionHeroFragmentToCreateUserSetFragment(set, model)
                                        navController.navigate(action)
                                    }
                                    true
                                }
                                R.id.deleteItem -> {
                                    TripleButtonUtils.onClickDelete(mutableListOf(set), set) {}
                                    true
                                }
                                else -> false
                            }
                        }
                        try {
                            val fieldPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                            fieldPopup.isAccessible = true
                            val mPopup = fieldPopup.get(popupMenu)
                            mPopup.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                                .invoke(mPopup, true)
                        } catch (_: Exception) {}
                        popupMenu.show()
                    }
                }
            }

            bind.commentButton.setOnClickListener {
                val action = HeroFragmentDirections.actionHeroFragmentToCommentsFragment(set)
                navController.navigate(action)
            }

            if (containsId(set))
                bind.likesBox.setImageResource(R.drawable.liked)
            else
                bind.likesBox.setImageResource(R.drawable.unliked)

            bind.likesCount.text = "${set.likes}"
            bind.likesBox.setOnClickListener {
                if ((MAIN.prefs.getNickname() == set.from) || !MAIN.prefs.getSigned()) return@setOnClickListener
                if (containsId(set)) {
                    set.likes -= 1
                    removeId(set)
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
    }

    private fun getGears(set: UserSet): Map<GearCell, Gear?> {
        return set.gears.map { gs ->
            val index = GEARS_LIST.allGears.map{ it.icon }.indexOf(stringToResource(gs.value))
            if (index == -1)
                gs.key to null
            else
                gs.key to GEARS_LIST.allGears[index]
        }.toMap()
    }

    private fun containsId(set: UserSet) : Boolean {
        for (id in set.userLikeIds)
            if (id == MAIN.prefs.getEmail())
                return true
        return false
    }

    private fun removeId(set: UserSet) {
        for ((index, id) in set.userLikeIds.withIndex()) {
            if (id == MAIN.prefs.getEmail()) {
                set.userLikeIds.removeAt(index)
            }
        }
    }

}