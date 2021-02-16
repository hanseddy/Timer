package com.example.timer.UI

import android.app.Dialog
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timer.R
import com.example.timer.VIEWMODEL.TimerViewModel
import com.example.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //observation variable.
    private var MainWorkSec: Int = 0
    private var MainWorkminute: Int = 0
    private var MainRestSec: Int = 0
    private var MainRestminute: Int = 0
    private var MainNumSet: Int = 0

    // variable of init
    lateinit var dialog: Dialog
    lateinit var Addfrag: BlankAddFragment
    lateinit var workFrag: workFragment
    lateinit var restFrag: RestFragment
    lateinit var Mainbinding: ActivityMainBinding
    private lateinit var model: TimerViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mainbinding = ActivityMainBinding.inflate(layoutInflater)
        val view = Mainbinding.root
        setContentView(view)
        model = run {
            ViewModelProvider(this).get(TimerViewModel::class.java)
        }
        Addfrag = BlankAddFragment()
        workFrag = workFragment()
        restFrag = RestFragment()
        dialog = Dialog(this)
        dialog.setContentView(R.layout.num_pick_layout)

        if (savedInstanceState == null) {
            Mainbinding.workButtonMain.visibility = View.GONE
            Invisible(Mainbinding.NumSetMainText)
            Invisible(Mainbinding.SetInfoMainText)
            supportFragmentManager.beginTransaction().add(R.id.constraint1, Addfrag).commit()
        }
        /****
         * Observing data through viewmodel.
         * Work Time
         * Rest Time
         * Number of set
         */
        // Number of set observation
        model.numSet.observe(this, androidx.lifecycle.Observer {
            Mainbinding.NumSetMainText.text = it.toString()
            MainNumSet = it
        })
        // work in minutes based time
        model.workTimerMin.observe(this, androidx.lifecycle.Observer {
            MainWorkminute = it
        })
        // work in second based time
        model.workTimersec.observe(this, androidx.lifecycle.Observer {
            MainWorkSec = it
        })
        // rest in minutes based time.
        model.restTimerMin.observe(this, androidx.lifecycle.Observer {
            MainRestminute = it
        })
        // rest in second based time.
        model.restTimersec.observe(this, androidx.lifecycle.Observer {
            MainRestSec = it
        })

        /********End of data observation*********/
        // work button management
        Mainbinding.workButtonMain.setOnClickListener {
            /* SportTimerManagement(MainNumSet,(MainWorkminute?.times(60)
             ?.plus(MainWorkSec!!)!!),(MainRestminute?.times(60)?.plus(MainRestSec!!)!!),model)*/
            mainTimerworktest(
                (MainWorkminute?.times(60)
                    ?.plus(MainWorkSec!!)!!),
                (MainRestminute?.times(60)?.plus(MainRestSec!!)),
                MainNumSet,
                model
            )
        }
    }


    /**
     * Method of MainActivity
     */
    // show pop up Dialog
    fun showWorkPopUp(view: View) {
        supportFragmentManager.beginTransaction().remove(Addfrag).commit()
        dialog.show()
    }

    // make a view invisible
    fun Invisible(view: View) {
        view.visibility = View.GONE
    }
    /**
     *  @sample manage the timer functionning
     *  @param param1 number of set
     *  @param param2 work time
     *  @param param3 rest time
     *  @param param4 model class
     *  @return NONE
     */

    /* fun SportTimerManagement(iterator:Int,work:Int,rest:Int, model: TimerViewModel){
         model.SportTimerManagement(iterator,work,rest,model)
     }*/
    /*******End of MainActivity Declaration********/

    fun mainTimerworktest(work: Int, rest: Int, numSet: Int, model: TimerViewModel) {
        val w = work
        val r = rest
        val n = numSet
        model.countDownWorktest(w, r, n, model)

        //model.countDownWorktest(work,rest,model)
    }
}