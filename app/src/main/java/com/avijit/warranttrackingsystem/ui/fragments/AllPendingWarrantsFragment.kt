package com.avijit.warranttrackingsystem.ui.fragments

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.avijit.warranttrackingsystem.adapters.AllPendingWarrantAdapter
import com.avijit.warranttrackingsystem.databinding.FragmentAllWarrantsPendingBinding
import com.avijit.warranttrackingsystem.models.SiWarrant
import com.avijit.warranttrackingsystem.utils.AppLocationService
import com.avijit.warranttrackingsystem.utils.AppUtils
import com.avijit.warranttrackingsystem.utils.Constants
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject


/**
 * Created by Avijit Acharjee on 12/27/2020 at 1:11 PM.
 * Email: avijitach@gmail.com.
 */
class AllPendingWarrantsFragment : Fragment() {
    private val TAG = "AllPendingWarrantsFragm"
    private lateinit var binding : FragmentAllWarrantsPendingBinding
    private val warrantList : ArrayList<SiWarrant> = ArrayList()
    private lateinit var appUtils: AppUtils
    private lateinit var adapter: AllPendingWarrantAdapter
    private lateinit var llm : LinearLayoutManager
    private lateinit var ctx: Context

    private var latitude : Float = 0f
    private var longitude : Float = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllWarrantsPendingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appUtils = AppUtils(context)


        llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.allWarrantRecyclerView.layoutManager = llm
        adapter = AllPendingWarrantAdapter(warrantList,latitude,longitude)
        binding.allWarrantRecyclerView.adapter =adapter
        loadData()
    }
    private fun loadData(){
        appUtils.dialog?.show()
        val tokenString : String
        tokenString = try {
            val jsonObject =
                JSONObject(activity?.getSharedPreferences(Constants.SHARED_PREFERENCES,Context.MODE_PRIVATE)?.getString("tokenBody","")+"")
            jsonObject.getString("access_token")
        }catch (e : Exception){
            ""
        }
        val requestQueue = Volley.newRequestQueue(context)
        val url = "http://warrant.susmoycse.com/server/api/get-assigned-si-warrants"
        val stringRequest: StringRequest =
            object : StringRequest(
                Method.GET,
                url,
                Response.Listener { response ->
                    appUtils.dialog?.dismiss()
                    try {
                        val jsonResponse : JSONObject = JSONObject(response)
                        val data : JSONArray = jsonResponse.getJSONArray("data")
                        warrantList.clear()
                        for(i in 0 until data.length()){
                            val siWarrant : SiWarrant = Gson().fromJson(data.getString(i),SiWarrant::class.java)
                            Log.d(TAG, "loadData: "+ Gson().toJson(siWarrant))
                            warrantList.add(siWarrant)
                        }
                        adapter.notifyDataSetChanged()
                        ObjectAnimator.ofFloat(binding.allWarrantRecyclerView,View.ROTATION_X,360f,0f).setDuration(500).start()
                    }catch (e : Exception){
                        Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show()
                    }
                },
                Response.ErrorListener { error->
                    appUtils.dialog?.dismiss()
                    Toast.makeText(context, "Failed to load.", Toast.LENGTH_SHORT).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headersMap : MutableMap<String,String> = HashMap()
                    headersMap["Authorization"] = "Bearer $tokenString"
                    return headersMap
                }

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    return HashMap()
                }
            }
        requestQueue.add(stringRequest)
    }
}