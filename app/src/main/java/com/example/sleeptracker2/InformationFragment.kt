package com.example.sleeptracker2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class InformationFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_information, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val min = activity?.findViewById(R.id.minimumDisplay_tv) as TextView
        val max = activity?.findViewById(R.id.maximumDisplay_tv) as TextView
        val avg = activity?.findViewById(R.id.averageDisplay_tv) as TextView

        val minDisplay = activity?.findViewById(R.id.minimum_tv) as TextView
        val maxDisplay = activity?.findViewById(R.id.maximum_tv) as TextView
        val avgDisplay = activity?.findViewById(R.id.average_tv) as TextView

        var minValue : Int = Byte.MAX_VALUE.toInt()
        var maxValue : Int = Byte.MIN_VALUE.toInt()
        var counter = 0
        var sum = 0

        lifecycleScope.launch(Dispatchers.IO) {
            for (item in (activity?.application as MyApplication).db.sleepDao().getHours()) {
                counter++
                sum+=Integer.parseInt(item)

                if(Integer.parseInt(item) > maxValue)
                    maxValue = Integer.parseInt(item)
                if(Integer.parseInt(item) < minValue)
                    minValue = Integer.parseInt(item)

                max.text = maxValue.toString()
                min.text = minValue.toString()
                avg.text = (sum/counter).toString()

                maxDisplay.text = "Maximum"
                minDisplay.text = "Minimum"
                avgDisplay.text = "Average"
            }
        }
    }

}