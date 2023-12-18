package com.example.palace_resorts.flows.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.palace_resorts.base.FragmentView
import com.example.palace_resorts.databinding.FragmentHomeBinding

class HomeFragment : FragmentView() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root
}