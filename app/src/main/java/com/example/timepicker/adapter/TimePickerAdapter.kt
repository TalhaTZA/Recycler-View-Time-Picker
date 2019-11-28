package com.example.timepicker.adapter

import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timepicker.databinding.RecyclerItemViewTimePickerBinding
import kotlinx.coroutines.*


class TimePickerAdapter(
    val list: ArrayList<String>,
    val display: DisplayMetrics
) : RecyclerView.Adapter<ViewHolder>() {


    val job = Job()
    val scope = CoroutineScope(job + Dispatchers.Default)


    init {
        create_list()
    }

    private fun create_list() {
        scope.launch {
            val minute = arrayOf("00", "15", "30", "45")

            for (i in 0..23) {
                for (j in 0..3) {
                    var time = "$i:${minute[j]}"

                    list.add(time)
                }
            }

            withContext(Dispatchers.Main) {
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position],display)
    }
}


class ViewHolder private constructor(private val itemViewBinding: RecyclerItemViewTimePickerBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    companion object {


        fun from(parent: ViewGroup): ViewHolder {

            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerItemViewTimePickerBinding
                .inflate(layoutInflater, parent, false)
            return ViewHolder(binding)


        }


    }

    fun bind(s: String,display: DisplayMetrics) {
        itemViewBinding.txtViewTime.layoutParams.width = (display.widthPixels / 3)
        Log.e("check","${display.widthPixels}")
        itemViewBinding.time = s
        itemViewBinding.executePendingBindings()
    }

}