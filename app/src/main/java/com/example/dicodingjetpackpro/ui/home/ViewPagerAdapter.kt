package com.example.dicodingjetpackpro.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.dicodingjetpackpro.R

class ViewPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val tabTitle = arrayOf(
        R.string.movies,
        R.string.tv_shows,
    )

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment?= null
        when(position){
            0 -> fragment = MovieFragment()
            1 -> fragment = ShowFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(tabTitle[position])
    }

    override fun getCount(): Int {
        return tabTitle.size
    }
}