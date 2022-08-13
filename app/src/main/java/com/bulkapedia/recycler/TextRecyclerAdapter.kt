package com.bulkapedia.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.R
import com.bulkapedia.databinding.TextRecyclerItemBinding

class TextRecyclerAdapter(private val strings: List<String>):
    RecyclerView.Adapter<TextRecyclerAdapter.TextAdapterPlaceHolder>() {

    class TextAdapterPlaceHolder(item: View): RecyclerView.ViewHolder(item) {

        val bind = TextRecyclerItemBinding.bind(item)

        fun bind(text: String) = with(bind) {
            recyclerItemTv.text = text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAdapterPlaceHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_recycler_item, parent, false)
        return TextAdapterPlaceHolder(item)
    }

    override fun onBindViewHolder(holder: TextAdapterPlaceHolder, position: Int) {
        holder.bind(strings[position])
    }

    override fun getItemCount(): Int = strings.size

}