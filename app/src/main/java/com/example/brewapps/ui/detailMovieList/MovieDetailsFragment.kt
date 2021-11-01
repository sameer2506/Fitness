package com.example.brewapps.ui.detailMovieList

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import com.example.brewapps.R
import com.example.brewapps.databinding.FragmentMovieDetailsBinding
import com.squareup.picasso.Picasso

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var ctx: Context
    private lateinit var layout:View

    private var moviePoster: String? = null
    private var movieTitle: String? = null
    private var movieReleaseDate: String? = null
    private var moviePopularity: String? = null
    private var movieDescription: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        ctx = requireContext()
        layout = requireActivity().findViewById<FragmentContainerView>(R.id.fragmentContainerViewMovieList)

        moviePoster = arguments?.getString("poster")
        movieTitle = arguments?.getString("movie")
        movieReleaseDate = arguments?.getString("release")
        moviePopularity = arguments?.getString("popularity")
        movieDescription = arguments?.getString("overview")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load("https://image.tmdb.org/t/p/w342${moviePoster}").into(binding.poster)
        binding.tvMovieName.text = movieTitle
        binding.releaseDate.text = movieReleaseDate
        binding.popularity.text = moviePopularity
        binding.description.text = movieDescription
        
    }

}