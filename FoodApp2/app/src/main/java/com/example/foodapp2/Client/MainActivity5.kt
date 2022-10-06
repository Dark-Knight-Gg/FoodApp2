package com.example.foodapp2.Client

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.foodapp2.Database.Database
import com.example.foodapp2.R
import java.io.ByteArrayOutputStream
import java.io.InputStream

class MainActivity5 : AppCompatActivity() {
    private val code1 =1
    private val code2 =2
    private val database = Database.getInstance(this)
    private lateinit var addTxtTitle:TextView
    private lateinit var addEdtName:EditText
    private lateinit var addEdtDescribe:EditText
    private lateinit var addEdtPrice:EditText
    private lateinit var addImg:ImageView
    private lateinit var addImgCamera:ImageView
    private lateinit var addImgFolder:ImageView
    private lateinit var addBtnAdd:Button
    private lateinit var addBtnCancel:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        bindData()
        initListener()

    }
    private fun bindData(){
        addTxtTitle=findViewById(R.id.addTxtTitle)
        addEdtName=findViewById(R.id.addEdtName)
        addEdtDescribe=findViewById(R.id.addEdtDescribe)
        addEdtPrice=findViewById(R.id.addEdtPrice)
        addImg= findViewById(R.id.addImg)
        addImgCamera=findViewById(R.id.addImgCamera)
        addImgFolder=findViewById(R.id.addImgFoder)
        addBtnAdd=findViewById(R.id.addBtnAdd)
        addBtnCancel=findViewById(R.id.addBtnCancle)
    }
    private fun initListener(){
        addBtnCancel.setOnClickListener{
            Toast.makeText(this,"Đã huỷ!",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }
        addImgCamera.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,code1)
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
                {

                }
                else
                {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), code1 )

                }

            }
        }

        addImgFolder.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, code2)
        }
        addBtnAdd.setOnClickListener{
            val name = addEdtName.text.toString()
            val describe  = addEdtDescribe.text.toString()
            val price = addEdtPrice.text.toString()
            val priceD:Double = price.toDouble()
            val bitmapDrawable :BitmapDrawable = addImg.drawable as BitmapDrawable
            val bitmap :Bitmap =bitmapDrawable.bitmap
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream)
            val picture :ByteArray =byteArrayOutputStream.toByteArray()
            database.insert(name,describe,priceD,picture)
            Toast.makeText(this,"Đã thêm thành công!",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==code1&&resultCode== RESULT_OK&& data!=null){
            val bitmap :Bitmap = data.extras?.get("data") as Bitmap
            addImg.setImageBitmap(bitmap)
        }
        if(requestCode==code2 && resultCode== RESULT_OK && data!=null){
            val uri : Uri? =data.data
            val inputStream : InputStream? = uri?.let { contentResolver.openInputStream(it) }
            val bitmap:Bitmap =BitmapFactory.decodeStream(inputStream)
            addImg.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}