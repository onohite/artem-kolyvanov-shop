package com.example.artem_kolyvanov_shop.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.artem_kolyvanov_shop.data.ProductDaoImpl
import com.example.artem_kolyvanov_shop.domain.ProductDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProductPreferencesModule {


    @Provides
    fun providePreferences(context: Context):SharedPreferences =
          context.getSharedPreferences("data",Context.MODE_PRIVATE)



    @Provides
    fun provideProduct(preferences: SharedPreferences) : ProductDao =
        ProductDaoImpl(preferences)
}