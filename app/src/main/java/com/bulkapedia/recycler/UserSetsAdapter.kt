package com.bulkapedia.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.RecyclerSetsItemBinding
import com.bulkapedia.databinding.TextRecyclerItemBinding
import com.bulkapedia.fragments.UserClientFragmentDirections
import com.bulkapedia.gears.GearsList
import com.bulkapedia.sets.GearCell
import com.bulkapedia.sets.UserSet
import com.bulkapedia.utils.TripleButtonUtils
import com.bulkapedia.utils.gearStringToResource
import com.bulkapedia.utils.heroStringToResource

class UserSetsAdapter (
    private val sets: MutableList<UserSet>,
    private val strings: MutableList<String>,
    private val navController: NavController
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val holders = mutableListOf<RecyclerView.ViewHolder>()

    companion object {
        const val VIEW_TYPE_EMPTY = 1
        const val VIEW_TYPE_FILLED = 2
    }

    class UserSetsHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = RecyclerSetsItemBinding.bind(item)
    }

    class TextAdapterPlaceHolder(item: View): RecyclerView.ViewHolder(item) {

        val bind = TextRecyclerItemBinding.bind(item)

        fun bind(text: String) = with(bind) {
            recyclerItemTv.text = text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = if (sets.isEmpty())
            TextAdapterPlaceHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.text_recycler_item, parent, false))
        else
            UserSetsHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_sets_item, parent, false))
        holders.add(holder)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_EMPTY) {
            (holder as TextAdapterPlaceHolder).bind(strings[position])
        } else {
            val uSet = sets[position]
            Database().getSet(uSet.setId) { set ->
                (holder as UserSetsHolder).binding.apply {
                    setHeroIcon.setImageResource(heroStringToResource(set.hero))
                    val ivGears = listOf(
                        setFragmentInclude.ivHead, setFragmentInclude.ivBody,
                        setFragmentInclude.ivArm, setFragmentInclude.ivLeg,
                        setFragmentInclude.ivDecor, setFragmentInclude.ivDevice
                    )
                    val gears = set.gears.map { gs ->
                        val index = GearsList.allGears.map{ it.icon }.indexOf(gearStringToResource(gs.value))
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
                    if (containsId(set))
                        likesBox.setImageResource(R.drawable.liked)
                    else
                        likesBox.setImageResource(R.drawable.unliked)

                    commentButton.setOnClickListener {
                        val action = UserClientFragmentDirections.actionUserClientFragmentToCommentsFragment(set)
                        navController.navigate(action)
                    }

                    settingsButton.setOnClickListener {
                        val wrapper = ContextThemeWrapper(MAIN, R.style.menuStyle_PopupMenu)
                        val popupMenu = PopupMenu(wrapper, it)
                        popupMenu.inflate(R.menu.client_settings_menu)
                        popupMenu.setOnMenuItemClickListener { item ->
                            return@setOnMenuItemClickListener when (item.itemId) {
                                R.id.editItem -> {
                                    TripleButtonUtils.onClickEdit(set) { model ->
                                        val action = UserClientFragmentDirections.actionUserClientFragmentToCreateUserSetFragment(model, set)
                                        navController.navigate(action)
                                    }
                                    true
                                }
                                R.id.deleteItem -> {
                                    TripleButtonUtils.onClickDelete(sets, set) {
                                        holders.remove(holder)
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
                    settingsButton.isClickable = MAIN.prefs.getNickname() == set.from

                    likesCount.text = set.likes.toString()
                    likesBox.setOnClickListener {
                        if (MAIN.prefs.getNickname() == set.from || !MAIN.prefs.getSigned()) return@setOnClickListener
                        if (containsId(set)) {
                            set.likes -= 1
                            removeId(set)
                            Database().addUserSet(set)
                            likesCount.text = "${set.likes}"
                            likesBox.setImageResource(R.drawable.unliked)
                        } else {
                            set.likes += 1
                            set.userLikeIds.add(MAIN.prefs.getEmail()!!)
                            Database().addUserSet(set)
                            likesCount.text = "${set.likes}"
                            likesBox.setImageResource(R.drawable.liked)
                        }
                    }
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

    override fun getItemViewType(position: Int): Int {
        return if (sets.isEmpty()) VIEW_TYPE_EMPTY
        else VIEW_TYPE_FILLED
    }

    override fun getItemCount(): Int = if (sets.isEmpty()) strings.size
    else sets.size

}