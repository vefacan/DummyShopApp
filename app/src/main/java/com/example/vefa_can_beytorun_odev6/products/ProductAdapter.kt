package com.example.vefa_can_beytorun_odev6.products

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.vefa_can_beytorun_odev6.R
import com.example.vefa_can_beytorun_odev6.models.DummyProducts
import com.example.vefa_can_beytorun_odev6.models.Product
import retrofit2.Response

class ProductAdapter(private val context: Activity, private val list: List<Product>): ArrayAdapter<Product>(context, R.layout.custom_listview,list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rootView = context.layoutInflater.inflate(R.layout.custom_listview,null,true)

        val product_img = rootView.findViewById<ImageView>(R.id.product_img)
        val product_title = rootView.findViewById<TextView>(R.id.product_title)
        val product_detail = rootView.findViewById<TextView>(R.id.product_detail)

        val product = list.get(position)


        Glide.with(context).load(product.thumbnail).into(product_img)
        product_title.text = product.title
        product_detail.text = product.description


        return rootView
    }







}