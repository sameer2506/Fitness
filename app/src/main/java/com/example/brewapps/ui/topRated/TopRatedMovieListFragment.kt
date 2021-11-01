package com.example.brewapps.ui.topRated

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brewapps.R
import com.example.brewapps.data.entities.Result
import com.example.brewapps.data.network.Resource
import com.example.brewapps.databinding.FragmentTopRatedMovieListBinding
import com.example.brewapps.ui.MovieListItem
import com.example.brewapps.ui.ViewModelClass
import com.example.brewapps.ui.ViewModelFactory
import com.example.brewapps.util.handleApiError
import com.example.brewapps.util.log
import com.example.brewapps.util.roundTheNumber
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class TopRatedMovieListFragment : Fragment(), KodeinAware, MovieListItem.OnItemClickListener {

    override val kodein by kodein()

    private val factory: ViewModelFactory by instance()

    private lateinit var binding: FragmentTopRatedMovieListBinding
    private lateinit var viewModel: ViewModelClass

    private lateinit var ctx: Context
    private lateinit var layout: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTopRatedMovieListBinding.inflate(layoutInflater)
        ctx = requireContext()
        layout =
            requireActivity().findViewById<FragmentContainerView>(R.id.fragmentContainerViewMovieList)
        viewModel = ViewModelProvider(this, factory).get(ViewModelClass::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovieList()

        binding.swipeRefresh.setOnRefreshListener {
            getMovieList()
        }
    }

    private fun getMovieList() {
        binding.swipeRefresh.isEnabled = true

        viewModel.getTopRatedData()

        viewModel.topRatedData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    bindUI(it.value.results)
                    log(it.value.toString())
                }
                is Resource.Loading -> {
                }
                is Resource.Failure -> {
                    handleApiError(it, ctx, layout)
                }
            }
        })

        binding.swipeRefresh.isRefreshing = false
    }

    private fun bindUI(list: List<Result>) {
        binding.recyclerViewNowPlaying.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = MovieListItem(list, this@TopRatedMovieListFragment, context)
        }
    }

    override fun onItemClick(list: Result) {
        val popularity = roundTheNumber(list.popularity)
        val bundle = bundleOf(
            "poster" to list.poster_path,
            "movie" to list.title,
            "release" to list.release_date,
            "popularity" to popularity,
            "overview" to list.overview
        )
        findNavController().navigate(R.id.action_movie_details, bundle)
    }


}