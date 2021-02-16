package com.example.timer.UI.PopUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.timer.R
import com.example.timer.VIEWMODEL.TimerViewModel
import com.example.timer.databinding.FragmentRestTimePickersBinding
import com.example.timer.databinding.FragmentWorkBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [restTimePickers.newInstance] factory method to
 * create an instance of this fragment.
 */
class restTimePickers : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //binding initialisation
    private var binding: FragmentRestTimePickersBinding? = null
    private val bind get() = binding!!
    private lateinit var model: TimerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = activity?.run {
            ViewModelProvider(this).get(TimerViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        // Inflate the layout for this fragment
        binding = FragmentRestTimePickersBinding.inflate(inflater, container, false)
        val view = bind.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.buttonRestTime.setOnClickListener {
            view.findNavController().navigate(R.id.numSetPickers)
        }
        binding!!.BackToWork.setOnClickListener {
            view.findNavController().navigate(R.id.workTimePicker)
        }

        //Minutes Number picker setting
        binding!!.restMinPicker.maxValue = 60
        binding!!.restMinPicker.minValue = 0
        //seconde Number picker setting
        binding!!.restSecPicker.maxValue = 60
        binding!!.restSecPicker.minValue = 0

        /***********listen time picked***********/
        binding!!.restMinPicker.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            model.restTimerMin.value = newVal
        })
        binding!!.restSecPicker.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            model.restTimersec.value = newVal
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment restTimePickers.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            restTimePickers().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}