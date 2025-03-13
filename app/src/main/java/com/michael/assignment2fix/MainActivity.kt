package com.michael.assignment2fix

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lateinit var imageView: ImageView
        lateinit var submitBtn: Button
        val jawaban = arrayOf("harimau", "singa", "gajah", "rusa", "ayam")
        val clue = arrayOf(
            "Hewan ini satu keluarga dengan kucing",
            "Hewan ini disebut raja hutan",
            "Hewan ini biasanya bermain di sirkus",
            "Hewan ini menjadi maskot provinsi NTB",
            "Hewan ini berkokok di pagi hari"
        )

        val img = arrayOf(
            R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5
        )
        var lastImg = 0

        imageView = findViewById(R.id.gambarGame)
        submitBtn = findViewById(R.id.btnSubmit)
        var clueText: TextView = findViewById(R.id.clueGame)
        var inputJawaban: EditText = findViewById(R.id.inputJawaban)

        imageView.setImageResource(img[lastImg])
        clueText.setText("Clue: " + clue[lastImg])

        submitBtn.setOnClickListener(){

            var isCorrect: Boolean = inputJawaban.text.toString().trim().equals(jawaban[lastImg], ignoreCase = true)

            val windowHasil = AlertDialog.Builder(this)
                .setTitle(if (isCorrect) "Jawaban kamu Benar YEAYYYYYY!" else "Jawaban Salah :(")
                .setMessage("Jawaban: "+ jawaban[lastImg])
                .setPositiveButton("OK") { windowHasil, _ ->
                    windowHasil.dismiss()
                    inputJawaban.text.clear()

                    var idx: Int
                    do{
                        idx = Random.nextInt(img.size)
                    }
                    while(idx==lastImg)
                    lastImg = idx
                    imageView.setImageResource(img[idx])
                    clueText.setText("Clue: " + clue[idx])
                }
                .create()

            windowHasil.show()


        }
    }
}