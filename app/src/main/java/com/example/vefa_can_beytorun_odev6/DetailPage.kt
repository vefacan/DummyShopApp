package com.example.vefa_can_beytorun_odev6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_page.*
import kotlinx.android.synthetic.main.activity_detail_page.view.*

class DetailPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)


        var extras = getIntent().getExtras();
        if (extras != null) {
            var name: String? = extras.getString("name");
            var description: String? = extras.getString("description");
            var img : String? = extras.getString("image");


            Glide.with(this@DetailPage).load(img).into(detailImg)
            detailTitle.text = name
            detailDesc.text = description
    }
}
}