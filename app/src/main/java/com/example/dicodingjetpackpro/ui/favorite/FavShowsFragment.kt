package com.example.dicodingjetpackpro.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.data.local.entity.MyModelEntity
import com.example.dicodingjetpackpro.databinding.FragmentFavChildBinding
import com.example.dicodingjetpackpro.ui.detail.DetailFragment
import com.example.dicodingjetpackpro.utils.DataMapper
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FavShowsFragment : Fragment() {
    private var _binding: FragmentFavChildBinding? = null
    private val binding get() = _binding as FragmentFavChildBinding
    private val mViewModel by viewModel<FavoriteViewModel>()
    private val mAdapter by inject<FavAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavChildBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFav.apply {
            adapter = mAdapter
            mViewModel.getFavoriteShows().observe(viewLifecycleOwner, {
                mAdapter.setDataMovie(it)
            })
            mAdapter.setOnItemClickCallback(object : FavAdapter.OnItemClickCallback{
                override fun onItemClicked(item: MyModelEntity) {
                    findNavController().navigate(
                        R.id.action_favoriteFragment_to_detailFragment, bundleOf(
                            DetailFragment.EXTRA_ID to DataMapper.mapEntityToModel(item),
                            DetailFragment.EXTRAS to DetailFragment.SHOW
                        )
                    )
                }
            })
        }
    }
}