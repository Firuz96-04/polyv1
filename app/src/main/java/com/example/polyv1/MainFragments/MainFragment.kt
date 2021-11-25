package com.example.polyv1.MainFragments

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.polyv1.R
import com.example.polyv1.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.basketWebView1.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                view?.visibility = View.INVISIBLE
                binding.spinkit2.visibility = View.VISIBLE
                binding.loadImage1.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.visibility = View.VISIBLE
                binding.spinkit2.visibility = View.INVISIBLE
                binding.loadImage1.visibility = View.INVISIBLE
            }
        }
        binding.basketWebView1.settings.javaScriptEnabled = true
        val setting = binding.basketWebView1.settings
        setting.domStorageEnabled = true

        binding.basketWebView1.loadUrl("https://spartapro.ru/")

        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}