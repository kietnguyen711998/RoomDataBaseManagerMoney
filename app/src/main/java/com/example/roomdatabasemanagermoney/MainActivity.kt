package com.example.roomdatabasemanagermoney

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabasemanagermoney.databinding.ActivityMainBinding
import com.example.roomdatabasemanagermoney.db.DatabaseDB
import com.example.roomdatabasemanagermoney.db.ManagerMoney
import com.example.roomdatabasemanagermoney.db.ManagerMoneyRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var managerMoneyViewModel: ManagerMoneyViewModel
    private lateinit var adapter: ManagerMoneyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = DatabaseDB.getInstance(application).managerMoneyDAO
        val repository= ManagerMoneyRepository(dao)
        val factory = ManagerMoneyViewModelFactory(repository)
        managerMoneyViewModel = ViewModelProvider(this,factory).get(ManagerMoneyViewModel::class.java)
        binding.myViewModel = managerMoneyViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        managerMoneyViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initRecyclerView(){
        binding.managerMoneyRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ManagerMoneyAdapter({ selectedItem: ManagerMoney ->listItemClicked(selectedItem)})
        binding.managerMoneyRecyclerView.adapter = adapter
        displayManagerMoneyList()
    }

    private fun displayManagerMoneyList(){
        managerMoneyViewModel.managermoneys.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(managerMoney: ManagerMoney){
        Toast.makeText(this,"selected name is ${managerMoney.name}", Toast.LENGTH_LONG).show()
        managerMoneyViewModel.initUpdateAndDelete(managerMoney)
    }
}