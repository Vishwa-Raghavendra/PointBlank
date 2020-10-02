package com.dscepointblank.pointblank.utilityClasses

import com.dscepointblank.pointblank.apis.CListAPI
import com.dscepointblank.pointblank.apis.CodeForcesAPI
import com.dscepointblank.pointblank.notifications.NotificationAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance
{
    companion object {
        lateinit var URL : String
        private val retrofit by lazy {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: NotificationAPI by lazy {
            URL = Constants.BASE_URL
            retrofit.create(
                NotificationAPI::class.java)
        }

        val codeForcesAPI: CodeForcesAPI by lazy {
            URL = Constants.CODE_FORCES_BASE_URL
            retrofit.create(CodeForcesAPI::class.java)
        }

        val clistAPI : CListAPI by lazy {
            URL = Constants.CLIST_BASE_URL
            retrofit.create(CListAPI::class.java)
        }
    }
}