package com.example.timepicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.timepicker.adapter.TimePickerAdapter
import com.example.timepicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: TimePickerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        callAdapters()

        applyAdapter()

    }

    private fun applyAdapter() {
        mBinding.apply {
            recyclerViewTimePicker.adapter = mAdapter
        }
    }

    private fun init() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mBinding.lifecycleOwner = this
    }

    private fun callAdapters() {

        mAdapter = TimePickerAdapter(arrayListOf())
    }


}
