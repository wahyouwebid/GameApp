package com.wahyou.app.ui.video

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.wahyou.app.databinding.ActivityVideoBinding


class VideoActivity : AppCompatActivity() {

    private val binding : ActivityVideoBinding by lazy {
        ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupVideoPlayer()
    }

    private fun setupVideoPlayer() {
        with(binding) {
            ytPlayer.also {
                lifecycle.addObserver(it)
                it.initialize(
                        object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                super.onReady(youTubePlayer)
                                youTubePlayer.cueVideo("UWnviaEXFsg", 0F)
                                youTubePlayer.play()
                            }
                        }, true
                )
                it.addFullScreenListener(object : YouTubePlayerFullScreenListener {
                    override fun onYouTubePlayerEnterFullScreen() {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    }

                    override fun onYouTubePlayerExitFullScreen() {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    }
                })
                it.enableBackgroundPlayback(true)
            }
        }
    }
}