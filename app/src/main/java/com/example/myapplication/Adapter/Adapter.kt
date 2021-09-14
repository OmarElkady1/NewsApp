package com.example.newsapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.newsapp.model.News


class Adapter( private val onNewsClick: OnNewsClick): RecyclerView.Adapter<Adapter.MyViewHolder>() {
   private val newsList= ArrayList<News>()
    private lateinit var tempArrayList: ArrayList<News>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        val viewHolder= MyViewHolder(view )
        view.setOnClickListener {
            onNewsClick.onClick(newsList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
          val currentList = newsList[position]
        holder.title.setText(currentList.title)
        holder.sourceName.setText(currentList.author)
        Glide.with(holder.itemView.context).load(currentList.imageUrl).into(holder.titleImage)
    }

    override fun getItemCount(): Int {
      return newsList.size
    }

    class MyViewHolder(view:View):RecyclerView .ViewHolder(view){
       val titleImage = view.findViewById<ImageView>(R.id.title_image)
       val title = view.findViewById<TextView>(R.id.title)
       val sourceName = view.findViewById<TextView>(R.id.sourceName)

    }
    fun updateData(newData:ArrayList<News>){
        newsList.clear()
        newsList.addAll(newData)

        notifyDataSetChanged()
    }


    interface OnNewsClick{
        fun onClick(news: News){

        }
    }
}
















