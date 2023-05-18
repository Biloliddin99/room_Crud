package com.example.room_crud.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.room_crud.MainActivity
import com.example.room_crud.databinding.ItemRvBinding
import com.example.room_crud.db.MyDbHelper
import com.example.room_crud.models.Student

class MyAAdapter(val context: MainActivity, val list: ArrayList<Student>) :
    RecyclerView.Adapter<MyAAdapter.Vh>() {
    val appDatabase = MyDbHelper.newInstance(context)

    inner class Vh(var itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(student: Student, position: Int) {
            itemRvBinding.name.text = student.name
            itemRvBinding.grade.text = student.grade.toString()

            itemRvBinding.itemCard.setOnClickListener {
                list.removeAt(position)
                notifyItemRemoved(position)
                notifyItemChanged(0, itemCount)
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                appDatabase.studentDao().delete(student)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)

    }

    override fun getItemCount(): Int = list.size

}