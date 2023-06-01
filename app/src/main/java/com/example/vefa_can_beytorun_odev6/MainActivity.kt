package com.example.vefa_can_beytorun_odev6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.vefa_can_beytorun_odev6.configs.ApiClient
import com.example.vefa_can_beytorun_odev6.configs.Util
import com.example.vefa_can_beytorun_odev6.models.JWTData
import com.example.vefa_can_beytorun_odev6.models.JWTUser
import com.example.vefa_can_beytorun_odev6.services.DummyService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    lateinit var dummyService: DummyService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dummyService = ApiClient.getClient().create(DummyService::class.java)

        btnLogin.setOnClickListener {
            val username = editTxtUsername.text.toString()
            val password = editTxtPassword.text.toString()
            val jwtUser = JWTUser(username, password)
            Log.d("deneme username", editTxtUsername.text.toString())
            Log.d("deneme pw", editTxtPassword.text.toString())

                 /*   username: 'kminchelle',
                            password: '0lelplR',           */

            dummyService.login(jwtUser).enqueue(object : Callback<JWTData> {
                override fun onResponse(call: Call<JWTData>, response: Response<JWTData>) {
                    val jwtUser = response.body()

                    // Statu değerlendirme ve açıklama
                    Log.d("status", response.code().toString())
                    if (response.code() == 400) {
                        Log.d("Hata?", "Şifre yanlış")
                    } else if (response.code() == 200) {
                        Log.d("Hata?", "Hata Yok şifre doğru.")
                    }

                    // Null kontrol
                    if (jwtUser != null) {
                        Util.user = jwtUser
                        Log.d("JWT USER", jwtUser.toString())

                        val intent = Intent(this@MainActivity,Products::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                override fun onFailure(call: Call<JWTData>, t: Throwable) {
                    // Fail durumunda gelecek hata
                    Log.e("Login Fail", t.toString())
                    Toast.makeText(
                        this@MainActivity,
                        "İnternet veya ağ sıkıntısı",
                        Toast.LENGTH_SHORT
                    ).show() }
            })
        }

    }
}