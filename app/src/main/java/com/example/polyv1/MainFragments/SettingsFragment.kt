package com.example.polyv1.MainFragments

import android.os.Bundle
import android.os.ParcelFileDescriptor.open
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.polyv1.Adapters.SettingAdaper2
import com.example.polyv1.Adapters.SettingAdapter
import com.example.polyv1.Model.Contacts
import com.example.polyv1.Model.SettingsDefault
import com.example.polyv1.R
import com.example.polyv1.databinding.FragmentSettingsBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val ContactsList = ArrayList<Contacts>()
    private val defaultList = ArrayList<SettingsDefault>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        loadContact()
        default_recyclerList()

        return view

    }

    fun default_recyclerList() {
        val recyclersettings2 = binding.recyclersettings2

        defaultList.add(SettingsDefault("Оценить приложение", R.drawable.ic_star))
        defaultList.add(SettingsDefault("О приложении", R.drawable.ic_info))
        recyclersettings2.layoutManager = LinearLayoutManager(context)
        recyclersettings2.adapter = SettingAdaper2(defaultList)
        recyclersettings2.hasFixedSize()
    }

    fun loadContact() {
        val recyclersettings = binding.recyclersettings

        recyclersettings.layoutManager = LinearLayoutManager(context)

        try {
            val obj = JSONObject(loadJsonContact())
            val contacts = obj.getJSONArray("contacts")
            for (i in 0 until contacts.length()) {
                val contact = contacts.getJSONObject(i)
                Log.d("Tag", contact.getString("title"))
                ContactsList.add(
                    Contacts(
                        contact.getString("title"),
                        contact.getString("icon"),
                        contact.getString("link"),
                        contact.getString("phone"),
                        contact.getString("email")
                    )
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        recyclersettings.adapter = SettingAdapter(ContactsList)
        recyclersettings.hasFixedSize()
    }

    fun loadJsonContact(): String {
        val json: String?
        try {
            val inp = context?.assets?.open("polydev.json")
            val size = inp?.available()
            val buffer = size?.let { ByteArray(it) }
            val charset: Charset = Charsets.UTF_8
            inp?.read(buffer)
            inp?.close()
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