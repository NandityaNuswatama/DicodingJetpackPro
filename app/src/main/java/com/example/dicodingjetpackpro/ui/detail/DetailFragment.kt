package com.example.dicodingjetpackpro.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.data.local.entity.MyModelEntity
import com.example.dicodingjetpackpro.databinding.FragmentDetailBinding
import com.example.dicodingjetpackpro.model.MyCast
import com.example.dicodingjetpackpro.model.MyModel
import com.example.dicodingjetpackpro.utils.imageLink
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding
    private val myModel by lazy {
        arguments?.getParcelable<MyModel>(EXTRA_ID)
    }
    private val source by lazy {
        arguments?.getInt(EXTRAS, 0)
    }
    private val mViewModel by viewModel<DetailViewModel>()
    private val genreAdapter by inject<GenreAdapter>()
    private val actorsAdapter by inject<ActorsAdapter>()
    
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRAS = "extras"
        const val MOVIE = 101
        const val SHOW = 110
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        initListener()
    }
    
    @SuppressLint("SetTextI18n")
    private fun setView() {
        myModel?.id?.let {
            when (source) {
                MOVIE -> {
                    mViewModel.getDetailMovie(it).observe(viewLifecycleOwner, { mModel ->
                        binding.apply {
                            titleDetail.text = mModel.title
                            score.text = mModel.rating.toString()
                            overview.text = mModel.overview
                            runtime.text = mModel.runtime + resources.getString(R.string.minutes)
                            date.text = mModel.date
                            backdrop.load(imageLink() + mModel.poster) {
                                placeholder(R.drawable.icon_image)
                            }
                            rvGenre.apply {
                                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                                adapter = genreAdapter
                                genreAdapter.setData(mModel?.genre!!)
                            }
                        }
                    })
                    mViewModel.getActorsMovie(it).observe(viewLifecycleOwner, {
                        binding.rvCast.apply {
                            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                            adapter = actorsAdapter
                            actorsAdapter.setData(it)
                            actorsAdapter.setOnItemClickCallback(object : ActorsAdapter.OnItemClickCallback {
                                override fun onItemClicked(myCast: MyCast) {
                                    Toast.makeText(requireContext(), "${myCast.name} as ${myCast.character}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                    })
                }
                SHOW -> {
                    mViewModel.getDetailShow(it).observe(viewLifecycleOwner, { mModel ->
                        binding.apply {
                            titleDetail.text = mModel.title
                            score.text = mModel.rating.toString()
                            overview.text = mModel.overview
                            runtime.text = mModel.runtime + resources.getString(R.string.minutes)
                            date.text = mModel.date
                            backdrop.load(imageLink() + mModel.poster)
                            rvGenre.apply {
                                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                                adapter = genreAdapter
                                genreAdapter.setData(mModel?.genre!!)
                            }
                        }
                    })
                    mViewModel.getActorsShow(it).observe(viewLifecycleOwner, {
                        binding.rvCast.apply {
                            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                            adapter = actorsAdapter
                            actorsAdapter.setData(it)
                            actorsAdapter.setOnItemClickCallback(object : ActorsAdapter.OnItemClickCallback {
                                override fun onItemClicked(myCast: MyCast) {
                                    Toast.makeText(requireContext(), "${myCast.name} as ${myCast.character}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                    })
                }
            }
        }
    }
    
    private fun initListener() {
        with(binding) {
            btnBack.setOnClickListener { findNavController().navigateUp() }
            mViewModel.isFavorite(myModel?.id!!, source!!).observe(viewLifecycleOwner,{
                initFavorite(it)
            })
        }
    }

    private fun insertFavorite(){
        val item = MyModelEntity(
            myModel?.id,
            myModel?.title,
            myModel?.poster,
            myModel?.background,
            myModel?.rating,
            myModel?.date,
            source!!
        )
        mViewModel.insertToDb(item)
        Toast.makeText(requireContext(), resources.getString(R.string.insertDB, myModel?.title), Toast.LENGTH_SHORT).show()
    }

    private fun deleteFavorite(){
        mViewModel.deleteById(myModel?.id!!, source!!)
        Toast.makeText(requireContext(), resources.getString(R.string.deleteDB, myModel?.title), Toast.LENGTH_SHORT).show()
    }

    private fun initFavorite(isFavorite: Boolean){
        if (!isFavorite){
            binding.fabBookmark.setOnClickListener {
                insertFavorite()
            }
            binding.fabBookmark.setImageResource(R.drawable.icon_bookmark_border)
        }else{
            binding.fabBookmark.setOnClickListener {
                deleteFavorite()
            }
            binding.fabBookmark.setImageResource(R.drawable.icon_bookmark)
        }
    }
}