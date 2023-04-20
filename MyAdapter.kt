package com.example.mealtalk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealtalk.databinding.FragmentHomeFragmentBinding

class MyAdapter(items: ArrayList<Meal>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>()  {
    private var mlistener: OnItemClickListener? = null
   private var items = ArrayList<Meal>()
    interface OnItemClickListener {
        fun onItemClick(position: Int, currentItem: Meal)
    }
    fun setMeals(items: ArrayList<Meal>){
        this.items=items
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mlistener = listener
    }


    class MyViewHolder(ItemView: View, listener: OnItemClickListener?,var binding: FragmentHomeFragmentBinding):RecyclerView.ViewHolder(ItemView)  {
        var Id :TextView
        var title: TextView
        init {
            Id = itemView.findViewById(R.id.noi)
            title = itemView.findViewById(R.id.title)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val ItemView = LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false)
        return MyViewHolder(ItemView,mlistener, FragmentHomeFragmentBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = items[position]
        holder.Id.text = currentItem.idIngredient
        holder.title.text = currentItem.strIngredient
        holder.itemView.setOnClickListener {
            mlistener?.onItemClick(position,currentItem)
        }
    }

}