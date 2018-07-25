package com.example.sugino.dagger_mvvm.injection.module

import com.example.sugino.dagger_mvvm.network.PostApi
import com.example.sugino.dagger_mvvm.utils.BASE_URL
import com.example.sugino.dagger_mvvm.utils.Qiita_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@Suppress("unused")

object NetWorkModule {
    @Provides
@Reusable
@JvmStatic
internal fun providePostApi(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @Provides
@Reusable
@JvmStatic
internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Qiita_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}