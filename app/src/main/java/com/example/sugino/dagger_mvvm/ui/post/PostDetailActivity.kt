package com.example.sugino.dagger_mvvm.ui.post

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.sugino.dagger_mvvm.R
import kotlinx.android.synthetic.main.post_detail.view.*

class PostDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_detail)

        val webView = findViewById<WebView>(R.id.webView)
        val url = intent.getStringExtra("url")

        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)

        webView .settings.javaScriptEnabled = true
    }
}