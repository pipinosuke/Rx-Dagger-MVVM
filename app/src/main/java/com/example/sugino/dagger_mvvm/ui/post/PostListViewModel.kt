package com.example.sugino.dagger_mvvm.ui.post

import android.arch.lifecycle.MutableLiveData
import android.telephony.euicc.DownloadableSubscription
import android.view.View
import com.example.sugino.dagger_mvvm.R
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
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

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
        errorMessage.value = null
    }

    private fun onRetrivePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrivePostListSuccess() {

    }

    private fun onRetrivePostListError() {
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}