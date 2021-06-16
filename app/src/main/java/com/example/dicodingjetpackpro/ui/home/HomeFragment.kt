package com.example.dicodingjetpackpro.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerAdapter = ViewPagerAdapter(requireContext(), childFragmentManager)
        with(binding) {
            viewPager.adapter = viewPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
            fabFav.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
            }
        }
    }
}