package com.brocoders.iistsupport

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.brocoders.iistsupport.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show()

        val facultyClick = findViewById<LinearLayout>(R.id.btn_faculty)
        facultyClick.setOnClickListener {
            val intent = Intent(this, FacultyActivity::class.java)
            startActivity(intent)
        }
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