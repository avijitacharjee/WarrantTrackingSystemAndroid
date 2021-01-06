package com.avijit.warranttrackingsystem.adapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.avijit.warranttrackingsystem.R
import com.avijit.warranttrackingsystem.databinding.FragmentDialogExecutionBinding
import com.avijit.warranttrackingsystem.databinding.FragmentDialogNonExecutionBinding
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
                    val dialogFragment : DialogFragment = NonExecutionDialogFragment(warrantList[position])
                    val ft : FragmentTransaction = (holder.binding.root.context as FragmentActivity).supportFragmentManager.beginTransaction()
                    dialogFragment.show(ft,"execution")
                    dialogFragment.isCancelable=false
                }

                true
            }
        }
        holder.binding.root.setOnClickListener{
            holder.binding.detailsLayout.visibility =  if ( holder.binding.detailsLayout.visibility == View.VISIBLE ) View.GONE else View.VISIBLE
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
    class NonExecutionDialogFragment(siWarrant: SiWarrant) : DialogFragment() {
        private lateinit var binding : FragmentDialogNonExecutionBinding
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
            binding.saveButton.setOnClickListener {

            }
            binding.cancelButton.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }
}