package com.bulkapedia.views.temps.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.ADMIN_EMAIL
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.database.User
import com.bulkapedia.databinding.DashboardDialogItemBinding
import com.bulkapedia.views.fragments.DashboardFragmentDirections

class DialogsDashboardRecycler (
    private var users: MutableList<User>,
    private val navController: NavController
) : RecyclerView.Adapter<DialogsDashboardRecycler.DialogViewHolder>() {

    class DialogViewHolder(val bind: DashboardDialogItemBinding): RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
        return DialogViewHolder(
            DashboardDialogItemBinding.bind(LayoutInflater.from(parent.context)
                .inflate(R.layout.dashboard_dialog_item, parent, false))
        )
    }

    override fun onBindViewHolder(holder: DialogViewHolder, position: Int) {
        val user = users[position]
        holder.bind.emailTv.text = user.email
        holder.bind.chatButton.setOnClickListener {
            Database().retrieveUserByEmail(user.email!!) { receiver ->
                Database().retrieveUserByEmail(ADMIN_EMAIL) { admin ->
                    val action = DashboardFragmentDirections
                        .actionDashboardFragmentToDevChatFragment(receiver = receiver, sender = admin)
                    navController.navigate(action)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(filtered: MutableList<User>) {
        users = filtered
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size

}