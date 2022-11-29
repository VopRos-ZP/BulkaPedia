package com.bulkapedia.views.temps.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.GEARS_LIST
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.FavoriteSetItemBinding
import com.bulkapedia.databinding.TextRecyclerItemBinding
import com.bulkapedia.views.fragments.UserClientFragmentDirections
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.data.sets.UserSet
import com.bulkapedia.utils.stringToResource

class FavoritesAdapter (
    private val favoriteSets: MutableList<UserSet>,
    private val strings: MutableList<String>,
    private val navController: NavController
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val holders = mutableListOf<RecyclerView.ViewHolder>()

    companion object {
        const val VIEW_TYPE_EMPTY = 1
        const val VIEW_TYPE_FILLED = 2
    }

    class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = FavoriteSetItemBinding.bind(view)
    }

    class TextAdapterPlaceHolder(item: View): RecyclerView.ViewHolder(item) {

        val bind = TextRecyclerItemBinding.bind(item)

        fun bind(text: String) = with(bind) {
            recyclerItemTv.text = text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = if (favoriteSets.isEmpty())
            TextAdapterPlaceHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.text_recycler_item, parent, false))
        else
            FavoriteViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_set_item, parent, false))
        holders.add(holder)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_EMPTY) {
            (holder as TextAdapterPlaceHolder).bind(strings[position])
        } else {
            val uSet = favoriteSets[position]
            Database().getSet(uSet.setId) { set ->
                (holder as FavoriteViewHolder).binding.apply {
                    setHeroIcon.setImageResource(stringToResource(set.hero))

                    val ivGears = listOf(
                        setFragmentInclude.ivHead, setFragmentInclude.ivBody,
                        setFragmentInclude.ivArm, setFragmentInclude.ivLeg,
                        setFragmentInclude.ivDecor, setFragmentInclude.ivDevice
                    )
                    val gears = set.gears.map { gs ->
                        val index = GEARS_LIST.allGears.map{ it.icon }.indexOf(stringToResource(gs.value))
                        if (index == -1)
                            gs.key to null
                        else
                            gs.key to GEARS_LIST.allGears[index]
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

                    profileBtn.setOnClickListener {
                        Database().retrieveUserByNickname(set.from) { user ->
                            val action =
                                UserClientFragmentDirections.actionUserClientFragmentSelf(user, true)
                            navController.navigate(action)
                        }
                    }

                    commentBtn.setOnClickListener {
                        val action = UserClientFragmentDirections.actionUserClientFragmentToCommentsFragment(set)
                        navController.navigate(action)
                    }

                    likesCount.text = set.likes.toString()
                    likesBox.setOnClickListener {
                        if (MAIN.prefs.getNickname() == set.from || !MAIN.prefs.getSigned()) return@setOnClickListener
                        if (containsId(set)) {
                            set.likes -= 1
                            removeId(set)
                            Database().addUserSet(set)
                            holders.remove(holder)
                            favoriteSets.removeAt(holder.adapterPosition)
                            notifyItemRemoved(holder.adapterPosition)
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
                break
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (favoriteSets.isEmpty()) VIEW_TYPE_EMPTY
        else VIEW_TYPE_FILLED
    }

    override fun getItemCount(): Int = if (favoriteSets.isEmpty()) strings.size
        else favoriteSets.size

}