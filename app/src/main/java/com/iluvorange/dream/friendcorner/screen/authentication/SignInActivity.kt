package com.iluvorange.dream.friendcorner.screen.authentication

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
//                // Check if we're running on Android 5.0 or higher
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    // Apply activity transition
//                } else {
//                    // Swap without transition
//                }
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                signInViewModel.resetNavigateToSignUp()
            }
        }
    }
}