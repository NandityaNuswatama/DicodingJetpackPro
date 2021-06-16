package com.example.dicodingjetpackpro.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerAdapter = FavoriteViewPagerAdapter(requireContext(), childFragmentManager)
        with(binding) {
            viewPager.adapter = viewPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
            viewPager.setSwipePagingEnabled(false)
        }
        binding.toolBarFav.toolBar.title = resources.getString(R.string.bookmarked)
    }
}