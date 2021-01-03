package com.avijit.warranttrackingsystem.adapters

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
import com.avijit.warranttrackingsystem.databinding.FragmentDialogExecutionBinding
import com.avijit.warranttrackingsystem.databinding.ItemAllWarrantPendingBinding
import com.avijit.warranttrackingsystem.models.SiWarrant
import kotlinx.android.synthetic.main.fragment_dialog_execution.*


/**
 * Created by Avijit Acharjee on 12/30/2020 at 12:41 AM.
 * Email: avijitach@gmail.com.
 */

class AllPendingWarrantAdapter(var warrantList: ArrayList<SiWarrant>) : RecyclerView.Adapter<AllPendingWarrantAdapter.ViewHolder>() {
    private val TAG = "AllPendingWarrantAdapte"
    init {
        Log.d(TAG, "Hello: ")
    }
    class ViewHolder(itemBinding: ItemAllWarrantPendingBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val binding : ItemAllWarrantPendingBinding = itemBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAllWarrantPendingBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return warrantList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name.text = warrantList[position].criminal_name
        holder.binding.address.text = warrantList[position].criminal_address
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
                true
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    class ExecutionDialogFragment(var siWarrant: SiWarrant) : DialogFragment() {
        private lateinit var binding : FragmentDialogExecutionBinding
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

            // Spinner 1 setup
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
    }
    class NonExecutionDialogFragment : DialogFragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        }
    }
}