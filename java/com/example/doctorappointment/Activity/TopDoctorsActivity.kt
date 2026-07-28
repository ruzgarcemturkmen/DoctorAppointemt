package com.example.doctorappointment.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctorappointment.Adapter.TopDoctorAdapter
import com.example.doctorappointment.Adapter.TopDoctorAdapter2
import com.example.doctorappointment.R
import com.example.doctorappointment.ViewModel.MainViewModel
import com.example.doctorappointment.databinding.ActivityTopDoctorsBinding

class TopDoctorsActivity : BaseActivity() {
    private lateinit var binding: ActivityTopDoctorsBinding
    private val viewModel= MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding= ActivityTopDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

initTopDoctors()

    }

    private fun initTopDoctors() {
        binding.apply {
            binding.progressBarTopDoctor.visibility= View.VISIBLE
            viewModel.doctors.observe(this@TopDoctorsActivity, androidx.lifecycle.Observer{
                viewTopDoctorList.layoutManager= LinearLayoutManager(this@TopDoctorsActivity, LinearLayoutManager.VERTICAL,false)
                binding.viewTopDoctorList.adapter= TopDoctorAdapter2(it)
                binding.progressBarTopDoctor.visibility= View.GONE
            })
            viewModel.loadDoctors()

            backBtn.setOnClickListener { finish() }
        }
    }
}