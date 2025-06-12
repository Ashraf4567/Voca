package com.example.voca

import android.app.Application
import com.example.voca.di.appModule
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Voca: Application() {

    companion object {
        lateinit var supabase: SupabaseClient
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initSupabase()
        startKoin {
            androidContext(this@Voca)
            modules(appModule)
        }
    }

    private fun initSupabase(){
        supabase = createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_KEY
        ) {
            install(Auth)
            //install(Postgrest)
            //install other modules
        }
    }
}