package com.example.myhomework.presentation.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhomework.R
import com.example.myhomework.databinding.FragmentListAutoBinding
import com.example.myhomework.domain.repository.Auto
import com.example.myhomework.domain.repository.ListAutoRepository
import com.example.myhomework.presentation.adapter.AutoAdapter
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListViewAutoFragment : Fragment() {

    private var autoAdapter: AutoAdapter? = null
    private val repository : ListAutoRepository by inject()

    private var _binding : FragmentListAutoBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged", "CommitTransaction", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        val currentView = inflater.inflate(R.layout.fragment_list_auto, container, false)
//        return currentView
        _binding = FragmentListAutoBinding.inflate(layoutInflater, container, false)

        return binding.root
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
            repository.addNote(newAuto) //Добавляем заметку в репозиторий
            autoAdapter?.notifyDataSetChanged() //Уведомляем адаптер о том, что данные изменились
            brandTextView.text.clear() // Очищаем поле ввода заголовка
            messageTextView.text.clear() //Очищаем поле ввода текста

            val newAutoDate = Auto.Card(date)
            repository.addNote(newAutoDate)
            autoAdapter?.notifyDataSetChanged()
            SimpleDateFormat.DATE_FIELD.toString()
        }
    }

    private fun backParent() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.newFragmentView, MainFragment(), "Main")
            .addToBackStack(null)
            .commit()
    }

    private fun setupListener() {

        binding.addNotesButton.setOnClickListener {
            binding.apply {
                if (titleNotesTv != null && messageNotesTv != null) {
                    handleAddNote(binding.titleNotesTv, binding.messageNotesTv)
                }
            }
        }

        binding.backMainFragment.setOnClickListener {
            backParent()
        }
    }

    private fun noteRecycler() {

        // Получаем изменяемый список заметок
        val notes = repository.getNotes() as MutableList<Auto>
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(view?.context)
        autoAdapter = AutoAdapter(requireContext(),notes)
        binding.notesRecyclerView.adapter = autoAdapter

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}


