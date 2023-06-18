package com.iluvorange.dream.friendcorner.util

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class PageTransformer: ViewPager.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        if (position < -1) {    // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.alpha = 0f;

        } else if (position <= 0) {    // [-1,0]
            view.alpha = 1f;
            view.translationX = 0f;
            view.scaleX = 1f;
            view.scaleY = 1f;

        } else if (position <= 1) {    // (0,1]
            view.translationX = -position * view.getWidth();
            view.alpha = 1 - abs(position);
            view.scaleX = 1 - abs(position);
            view.scaleY = 1 - abs(position);

        } else {    // (1,+Infinity]
            // This page is way off-screen to the right.
            view.alpha = 0f;
        }
    }

}