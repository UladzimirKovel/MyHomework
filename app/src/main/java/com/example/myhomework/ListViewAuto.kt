package com.example.myhomework

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomework.adapter.AutoAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListViewAuto : AppCompatActivity() {

    private var notesRecyclerView: RecyclerView? = null
    private var autoAdapter: AutoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        notesRecyclerView = findViewById(R.id.notes_recycler_view)

        setupRecyclerView()

        val backMainActivity: Button = findViewById(R.id.back_main_activity)
        val addButton: Button = findViewById(R.id.add_notes_button)
        val brandTextView: EditText = findViewById(R.id.title_notes_tv)
        val messageTextView: EditText = findViewById(R.id.message_notes_tv)

        addButton.setOnClickListener {
            handleAddNote(brandTextView, messageTextView)
        }
        backMainActivity.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        // Получаем изменяемый список заметок
        val notes = ListAuto.getNotes() as MutableList<Auto>
        autoAdapter = AutoAdapter(notes)
        notesRecyclerView?.adapter = autoAdapter
        notesRecyclerView?.layoutManager = LinearLayoutManager(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleAddNote(brandTextView: EditText, messageTextView: EditText) {
        val title = brandTextView.text.toString()
        val text = messageTextView.text.toString()
        val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

        if (title.isNotEmpty() && title.isNotBlank()) {
            val newAuto = Auto(title, text, date)
            ListAuto.addNote(newAuto) //Добавляем заметку в репозиторий
            autoAdapter?.notifyDataSetChanged() //Уведомляем адаптер о том, что данные изменились
            brandTextView.text.clear() // Очищаем поле ввода заголовка
            messageTextView.text.clear() //Очищаем поле ввода текста //
        }
    }
}

