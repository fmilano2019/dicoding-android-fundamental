package com.example.githubuserapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.adapter.ListUserAdapter
import com.example.githubuserapp.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_followers.*

/**
 * A simple [Fragment] subclass.
 */
class FollowersFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var adapter: ListUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewModel()
        setupObservers()
    }

    private fun setupObservers() {
        detailViewModel.getFollowers().observe(viewLifecycleOwner, Observer {
            pb_followers.visibility = View.GONE
            adapter.addUsers(it)
        })
        detailViewModel.getMessage().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                pb_followers.visibility = View.GONE
            }
        })
        detailViewModel.clearMessage()
    }

    private fun setupViewModel() {
        detailViewModel =
            ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
                DetailViewModel::class.java
            )
    }

    private fun setupUI() {
        pb_followers.visibility = View.VISIBLE
        rv_followers.setHasFixedSize(true)
        rv_followers.layoutManager = LinearLayoutManager(context)
        adapter = ListUserAdapter(arrayListOf()) {}
        rv_followers.adapter = adapter
    }

}
