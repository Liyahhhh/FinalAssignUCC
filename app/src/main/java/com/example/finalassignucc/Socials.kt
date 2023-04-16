package com.example.finalassignucc

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi

class Socials : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socials)
        val instagram: ImageView = findViewById(R.id.instagram)
        val facebook: ImageView = findViewById(R.id.Facebook)
        val twitter: ImageView = findViewById(R.id.twitter)
        val web: WebView = findViewById(R.id.webView)
        val back: ImageView = findViewById(R.id.back)

        //back button
        back.setOnClickListener{
            finish()
        }

        web.webViewClient = CustomWebViewClient(this)

        //On click listeners to upload the corresponding web pages
        instagram.setOnClickListener {
            instagram.setImageResource(R.drawable.instagram2)
            facebook.setImageResource(R.drawable.facebook1)
            twitter.setImageResource(R.drawable.twitter1)
            web.loadUrl("https://www.instagram.com/uccjamaica/")
        }
        facebook.setOnClickListener {
            facebook.setImageResource(R.drawable.facebook2)
            instagram.setImageResource(R.drawable.instagram1)
            twitter.setImageResource(R.drawable.twitter1)
            web.loadUrl("https://www.facebook.com/uccjamaica/")
        }
        twitter.setOnClickListener {
            twitter.setImageResource(R.drawable.twitter2)
            instagram.setImageResource(R.drawable.instagram1)
            facebook.setImageResource(R.drawable.facebook1)
            web.loadUrl("https://twitter.com/UCCjamaica/")
        }


        val webSettings = web.settings
        webSettings.javaScriptEnabled = true
    }

    //WebClientClass to load the web page
    class CustomWebViewClient internal constructor(private val activity: Activity) :
        WebViewClient() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url: String = request?.url.toString();
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceError
        ) {
            Toast.makeText(activity, "Error! $error", Toast.LENGTH_SHORT).show()
        }


    }
}