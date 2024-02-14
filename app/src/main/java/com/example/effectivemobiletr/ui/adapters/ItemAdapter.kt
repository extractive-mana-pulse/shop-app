package com.example.effectivemobiletr.ui.adapters

import android.annotation.SuppressLint
import android.content.ContextWrapper
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletr.R
import com.example.effectivemobiletr.databinding.ItemViewBinding
import com.example.effectivemobiletr.model.Items
import com.example.effectivemobiletr.ui.fragments.DetailFragment

class ItemAdapter :  RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var item = emptyList<Items>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemViewBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val list = item[position]
        val context = holder.itemView.context
        list.apply {
            holder.binding.apply {

                val ids = listOf(
                    "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
                    "54a876a5-2205-48ba-9498-cfecff4baa6e",
                    "75c84407-52e1-4cce-a73a-ff2d3ac031b3",
                    "16f88865-ae74-4b7c-9d85-b68334bb97db",
                    "15f88865-ae74-4b7c-9d81-b78334bb97db",
                    "26f88856-ae74-4b7c-9d85-b68334bb97db",
                    "88f88865-ae74-4b7c-9d81-b78334bb97db",
                    "55f58865-ae74-4b7c-9d81-b78334bb97db"
                )
                val images = listOf(
                    listOf(R.drawable.vox, R.drawable.eveline),
                    listOf(R.drawable.deep_clean, R.drawable.coenzyme),
                    listOf(R.drawable.eveline, R.drawable.vox),
                    listOf(R.drawable.deep_clean, R.drawable.hand_mask),
                    listOf(R.drawable.deep_clean, R.drawable.vox),
                    listOf(R.drawable.beauty_deco, R.drawable.coenzyme),
                    listOf(R.drawable.hand_mask, R.drawable.beauty_deco),
                    listOf(R.drawable.deep_clean, R.drawable.eveline)
                )

                val idToImagesMap = mutableMapOf<String, List<Int>>()
                for (i in ids.indices) {
                    idToImagesMap[ids[i]] = images[i]
                }

                if (idToImagesMap.containsKey(id)) {
                    val imagesForId = idToImagesMap[id]
                    imagesForId?.forEach { imageId ->
                        itemImage.setImageResource(imageId)
                    }
                } else {
                    itemImage.setImageResource(R.drawable.ic_launcher_foreground)
                }

                itemOldPrice.text = price?.price + price?.unit
                discountTv.text = "-" + price?.discount.toString() + "%"
                actualPrice.text = price?.priceWithDiscount + price?.unit
                itemTitle.text = title
                itemDescription.text = description
                itemRatingTv.text = feedback?.rating.toString()
                itemRatingCounterTv.text = "(" + feedback?.count.toString() + ")"
                itemOldPrice.paintFlags = itemOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                holder.itemView.setOnClickListener {

                    val bundle = Bundle().apply {
                        putString("id", id)
                        putString("oldPrice", price?.price + price?.unit)
                        putString("discount", "-" + price?.discount.toString() + "%")
                        putString("actualPrice", price?.priceWithDiscount + price?.unit)
                        putString("title", title)
                        putString("subTitle", subtitle)
                        putString("description", description)
                        putString("rate", feedback?.rating.toString())
                        putString("rateCounter", "(" + feedback?.count.toString() + ")")
                        putString("available", available.toString())
                        putString("compound", ingredients)
                        putString("articul", info.forEach { x -> x.value }.toString())
                        putString("articulTitle", info.forEach { x -> x.title }.toString())
                    }

                    val detailFragment = DetailFragment()
                    detailFragment.arguments = bundle

                    // this context1 added cause of hilt error !
                    val context1 = (context as ContextWrapper).baseContext
                    val fragmentManager = (context1 as AppCompatActivity).supportFragmentManager
                    fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_container, detailFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<Items>){
        item = newList
        notifyDataSetChanged()
    }
}