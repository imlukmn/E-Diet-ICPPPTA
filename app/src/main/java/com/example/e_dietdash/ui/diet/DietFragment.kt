package com.example.e_dietdash.ui.diet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.e_dietdash.R
import com.example.e_dietdash.activity.BuahActivity
import com.example.e_dietdash.activity.MainActivity
import com.example.e_dietdash.databinding.FragmentDietBinding

class DietFragment : Fragment() {

    private var binding: FragmentDietBinding? = null
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_diet, container, false)


        val lmmenu = LinearLayoutManager(activity)
        lmmenu.orientation = LinearLayoutManager.HORIZONTAL
        val lmnutrition = LinearLayoutManager(activity)
        lmnutrition.orientation = LinearLayoutManager.VERTICAL

        val linearLayout = view.findViewById<CardView>(R.id.to_buah)
        linearLayout.setOnClickListener {
            val intent = Intent (activity, BuahActivity::class.java)
            activity?.startActivity(intent)
        }

        val mainActivity = activity as MainActivity
        viewPager = mainActivity.findViewById(R.id.viewPager)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}