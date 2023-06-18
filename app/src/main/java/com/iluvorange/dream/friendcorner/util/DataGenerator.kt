package com.iluvorange.dream.friendcorner.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.iluvorange.dream.friendcorner.R.*
import com.iluvorange.dream.friendcorner.data.model.general.Images


object DataGenerator {

    fun getIntroOrangeData(ctx: Context): List<Images> {
        val items = arrayListOf<Images>()
        val drw_arr = ctx.resources.obtainTypedArray(array.photo_illustration_orange)
        val title = ctx.resources.getStringArray(array.strings_wizard_title)
        val content = ctx.resources.getStringArray(array.strings_wizard_content)
        for (i in 0 until drw_arr.length()) {
            val obj = Images()
            obj.setImages(drw_arr.getResourceId(i, -1))
            obj.setName(title[i])
            obj.setBrief(content[i])
            obj.setImageDrawable(ContextCompat.getDrawable(ctx, obj.getImages()))
            items.add(obj)
        }
        return items
    }
}