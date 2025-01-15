package com.example.myhomework.presentation.view_model

import android.annotation.SuppressLint
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.example.myhomework.domain.repository.Auto
import com.example.myhomework.domain.repository.ListAutoRepository
import com.example.myhomework.presentation.adapter.AutoAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListViewAutoModel: ViewModel() {

    private val repository: ListAutoRepository? = null
    private val autoAdapter: AutoAdapter? = null

    @SuppressLint("NotifyDataSetChanged")
    fun handleAddNote(brandTextView: EditText, messageTextView: EditText) {
        val title = brandTextView.text.toString()
        val text = messageTextView.text.toString()
        val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

        if (title.isNotEmpty() && title.isNotBlank()) {

            val newAuto = Auto.User(title, text)
            repository?.addNote(newAuto) //Добавляем заметку в репозиторий
            autoAdapter?.notifyDataSetChanged() //Уведомляем адаптер о том, что данные изменились
            brandTextView.text.clear() // Очищаем поле ввода заголовка
            messageTextView.text.clear() //Очищаем поле ввода текста

            val newAutoDate = Auto.Card(date)
            repository?.addNote(newAutoDate)
            autoAdapter?.notifyDataSetChanged()
            SimpleDateFormat.DATE_FIELD.toString()
        }
    }
}