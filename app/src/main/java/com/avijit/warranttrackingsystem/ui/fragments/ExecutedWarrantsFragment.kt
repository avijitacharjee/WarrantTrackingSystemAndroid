package com.avijit.warranttrackingsystem.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.avijit.warranttrackingsystem.databinding.FragmentExecutedWarrantsBinding
import com.avijit.warranttrackingsystem.models.SiWarrant
import com.avijit.warranttrackingsystem.utils.AppUtils
import com.avijit.warranttrackingsystem.utils.Constants
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Avijit Acharjee on 1/4/2021 at 10:39 AM.
 * Email: avijitach@gmail.com.
 */
class ExecutedWarrantsFragment : Fragment() {
    private val TAG = "ExecutedWarrantsFragmen"
    private lateinit var appUtils : AppUtils
    private lateinit var binding : FragmentExecutedWarrantsBinding
    private var warrantList : ArrayList<SiWarrant> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExecutedWarrantsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appUtils = AppUtils(context)

    }
    private fun loadData(){
        appUtils.dialog?.show()
        val tokenString : String
        tokenString = try {
            val jsonObject : JSONObject =
                JSONObject(activity?.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)?.getString("tokenBody","")+"")
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
                            val siWarrant : SiWarrant = Gson().fromJson(data.getString(i),
                                SiWarrant::class.java)
                            Log.d(TAG, "loadData: "+ Gson().toJson(siWarrant))
                            warrantList.add(siWarrant)
                        }
                        //adapter.notifyDataSetChanged()
                    }catch (e : Exception){
                        Toast.makeText(context,e.toString(), Toast.LENGTH_LONG).show()
                    }
                },
                Response.ErrorListener { error->
                    appUtils.dialog?.dismiss()
                    Toast.makeText(context, "Failed to load.", Toast.LENGTH_SHORT).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    var headersMap : MutableMap<String,String> = HashMap()
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