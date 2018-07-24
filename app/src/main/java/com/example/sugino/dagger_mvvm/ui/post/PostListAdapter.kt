package com.example.sugino.dagger_mvvm.ui.post

import android.content.ClipData
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sugino.dagger_mvvm.R
import com.example.sugino.dagger_mvvm.databinding.ItemPostBinding
import com.example.sugino.dagger_mvvm.model.Post
import android.widget.AdapterView.OnItemClickListener



class PostListAdapter: RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private lateinit var postList: List<Post>

    private lateinit var listener: OnRecyclerClickListener

    interface OnRecyclerClickListener {
        fun onClickListener (item: Post) {
        }
    }

    fun setRecyclerClickListener(listener: OnRecyclerClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val binding: ItemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(p0.context), R.layout.item_post, p0, false)
        p0.setOnClickListener(this@PostListAdapter)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(postList[p1])
    }

    override fun getItemCount(): Int {
        return if (::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList: List<Post>) {
        this.postList = postList
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Post {
        return postList.get(position)
    }

    class ViewHolder( private val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PostViewModel()
        fun bind(post: Post) {
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}