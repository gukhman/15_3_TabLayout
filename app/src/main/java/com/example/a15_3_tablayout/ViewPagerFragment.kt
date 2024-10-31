package com.example.a15_3_tablayout

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

class ViewPagerFragment : Fragment() {

    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        webView = view.findViewById(R.id.webView)
        return view
    }

    //интересно что getParcelable в старом формате deprecated, а в новом требует API 33.
    //ниже реализовано для обоих вариантов
    @SuppressLint("SetJavaScriptEnabled")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val page = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("page", Page::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("page")
        }
        page?.let {
            webView.webViewClient = WebViewClient()
            webView.settings.apply {
                domStorageEnabled = true           // Включить DOM Storage
                cacheMode = android.webkit.WebSettings.LOAD_DEFAULT
                javaScriptEnabled = true
            }
            webView.clearCache(true)
            webView.loadUrl(it.url)
        }
    }
}