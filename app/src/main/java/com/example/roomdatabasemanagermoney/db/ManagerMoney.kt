package com.example.roomdatabasemanagermoney.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "managermoney")
data class ManagerMoney(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "kilo")
    var kilo: String = "",

    @ColumnInfo(name = "price")
    var price: String = "",

    @ColumnInfo(name = "address")
    var address: String = ""

)