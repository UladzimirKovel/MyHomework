package com.example.myhomework.ui.theme.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomework.Auto
import com.example.myhomework.ListAuto
import com.example.myhomework.R


class AutoAdapter(private val notes: MutableList<Auto>) :
    RecyclerView.Adapter<AutoAdapter.NoteViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val brandTextView: TextView = itemView.findViewById(R.id.brand_tv)
        val statusTextView: TextView = itemView.findViewById(R.id.status_tv)
        val releaseData: TextView = itemView.findViewById(R.id.release_date_tv)
        private val deleteButton: Button = itemView.findViewById(R.id.delete_notes_data)

        init {
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    try {
                        val noteToRemove = notes[position]
                        ListAuto.removeNote(noteToRemove)// Удаляем заметку из репозитория
                        notifyDataSetChanged()// Уведомляем адаптер о том, что данные изменились
                    } catch (e: Exception) {
                        Log.e("NotesAdapter", "Error deleting note: ${e.message}")
                        Toast.makeText(itemView.context, "Error deleting note", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_auto, parent, false)
        )
    }//Создает Holder,где будут храниться наши View

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.apply {
            brandTextView.text = note.brand
            statusTextView.text = note.status
            releaseData.text = note.releaseData
        }

    }//Ф-ия,которая будет отрисовывать наш Holder в нашем RecyclerView

    override fun getItemCount(): Int {
        return notes.size
    }


}