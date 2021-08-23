package com.example.lucky_ticket_kotlin

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var number: EditText? = null
    private var lamp: ImageView? = null
    private var drawable: Drawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number = findViewById(R.id.editText)
        lamp = findViewById(R.id.lamp)
        drawable = getDrawable(R.drawable.shape)

    }

    fun changeColor(view: View){
        val num = number!!.text.toString().trim { it <= ' ' }

        if (TextUtils.isEmpty(num) || num.length != 6) {
            Toast.makeText(applicationContext, "incorrectly entered number", Toast.LENGTH_SHORT)
                .show()
            return
        }

        if (isLucky(num)) {
            drawable!!.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
            lamp!!.background = drawable
        } else {
            drawable!!.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
            lamp!!.background = drawable
        }
    }

    fun isLucky(num: String): Boolean {
        return sumOfDigits(num, 0) == sumOfDigits(num, num.length - 3)
    }

    fun sumOfDigits(num: String, start: Int): Int {
        var sum = 0
        for (i in start until start + 3) sum += Character.digit(num[i], 10)
        return sum
    }
}