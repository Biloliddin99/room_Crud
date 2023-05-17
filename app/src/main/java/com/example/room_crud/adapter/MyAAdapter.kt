package com.example.room_crud.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room_crud.databinding.ItemRvBinding
import com.example.room_crud.models.Student

class MyAAdapter(var list: List<Student>) : RecyclerView.Adapter<MyAAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(student: Student) {
            itemRvBinding.name.text = student.name
            itemRvBinding.grade.text = student.grade.toString()


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int = list.size

}