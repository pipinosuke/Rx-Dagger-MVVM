package com.example.sugino.dagger_mvvm.ui.post

import android.arch.lifecycle.MutableLiveData
import android.telephony.euicc.DownloadableSubscription
import android.view.View
import com.example.sugino.dagger_mvvm.base.BaseViewModel
import com.example.sugino.dagger_mvvm.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel: BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

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
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrivePostListFinish() {
        loadingVisibility.value = View.GONE
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