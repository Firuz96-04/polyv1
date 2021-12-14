package com.example.polyv1.MainFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.polyv1.Adapters.CatalogAdapter
import com.example.polyv1.Model.Rows
import com.example.polyv1.R
import com.example.polyv1.databinding.FragmentCatalogBinding
import com.example.polyv1.databinding.FragmentSettingsBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset


class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private val catalogList = ArrayList<Rows>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        val view = binding.root
        //   return inflater.inflate(R.layout.fragment_catalog, container, false)

        getJson()
        return view
    }

    fun getJson() {
        val catalog_recyclerview = binding.catalogRecyclerview
        catalog_recyclerview.layoutManager = LinearLayoutManager(context)
        try {
            val obj = JSONObject(loadJson())
            val tabs = obj.getJSONArray("tabs")
            val tab = tabs.getJSONObject(0)
            val title = tab.getString("title")
            val rows = tab.getJSONArray("rows")
            for (i in 0 until rows.length()) {
                val row = rows.getJSONObject(i)
                Log.d("Tag", row.getString("title"))
                catalogList.add(
                    Rows(
                        row.getString("title"),
                        row.getString("url"),
                        row.getString("icon")
                    )
                )
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        catalog_recyclerview.adapter = CatalogAdapter(catalogList)
        catalog_recyclerview.hasFixedSize()
    }

    fun loadJson(): String {
        val json: String?
        try {
            val inps = context?.assets?.open("polydev.json")
            val size = inps?.available()
            val buffer = size?.let { ByteArray(it) }
            val charset: Charset = Charsets.UTF_8
            inps?.read(buffer)
            inps?.close()
            json = buffer?.let { String(it, charset) }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json!!
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}