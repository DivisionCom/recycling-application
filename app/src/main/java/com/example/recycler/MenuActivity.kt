package com.example.recycler

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recycler.databinding.ActivityMenuBinding
import kotlin.random.Random

class MenuActivity : AppCompatActivity() {

private lateinit var binding: ActivityMenuBinding

    private var scoreNum = 0.0
    private var lifes = 3
    private var randomImages: IntArray = intArrayOf(
        R.drawable.glass_1,
        R.drawable.glass_2,
        R.drawable.glass_3,
        R.drawable.plastic_1,
        R.drawable.plastic_2,
        R.drawable.plastic_3,
        R.drawable.aluminum_1,
        R.drawable.aluminum_2,
        R.drawable.aluminum_3,
        R.drawable.other_1,
        R.drawable.other_2,
        R.drawable.other_3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_menu)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
           R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        // setupActionBarWithNavController(navController, appBarConfiguration)
         navView.setupWithNavController(navController)

        scoreUpdate("initial")
        heartUpdate()
    }

    fun rightArrowClick(view: View) {
        scoreUpdate("right")
        heartUpdate()
    }

    fun leftArrowClick(view: View) {
        scoreUpdate("left")
        heartUpdate()
    }

    fun upArrowClick(view: View) {
        scoreUpdate("up")
        heartUpdate()
    }

    fun downArrowClick(view: View) {
        scoreUpdate("down")
        heartUpdate()
    }

     @SuppressLint("UseCompatLoadingForDrawables")
     private fun scoreUpdate(direction: String) {
        val mainObject = findViewById<ImageView>(R.id.image_object)
        val score = findViewById<TextView>(R.id.text_score_number)

        val currentImage = mainObject.drawable.constantState
        val glass1 = resources.getDrawable(R.drawable.glass_1).constantState
        val glass2 = resources.getDrawable(R.drawable.glass_2).constantState
        val glass3 = resources.getDrawable(R.drawable.glass_3).constantState
        val plastic1 = resources.getDrawable(R.drawable.plastic_1).constantState
        val plastic2 = resources.getDrawable(R.drawable.plastic_2).constantState
        val plastic3 = resources.getDrawable(R.drawable.plastic_3).constantState
        val aluminum1 = resources.getDrawable(R.drawable.aluminum_1).constantState
        val aluminum2 = resources.getDrawable(R.drawable.aluminum_2).constantState
        val aluminum3 = resources.getDrawable(R.drawable.aluminum_3).constantState
        val other1 = resources.getDrawable(R.drawable.other_1).constantState
        val other2 = resources.getDrawable(R.drawable.other_2).constantState
        val other3 = resources.getDrawable(R.drawable.other_3).constantState

         when (direction) {
             "right" -> {
                 when (currentImage) {
                     glass1 -> scoreNum += 1.0
                     glass2 -> scoreNum += 1.0
                     glass3 -> scoreNum += 1.0
                     else -> lifes--
                 }
             }
             "left" -> {
                 when (currentImage) {
                     plastic1 -> scoreNum += 1.0
                     plastic2 -> scoreNum += 1.0
                     plastic3 -> scoreNum += 1.0
                     else -> lifes--
                 }
             }
             "up" -> {
                 when (currentImage) {
                     aluminum1 -> scoreNum += 1.0
                     aluminum2 -> scoreNum += 1.0
                     aluminum3 -> scoreNum += 1.0
                     else -> lifes--
                 }
             }
             "down" -> {
                 when (currentImage) {
                     other1 -> scoreNum += 1.0
                     other2 -> scoreNum += 1.0
                     other3 -> scoreNum += 1.0
                     else -> lifes--
                 }
             }
         }

        val random = Random
        mainObject.setImageResource(randomImages[random.nextInt(randomImages.size)])

        score.text = scoreNum.toString()
    }

    private fun heartUpdate() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Игра провалена!")
        builder.setMessage("Хотите попробовать еще раз?")
        builder.setPositiveButton("Начать заново") {
                dialog, which ->
            Toast.makeText(this, "Начать заново", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Обучение") {
                dialog, which ->
            Toast.makeText(this, "Обучение", Toast.LENGTH_SHORT).show()
        }

        val heart = findViewById<ImageView>(R.id.image_heart)

        when (lifes) {
            3 -> heart.setImageResource(R.drawable.heart_full)
            2 -> heart.setImageResource(R.drawable.heart_1mis)
            1 -> heart.setImageResource(R.drawable.heart_2mis)
            0 -> heart.setImageResource(R.drawable.heart_3mis)
            else -> {
                builder.show()
            }
        }

    }
}