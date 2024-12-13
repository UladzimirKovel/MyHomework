package com.example.myhomework.presentation.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomework.R
import com.example.myhomework.domain.model.Auto
import com.example.myhomework.domain.model.ListAutoRepository
import com.example.myhomework.presentation.adapter.AutoAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListViewAutoFragment : Fragment() {

    private var notesRecyclerView: RecyclerView? = null
    private var autoAdapter: AutoAdapter? = null

    @SuppressLint("NotifyDataSetChanged", "CommitTransaction", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_list_auto, container, false)
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
        noteRecycler()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleAddNote(brandTextView: EditText, messageTextView: EditText) {
        val title = brandTextView.text.toString()
        val text = messageTextView.text.toString()
        val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

        if (title.isNotEmpty() && title.isNotBlank()) {
            val newAuto = Auto.User(title, text)
            ListAutoRepository.addNote(newAuto) //Добавляем заметку в репозиторий
            autoAdapter?.notifyDataSetChanged() //Уведомляем адаптер о том, что данные изменились
            brandTextView.text.clear() // Очищаем поле ввода заголовка
            messageTextView.text.clear() //Очищаем поле ввода текста
            val newAutoDate = Auto.Card(date)
            ListAutoRepository.addNote(newAutoDate)
            autoAdapter?.notifyDataSetChanged()
            SimpleDateFormat.DATE_FIELD.toString()
        }
    }

    private fun backParent(){
        parentFragmentManager.beginTransaction()
            .replace(R.id.newFragmentView, MainFragment(), "Main")
            .addToBackStack(null)
            .commit()
    }

    private fun setupListener() {

        val backMainActivity: Button? = view?.findViewById(R.id.back_main_fragment)
        val addButton: Button? = view?.findViewById(R.id.add_notes_button)
        val brandTextView: EditText? = view?.findViewById(R.id.title_notes_tv)
        val messageTextView: EditText? = view?.findViewById(R.id.message_notes_tv)

        addButton?.setOnClickListener {
            if (brandTextView != null && messageTextView != null) {
                handleAddNote(brandTextView, messageTextView)
            }
        }

        backMainActivity?.setOnClickListener {
            backParent()
        }
    }

    private fun noteRecycler() {
        notesRecyclerView = view?.findViewById(R.id.notes_recycler_view)

        // Получаем изменяемый список заметок
        val notes = ListAutoRepository.getNotes() as MutableList<Auto>
        notesRecyclerView?.layoutManager = LinearLayoutManager(view?.context)
        autoAdapter = AutoAdapter(notes)
        notesRecyclerView?.adapter = autoAdapter

        val smoothScroller = object : LinearSmoothScroller(view?.context) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_END
            }
        }

        smoothScroller.targetPosition = notes.size - 1
        notesRecyclerView?.layoutManager?.startSmoothScroll(smoothScroller)

    }
}


