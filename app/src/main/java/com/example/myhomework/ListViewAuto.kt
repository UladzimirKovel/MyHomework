package com.example.myhomework

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListViewAuto : AppCompatActivity() {

    private var notesRecyclerView: RecyclerView? = null
    private var autoAdapter: AutoAdapter? = null
    private var backMainActivity: Button? = null
    private var addButton: Button? = null
    private var brandTextView: EditText? = null
    private var messageTextView: EditText? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        notesRecyclerView = findViewById(R.id.notes_recycler_view)

        setupRecyclerView()
        initialize()
        initClick()
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

            val newAuto = Auto.User(title, text)
            ListAuto.addNote(newAuto) //Добавляем заметку в репозиторий
            autoAdapter?.notifyDataSetChanged() //Уведомляем адаптер о том, что данные изменились
            brandTextView.text.clear() // Очищаем поле ввода заголовка
            messageTextView.text.clear() //Очищаем поле ввода текста

            val newAutoDate = Auto.Card(date)
            ListAuto.addNote(newAutoDate)
            autoAdapter?.notifyDataSetChanged()
            SimpleDateFormat.DATE_FIELD.toString()
        }
    }

    private fun initClick() {
        addButton?.setOnClickListener {
            handleAddNote(brandTextView!!, messageTextView!!)
        }

        backMainActivity?.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }

    private fun initialize() {
        backMainActivity = findViewById(R.id.back_main_activity)
        addButton = findViewById(R.id.add_notes_button)
        brandTextView = findViewById(R.id.title_notes_tv)
        messageTextView = findViewById(R.id.message_notes_tv)
    }
}


