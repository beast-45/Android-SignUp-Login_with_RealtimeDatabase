package com.example.accessdatabase

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.accessdatabase.databinding.ActivityLoginBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    lateinit var databasereference : DatabaseReference
    lateinit var binding: ActivityLoginBinding
    companion object{
        const val key1 = "com.example.accessdatabase.key1name"
        const val key2 = "com.example.accessdatabase.key2email"
        const val key3 = "com.example.accessdatabase.key3userid"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener()
        {
            //Take reference till node "users"
            val loginusername = binding.etloginusernameinput.text.toString()
            if(loginusername.isNotEmpty())
            {
                readData(loginusername)

            }else{
                Toast.makeText(this , "Please Enter the Username" , Toast.LENGTH_SHORT).show()

            }
        }

        binding.SigninText.setOnClickListener()
        {
            intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun readData(loginusername: String) {
        databasereference = FirebaseDatabase.getInstance().getReference("Users")
        databasereference.child(loginusername).get().addOnSuccessListener {
            //if user exists or not
            if(it.exists()){
                //welcome the user in the app take to welcom page also pass uers information to the welcome page
                val email = it.child("email").value.toString()
                val password = it.child("password").value.toString()
                val name  = it.child("name").value.toString()
                val intentWelcome = Intent(this , WelcomeScreen::class.java)
                intentWelcome.putExtra(key1 , email)
                intentWelcome.putExtra(key2 , name)
                intentWelcome.putExtra(key3 , password)
                startActivity(intentWelcome)
            }
            else{
                Toast.makeText(this , "User Doesn't Exist", Toast.LENGTH_SHORT).show()

            }
        }.addOnFailureListener()
        {
            Toast.makeText(this , "Can't Connct to databbase", Toast.LENGTH_SHORT).show()
        }

    }
}