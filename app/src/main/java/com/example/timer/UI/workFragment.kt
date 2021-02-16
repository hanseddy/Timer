package com.example.timer.UI

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.timer.R
import com.example.timer.VIEWMODEL.TimerViewModel
import com.example.timer.databinding.FragmentWorkBinding
import kotlinx.android.synthetic.main.fragment_work.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [workFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class workFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var binding: FragmentWorkBinding? = null
    private val bind get() = binding!!
    private lateinit var model: TimerViewModel
    var Workminute: Int = 0
    var WorkSec: Int = 0
    private val TAG: String = "WorkFragment"
    //lateinit var W_constraint: ConstraintLayout


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
        Log.i(TAG, "workFragment created")

        // Inflate the layout for this fragment
        binding = FragmentWorkBinding.inflate(inflater, container, false)
        val view = bind.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "workFragment")
        /****
         * viewmodel initialisation
         */
        model = activity?.run {
            ViewModelProvider(this).get(TimerViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        /***
         * Viemodel livedata: workTimerMin and workTimerSec
         */
        model.workTimerMin.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding!!.workmin.text = it.toString()
            Workminute = it
        })

        model.workTimersec.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding!!.worksec.text = it.toString()
            WorkSec = it
        })

        /**
         * UI state updating
         */
        model.workState.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it) {
                /* true -> binding?.WorkBaseConstraint?.setBackgroundColor(0xFF42A80F.toInt())
                 false ->binding?.WorkBaseConstraint?.setBackgroundColor(0xFFFFF)*/
                true -> binding?.WorkBaseConstraint?.setBackgroundResource(R.drawable.work_backgrd)
                false -> binding?.WorkBaseConstraint?.setBackgroundColor(0xFFFFF)
            }
        })


        /**
         * Button MainActivity
         */
        /* (activity as MainActivity).Mainbinding.workButtonMain.setOnClickListener {
             (Workminute.times(60).plus(WorkSec))?.let { it1 -> WorkcountDownNum(it1) }  //get the work overal time and convert it in second (min*60+sec) and start countdown
             Log.i("workFragment","button workTime ")
         }*/
    }

    /****
     * function: WorkCountDown
     * @param param1 Int
     * @return: NULL
     * Design: count down time in sec
     */
    /*fun WorkcountDownNum(num:Int){
    //model.countDownNum(num)
    model.countDownWork(num,model)
}*/


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment workFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            workFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}



