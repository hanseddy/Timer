package com.example.timer.VIEWMODEL

import android.os.CountDownTimer
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timer.R
import com.example.timer.UI.workFragment
import java.lang.Integer.parseInt

class TimerViewModel : ViewModel() {
    init {
        Log.i("TimerViewModel", "viewmodel est instantier")
    }

    //:MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    val workTimerMin: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val workTimersec: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    val restTimerMin: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val restTimersec: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    val numSet: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    //states of the timer
    val workState: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val restState: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }


/*    fun countDownNum(num:Int){
        val time:Long=(num.times(1000)).toLong()
        Log.i("workFragment","countdown")
        val timer = object: CountDownTimer(time, 1000){
            override fun onTick(millisUntilFinished: Long) {
                //textNum.text= ((millisUntilFinished/1000).toString())
                //set model
                workTimerMin.value = ((millisUntilFinished/1000)/60).toInt()
                workTimersec.value = ((millisUntilFinished/1000)%60).toInt()

                //restTimerMin.value = ((millisUntilFinished/1000)/60).toInt()
                //restTimersec.value = ((millisUntilFinished/1000)%60).toInt()
            }
            override fun onFinish() {
                //countDownNum(rest)
            }
        }.start()
    }*/

/*    fun countDownWork(num:Int,model: TimerViewModel){
        val time:Long=(num.times(1000)).toLong()
        Log.i("workFragment","countdown")
        val timer = object: CountDownTimer(time, 1000){
            override fun onTick(millisUntilFinished: Long) {
                //textNum.text= ((millisUntilFinished/1000).toString())
                //set model
                workTimerMin.value = ((millisUntilFinished/1000)/60).toInt()
                workTimersec.value = ((millisUntilFinished/1000)%60).toInt()
            }
            override fun onFinish() {
                countDownRest(((model.restTimerMin.value)?.times(60)!!.plus((model.restTimersec.value!!))),model)
            }
        }.start()
    }*/

    fun countDownWorktest(num: Int, rest: Int, numset: Int, model: TimerViewModel) {
        // work State
        model.workState.value = true
        //local variable
        val L_work = num
        val L_rest = rest
        val L_numset = numset
        val worktime: Long = (L_work.times(1000)).toLong()
        //val resttime:Long=(rest.times(1000)).toLong()
        Log.i("workFragment", "countdown")
        val timer = object : CountDownTimer(worktime.plus(1), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //textNum.text= ((millisUntilFinished/1000).toString())
                //set model
                workTimerMin.value = ((millisUntilFinished / 1000) / 60).toInt()
                workTimersec.value = ((millisUntilFinished / 1000) % 60).toInt()
            }

            override fun onFinish() {
                //End of work state
                model.workState.value = false
                model.workTimerMin.value = L_work.div(60)
                model.workTimersec.value = L_work % 60
                countDownRest(L_work, L_rest, L_numset, model)
            }
        }.start()
    }

    fun countDownRest(work: Int, rest: Int, numset: Int, model: TimerViewModel) {
        // rest state
        model.restState.value = true
        val L_work = work
        val L_rest = rest
        var L_numset = numset
        val time: Long = (L_rest.times(1000)).toLong()
        Log.i("workFragment", "countdown")
        val timer = object : CountDownTimer(time.plus(1), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //set model
                model.restTimerMin.value = ((millisUntilFinished / 1000) / 60).toInt()
                model.restTimersec.value = ((millisUntilFinished / 1000) % 60).toInt()
            }

            override fun onFinish() {
                model.restState.value = false
                model.restTimerMin.value = L_rest.div(60)
                model.restTimersec.value = L_rest % 60
                L_numset = L_numset.minus(1)
                model.numSet.value = L_numset
                if (model.numSet.value!! > 0) {
                    countDownWorktest(L_work, L_rest, L_numset, model)
                }
            }
        }.start()
    }


    //quand on appuis sur le bouton
    //tant que le nombre de set n'est pas inférieur ou égale à 0
    //on lance nombre de fois == set =>
    // - work et rest
/*    fun SportTimerManagement(iterator:Int,work:Int,rest:Int, model: TimerViewModel){
        var LocalIterator=iterator
        val LocalWork=work
        val LocalRest=rest
        while (LocalIterator>0){
            model.numSet.value=LocalIterator
           countDownWork(LocalWork,model)
           //countDownRest(LocalRest)
            LocalIterator--
       }

    }*/


}