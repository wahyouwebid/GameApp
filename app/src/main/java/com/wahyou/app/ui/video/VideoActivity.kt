package com.wahyou.app.ui.video

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings.PluginState
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.wahyou.app.databinding.ActivityGameBinding


class VideoActivity : AppCompatActivity() {

    private val binding : ActivityGameBinding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // deeplink
        val action: String? = intent?.action
        val data: Uri? = intent?.data

        Log.i("Action Video", action.toString());
        Log.i("Data Video", data.toString());

        // teu jadi di webview keun
        // setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(){
        with(binding){

            webView.getSettings().setJavaScriptEnabled(true)
            webView.getSettings().setPluginState(PluginState.ON)
            webView.loadUrl("http://www.youtube.com/embed/zZVEI5XkUYU?autoplay=1&vq=small")
            webView.setWebChromeClient(WebChromeClient())
        }
    }
}