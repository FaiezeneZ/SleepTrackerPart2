package com.example.sleeptracker2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SleepFragment : Fragment() {
    private val sleepList = mutableListOf<Sleep>()
    private lateinit var sleepRv : RecyclerView
    private lateinit var sleepAdapter : SleepAdapter
    private lateinit var addBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sleep, container, false)

        sleepRv = view.findViewById(R.id.list_rv)
        sleepAdapter = SleepAdapter(requireContext(),sleepList)
        sleepRv.adapter = sleepAdapter
        sleepRv.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch{
            (requireActivity().application as MyApplication).db.sleepDao().getAll().collect{ databaseList ->
                sleepList.clear()
                databaseList.map { mappedList ->
                    sleepList.addAll(listOf(mappedList))
                    sleepAdapter.notifyDataSetChanged()
                }

            }
        }

        //sleepRv.layoutManager = LinearLayoutManager(requireContext())

        addBtn = view.findViewById<Button>(R.id.add_btn)
        addBtn.setOnClickListener{
            val i = Intent(requireContext(), DetailActivity::class.java)
            startActivity(i)
        }
    }

}