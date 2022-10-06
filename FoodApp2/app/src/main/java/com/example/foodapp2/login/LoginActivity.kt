package com.example.foodapp2.login

import Register.MainActivity2
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp2.*
import com.example.foodapp2.Admin.MainActivity3
import com.example.foodapp2.Client.MainActivity4
import com.example.foodapp2.Database.Database
import com.example.foodapp2.model.User

class LoginActivity : AppCompatActivity() {
    private lateinit var loginTxtTitle: TextView
    private lateinit var loginEdtUsername: EditText
    private lateinit var loginEdtPassword: EditText
    private lateinit var loginCheckbox: CheckBox
    private lateinit var loginBtnLogin: Button
    private lateinit var loginBtnRegister: Button
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            "Data",
            MODE_PRIVATE
        )
    }

    private val database by lazy { Database.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bindView()
        bindData()
        initListener()
    }

    private fun bindView() {
        loginTxtTitle = findViewById(R.id.loginTxtTitle)
        loginEdtUsername = findViewById(R.id.loginEdtUsername)
        loginEdtPassword = findViewById(R.id.loginEdtPassword)
        loginCheckbox = findViewById(R.id.loginCheckbox)
        loginBtnLogin = findViewById(R.id.loginBtnLogin)
        loginBtnRegister = findViewById(R.id.loginBtnRegister)
    }

    private fun bindData() {
        val userName = sharedPreferences.getString("Username", "")
        val password = sharedPreferences.getString("Password", "")
        val isChecked = sharedPreferences.getBoolean("Checked", false)
        loginEdtUsername.setText(userName)
        loginEdtPassword.setText(password)
        loginCheckbox.isChecked = isChecked
    }

    private fun initListener() {
        loginBtnRegister.setOnClickListener {
            intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        loginBtnLogin.setOnClickListener {
            val username = loginEdtUsername.text.toString()
            val password = loginEdtPassword.text.toString()
            val flag: Boolean = database.isLogin(User(username, password))

            if (username == "Admin" && password == "admin123") {
                intent = Intent(this, MainActivity4::class.java)
                startActivity(intent)
                return@setOnClickListener
            }

            if (flag) {
                if (loginCheckbox.isChecked) {
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("Username", username)
                    editor.putString("Password", password)
                    editor.putBoolean("Checked", true)
                    editor.commit()
                } else {
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.remove("Username")
                    editor.remove("Password")
                    editor.remove("Checked")
                    editor.commit()
                }
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
                return@setOnClickListener
            }
            Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT)
                .show()
        }
    }
}