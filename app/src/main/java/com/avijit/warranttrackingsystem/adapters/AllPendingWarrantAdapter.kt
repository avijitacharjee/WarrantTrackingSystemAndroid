package com.avijit.warranttrackingsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.avijit.warranttrackingsystem.databinding.ItemAllWarrantPendingBinding
import com.avijit.warranttrackingsystem.models.SiWarrant

/**
 * Created by Avijit Acharjee on 12/30/2020 at 12:41 AM.
 * Email: avijitach@gmail.com.
 */

class AllPendingWarrantAdapter(var warrantList : ArrayList<SiWarrant>) : RecyclerView.Adapter<AllPendingWarrantAdapter.ViewHolder>() {

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
        holder.binding.name.text = "Okay"
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}