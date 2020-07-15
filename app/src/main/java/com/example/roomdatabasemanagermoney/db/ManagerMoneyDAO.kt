package com.example.roomdatabasemanagermoney.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ManagerMoneyDAO {
    @Insert
    suspend fun insertManagerMoney(managerMoney: ManagerMoney): Long

    @Update
    suspend fun updateManagerMoney(managerMoney: ManagerMoney): Int

    @Delete
    suspend fun deleteManagerMoney(managerMoney: ManagerMoney): Int

    @Query("DELETE FROM managermoney")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM managermoney")
    fun getAllSubscribers(): LiveData<List<ManagerMoney>>

}