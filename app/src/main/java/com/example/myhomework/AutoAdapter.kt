package com.example.myhomework

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AutoAdapter(
    private val context: Context,
    private val notes: MutableList<Auto>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val releaseData: TextView = itemView.findViewById(R.id.info_block_view)
        private val deleteButton1: Button = itemView.findViewById(R.id.delete_notes_data)
        private val shareButton1: Button = itemView.findViewById(R.id.share_note)

        init {
            shareButton1.setOnClickListener {
                shareNote(adapterPosition)
            }

            deleteButton1.setOnClickListener {
                deleteNote(adapterPosition)
            }
        }
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val brandTextView: TextView = itemView.findViewById(R.id.brand_tv)
        val statusTextView: TextView = itemView.findViewById(R.id.status_tv)

        private val deleteButton: Button = itemView.findViewById(R.id.delete_notes_data)
        private val shareButton: Button = itemView.findViewById(R.id.share_note)

        init {
            shareButton.setOnClickListener {
                shareNote(adapterPosition)
            }

            deleteButton.setOnClickListener {
                deleteNote(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            AdapterType.USER_TYPE.ordinal -> NoteViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_auto, parent, false)
            )

            AdapterType.CARD_TYPE.ordinal -> CardViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_new_item, parent, false)
            )

            else -> throw IllegalArgumentException("No such type")
        }/*return NoteViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_auto, parent, false)
        )  //Создает Holder,где будут храниться наши View*/
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val note = notes[position]) {

            is Auto.User -> (holder as NoteViewHolder).apply {
                statusTextView.text = note.status
                brandTextView.text = note.brand
            }

            is Auto.Card -> (holder as CardViewHolder).apply {
                releaseData.text = note.releaseData
            }

            else -> throw IllegalArgumentException("Unknown type at position")
        }/*holder.brandTextView.text = note.brand
        holder.statusTextView.text = note.status
        holder.releaseData.text = note.releaseData*/
    }   //Ф-ия,которая будет отрисовывать наш Holder в нашем RecyclerView

    override fun getItemCount(): Int = notes.size

    override fun getItemViewType(position: Int): Int = when (notes[position]) {
        is Auto.User -> AdapterType.USER_TYPE.ordinal
        is Auto.Card -> AdapterType.CARD_TYPE.ordinal
        else -> throw IllegalArgumentException("Unknown type at position")
    }

    private enum class AdapterType {
        USER_TYPE, CARD_TYPE
    }

    private fun shareNote(position: Int) {

        if (position != RecyclerView.NO_POSITION) {
            val shareText = when (val noteToShare = notes[position]) {
                is Auto.User -> "Brand: ${noteToShare.brand}, Status: ${noteToShare.status}"
                is Auto.Card -> "Release Data: ${noteToShare.releaseData}"
                else -> "Unknown note type"
            }
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            context.startActivity(
                Intent.createChooser(
                    shareIntent, "Share note via"
                )
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteNote(position: Int) {

        if (position != RecyclerView.NO_POSITION) {

            val noteToRemove = notes[position]// Извлекаем заметку, которую нужно удалить, по текущей позиции
            ListAuto.removeNote(noteToRemove)// Удаляем заметку из репозитория
            notifyDataSetChanged()// Уведомляем адаптер о том, что данные изменились
        }
    }
}