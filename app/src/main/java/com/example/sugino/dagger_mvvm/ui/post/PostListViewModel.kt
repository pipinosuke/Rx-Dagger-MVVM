package com.example.sugino.dagger_mvvm.ui.post

import com.example.sugino.dagger_mvvm.base.BaseViewModel
import com.example.sugino.dagger_mvvm.network.PostApi
import javax.inject.Inject

class PostListViewModel: BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
}