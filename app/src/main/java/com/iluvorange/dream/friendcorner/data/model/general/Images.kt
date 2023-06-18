package com.iluvorange.dream.friendcorner.data.model.general

import android.graphics.drawable.Drawable

class Images {

    private var image: Int = 0
    private var imageDrawable: Drawable? = null
    private var name: String = ""
    private var brief: String = ""
    private var counter: Int = 0

    constructor()
    constructor(image: Int, imageDrawable: Drawable?, name: String, brief: String, counter: Int) {
        this.image = image
        this.imageDrawable = imageDrawable
        this.name = name
        this.brief = brief
        this.counter = counter
    }

    fun getImages(): Int {
        return image
    }

    fun setImages(image: Int) {
        this.image = image
    }

    fun getImageDrawable(): Drawable? {
        return imageDrawable
    }

    fun setImageDrawable(imageDrawable: Drawable?) {
        this.imageDrawable = imageDrawable
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getBrief(): String {
        return brief
    }

    fun setBrief(brief: String) {
        this.brief = brief
    }

    fun getCounter(): Int {
        return counter
    }

    fun setCounter(counter: Int) {
        this.counter = counter
    }

}