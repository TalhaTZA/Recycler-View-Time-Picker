package com.example.timepicker

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timepicker.adapter.TimePickerAdapter
import com.example.timepicker.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: TimePickerAdapter
    private var mLastPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        callAdapters()

        applyAdapter()

    }

    private fun applyAdapter() {
        mBinding.apply {
            recyclerViewTimePicker.apply {
                adapter = mAdapter
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {


                        //Log.e("check","${(layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()}")

                        val lm = layoutManager as LinearLayoutManager
                        var position = lm.findLastCompletelyVisibleItemPosition()

//                        if (position == 0) {
//                            position = 1
//                        }

                        //if (mLastPosition != position) {
                        //Log.e("check", "$mLastPosition  $position")
                        //mLastPosition = position
                        try {
                            val view = lm.findViewByPosition(position) as TextView
                            val viewLast = lm.findViewByPosition(position - 1) as TextView
                            val viewFront = lm.findViewByPosition(position + 1) as TextView
                            view.setTypeface(null, Typeface.BOLD)
                            viewLast.setTypeface(null, Typeface.NORMAL)
                            viewFront.setTypeface(null, Typeface.NORMAL)

                            adapter!!.notifyDataSetChanged()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        //}


                        super.onScrolled(recyclerView, dx, dy)
                    }
                })
            }
        }
    }

    private fun init() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mBinding.lifecycleOwner = this
    }

    private fun callAdapters() {

        val display = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(display)
        mAdapter = TimePickerAdapter(arrayListOf(), display)

    }


}
