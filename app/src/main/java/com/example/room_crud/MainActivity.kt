package com.example.room_crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.room_crud.adapter.MyAAdapter
import com.example.room_crud.databinding.ActivityMainBinding
import com.example.room_crud.databinding.ItemDialogBinding
import com.example.room_crud.db.MyDbHelper
import com.example.room_crud.models.Student

class MainActivity : AppCompatActivity() {
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var myAAdapter: MyAAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myDbHelper = MyDbHelper.newInstance(this)

        binding.apply {
            btbAdd.setOnClickListener {
                val dialog = AlertDialog.Builder(this@MainActivity).create()
                val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
                dialog.setView(itemDialogBinding.root)
                itemDialogBinding.btnSave.setOnClickListener {
                    val student = Student(
                        itemDialogBinding.name.text.toString(),
                        itemDialogBinding.grade.text.toString().toInt()
                    )
                    myDbHelper.studentDao().addStudent(student)
                    Toast.makeText(this@MainActivity, "saved", Toast.LENGTH_SHORT).show()
                    dialog.cancel()
                    onResume()
                }
                dialog.show()
            }

            search.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {

                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {

                    val searchQuery = p0 ?:""
                  val list = myDbHelper.studentDao().searchStudent(searchQuery)
                    myAAdapter.list.clear()
                    myAAdapter.list.addAll(list)
                    myAAdapter.notifyDataSetChanged()
                    return true
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        myAAdapter = MyAAdapter(myDbHelper.studentDao().getAllStudent() as ArrayList<Student>)
        binding.rv.adapter = myAAdapter
    }
}