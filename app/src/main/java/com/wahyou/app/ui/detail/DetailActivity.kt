package com.wahyou.app.ui.detail

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.link.DefaultLinkHandler
import com.wahyou.app.R
import com.wahyou.app.data.model.Game
import com.wahyou.app.databinding.ActivityDetailBinding
import com.wahyou.app.utils.unzip
import java.io.File

class DetailActivity : AppCompatActivity() {

    private val binding : ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val data : Game? by lazy {
        intent.getParcelableExtra("data")
    }

    private var downloadReference: Long = 0
    private var fileLocation = ""
    private val nameFile = "MobileGerakTouch.zip"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupLoadPdf()
    }

    private fun setupToolbar(){
        with(binding){
            imgBack.setOnClickListener { finish() }
            setSupportActionBar(toolbar);
            tvTitleToolbar.text = data?.name
        }
    }

    private fun setupLoadPdf(){
        with(binding){
            pdfView.fromAsset("GameBalapMobilOnline.pdf")
                .swipeHorizontal(false)
                .enableAnnotationRendering(true)
                .linkHandler(DefaultLinkHandler(pdfView))
                .load()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_download -> {
                setupDownloadGame()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupDownloadGame(){
        val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri: Uri = Uri.parse(data?.urlGame)

        val request = DownloadManager.Request(uri)
        request.setTitle("Game Mobil Gerak")
        request.setDescription("Downloading")
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, nameFile)
        request.setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
        )
        fileLocation = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
        ).toString() + nameFile

        downloadReference = downloadManager.enqueue(request)
        val downloadQuery = DownloadManager.Query()
        downloadQuery.setFilterById(downloadReference)
        val cursor = downloadManager.query(downloadQuery)
        if (cursor.moveToFirst()) {
            checkStatusDownload(cursor)
        }
    }

    private fun checkStatusDownload(cursor: Cursor) {
        val columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
        when (cursor.getInt(columnIndex)) {
            DownloadManager.STATUS_SUCCESSFUL -> {
                setupUnZip(true)
            }
        }
    }

    private fun setupUnZip(statusDownload : Boolean){
        if(statusDownload) {
            val zipFile = File(fileLocation)
            zipFile.unzip()
        }
    }
}