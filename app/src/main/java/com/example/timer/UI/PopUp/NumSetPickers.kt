package com.example.timer.UI.PopUp

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.timer.R
import com.example.timer.UI.BlankAddFragment
import com.example.timer.UI.MainActivity
import com.example.timer.VIEWMODEL.TimerViewModel
import com.example.timer.databinding.FragmentNumSetPickersBinding
import com.example.timer.databinding.FragmentRestTimePickersBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NumSetPickers.newInstance] factory method to
 * create an instance of this fragment.
 */
class NumSetPickers : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //binding initialisation
    private var binding: FragmentNumSetPickersBinding? = null
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
        binding = FragmentNumSetPickersBinding.inflate(inflater, container, false)
        val view = bind.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.buttonSetNum.setOnClickListener {
            view.findNavController().navigate(R.id.workTimePicker)
            (activity as MainActivity).dialog.dismiss() // dismiss pop up

        }
        binding!!.BackToRest.setOnClickListener {
            view.findNavController().navigate(R.id.restTimePickers)
        }
        //Minutes Number picker setting
        binding!!.NumSetPicker.maxValue = 100
        binding!!.NumSetPicker.minValue = 1

        /***********listen time picked***********/
        binding!!.NumSetPicker.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            model.numSet.value = newVal
            // Log.i("workTimer","la nouvelle valeur est $newVal")
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as MainActivity).supportFragmentManager.beginTransaction()
            .add(R.id.PortraitWorkTimerFragment, (activity as MainActivity).workFrag).commit()
        (activity as MainActivity).supportFragmentManager.beginTransaction()
            .add(R.id.PortraitRestTimerFragment, (activity as MainActivity).restFrag).commit()
        (activity as MainActivity).Mainbinding.workButtonMain.visibility = View.VISIBLE
        (activity as MainActivity).Mainbinding.NumSetMainText.visibility = View.VISIBLE
        (activity as MainActivity).Mainbinding.SetInfoMainText.visibility = View.VISIBLE
        (activity as MainActivity).Mainbinding.constraint1.isClickable = false
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NumSetPickers.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NumSetPickers().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}