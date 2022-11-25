package com.bulkapedia.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.database.User
import com.bulkapedia.databinding.UserDashboardItemBinding
import com.bulkapedia.views.StatsView

class UsersDashboardRecycler(
    private var users: MutableList<User>
) : RecyclerView.Adapter<UsersDashboardRecycler.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bind = UserDashboardItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_dashboard_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val oUser = users[position]
        Database().retrieveUserByEmail(oUser.email!!) { user ->
            holder.bind.apply {
                emailTv.text = user.email
                passwordTv.text = user.password
                nicknameTv.text = user.nickname
                // init onClickSettings
                userSettings.setOnClickListener {
                    val wrapper = ContextThemeWrapper(MAIN, R.style.menuStyle_PopupMenu)
                    val menu = PopupMenu(wrapper, it)
                    menu.inflate(R.menu.user_dashboard_menu)
                    menu.menu.forEach { mi ->
                        if (user.mains.map { i -> it.context.getString(i.key.toInt()) }.contains(mi.title)) {
                            mi.isChecked = true
                        }
                    }
                    menu.setOnMenuItemClickListener { mi ->
                        when (mi.itemId) {
                            R.id.arnieItem -> addMains(user, R.string.arnie)
                            R.id.sparkleItem -> addMains(user, R.string.sparkle)
                            R.id.cyclopsItem -> addMains(user, R.string.cyclops)
                            R.id.hurricaneItem -> addMains(user, R.string.hurricane)
                            R.id.ghostItem -> addMains(user, R.string.ghost)
                            R.id.angelItem -> addMains(user, R.string.angel)
                            R.id.freddieItem -> addMains(user, R.string.freddie)
                            R.id.ravenItem -> addMains(user, R.string.raven)
                            R.id.blotItem -> addMains(user, R.string.blot)
                            R.id.fireflyItem -> addMains(user,  R.string.firefly)
                            R.id.lynxItem -> addMains(user, R.string.lynx)
                            R.id.slayerItem -> addMains(user, R.string.slayer)
                            R.id.mirageItem -> addMains(user, R.string.mirage)
                            R.id.smogItem -> addMains(user, R.string.smog)
                            R.id.dragoonItem -> addMains(user, R.string.dragoon)
                            R.id.bastionItem -> addMains(user, R.string.bastion)
                            R.id.berthaItem -> addMains(user, R.string.bertha)
                            R.id.leviathanItem -> addMains(user, R.string.leviathan)
                            R.id.stalkerItem -> addMains(user, R.string.stalker)
                            R.id.docItem -> addMains(user, R.string.doc)
                            R.id.leviItem -> addMains(user, R.string.levi)
                            R.id.satoshiItem -> addMains(user, R.string.satoshi)
                            else -> false
                        }
                    }
                    menu.show()
                }
            }
        }
    }

    private fun addMains(user: User, res: Int): Boolean {
        StatsView(MAIN, user, res).show()
        return true
    }

    fun filter(filtered: MutableList<User>) {
        users = filtered
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size


}