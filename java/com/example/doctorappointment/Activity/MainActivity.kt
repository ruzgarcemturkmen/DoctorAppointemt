package com.example.doctorappointment.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import DoctorsModel
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctorappointment.Adapter.CategoryAdapter
import com.example.doctorappointment.Adapter.TopDoctorAdapter
import com.example.doctorappointment.Adapter.TopDoctorAdapter2
import com.example.doctorappointment.R
import com.example.doctorappointment.ViewModel.MainViewModel
import com.example.doctorappointment.databinding.ActivityMainBinding
import java.util.Observer

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val  viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initTopDoctors()
    }

    private fun initTopDoctors() {
         binding.apply {
             binding.progressBarTopDoctor.visibility= View.VISIBLE
             viewModel.doctors.observe(this@MainActivity, androidx.lifecycle.Observer{
                 recyclerViewTopDoctor.layoutManager= LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
                 binding.recyclerViewTopDoctor.adapter= TopDoctorAdapter(it)
                 binding.progressBarTopDoctor.visibility= View.GONE
             })
             viewModel.loadDoctors()

             doctorListTxt.setOnClickListener {
                 startActivity(Intent(this@MainActivity, TopDoctorsActivity::class.java))
             }
         }
         }

    private fun initCategory() {
         binding.progressBarCategory.visibility= View.VISIBLE
        viewModel.category.observe(this, androidx.lifecycle.Observer{
            binding.viewCategory.layoutManager= LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
            binding.viewCategory.adapter= CategoryAdapter(it)
            binding.progressBarCategory.visibility= View.GONE
        })
        viewModel.loadCategory()
    }
}