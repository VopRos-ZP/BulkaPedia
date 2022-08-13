package com.bulkapedia.listeners

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (
    fa: FragmentActivity,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}