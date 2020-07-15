package com.example.roomdatabasemanagermoney.db

class ManagerMoneyRepository(private val dao: ManagerMoneyDAO) {

    val managermoneys = dao.getAllSubscribers()

    suspend fun insert(managerMoney: ManagerMoney): Long {
        return dao.insertManagerMoney(managerMoney)
    }

    suspend fun update(managerMoney: ManagerMoney): Int {
        return dao.updateManagerMoney(managerMoney)
    }

    suspend fun delete(managerMoney: ManagerMoney): Int {
        return dao.deleteManagerMoney(managerMoney)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}