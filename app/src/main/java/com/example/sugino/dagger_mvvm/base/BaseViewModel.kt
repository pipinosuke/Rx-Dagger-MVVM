package com.example.sugino.dagger_mvvm.base

import android.arch.lifecycle.ViewModel
import com.example.sugino.dagger_mvvm.injection.component.DaggerViewModelInjector
import com.example.sugino.dagger_mvvm.injection.component.ViewModelInjector
import com.example.sugino.dagger_mvvm.injection.module.NetWorkModule
import com.example.sugino.dagger_mvvm.ui.post.PostListViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetWorkModule)
            .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}