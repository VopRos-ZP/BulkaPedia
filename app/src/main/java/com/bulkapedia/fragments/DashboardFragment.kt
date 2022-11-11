package com.bulkapedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.DashboardLayoutBinding
import com.bulkapedia.listeners.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment() {

    private lateinit var bind: DashboardLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DashboardLayoutBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.actionBar.actionBar.title = getString(R.string.dashboard)
        bind.actionBar.actionBar.setNavigationIcon(R.drawable.backspace)
        bind.actionBar.actionBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        Database().getAllUsers { users ->
            val fragments = listOf(
                UserDashboardRecyclerFragment(users)
            )
            bind.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            bind.viewPager2.adapter = ViewPagerAdapter(MAIN as AppCompatActivity, fragments)
            TabLayoutMediator(bind.tabLayout, bind.viewPager2) { tab, _ ->
                tab.text = getString(R.string.users)
            }.attach()
        }
    }

}