package com.example.globalconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation

class main : Fragment() ,View.OnClickListener {

    var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<ImageView>(R.id.imageView).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.imageView2).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.imageView3).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.imageView4).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.imageView6).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.imageView11).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.imageView -> navController!!.navigate(R.id.action_main_to_money)
            R.id.imageView2 -> navController!!.navigate(R.id.action_main_to_temp)
            R.id.imageView3 -> navController!!.navigate(R.id.action_main_to_weight)
            R.id.imageView4 -> navController!!.navigate(R.id.action_main_to_distance)
            R.id.imageView6 -> navController!!.navigate(R.id.action_main_to_scale)
            R.id.imageView11 -> navController!!.navigate(R.id.action_main_to_prssure)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}

