package com.iluvorange.dream.friendcorner.screen.introduction

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.iluvorange.dream.friendcorner.MainActivity
import com.iluvorange.dream.friendcorner.R
import com.iluvorange.dream.friendcorner.data.model.general.Images
import com.iluvorange.dream.friendcorner.databinding.ActivityIntroductionBinding
import com.iluvorange.dream.friendcorner.util.Const.IS_INTRO_SCREEN_SHOW_BEFORE
import com.iluvorange.dream.friendcorner.util.Const.USER_SECRET_SHARED_PREFERENCES
import com.iluvorange.dream.friendcorner.util.DataGenerator
import com.iluvorange.dream.friendcorner.util.PageTransformer


class IntroductionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroductionBinding
    private var maxStep: Int = 0
    private var items = listOf<Images>()
    private var introductionViewPagerAdapter: IntroductionViewPagerAdapter? = null

    private var viewPagerPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            bottomProgressDots(position)
            if (position == maxStep - 1) {
                binding.btnNext.text = getString(com.iluvorange.dream.friendcorner.R.string.DONE)
            } else {
                binding.btnNext.text = getString(com.iluvorange.dream.friendcorner.R.string.NEXT)
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        items = DataGenerator.getIntroOrangeData(this@IntroductionActivity)
        maxStep = items.size
        initComponent()
        //  viewpager change listener

    }

    private fun initComponent() {
        val viewPager = binding.viewPager
        val btnNext = binding.btnNext

        // adding bottom dots
        bottomProgressDots(0)
        introductionViewPagerAdapter = IntroductionViewPagerAdapter(this@IntroductionActivity, items, maxStep)
        viewPager.adapter = introductionViewPagerAdapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
        viewPager.setPageTransformer(false, PageTransformer())
        btnNext.setOnClickListener { _ ->
            val current: Int = viewPager.currentItem + 1
            if (current < maxStep) {
                // move to next screen
                viewPager.currentItem = current
            } else {
                val masterKey = MasterKey.Builder(applicationContext, MasterKey.DEFAULT_MASTER_KEY_ALIAS).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
                val encryptedSharedPreferences = EncryptedSharedPreferences.create(applicationContext, USER_SECRET_SHARED_PREFERENCES, masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
                encryptedSharedPreferences.edit().apply {
                    putBoolean(IS_INTRO_SCREEN_SHOW_BEFORE, true)
                }.apply()
//                startActivity(Intent(this@IntroductionActivity, MainActivity::class.java))
                finish()
            }
        }
        binding.btClose.setOnClickListener {
            finish()
        }
    }


    private fun bottomProgressDots(currentIndex: Int) {
        val dotsLayout = binding.layoutDots
        val dots = arrayOfNulls<ImageView?>(maxStep)
        dotsLayout.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            val height = 8
            val width = if (i == currentIndex) height * 15 else height * 4
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams(width, height))
            params.setMargins(2, 10, 2, 10)
            dots[i]?.layoutParams = params
            dots[i]?.setImageResource(R.drawable.shape_rectangle)
            dots[i]?.setColorFilter(ContextCompat.getColor(this@IntroductionActivity, R.color.grey_20), PorterDuff.Mode.SRC_IN)
            dotsLayout.addView(dots[i])
        }
        dots[currentIndex]?.setImageResource(R.drawable.shape_rectangle)
        dots[currentIndex]?.setColorFilter(ContextCompat.getColor(this@IntroductionActivity, R.color.orange_700), PorterDuff.Mode.SRC_IN)
    }
}