package com.example.myproject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.databinding.TitleListBinding

class TitleAdapter(val setTitle: (String) -> Unit) :
    RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

    private val titleList = arrayListOf<String>()
    private lateinit var binding: TitleListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.title_list, parent,
            false
        )
        binding.adapter = this
        return TitleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {

        holder.bind(titleList[position])
    }

    override fun getItemCount() = titleList.size

    fun setTitleList(titleList: List<String>) {
        with(this.titleList) {
            clear()
            addAll(titleList)
        }
        notifyDataSetChanged()
    }

    fun setSelectedTitle(title: String){
        setTitle(title)
    }

    class TitleViewHolder(private val binding: TitleListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            binding.title = title
            binding.executePendingBindings()
        }
    }
}