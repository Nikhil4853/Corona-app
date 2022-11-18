package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.corona.stateData

class adapter: RecyclerView.Adapter<viewHolder>() {
    private val item: ArrayList<stateData> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val ItemView= LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        val ViewHolder= viewHolder(ItemView)
        return ViewHolder
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val It=item[position]
        holder.Active.text=It.ActiveCsse.toString()
        holder.Death.text=It.DeathCase.toString()
        holder.State.text=It.StateName.toString()
        holder.Recoverd.text=It.RecoverdCase.toString()
    }

    override fun getItemCount(): Int {
        return item.size
    }
    fun updateState(updatedState:ArrayList<stateData>){
        item.clear()
        item.addAll(updatedState)
        notifyDataSetChanged()
    }
}
class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var Active: TextView =itemView.findViewById(R.id.itemActiveCase)
    var State: TextView =itemView.findViewById(R.id.itemStateName)
    var Death: TextView =itemView.findViewById(R.id.itemDeathCase)
    var Recoverd: TextView =itemView.findViewById(R.id.itemRecoverdCase)
}