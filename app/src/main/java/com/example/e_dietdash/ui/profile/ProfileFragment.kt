package com.example.e_dietdash.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.e_dietdash.R
import com.example.e_dietdash.`object`.CustomDialog
import com.example.e_dietdash.activity.QuestionActivity
import com.example.e_dietdash.activity.SplashActivity
import com.example.e_dietdash.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val btnLogout = view.findViewById<Button>(R.id.btn_logout)
        val btnEdit = view.findViewById<Button>(R.id.btn_update)
        btnEdit.setOnClickListener {
            startActivity(Intent(requireContext(), QuestionActivity::class.java).apply {
                putExtra(QuestionActivity.REQ_EDIT, true)
            })
        }
        btnLogout.setOnClickListener {
            initFirebaseAuth()
            auth.signOut()
            val intent = Intent(requireContext(), SplashActivity::class.java)
            startActivity(intent)
            val activity = requireActivity()
            activity.finish()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
    }
}