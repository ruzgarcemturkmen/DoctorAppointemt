package com.example.doctorappointment.Activity

import android.os.Bundle
import DoctorsModel
import android.content.Intent
import android.net.Uri
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.doctorappointment.R
import com.example.doctorappointment.databinding.ActivityDetailBinding
import java.net.URI
import java.util.ResourceBundle.getBundle

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item:DoctorsModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getBundle()
    }

    private fun getBundle() {
item=intent.getParcelableExtra("object")!!

        binding.apply {
            titleTxt.text=item.Name
            specialTxt.text=item.Special
            patiensTxt.text=item.Patiens
            bioTxt.text=item.Biography
            addressTxt.text=item.Address
            experiensTxt.text=item.Expriense.toString()+"Years"
            ratingTxt.text="${item.Rating}"
            backBtn.setOnClickListener { finish() }

            websiteBtn.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(item.Site))
                    startActivity(i)
            }

            messageBtn.setOnClickListener {
                val uri= Uri.parse("smsto:${item.Mobile}")
                val intent= Intent(Intent.ACTION_SENDTO,uri)
                intent.putExtra("sms_body","the SMS text")
                startActivity(intent)
            }

            callBtn.setOnClickListener {
                val uri="tel:"+item.Mobile.trim()
                val intent= Intent(Intent.ACTION_DIAL,Uri.parse(uri))
                startActivity(intent)
            }

            directionBtn.setOnClickListener {
                val intent= Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_SUBJECT,item.Name)
                intent.putExtra(Intent.EXTRA_TEXT, item.Name + " " + item.Address + "" + item.Mobile)
                startActivity(Intent.createChooser(intent,"Choose one"))
            }

            Glide.with(this@DetailActivity)
                .load(item.Picture)
                .into(img)
        }
    }
}