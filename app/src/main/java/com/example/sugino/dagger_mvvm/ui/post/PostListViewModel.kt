package com.example.sugino.dagger_mvvm.ui.post

import android.telephony.euicc.DownloadableSubscription
import com.example.sugino.dagger_mvvm.base.BaseViewModel
import com.example.sugino.dagger_mvvm.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel: BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = postApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrivePostListStart() }
                .doOnTerminate { onRetrivePostListFinish() }
                .subscribe(
                        { onRetrivePostListSuccess() },
                        { onRetrivePostListError() }
                )
    }

    private fun onRetrivePostListStart() {

    }

    private fun onRetrivePostListFinish() {

    }

    private fun onRetrivePostListSuccess() {

    }

    private fun onRetrivePostListError() {

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}