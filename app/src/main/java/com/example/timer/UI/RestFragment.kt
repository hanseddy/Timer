package com.example.timer.UI

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.timer.R
import com.example.timer.VIEWMODEL.TimerViewModel
import com.example.timer.databinding.FragmentRestBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var binding: FragmentRestBinding? = null
    private val bind get() = binding!!
    private lateinit var model: TimerViewModel
    lateinit var toneG: ToneGenerator
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
        // Inflate the layout for this fragment
        model = activity?.run {
            ViewModelProvider(this).get(TimerViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        binding = FragmentRestBinding.inflate(inflater, container, false)
        val view = bind.root
        return view
        //return inflater.inflate(R.layout.fragment_rest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.restTimerMin.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding!!.Restmin.text = it.toString()
        })
        model.restTimersec.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding!!.Restsec.text = it.toString()
        })
        toneG = ToneGenerator(AudioManager.STREAM_ALARM, 50)
        model.restState.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it) {
                true -> binding?.RestBaseConstraint?.setBackgroundResource(R.drawable.rest_backgrd)
                true -> toneG.startTone(500)
                false -> binding?.RestBaseConstraint?.setBackgroundColor(0xFFFFF)
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}