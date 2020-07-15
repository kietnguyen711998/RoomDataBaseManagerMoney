package com.example.roomdatabasemanagermoney

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabasemanagermoney.db.ManagerMoney
import com.example.roomdatabasemanagermoney.db.ManagerMoneyRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ManagerMoneyViewModel(private val repository: ManagerMoneyRepository) : ViewModel(),
    Observable {

    val managermoneys = repository.managermoneys
    private var isUpdateOrDelete = false
    private lateinit var managerMoneyToUpdateOrDelete: ManagerMoney

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputKilo = MutableLiveData<String>()

    @Bindable
    val inputPrice = MutableLiveData<String>()

    @Bindable
    val inputAddress = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }
    fun saveOrUpdate() {
        if (inputName.value == null) {
            statusMessage.value = Event("Please enter subscriber's name")
        } else if (inputKilo.value == null) {
            statusMessage.value = Event("Please enter subscriber's email")
        } else if (inputPrice.value == null) {
            statusMessage.value = Event("Please enter subscriber's phone")
        } else if (inputAddress.value == null) {
            statusMessage.value = Event("Please enter subscriber's price")
        } else {
            if (isUpdateOrDelete) {
                managerMoneyToUpdateOrDelete.name = inputName.value!!
                managerMoneyToUpdateOrDelete.kilo = inputKilo.value!!
                managerMoneyToUpdateOrDelete.price = inputPrice.value!!
                managerMoneyToUpdateOrDelete.address = inputAddress.value!!
                update(managerMoneyToUpdateOrDelete)
            } else {
                val name = inputName.value!!
                val kilo = inputKilo.value!!
                val price = inputPrice.value!!
                val address = inputAddress.value!!


                insert(ManagerMoney(0, name, kilo, price, address))
                inputName.value = null
                inputKilo.value = null
                inputPrice.value = null
                inputAddress.value = null

                

            }
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(managerMoneyToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    fun insert(managerMoney: ManagerMoney): Job = viewModelScope.launch {
        val newRowId = repository.insert(managerMoney)
        if (newRowId > -1) {
            statusMessage.value = Event("ManagerAppMoney Inserted Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun update(managerMoney: ManagerMoney): Job = viewModelScope.launch {
        val noOfRows = repository.update(managerMoney)
        if (noOfRows > 0) {
            inputName.value = null
            inputKilo.value = null
            inputPrice.value = null
            inputAddress.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRows Row Updated Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun delete(managerMoney: ManagerMoney): Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(managerMoney)

        if (noOfRowsDeleted > 0) {
            inputName.value = null
            inputKilo.value = null
            inputPrice.value = null
            inputAddress.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRowsDeleted Row Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun clearAll(): Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
        if (noOfRowsDeleted > 0) {
            statusMessage.value = Event("$noOfRowsDeleted ManagerMoney Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun initUpdateAndDelete(managerMoney: ManagerMoney) {
        inputName.value = managerMoney.name
        inputKilo.value = managerMoney.kilo
        inputPrice.value = managerMoney.price
        inputAddress.value = managerMoney.address
        isUpdateOrDelete = true
        managerMoneyToUpdateOrDelete = managerMoney
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}