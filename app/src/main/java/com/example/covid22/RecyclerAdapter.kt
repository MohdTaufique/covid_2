package com.example.covid22

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid22.model.State

class RecyclerAdapter(val context: Context, val itemList: ArrayList<State>) : RecyclerView.Adapter<RecyclerAdapter.DashBoardViewHolder>() {
    class DashBoardViewHolder (view: View): RecyclerView.ViewHolder(view){
        var loc: TextView = view.findViewById(R.id.recyclerRow)
        var confirmedCase: TextView = view.findViewById(R.id.recyclerRow2)
        var confirmedCaseForeign: TextView = view.findViewById(R.id.recyclerRow3)
        var discharge: TextView = view.findViewById(R.id.recyclerRow4)
        var death: TextView = view.findViewById(R.id.recyclerRow5)
        var totalCase: TextView = view.findViewById(R.id.recyclerRow6)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent,false)
        return DashBoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        val state = itemList[position]
        holder.loc.text = state.location
        holder.confirmedCase.text = state.confirmedCasesIndia
        holder.confirmedCaseForeign.text =state.confirmedCasesForeign
        holder.discharge.text =state.discharged
        holder.death.text =state.death
        holder.totalCase.text =state.totalCases

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}