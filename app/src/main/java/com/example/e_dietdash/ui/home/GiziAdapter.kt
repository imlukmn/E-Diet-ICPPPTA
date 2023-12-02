package com.example.e_dietdash.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_dietdash.R
import com.example.e_dietdash.adapter.FirestoreAdapter
import com.example.e_dietdash.model.Gizi
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query

class GiziAdapter(query: Query) : FirestoreAdapter<GiziAdapter.GiziViewHolder>(query) {

    class GiziViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val natrium: TextView = itemView.findViewById(R.id.percent_na)
        private val kalium: TextView = itemView.findViewById(R.id.percent_k)
        private val serat: TextView = itemView.findViewById(R.id.percent_fiber)
        private val lemak: TextView = itemView.findViewById(R.id.percent_fat)
        private val date: TextView = itemView.findViewById(R.id.date)


        fun bind(snapshot: DocumentSnapshot) {
            val gizi: Gizi? = snapshot.toObject(Gizi::class.java)
            natrium.text = gizi?.natrium.toString()
            kalium.text = gizi?.kalium.toString()
            serat.text = gizi?.serat.toString()
            lemak.text = gizi?.lemak.toString()
            date.text = gizi?.date.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiziViewHolder {
        return GiziViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.list_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GiziViewHolder, position: Int) {
        getSnapshot(position)?.let { snapshot -> holder.bind(snapshot) }
    }
}