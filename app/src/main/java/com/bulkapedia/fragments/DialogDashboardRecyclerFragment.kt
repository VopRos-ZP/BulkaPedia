package com.bulkapedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulkapedia.MAIN
import com.bulkapedia.database.User
import com.bulkapedia.databinding.UserDashboardRecyclerFragmentBinding
import com.bulkapedia.recycler.DialogsDashboardRecycler

class DialogDashboardRecyclerFragment (
    private val users: MutableList<User>,
    private val navController: NavController
) : Fragment() {

    private lateinit var bind: UserDashboardRecyclerFragmentBinding
    private lateinit var adapter: DialogsDashboardRecycler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = UserDashboardRecyclerFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.userRecycler.setsRecycler.layoutManager =
            LinearLayoutManager(MAIN, LinearLayoutManager.VERTICAL, false)
        adapter = DialogsDashboardRecycler(users, navController)
        bind.userRecycler.setsRecycler.adapter = adapter
        bind.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                filter(msg)
                return false
            }
        })
    }

    private fun filter(text: String) {
        val filtered = mutableListOf<User>()
        for (user in users) {
            if ((user.email?.contains(text, true) == true) || text.isEmpty()) {
                filtered.add(user)
            }
        }
        if (filtered.isNotEmpty())
            adapter.filter(filtered)
    }

}