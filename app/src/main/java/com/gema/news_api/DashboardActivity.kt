package com.gema.news_api

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.gema.news_api.adapter.BeritaAdapter
import com.gema.news_api.api.ApiClient
import com.gema.news_api.models.BeritaResponse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private lateinit var svJudul: SearchView
    private lateinit var rvBerita: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var floatingBtnTambah: FloatingActionButton
    private lateinit var beritaAdapter: BeritaAdapter
    private lateinit var imgNotFound: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        svJudul = findViewById(R.id.svJudul)
        rvBerita = findViewById(R.id.rvBerita)
        progressBar = findViewById(R.id.progressBar)
        floatingBtnTambah = findViewById(R.id.floatingBtnTambah)
        imgNotFound = findViewById(R.id.imgNotFound)

        // panggil method getBerita
        getBerita("")
        svJudul.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            //method pencarian
            override fun onQueryTextChange(pencarian: String?): Boolean {
                getBerita(pencarian.toString())
                return true
            }
        })
        floatingBtnTambah.setOnClickListener {
            startActivity(Intent(this, TambahBeritaActivity::class.java))
        }

    }

    private fun getBerita(judul: String){
        progressBar.visibility = View.VISIBLE
        ApiClient.apiService.getListBerita(judul).enqueue(object: Callback<BeritaResponse> {
            override fun onResponse(
                call: Call<BeritaResponse>,
                response: Response<BeritaResponse>
            ) {
                if (response.isSuccessful){
                  if(response.body()!!.success ){

                      beritaAdapter = BeritaAdapter(arrayListOf())
                      rvBerita.adapter = beritaAdapter
                      beritaAdapter.setData(response.body()!!.data)
                      imgNotFound.visibility = View.GONE


                  }else{
                      beritaAdapter = BeritaAdapter(arrayListOf())
                      rvBerita.adapter = beritaAdapter
                      imgNotFound.visibility = View.VISIBLE
                  }
                }
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<BeritaResponse>, t: Throwable) {
               Toast.makeText(this@DashboardActivity,"Error : ${t.message}",Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }
        })
    }
}