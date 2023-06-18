package com.iluvorange.dream.friendcorner.screen.introduction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.iluvorange.dream.friendcorner.R
import com.iluvorange.dream.friendcorner.data.model.general.Images


class IntroductionViewPagerAdapter(private val mContext: Context, private val itemList: List<Images>,private val maxStep: Int): PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater?.inflate(R.layout.item_introduction, container, false)
        view?.findViewById<TextView>(R.id.title)?.text = itemList[position].getName()
        view?.findViewById<TextView>(R.id.description)?.text = itemList[position].getBrief()
        view?.findViewById<ImageView>(R.id.image)?.setImageResource(itemList[position].getImages())
        container.addView(view)
        return view!!

    }

    override fun getCount(): Int = maxStep
    override fun isViewFromObject(view: View, `object`: Any): Boolean = view ==`object`

    @Deprecated("Deprecated in Java")
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View?
        container.removeView(view)
    }
}