package com.avijit.warranttrackingsystem.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.avijit.warranttrackingsystem.databinding.ItemAllWarrantPendingBinding
import com.avijit.warranttrackingsystem.models.SiWarrant
import kotlinx.android.synthetic.main.item_all_warrant_pending.view.*

/**
 * Created by Avijit Acharjee on 12/30/2020 at 12:41 AM.
 * Email: avijitach@gmail.com.
 */

class AllPendingWarrantAdapter(var warrantList : ArrayList<SiWarrant>) : RecyclerView.Adapter<AllPendingWarrantAdapter.ViewHolder>() {
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
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}