package com.iluvorange.dream.friendcorner.screen.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iluvorange.dream.friendcorner.R
import com.iluvorange.dream.friendcorner.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}