package com.avijit.warranttrackingsystem.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.avijit.warranttrackingsystem.R
import com.avijit.warranttrackingsystem.databinding.FragmentDialogExecutionBinding
import com.avijit.warranttrackingsystem.databinding.FragmentDialogNonExecutionBinding
import com.avijit.warranttrackingsystem.databinding.ItemAllWarrantPendingBinding
import com.avijit.warranttrackingsystem.models.SiWarrant
import com.avijit.warranttrackingsystem.utils.AppUtils
import com.avijit.warranttrackingsystem.utils.Constants
import kotlinx.android.synthetic.main.fragment_dialog_execution.*
import org.json.JSONObject


/**
 * Created by Avijit Acharjee on 12/30/2020 at 12:41 AM.
 * Email: avijitach@gmail.com.
 */

class AllPendingWarrantAdapter(var warrantList: ArrayList<SiWarrant>,var lattitude : Float,var longitude : Float) : RecyclerView.Adapter<AllPendingWarrantAdapter.ViewHolder>() {
    private val TAG = "AllPendingWarrantAdapte"
    init {
        Log.d(TAG, "Hello: ")
    }
    class ViewHolder(itemBinding: ItemAllWarrantPendingBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        /*init {
            ObjectAnimator.ofFloat(
                itemBinding.root,
                View.TRANSLATION_X,
                itemBinding.root.width.toFloat(),
                0f
            ).setDuration(500).start()
        }*/
        val binding : ItemAllWarrantPendingBinding = itemBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAllWarrantPendingBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return warrantList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ObjectAnimator.ofFloat(holder.binding.root,View.TRANSLATION_X,holder.binding.root.width.toFloat(),0f).setDuration(500).start()
        holder.binding.name.text = warrantList[position].criminal_name
        holder.binding.address.text = warrantList[position].criminal_address
        holder.binding.grNumberTextView.text =": ${warrantList[position].gr_number}"
        holder.binding.processNumberTextView.text = ": ${warrantList[position].process_number}"
        holder.binding.fatherTextView.text = ": ${warrantList[position].criminal_father_name}"
        holder.binding.typeTextView.text = ": ${warrantList[position].warrant_type}"
        holder.binding.moreImageView.setOnClickListener {
            var menu = PopupMenu(holder.binding.root.context, holder.binding.moreImageView)
            menu.menu.add("Execution")
            menu.menu.add("Non-execution")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                menu.gravity = Gravity.CENTER
            }
            menu.show()
            menu.setOnMenuItemClickListener {
//                Toast.makeText(holder.binding.root.context,"${it.order} -> ${it.title}",Toast.LENGTH_LONG).show()
                if(it.title == "Execution"){
                    val dialogFragment : DialogFragment = ExecutionDialogFragment(warrantList[position])
                    val ft : FragmentTransaction = (holder.binding.root.context as FragmentActivity).supportFragmentManager.beginTransaction()
                    dialogFragment.show(ft,"execution")
                    dialogFragment.isCancelable=false
                }
                else if (it.title == "Non-execution"){
                    val dialogFragment : DialogFragment = NonExecutionDialogFragment(warrantList[position],lattitude,longitude)
                    val ft : FragmentTransaction = (holder.binding.root.context as FragmentActivity).supportFragmentManager.beginTransaction()
                    dialogFragment.show(ft,"execution")
                    dialogFragment.isCancelable=false
                }

                true
            }
        }
        holder.binding.root.setOnClickListener{
            holder.binding.detailsLayout.visibility =  if ( holder.binding.detailsLayout.visibility == View.VISIBLE ) View.GONE else View.VISIBLE
            //        android:animateLayoutChanges="true"
            /*var view : View = holder.binding.detailsLayout
            if (view .visibility == View.VISIBLE ){
                view.animate()
                    .alpha(0.0f)
                    .setDuration(1000)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            view.visibility = View.GONE
                        }
                    })
            }
            else {
                view.visibility = View.VISIBLE
                view.animate()
                    .alpha(1.0f)
                    .setDuration(1000)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                        }
                    })
            }*/
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    class ExecutionDialogFragment(var siWarrant: SiWarrant) : DialogFragment() {
        private val TAG = "AllPendingWarrantAdapte"
        private lateinit var binding : FragmentDialogExecutionBinding
        private lateinit var appUtils: AppUtils
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentDialogExecutionBinding.inflate(inflater,container,false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            //ObjectAnimator.ofFloat(view,View.ALPHA,0f,1f).setDuration(400).start()
            // Spinner 1 setup
            appUtils = AppUtils(context)
            val type = arrayOf<String?>(
                "-- Select --",
                "Arrest",
                "Otherway",
                "Recall"
            )

            val adapter1: ArrayAdapter<String?> = ArrayAdapter(
                binding.root.context,
                android.R.layout.simple_expandable_list_item_1,
                type
            )

            binding.spinner.setAdapter(adapter1)
            val otherways = arrayOf<String?>(
                "-- Select --",
                "Death",
                "NER",
                "Otherways Disposal"
            )
            spinner.onItemClickListener = OnItemClickListener { adapterView, v, position, id ->
                if(position==2){
                    binding.spinner2Layout.visibility = View.VISIBLE
                }else {
                    binding.spinner2Layout.visibility = View.INVISIBLE
                }
            }

            //Spinner 2 setup
            val adapter2: ArrayAdapter<String?> = ArrayAdapter(
                binding.root.context,
                android.R.layout.simple_expandable_list_item_1,
                otherways
            )
            binding.spinner2.setAdapter(adapter2)
            binding.saveButton.setOnClickListener {
                postData()
            }
            binding.cancelButton.setOnClickListener {
                dialog?.dismiss()
            }
            buttonEffect(binding.saveButton)
            buttonEffect(binding.cancelButton)

        }
        fun buttonEffect(button: View) {
            button.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        v.background.setColorFilter(-0xeeeeee, PorterDuff.Mode.SRC_ATOP)
                        v.invalidate()
                    }
                    MotionEvent.ACTION_UP -> {
                        v.background.clearColorFilter()
                        v.invalidate()
                    }
                }
                false
            }
        }
        private fun postData(){
            appUtils.dialog?.show()
            val tokenString : String
            tokenString = try {
                val jsonObject =
                    JSONObject(activity?.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)?.getString("tokenBody","")+"")
                jsonObject.getString("access_token")
            }catch (e : Exception){
                ""
            }
            val requestQueue = Volley.newRequestQueue(context)
            var executionType : String = binding.spinner.text.toString()
            if(executionType == "-- Select --" || executionType==""){
                appUtils.dialog?.dismiss()
                appUtils.showRedToast("Please select type")
                return
            }else if (executionType=="Otherway"){
                if(binding.spinner2.text.toString() == "-- Select --" || binding.spinner2.text.toString() == "") {
                    appUtils.dialog?.dismiss()
                    appUtils.showRedToast("Please select type")
                    return
                }else {
                    executionType = binding.spinner2.text.toString()
                }
            }
            val url = "http://warrant.susmoycse.com/server/api/save-execution/${siWarrant.id}/${executionType}"
            Log.d(TAG, "postData: $url")
            val stringRequest: StringRequest =
                object : StringRequest(
                    Method.GET,
                    url,
                    Response.Listener { response ->
                        appUtils.dialog?.dismiss()
                        Toast.makeText(context,"Data inserted successfully",Toast.LENGTH_SHORT).show()
                        dialog?.dismiss()
                    },
                    Response.ErrorListener { error->
                        appUtils.dialog?.dismiss()
                        Toast.makeText(context, "Failed to load.", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "postData: $error")
                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headersMap : MutableMap<String,String> = HashMap()
                        headersMap["Authorization"] = "Bearer $tokenString"
                        return headersMap
                    }

                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String,String>()
                        return params
                    }
                }
            requestQueue.add(stringRequest)
        }
    }
    class NonExecutionDialogFragment(var siWarrant: SiWarrant,var latitude : Float,var longitude : Float) : DialogFragment() {
        private val TAG = "AllPendingWarrantAdapte"
        private lateinit var binding : FragmentDialogNonExecutionBinding
        private lateinit var appUtils: AppUtils
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentDialogNonExecutionBinding.inflate(inflater,container,false)
            /*dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))*/

            return binding.root
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setStyle(STYLE_NO_TITLE, R.style.DialogStyle);
        }

        override fun onStart() {
            super.onStart()
            //dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            setStyle(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
            appUtils = AppUtils(context)
            //ObjectAnimator.ofFloat(view,View.ALPHA,0f,1f).setDuration(400).start()
            binding.saveButton.setOnClickListener {
                postData()
            }
            binding.cancelButton.setOnClickListener {
                dialog?.dismiss()
            }


        }
        private fun postData(){
            appUtils.dialog?.show()
            val tokenString : String
            tokenString = try {
                val jsonObject =
                    JSONObject(activity?.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)?.getString("tokenBody","")+"")
                jsonObject.getString("access_token")
            }catch (e : Exception){
                ""
            }
            val requestQueue = Volley.newRequestQueue(context)
            val url = "http://warrant.susmoycse.com/server/api/add-non-execution"
            val stringRequest: StringRequest =
                object : StringRequest(
                    Method.POST,
                    url,
                    Response.Listener { response ->
                        appUtils.dialog?.dismiss()
                        Toast.makeText(context,"Data inserted successfully",Toast.LENGTH_SHORT).show()
                        dialog?.dismiss()
                    },
                    Response.ErrorListener { error->
                        appUtils.dialog?.dismiss()
                        Toast.makeText(context, "Failed to load.", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "postData: "+error.toString())
                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headersMap : MutableMap<String,String> = HashMap()
                        headersMap["Authorization"] = "Bearer $tokenString"
                        return headersMap
                    }

                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String,String>()
                        params["assigned_warrants_id"] = siWarrant.warrant_id
                        params["name"] = binding.nameEditText.text.toString()
                        params["address"] = binding.addressEditText.text.toString()
                        params["contact_no"] = binding.contactEditText.text.toString()
                        params["description"] = binding.descriptionEditText.text.toString()
                        params["Latitude"] = "$latitude"
                        params["Longitude"] = "$longitude"
                        Log.d(TAG, params.toString())
                        return params
                    }
                }
            requestQueue.add(stringRequest)
        }
    }
}