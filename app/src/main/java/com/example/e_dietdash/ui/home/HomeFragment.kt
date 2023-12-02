package com.example.e_dietdash.ui.home

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_dietdash.R
import com.example.e_dietdash.`object`.Const.DATA
import com.example.e_dietdash.`object`.CustomDialog
import com.example.e_dietdash.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.coroutines.channels.ticker

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    lateinit var Name : TextView
    lateinit var Gender : TextView
    lateinit var Age : TextView
    lateinit var Grade : TextView
    lateinit var Sistolik : TextView
    lateinit var Diastolik : TextView
    lateinit var RvHome : RecyclerView
    lateinit var adapter: GiziAdapter

    private val auth = FirebaseAuth.getInstance()
    private val user = auth.currentUser
    private val userId = user?.uid

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val query: Query = FirebaseFirestore.getInstance().collection(DATA).whereEqualTo("strId", userId)
        val recyclerView: RecyclerView = view.findViewById(R.id.rv_home)
        Name = view.findViewById(R.id.name)
        Gender = view.findViewById(R.id.gender)
        Age = view.findViewById(R.id.age)
        Grade = view.findViewById(R.id.grade)
        Sistolik = view.findViewById(R.id.sistolik)
        Diastolik = view.findViewById(R.id.diastolik)

        adapter = GiziAdapter(query)
        recyclerView.adapter = adapter

        activity?.let { CustomDialog.showLoading(it) }
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val userId = user?.uid
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val docRef = db.document("users/$userId")
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                val data = document.data
                val age = data?.get("intAge")
                val jk = data?.get("jk")
                val strDiastolik = data?.get("strDiastolik")
                val strSistolik = data?.get("strSistolik")
                val nama = data?.get("strName")
                val grad = data?.get("grades").toString()

                when (grad) {
                    "3" -> {
                        Grade.text = "Grade 1"
                    }
                    "2" -> {
                        Grade.text = "Grade 2"
                    }
                    "1" -> {
                        Grade.text = "Grade 1"
                    }
                    "0" -> {
                        Grade.text = "Normal"
                    }
                }
                Name.text = nama.toString()
                Gender.text = jk.toString()
                Age.text = age.toString() + " Tahun"
                Sistolik.text = strSistolik.toString()
                Diastolik.text = strDiastolik.toString()

                CustomDialog.hideLoading()
            } else {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }



        val lm = LinearLayoutManager(activity)
        lm.orientation = LinearLayoutManager.VERTICAL



        return view
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.startListening()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}