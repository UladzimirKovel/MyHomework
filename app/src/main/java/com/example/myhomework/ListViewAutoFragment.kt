package com.example.myhomework

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
import com.example.myhomework.ui.theme.adapter.AutoAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListViewAutoFragment : Fragment() {

    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var autoAdapter: AutoAdapter

    @SuppressLint("NotifyDataSetChanged", "CommitTransaction", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_list_auto, container, false)

        notesRecyclerView = currentView.findViewById(R.id.notes_recycler_view)

        // Получаем изменяемый список заметок
        val notes = ListAuto.getNotes() as MutableList<Auto>
        notesRecyclerView.layoutManager = LinearLayoutManager(currentView.context)
        autoAdapter = AutoAdapter(notes)
        notesRecyclerView.adapter = autoAdapter

        val backMainActivity: Button = currentView.findViewById(R.id.back_main_fragment)
        val addButton: Button = currentView.findViewById(R.id.add_notes_button)
        val brandTextView: EditText = currentView.findViewById(R.id.title_notes_tv)
        val messageTextView: EditText = currentView.findViewById(R.id.message_notes_tv)

        addButton.setOnClickListener {
            val title = brandTextView.text.toString()
            val text = messageTextView.text.toString()
            val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            if (title.isNotEmpty() && title.isNotBlank()) {
                val newAuto = Auto.User(title, text)
                ListAuto.addNote(newAuto) //Добавляем заметку в репозиторий
                autoAdapter.notifyDataSetChanged() //Уведомляем адаптер о том, что данные изменились
                brandTextView.text.clear() // Очищаем поле ввода заголовка
                messageTextView.text.clear() //Очищаем поле ввода текста
                val newAutoDate = Auto.Card(date)
                ListAuto.addNote(newAutoDate)
                autoAdapter.notifyDataSetChanged()
                SimpleDateFormat.DATE_FIELD.toString()
            }
        }

        val smoothScroller = object: LinearSmoothScroller(currentView.context){
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_END
            }
        }

        smoothScroller.targetPosition = notes.size - 1
        notesRecyclerView.layoutManager?.startSmoothScroll(smoothScroller)

        backMainActivity.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, MainFragment(), "Main")
                .addToBackStack(null)
                .commit()
        }
        return currentView
    }
}


