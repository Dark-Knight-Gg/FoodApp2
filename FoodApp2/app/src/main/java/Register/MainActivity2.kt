package Register

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp2.Database.Database
import com.example.foodapp2.R
import com.example.foodapp2.login.LoginActivity

class MainActivity2 : AppCompatActivity() {
    private lateinit var registerTxtTitle: TextView
    private lateinit var registerEdtUsername: EditText
    private lateinit var registerEdtPassword: EditText
    private lateinit var registerEdtRepeatPassword: EditText
    private lateinit var registerBtnRegister: Button
    private lateinit var registerImgBack: ImageView
    private val database by lazy { Database.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        bindView()
        initListener()
    }
    private fun bindView() {
        registerTxtTitle = findViewById(R.id.registerTxtTitle)
        registerEdtUsername = findViewById(R.id.registerEdtUsername)
        registerEdtPassword = findViewById(R.id.registerEdtPassword)
        registerEdtRepeatPassword = findViewById(R.id.registerEdtRepeatPassword)
        registerBtnRegister = findViewById(R.id.registerBtnRegister)
        registerImgBack = findViewById(R.id.registerImgBack)
    }
    private fun initListener(){
        registerImgBack.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        registerBtnRegister.setOnClickListener {
            val username = registerEdtUsername.text.toString()
            val password = registerEdtPassword.text.toString()
            val repeatpassword = registerEdtRepeatPassword.text.toString()
            if (!database.isUsernameExists(username)) {
                if (password == repeatpassword) {
                    database.queryData("INSERT INTO Users VALUES(null,'$username','$password')")
                    intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Nhắc lại mật khẩu chưa đúng!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tên tài khảon đã tồn tại!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}