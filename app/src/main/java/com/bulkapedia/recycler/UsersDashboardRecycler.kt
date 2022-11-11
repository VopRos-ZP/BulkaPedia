package com.bulkapedia.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.database.User
import com.bulkapedia.databinding.UserDashboardItemBinding

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
                    // soon
                }
            }
        }
    }

    fun filter(filtered: MutableList<User>) {
        users = filtered
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size


}