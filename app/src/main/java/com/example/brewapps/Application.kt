package com.example.brewapps

import android.app.Application
import com.example.brewapps.data.network.Api
import com.example.brewapps.data.network.NetworkConnectionInterceptor
import com.example.brewapps.data.repositories.Repository
import com.example.brewapps.ui.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class Application : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@Application))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { Api(instance()) }

        // Repository

        bind() from singleton { Repository(instance()) }

        // View Model Factory

        bind() from provider { ViewModelFactory(instance()) }


    }

}