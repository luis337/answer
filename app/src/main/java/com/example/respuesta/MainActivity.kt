package com.example.respuesta

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val background = findViewById<ConstraintLayout>(R.id.background)
        val text = findViewById<TextView>(R.id.textView)

        val yes = "SÍ"
        val no = "NO"

        val green = Color.GREEN
        val red = Color.RED

        button.setOnClickListener {
            button.isEnabled = false
            val random = Random.nextBoolean()

            val anim = ValueAnimator()
            anim.setIntValues(green, red)
            anim.setEvaluator(ArgbEvaluator())
            anim.repeatMode = ValueAnimator.REVERSE
            anim.repeatCount = if (random) 9 else 10
            anim.duration = 500
            anim.addUpdateListener { animation ->
                background.setBackgroundColor(animation.animatedValue as Int)
                if (animation.animatedFraction <= 0.5)
                    text.text = yes
                else
                    text.text = no
                animation.doOnEnd { button.isEnabled = true }
            }
            anim.start()
        }
    }
}