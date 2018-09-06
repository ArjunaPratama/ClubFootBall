package com.arjuna.clubfootball

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.arjuna.clubfootball.Model.FootClub
import com.arjuna.clubfootball.R.id.img_Club
import com.arjuna.clubfootball.R.id.txt_Club
import com.arjuna.clubfootball.activity.DetailActivity
import com.bumptech.glide.Glide
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val clubnya: MutableList<FootClub> = mutableListOf()

        val nama = resources.getStringArray(R.array.club_Name)
        val image = resources.obtainTypedArray(R.array.club_Image)
        val desc = resources.getStringArray(R.array.club_Decs)

        clubnya.clear()


        for (i in nama.indices){
            clubnya.add(FootClub(nama[i], image.getResourceId(i, 0), desc[i]))
        }

        image.recycle()

        verticalLayout {
            recyclerView {
                lparams(width = matchParent, height = matchParent)
                layoutManager = LinearLayoutManager(context)
                adapter = ClubAdapter(clubnya) {
                    startActivity<DetailActivity>(
                            "nama" to it.ClubName,
                            "image" to it.ClubImage,
                            "desc" to it.ClubDesc
                    )
                }
            }
        }
    }
    class ClubAdapter(private val clubs: List<FootClub>, private val listener: (FootClub) -> Unit) : RecyclerView.Adapter<ClubAdapter.MyViewHolder>() {

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bindItem(clubs[position], listener)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(ClubUI().createView(AnkoContext.create(parent.context, parent)))
        }

        override fun getItemCount(): Int = clubs.size

        class ClubUI : AnkoComponent<ViewGroup> {

            override fun createView(ui: AnkoContext<ViewGroup>): View {
                return with(ui) {
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        padding = dip(16)

                        imageView {
                            id = R.id.img_Club
                        }.lparams(width = dip(60), height = dip(60))

                        textView {
                            id = R.id.txt_Club
                            textSize = 16f
                        }.lparams(width = wrapContent, height = wrapContent) {
                            gravity = Gravity.CENTER_VERTICAL
                            margin = dip(8)
                        }
                    }
                }
            }
        }
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val namaClubAdap: TextView = view.find(txt_Club)
            private val imageClubAdap: ImageView = view.find(img_Club)
            fun bindItem(clubs: FootClub, listener: (FootClub) -> Unit) {
                namaClubAdap.text = clubs.ClubName
                Glide.with(itemView.context)
                        .load(clubs.ClubImage)
                        .into(imageClubAdap)
                itemView.onClick { listener(clubs) }
            }
        }

    }
}
