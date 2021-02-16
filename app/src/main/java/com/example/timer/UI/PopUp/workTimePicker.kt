package com.example.timer.UI.PopUp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.timer.R
import com.example.timer.VIEWMODEL.TimerViewModel
import com.example.timer.databinding.FragmentRestTimePickersBinding
import com.example.timer.databinding.FragmentWorkTimePickerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [workTimePicker.newInstance] factory method to
 * create an instance of this fragment.
 */
class workTimePicker : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var model: TimerViewModel
    private var binding: FragmentWorkTimePickerBinding? = null
    private val bind get() = binding!!


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
        binding = FragmentWorkTimePickerBinding.inflate(inflater, container, false)
        val view = bind.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.buttonNextWork.setOnClickListener {
            view.findNavController().navigate(R.id.restTimePickers)
        }

        //Minutes Number picker setting
        binding!!.workMinPicker.maxValue = 60
        binding!!.workMinPicker.minValue = 0
        //seconde Number picker setting
        binding!!.workSecPicker.maxValue = 60
        binding!!.workSecPicker.minValue = 0

        /***********listen time picked***********/
        binding!!.workMinPicker.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            model.workTimerMin.value = newVal // updates viewmodel
        })
        binding!!.workSecPicker.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            model.workTimersec.value = newVal // updates viewmodel
        })
    }

    /**
     * update viewmodel data by listening number picker change
     * @author hans
     * @param Workmin : minutes (int)
     * @param Worksec : seconde (int)
     * @param Restmin : minutes (int)
     * @param Restsec : seconde (int)
     * @return null
     * data set is situated in "TimerViewModel"
     */
/*    fun SetTimePickerData(Workmin:Int,Worksec:Int,Restmin:Int,Restsec:Int){
        model.workTimerMin.value=Workmin
        model.workTimersec.value=Worksec
        model.restTimerMin.value=Restmin
        model.restTimersec.value=Restsec
    }*/





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment workTimePicker.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            workTimePicker().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}