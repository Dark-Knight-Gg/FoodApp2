package com.example.foodapp2.Admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.foodapp2.Client.MainActivity6
import com.example.foodapp2.Database.Database
import com.example.foodapp2.R
import com.example.foodapp2.databinding.ActivityMain3Binding
import com.example.foodapp2.foodlist.FoodListFragment
import com.example.foodapp2.foodlist.YourFoodFragment
import com.example.foodapp2.login.LoginActivity

class MainActivity3 : AppCompatActivity() {
    private val database by lazy { Database.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        intListener()
        createFragment()
    }

    private fun intListener() {
        val binding: ActivityMain3Binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main3)
        binding.atv3ImgBack.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.avt3ImgHistory.setOnClickListener {
            intent = Intent(this, MainActivity6::class.java)
            startActivity(intent)
        }
    }

    private fun createFragment() {
        val fragmentOne = FoodListFragment()
        val fragmentTwo = YourFoodFragment()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.foodFragOne, fragmentOne)
        fragmentTransaction.add(R.id.foodFragTwo, fragmentTwo)
        fragmentTransaction.commit()
    }
}