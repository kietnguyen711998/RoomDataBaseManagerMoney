package com.example.roomdatabasemanagermoney

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasemanagermoney.databinding.ListItemBinding
import com.example.roomdatabasemanagermoney.db.ManagerMoney

class ManagerMoneyAdapter(private val clickListener: (ManagerMoney) -> Unit) :
    RecyclerView.Adapter<MyViewHolder>() {
    private val managerMoneysList = ArrayList<ManagerMoney>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return managerMoneysList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(managerMoneysList[position], clickListener)
    }

    fun setList(managerMoneys: List<ManagerMoney>) {
        managerMoneysList.clear()
        managerMoneysList.addAll(managerMoneys)

    }
}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(managerMoney: ManagerMoney, clickListener: (ManagerMoney) -> Unit) {
        binding.nameTextView.text = managerMoney.name
        binding.kiloTextView.text = managerMoney.kilo
        binding.priceTextView.text = managerMoney.price
        binding.addressTextView.text = managerMoney.address
        binding.listitemlayout.setOnClickListener {
            clickListener(managerMoney)
        }
    }
}