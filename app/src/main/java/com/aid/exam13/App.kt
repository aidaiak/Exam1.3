package com.aid.exam13

import android.app.Application
import androidx.room.Room
import com.aid.exam13.database.AppDatabase
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    lateinit var database: AppDatabase
    private val httpInterceptor = HttpLoggingInterceptor().also {
        it.setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val okhttpClient = OkHttpClient.Builder()
        .addInterceptor(httpInterceptor)
        .build()

    val client = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .client(okhttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RickMortyAPI::class.java)

    override fun onCreate() {
        mInstance = this
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        private var mInstance: App? = null
        val instance: App
            get() = mInstance!!
    }
}

val Any.Injector: App
    get() = App.instance