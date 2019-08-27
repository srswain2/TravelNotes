package com.appsnipp.travelnotes.cardLayoutList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.appsnipp.travelnotes.R

import java.util.ArrayList


class CustomAdapter(private val dataSet: ArrayList<DataModel>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var textViewName: TextView
        internal var textViewVersion: TextView
        internal var imageViewIcon: ImageView
        // internal var relativeLayoutCardView: RelativeLayout

        init {
            this.textViewName = itemView.findViewById(R.id.textViewName) as TextView
            this.textViewVersion = itemView.findViewById(R.id.textViewVersion) as TextView
            this.imageViewIcon = itemView.findViewById(R.id.imageView) as ImageView
            // this.relativeLayoutCardView = itemView.findViewById(R.id.relativeLayoutCardView) as RelativeLayout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cards_layout, parent, false)

        view.setOnClickListener(cardLayoutListActivity.myOnClickListener)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {

        val textViewName = holder.textViewName
        val textViewVersion = holder.textViewVersion
        val imageView = holder.imageViewIcon
        // val relativeLayoutCardViewBackground = holder.relativeLayoutCardView

        textViewName.text = dataSet[listPosition].name
        textViewName.bringToFront()
        textViewVersion.text = dataSet[listPosition].version
        textViewVersion.bringToFront()
        imageView.setImageResource(dataSet[listPosition].image)
        // relativeLayoutCardViewBackground.setBackgroundResource(dataSet[listPosition].image)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
