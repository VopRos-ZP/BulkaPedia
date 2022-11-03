package com.bulkapedia.recycler

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.RecyclerSetsItemBinding
import com.bulkapedia.fragments.UserClientFragmentDirections
import com.bulkapedia.gears.GearsList
import com.bulkapedia.sets.GearCell
import com.bulkapedia.sets.UserSet
import com.bulkapedia.utils.TripleButtonUtils

class UserSetsAdapter (private val sets: MutableList<UserSet>,
    private val navController: NavController
): RecyclerView.Adapter<UserSetsAdapter.UserSetsHolder>() {

    class UserSetsHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = RecyclerSetsItemBinding.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSetsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_sets_item, parent, false)
        return UserSetsHolder(view)
    }

    override fun onBindViewHolder(holder: UserSetsHolder, position: Int) {
        val uSet = sets[position]
        holder.binding.apply {
            setHeroIcon.setImageResource(uSet.hero)
            val ivGears = listOf(
                setFragmentInclude.ivHead, setFragmentInclude.ivBody,
                setFragmentInclude.ivArm, setFragmentInclude.ivLeg,
                setFragmentInclude.ivDecor, setFragmentInclude.ivDevice
            )
            val gears = uSet.gears.map { gs ->
                val index = GearsList.allGears.map{ it.icon }.indexOf(gs.value)
                if (index == -1)
                    gs.key to null
                else
                    gs.key to GearsList.allGears[index]
            }.toMap()
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
            if (containsId(uSet))
                likesBox.setImageResource(R.drawable.liked)

            commentButton.setOnClickListener {
                val action = UserClientFragmentDirections.actionUserClientFragmentToCommentsFragment(uSet)
                navController.navigate(action)
            }

            settingsButton.setOnClickListener {
                val wrapper = ContextThemeWrapper(MAIN, R.style.menuStyle_PopupMenu)
                val popupMenu = PopupMenu(wrapper, it)
                popupMenu.inflate(R.menu.client_settings_menu)
                popupMenu.setOnMenuItemClickListener { item ->
                    return@setOnMenuItemClickListener when (item.itemId) {
                        R.id.editItem -> {
                            TripleButtonUtils.onClickEdit(uSet) { model ->
                                val action = UserClientFragmentDirections.actionUserClientFragmentToCreateUserSetFragment(model, uSet)
                                navController.navigate(action)
                            }
                            true
                        }
                        R.id.deleteItem -> {
                            TripleButtonUtils.onClickDelete(sets, uSet) {
                                notifyItemRemoved(holder.adapterPosition)
                            }
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
            settingsButton.isClickable = MAIN.prefs.getNickname() == uSet.from

            likesCount.text = uSet.likes.toString()
            likesBox.setOnClickListener {
                if (MAIN.prefs.getNickname() == uSet.from) return@setOnClickListener
                if (containsId(uSet)) {
                    uSet.likes -= 1
                    removeId(uSet)
                    Database().addUserSet(uSet)
                    likesCount.text = "${uSet.likes}"
                    likesBox.setImageResource(R.drawable.unliked)
                } else {
                    uSet.likes += 1
                    uSet.userLikeIds.add(MAIN.prefs.getEmail()!!)
                    Database().addUserSet(uSet)
                    likesCount.text = "${uSet.likes}"
                    likesBox.setImageResource(R.drawable.liked)
                }
            }
        }
    }

    private fun containsId(set: UserSet): Boolean {
        for (id in set.userLikeIds) {
            if (id == MAIN.prefs.getEmail()) {
                return true
            }
        }
        return false
    }

    private fun removeId(set: UserSet) {
        for ((index, id) in set.userLikeIds.withIndex()) {
            if (id == MAIN.prefs.getEmail()) {
                set.userLikeIds.removeAt(index)
            }
        }
    }

    override fun getItemCount(): Int = sets.size

}