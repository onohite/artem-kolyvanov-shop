package com.example.artem_kolyvanov_shop.di

import android.content.Context
import com.example.artem_kolyvanov_shop.di.modules.MainApiModule
import com.example.artem_kolyvanov_shop.di.modules.ProductPreferencesModule
import com.example.artem_kolyvanov_shop.ui.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [
        ProductPreferencesModule::class,
        MainApiModule::class
    ]
)

@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context):Builder
        fun build(): AppComponent
    }

    

    fun inject (activity: CatalogActivity)
    fun inject (activity: DetailedActivity)
    fun inject (activity: BasketActivity)
    fun inject (activity: CheckoutActivity)
    fun inject (activity: CategoryActivity)
}