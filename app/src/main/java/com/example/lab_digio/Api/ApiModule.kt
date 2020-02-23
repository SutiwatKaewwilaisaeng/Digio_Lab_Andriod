package com.example.lab_digio.Api

import com.example.lab_digio.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiModule {

    companion object{
        fun QRcodeInterceptor():APIService{
            val qrCodelogging = HttpLoggingInterceptor()
            qrCodelogging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(qrCodelogging)
                .connectTimeout(45,TimeUnit.SECONDS)
                .writeTimeout(45,TimeUnit.SECONDS)
                .readTimeout(45,TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BuildConfig.URL)
                .client(okHttpClient)
                .build()
            return  retrofit.create(APIService::class.java)

        }
    }
}