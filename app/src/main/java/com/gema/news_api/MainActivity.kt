package com.gema.news_api

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gema.news_api.api.ApiClient
import com.gema.news_api.models.RegisterRequest
import com.gema.news_api.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
private lateinit var btnRegis : Button
private lateinit var btnLogin : Button
private val storagePermission =101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        btnRegis = findViewById(R.id.btnRegister)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegis.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))

        }

        btnLogin.setOnClickListener{
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
        }

       checkStoragePermission()


    }

    private fun checkStoragePermission() {
        if(ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                storagePermission
                )

        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )

        }

    }
}