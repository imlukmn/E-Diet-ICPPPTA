package com.example.e_dietdash.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.e_dietdash.R
import com.example.e_dietdash.`object`.Const
import com.example.e_dietdash.`object`.Const.DATA
import com.example.e_dietdash.`object`.CustomDialog
import com.example.e_dietdash.model.Gizi
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class BuahActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    private val mFirestore = FirebaseFirestore.getInstance()
    private val mUsersCollection = mFirestore.collection(Const.DATA)
    val user = auth.currentUser
    val userId = user?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buah)

        val kirim = findViewById<Button>(R.id.kirim)
        val viewPisang = findViewById<LinearLayout>(R.id.view_pisang)
        val inputPisang = findViewById<EditText>(R.id.input_pisang)
        val checkPisang = findViewById<CheckBox>(R.id.check_pisang)
        val viewAnggur = findViewById<LinearLayout>(R.id.view_anggur)
        val inputAnggur = findViewById<EditText>(R.id.input_anggur)
        val checkAnggur = findViewById<CheckBox>(R.id.check_anggur)

        checkPisang.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewPisang.visibility = View.VISIBLE
            }
            if (!isChecked) {
                viewPisang.visibility = View.GONE
                inputPisang.text = null
            }
        }
        checkAnggur.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewAnggur.visibility = View.VISIBLE
            }
            if (!isChecked) {
                viewAnggur.visibility = View.GONE
                inputAnggur.text = null
            }
        }

        kirim.setOnClickListener {
            var natrium = 0
            var kalium = 0
            var serat = 0
            var lemak = 0

            val time = Calendar.getInstance().time
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val current = formatter.format(time)
            val auth = FirebaseAuth.getInstance()
            val user = auth.currentUser
            val userId = user?.uid
            val db: FirebaseFirestore = FirebaseFirestore.getInstance()
            val docRef = db.collection("data").whereEqualTo("userId", userId).whereEqualTo("date", current)
            docRef.get().addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    for (document in documents) {
                        val data = document.data
                        kalium = data["kalium"] as Int
                        natrium = data["natrium"] as Int
                        serat = data["serat"] as Int
                        lemak = data["lemak"] as Int
                        Toast.makeText(this, kalium.toString(), Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, natrium.toString(), Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, serat.toString(), Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, lemak.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            if (!inputPisang.text.toString().isEmpty()) {
                val inputPisangs = inputPisang.text.toString().toInt()
                natrium += (inputPisangs * 0.2).toInt()
                kalium += (inputPisangs * 0.3).toInt()
                serat += (inputPisangs * 0.4).toInt()
                lemak += (inputPisangs * 0.5).toInt()
            }
            if (!inputAnggur.text.toString().isEmpty()) {
                val inputAnggurs = inputAnggur.text.toString().toInt()
                natrium += (inputAnggurs * 0.2).toInt()
                kalium += (inputAnggurs * 0.3).toInt()
                serat += (inputAnggurs * 0.4).toInt()
                lemak += (inputAnggurs * 0.5).toInt()
            }
            setData(userId, natrium, kalium, serat, lemak)
        }
    }

    private fun setData(strId: String?, natrium: Int?, kalium: Int?, serat: Int?, lemak: Int?) {
        createUser(strId, natrium, kalium, serat, lemak).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                CustomDialog.hideLoading()
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            CustomDialog.hideLoading()
            Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createUser(strId: String?, natrium: Int?, kalium: Int?, serat: Int?, lemak: Int?): Task<Void> {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val current = formatter.format(time)
        val writeBatch = mFirestore.batch()
        val ids = "$strId-$current"
        val data = Gizi(strId, natrium, kalium, serat, lemak, current)
        writeBatch.set(mUsersCollection.document(ids), data)
        return writeBatch.commit()
    }
}