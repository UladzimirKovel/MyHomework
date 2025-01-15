package com.example.myhomework.presentation.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomework.R
import com.example.myhomework.domain.repository.Auto
import com.example.myhomework.domain.repository.ListAutoRepository
import com.example.myhomework.presentation.adapter.AutoAdapter
import com.example.myhomework.presentation.view_model.ListViewAutoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListViewAutoFragment : Fragment() {

    private var notesRecyclerView: RecyclerView? = null
    private var autoAdapter: AutoAdapter? = null
    private val repository : ListAutoRepository by inject()
    private val listAutoModel: ListViewAutoModel by viewModel()

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



    private fun backParent() {
        findNavController().navigate(R.id.mainFragment)
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.newFragmentView, MainFragment(), "Main")
//            .addToBackStack(null)
//            .commit()
    }

    private fun setupListener() {

        val backMainActivity: Button? = view?.findViewById(R.id.back_main_fragment)
        val addButton: Button? = view?.findViewById(R.id.add_notes_button)
        val brandTextView: EditText? = view?.findViewById(R.id.title_notes_tv)
        val messageTextView: EditText? = view?.findViewById(R.id.message_notes_tv)
        val pbAdd: ProgressBar? = view?.findViewById(R.id.pbAdd)

        addButton?.setOnClickListener {
            if (brandTextView != null && messageTextView != null) {
                listAutoModel.handleAddNote(brandTextView, messageTextView)
            }

            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                pbAdd?.isIndeterminate = true
            }
        }

        backMainActivity?.setOnClickListener {
            backParent()
        }
    }

    private fun noteRecycler() {
        notesRecyclerView = view?.findViewById(R.id.notes_recycler_view)

        // Получаем изменяемый список заметок
        val notes = repository.getNotes() as MutableList<Auto>
        notesRecyclerView?.layoutManager = LinearLayoutManager(view?.context)
        autoAdapter = AutoAdapter(requireContext(),notes)
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


