package com.wahyou.app.ui.game

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.wahyou.app.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity() {

    private val binding : ActivityGameBinding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // deeplink
        val action: String? = intent?.action
        val data: Uri? = intent?.data

        Log.i("Action", action.toString());
        Log.i("Data", data.toString());

        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(){
        with(binding){
            webView.settings.javaScriptEnabled = true
            webView.settings.allowFileAccessFromFileURLs = true
            webView.settings.allowFileAccess = true

            webView.webViewClient = object : WebViewClient(){
                override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                    view?.loadUrl(url)
                    return true
                }
            }

            // cek file game di lokal storage

            // kalo ga ada, akses online ti (data.toString())

            webView.loadUrl("file:///android_asset/game/MobilGerakTouch/index.html")
        }
    }
}