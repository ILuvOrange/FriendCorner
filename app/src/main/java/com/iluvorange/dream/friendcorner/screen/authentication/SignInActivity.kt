package com.iluvorange.dream.friendcorner.screen.authentication

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.iluvorange.dream.friendcorner.R
import com.iluvorange.dream.friendcorner.databinding.ActivitySignInBinding


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.signInViewModel =signInViewModel
        setContentView(binding.root)

        signInViewModel.getSignUpNavigateClick.observe(this){isSuccessNavigateToSignUp ->
            if (isSuccessNavigateToSignUp){
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right,0)
                signInViewModel.resetNavigateToSignUp()
            }
        }
    }
}