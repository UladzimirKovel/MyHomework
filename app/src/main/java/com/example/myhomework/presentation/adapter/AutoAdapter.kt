package com.example.myhomework.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomework.databinding.ItemAutoBinding
import com.example.myhomework.databinding.RecyclerViewNewItemBinding
import com.example.myhomework.domain.repository.Auto
import com.example.myhomework.domain.repository.ListAutoRepository


class AutoAdapter(
    private val context: Context,
    private val notes: MutableList<Auto>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class CardViewHolder(binding: RecyclerViewNewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val releaseData: TextView = binding.infoBlockView

//        private val deleteButton1: Button = itemView.findViewById(R.id.delete_notes_data)
//        private val shareButton1: Button = itemView.findViewById(R.id.share_note)

        init {
            binding.shareNote.setOnClickListener {
                shareNote(adapterPosition)
            }
//            shareButton1.setOnClickListener {
//                shareNote(adapterPosition)
//            }

            binding.deleteNotesData.setOnClickListener {
                deleteNote(adapterPosition)
            }
//            deleteButton1.setOnClickListener {
//                deleteNote(adapterPosition)
//            }
        }
    }

    inner class NoteViewHolder(val binding: ItemAutoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val localBinding = binding

//        val brandTextView: TextView = itemView.findViewById(R.id.brand_tv)
//        val statusTextView: TextView = itemView.findViewById(R.id.status_tv)
//        private val deleteButton: Button = itemView.findViewById(R.id.delete_notes_data)
//        private val shareButton: Button = itemView.findViewById(R.id.share_note)

        init {

            binding.shareNote.setOnClickListener {
                shareNote(adapterPosition)
            }

//            shareButton.setOnClickListener {
//                shareNote(adapterPosition)
//            }

            binding.deleteNotesData.setOnClickListener {
                deleteNote(adapterPosition)
            }

//            deleteButton.setOnClickListener {
//                deleteNote(adapterPosition)
//            }
        }
    }

    //    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoViewHolder {
//
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = ItemAutoBinding.inflate(layoutInflater, parent, false)
//        return AutoViewHolder(binding)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {

            AdapterType.USER_TYPE.ordinal -> {

                val itemAutoBinding =
                    ItemAutoBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                NoteViewHolder(itemAutoBinding)
            }

            AdapterType.CARD_TYPE.ordinal -> {

                val recyclerViewNewItemBinding =
                    RecyclerViewNewItemBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                CardViewHolder(recyclerViewNewItemBinding)
            }

            else -> throw IllegalArgumentException("No such type")
        }
//        return NoteViewHolder(
//        LayoutInflater.from(parent.context).inflate(R.layout.item_auto, parent, false)
//        )  //Создает Holder,где будут храниться наши View
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (val note = notes[position]) {

            is Auto.User -> (holder as NoteViewHolder).apply {
                binding.statusTv.text = note.status
                binding.brandTv.text = note.brand

//                localBinding.brandTv.text = note.brand
//                localBinding.statusTv.text = note.status

//                statusTextView.text = note.status
//                brandTextView.text = note.brand

            }

            is Auto.Card -> (holder as CardViewHolder).apply {

                releaseData.text = note.releaseData
            }

            else -> throw IllegalArgumentException("Unknown type at position")
        }
//        holder.brandTextView.text = note.brand
//        holder.statusTextView.text = note.status
//        holder.releaseData.text = note.releaseData
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
            ListAutoRepository.removeNote(noteToRemove)// Удаляем заметку из репозитория
            notifyDataSetChanged()// Уведомляем адаптер о том, что данные изменились
        }
    }
}