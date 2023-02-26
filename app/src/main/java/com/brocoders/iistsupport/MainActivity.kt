package com.brocoders.iistsupport

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.brocoders.iistsupport.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                } finally {
                    if((FirebaseAuth.getInstance().currentUser != null) && FirebaseAuth.getInstance().currentUser!!.isEmailVerified){
                        val intent=Intent(this@MainActivity,HomeActivity::class.java)
                        startActivity(intent)
                    }else{
                        val intent=Intent(this@MainActivity,LoginActivity::class.java)
                        startActivity(intent)
                    }
                    finish()
                }
            }
        }
        thread.start()
    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        AlertDialog.Builder(this@MainActivity)
            .setIcon(R.drawable.ic_warning)
            .setTitle("Exit App")
            .setMessage("Do you want to exit this app?")
            .setPositiveButton("Yes") { dialogInterface, i -> finish() }
            .setNegativeButton("No") { dialogInterface, i -> dialogInterface.dismiss() }.show()
    }

}