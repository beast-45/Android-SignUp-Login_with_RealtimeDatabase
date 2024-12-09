package com.example.accessdatabase

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.accessdatabase.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User

class MainActivity : AppCompatActivity() {
    lateinit var database : DatabaseReference
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginText.setOnClickListener()
        {
            intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)
        }


        binding.SignButton.setOnClickListener()
        {
            val name = binding.etnameinput.text.toString()
            val mail = binding.etemailinput.text.toString()
            val username = binding.etusernameinput.text.toString()
            val pass = binding.etpasswordinput.text.toString()

            val user = Users(name , mail , username , pass)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(username).setValue(user).addOnSuccessListener {
                Toast.makeText(this , "User Registered" , Toast.LENGTH_SHORT).show()
            }.addOnFailureListener(){
                Toast.makeText(this , "User Not Registered" , Toast.LENGTH_SHORT).show()
            }
            binding.etnameinput.text?.clear()
            binding.etemailinput.text?.clear()
            binding.etusernameinput.text?.clear()
            binding.etpasswordinput.text?.clear()

            intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)

        }
    }
}