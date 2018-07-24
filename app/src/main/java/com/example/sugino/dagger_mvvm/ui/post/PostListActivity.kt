package com.example.sugino.dagger_mvvm.ui.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Message
import android.os.PersistableBundle
import android.renderscript.ScriptGroup
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import com.example.sugino.dagger_mvvm.R
import com.example.sugino.dagger_mvvm.databinding.ActivityPostListBinding
import com.example.sugino.dagger_mvvm.model.Post
import kotlinx.android.synthetic.main.activity_post_list.view.*

class PostListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: PostListViewModel

//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onCreate(savedInstanceState, persistentState)
//    }

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(PostListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            it -> if (it != null) showError(it) else hideError()
        })
        binding.viewModel = viewModel
    }

    private inner class onItemClickListener: RecyclerView.onItemClickListener {
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            val intent = Intent(this@PostListActivity, DetailActivity::class.java )
            val result: Post = viewModel.postListAdapter.getItem(p2)
            intent.putExtra("title", result.title)
            startActivity(intent)
        }
    }

    private fun showError(@StringRes errorMessage:Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}