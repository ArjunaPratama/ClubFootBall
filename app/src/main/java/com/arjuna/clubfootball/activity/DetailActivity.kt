package com.arjuna.clubfootball.activity

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.arjuna.clubfootball.Model.FootClub
import com.arjuna.clubfootball.R
import com.arjuna.clubfootball.R.array.*
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {
    private var namaClub: String = ""
    private var descClub: String = ""
    private var imgClub: Int = 0


    private lateinit var txtNamaClub: TextView
    private lateinit var txtDescClub: TextView
    private lateinit var imgViewClub: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        linearLayout {
            orientation = LinearLayout.VERTICAL
            padding = dip(16)

            imgViewClub = imageView().lparams(width = dip(100), height = dip(100)) {
                gravity = Gravity.CENTER_HORIZONTAL
            }
            txtNamaClub = textView {
                gravity = Gravity.CENTER
                typeface = Typeface.DEFAULT_BOLD
                topPadding = dip(8)
                textSize = 16f
            }
            txtDescClub = textView {
                topPadding = dip(10)
                textSize = 14f
            }
        }
        val intentClub = intent
        namaClub = intentClub.getStringExtra("nama")
        descClub = intentClub.getStringExtra("desc")
        imgClub = intentClub.getIntExtra("image", 0)

        txtNamaClub.text = namaClub
        txtDescClub.text = descClub
        Glide.with(this)
                .load(imgClub)
                .into(imgViewClub)


    }
}
