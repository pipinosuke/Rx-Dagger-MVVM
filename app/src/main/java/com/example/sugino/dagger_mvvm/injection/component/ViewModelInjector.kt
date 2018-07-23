package com.example.sugino.dagger_mvvm.injection.component

import com.example.sugino.dagger_mvvm.injection.module.NetWorkModule
import com.example.sugino.dagger_mvvm.ui.post.PostListViewModel
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetWorkModule::class)])

interface ViewModelInjector {

    fun inject(postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetWorkModule): Builder
    }
}