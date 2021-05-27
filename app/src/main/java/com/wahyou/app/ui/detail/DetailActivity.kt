package com.wahyou.app.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.link.DefaultLinkHandler
import com.wahyou.app.data.model.Game
import com.wahyou.app.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding : ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val data : Game? by lazy {
        intent.getParcelableExtra("data")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupLoadPdf()
    }

    private fun setupToolbar(){
        with(binding){
            imgBack.setOnClickListener { finish() }
            tvTitleToolbar.text = data?.name
        }
    }

    private fun setupLoadPdf(){
        with(binding){
            pdfView.fromAsset("sample.pdf")
                .swipeHorizontal(false)
                .enableAnnotationRendering(true)
                .linkHandler(DefaultLinkHandler(pdfView))
                .load()
        }
    }
}