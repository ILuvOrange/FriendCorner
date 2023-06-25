package com.iluvorange.dream.friendcorner.screen.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iluvorange.dream.friendcorner.R
import com.iluvorange.dream.friendcorner.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, R.anim.slide_out_right);
    }

}