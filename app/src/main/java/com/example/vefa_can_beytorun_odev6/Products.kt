package com.example.vefa_can_beytorun_odev6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vefa_can_beytorun_odev6.configs.ApiClient
import com.example.vefa_can_beytorun_odev6.models.DummyProducts
import com.example.vefa_can_beytorun_odev6.products.ProductAdapter
import com.example.vefa_can_beytorun_odev6.services.DummyService
import kotlinx.android.synthetic.main.activity_products.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Products : AppCompatActivity() {

    lateinit var dummyService: DummyService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        dummyService = ApiClient.getClient().create(DummyService::class.java)


        dummyService.getProducts(10).enqueue(object : Callback<DummyProducts>{

            override fun onResponse(call: Call<DummyProducts>, response: Response<DummyProducts>) {

                val customAdapter = response.body()?.let { ProductAdapter(
                    this@Products,
                    it.products
                ) }
                product_listview.adapter = customAdapter

                product_listview.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
                    val intent = Intent(this@Products, DetailPage::class.java)
                    intent.putExtra("name", response.body()?.products?.get(position)?.title)
                    intent.putExtra("description", response.body()?.products?.get(position)?.description)
                    intent.putExtra("image", response.body()?.products?.get(position)?.thumbnail)
                    startActivity(intent)
                })

            }

            override fun onFailure(call: Call<DummyProducts>, t: Throwable) {
                Log.e("fail", t.toString())
            }


        })


        btnSearch.setOnClickListener {

            if (txtInputEdittext2.text.toString().isEmpty()){

                Toast.makeText(this@Products,"Lütfen bir harf giriniz.",Toast.LENGTH_SHORT).show()

            }

            if (txtInputEdittext2.text.toString().isNotEmpty()){

                dummyService.searchProducts(txtInputEdittext2.text.toString()).enqueue(object : Callback<DummyProducts>{
                    override fun onResponse(
                        call: Call<DummyProducts>,
                        response: Response<DummyProducts>
                    ) {
                        val customAdapter = response.body()?.let { ProductAdapter(
                            this@Products,
                            it.products
                        ) }
                        product_listview.adapter = customAdapter
                        customAdapter?.notifyDataSetChanged()

                        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(btnSearch.windowToken, 0)


                    }

                    override fun onFailure(call: Call<DummyProducts>, t: Throwable) {
                        Log.d("fail",t.toString())
                    }
                })
            }


        }




    }
}


