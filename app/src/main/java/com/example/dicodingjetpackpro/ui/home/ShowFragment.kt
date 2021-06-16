package com.example.dicodingjetpackpro.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.databinding.FragmentRecyclerviewOnlyBinding
import com.example.dicodingjetpackpro.model.MyModel
import com.example.dicodingjetpackpro.ui.RecyclerViewAdapter
import com.example.dicodingjetpackpro.ui.detail.DetailFragment
import com.example.dicodingjetpackpro.ui.detail.DetailFragment.Companion.EXTRA_ID
import com.example.dicodingjetpackpro.ui.detail.DetailFragment.Companion.SHOW
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ShowFragment: Fragment() {
    private var _binding: FragmentRecyclerviewOnlyBinding?= null
    private val binding get() = _binding as FragmentRecyclerviewOnlyBinding
    private val mViewModel by viewModel<HomeViewModel>()
    private val mAdapter by inject<RecyclerViewAdapter>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerviewOnlyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerview()
    }
    
    private fun initRecyclerview(){
        binding.rvListGeneral.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
//            mViewModel.getShowList.observe(viewLifecycleOwner, {
//                mAdapter.setData(it)
//            })
            lifecycleScope.launchWhenCreated {
                mViewModel.getShowList().collectLatest {
                    mAdapter.submitData(it)
                }
            }
            mAdapter.setOnItemClickCallback(object : RecyclerViewAdapter.OnItemClickCallback {
                override fun onItemClicked(myModel: MyModel) {
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundleOf(EXTRA_ID to myModel, DetailFragment.EXTRAS to SHOW))
                }
            })
        }
    }
}