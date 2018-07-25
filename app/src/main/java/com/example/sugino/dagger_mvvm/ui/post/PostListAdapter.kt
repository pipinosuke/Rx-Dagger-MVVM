package com.example.sugino.dagger_mvvm.ui.post

import android.content.ClipData
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.sugino.dagger_mvvm.R
import com.example.sugino.dagger_mvvm.databinding.ItemPostBinding
import com.example.sugino.dagger_mvvm.model.Post

class PostListAdapter: RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private lateinit var postList: List<Post>
    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        setOnItemClickListener(listener)
        val layoutInflater = LayoutInflater.from(p0.context)
        val binding: ItemPostBinding = ItemPostBinding.inflate(layoutInflater, p0, false)
                //DataBindingUtil.inflate(LayoutInflater.from(p0.context), R.layout.item_post, p0, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(postList[p1])
        p0.binding.originalLinearLayout.setOnClickListener({
            listener.onClick(it, postList[p1])
        })
    }

    override fun getItemCount(): Int {
        return if (::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList: List<Post>) {
        this.postList = postList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClick(view: View, item: Post )
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class ViewHolder( var binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PostViewModel()
        fun bind(post: Post) {
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}