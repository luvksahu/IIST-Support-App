package com.brocoders.iistsupport

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.brocoders.iistsupport.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


    override fun onBackPressed() {
        AlertDialog.Builder(this@MainActivity)
            .setIcon(R.drawable.ic_warning)
            .setTitle("Exit App")
            .setMessage("Do you want to exit this app?")
            .setPositiveButton("Yes") { dialogInterface, i -> finish() }
            .setNegativeButton("No") { dialogInterface, i -> dialogInterface.dismiss() }.show()
    }

}