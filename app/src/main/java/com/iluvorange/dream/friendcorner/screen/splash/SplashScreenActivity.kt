package com.iluvorange.dream.friendcorner.screen.splash


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.iluvorange.dream.friendcorner.MainActivity
import com.iluvorange.dream.friendcorner.R
import com.iluvorange.dream.friendcorner.databinding.ActivitySplashScreenBinding
import com.iluvorange.dream.friendcorner.screen.authentication.SignInActivity
import com.iluvorange.dream.friendcorner.screen.introduction.IntroductionActivity
import com.iluvorange.dream.friendcorner.util.Const.IS_INTRO_SCREEN_SHOW_BEFORE
import com.iluvorange.dream.friendcorner.util.Const.USER_SECRET_SHARED_PREFERENCES

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySplashScreenBinding
    private var isShowedIntroScreenBefore: Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //SharedPreferences
        val masterKey = MasterKey.Builder(applicationContext, MasterKey.DEFAULT_MASTER_KEY_ALIAS).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        val encryptedSharedPreferences = EncryptedSharedPreferences.create(applicationContext, USER_SECRET_SHARED_PREFERENCES, masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
        isShowedIntroScreenBefore = encryptedSharedPreferences.getBoolean(IS_INTRO_SCREEN_SHOW_BEFORE, false)

        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.push_down_in)
        binding.logo.startAnimation(animation)
        val animation2 = AnimationUtils.loadAnimation(applicationContext, R.anim.push_up_in)

        animation.setAnimationListener(object: AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                binding.logoBig.startAnimation(animation2)
            }
            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
        })

        animation2.setAnimationListener(object: AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                if (isShowedIntroScreenBefore == true){
                    startActivity(Intent(this@SplashScreenActivity, SignInActivity::class.java))
                }else{
                    startActivity(Intent(this@SplashScreenActivity, IntroductionActivity::class.java))
                }
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })

    }
}