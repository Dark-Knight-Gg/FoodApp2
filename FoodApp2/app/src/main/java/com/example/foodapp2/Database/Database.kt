package com.example.foodapp2.Database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteStatement
import com.example.foodapp2.model.Food
import com.example.foodapp2.model.History
import com.example.foodapp2.model.User

class Database(context: Context) : SQLiteOpenHelper(context, "Data.sql", null, 1) {
    companion object {
        private var instance: Database? = null
        fun getInstance(context: Context): Database {
            if (instance == null) {
                instance = Database(context)
            }
            return instance!!
        }
    }

    fun queryData(sql: String) {
        val data: SQLiteDatabase = writableDatabase
        data.execSQL(sql)
    }

    fun insert(name: String, describe: String, price: Double, picture: ByteArray) {
        val data = writableDatabase
        val sql = "INSERT INTO Food VALUES(null,?,?,?,?)"
        val statement: SQLiteStatement = data.compileStatement(sql)
        statement.clearBindings()
        statement.bindString(1, name)
        statement.bindString(2, describe)
        statement.bindDouble(3, price)
        statement.bindBlob(4, picture)
        statement.executeInsert()
    }

    fun isUsernameExists(username: String): Boolean {
        val db: SQLiteDatabase = readableDatabase
        val cursor = db.query(
            "Users",
            arrayOf("Username", "Password"),
            "Username" + "=?",
            arrayOf(username),
            null,
            null,
            null
        )
        if (cursor != null && cursor.moveToFirst()) {
            cursor.close()
            return true
        }
        return false
    }

    fun isLogin(user: User): Boolean {
        val db: SQLiteDatabase = readableDatabase
        val cursor = db.query(
            "Users",
            arrayOf("Username", "Password"),
            "Username" + "=?",
            arrayOf(user.username),
            null,
            null,
            null
        )
        if (cursor != null && cursor.moveToFirst()) {
            val user1 = User(cursor.getString(0), cursor.getString(1))
            if (user.password == user1.password) {
                return true
            }
            cursor.close()
        }
        return false
    }

    fun getData(sql: String): Cursor? {
        val data: SQLiteDatabase = readableDatabase
        return data.rawQuery(sql, null)
    }

    fun getFoodList(): List<Food> {
        val result = mutableListOf<Food>()
        val cursor: Cursor? = getData("SELECT * FROM Food")
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val describe = cursor.getString(2)
                val price = cursor.getDouble(3)
                val picture = cursor.getBlob(4)
                result.add(Food(id, name, describe, price, picture))
            }
        }
        return result
    }
    fun getHistory():List<History>{
        val result = mutableListOf<History>()
        val cursor: Cursor? = getData("SELECT * FROM History")
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val date = cursor.getString(1)
                val name = cursor.getString(2)
                val price = cursor.getDouble(3)
                val count = cursor.getInt(4)
                val money = cursor.getDouble(5)
                result.add(History(id, date, name, price, count, money))
            }
        }
        return result
    }

    override fun onCreate(db: SQLiteDatabase?) {
        queryData("CREATE TABLE IF NOT EXISTS Users(Id INTEGER PRIMARY KEY AUTOINCREMENT, Username NVARCHAR(200), Password NVARCHAR(200))")
        queryData("CREATE TABLE IF NOT EXISTS Food(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name NVARCHAR(200), Describe NVARCHAR(400),Price DOUBLE,Picture BLOB)")
        queryData("CREATE TABLE IF NOT EXISTS History(Id INTEGER PRIMARY KEY AUTOINCREMENT,Day DATE,Name NVARCHAR(200),Price DOUBLE,Count INTEGER,Money DOUBLE)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}