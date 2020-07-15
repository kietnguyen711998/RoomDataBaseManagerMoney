package com.example.roomdatabasemanagermoney

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabasemanagermoney.db.ManagerMoneyRepository

class ManagerMoneyViewModelFactory(private val repository: ManagerMoneyRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ManagerMoneyViewModel::class.java)) {
            return ManagerMoneyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}