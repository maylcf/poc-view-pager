package com.mayarafelix.viewpagerpoc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnBoardingAdapter(val list: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getLastPosition(): Int {
        return itemCount - 1
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.setup(list[position])
    }

    class OnBoardingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: TextView = view.findViewById(R.id.onboarding_item_title)
        private val description: TextView = view.findViewById(R.id.onboarding_item_description)
        private val image: ImageView = view.findViewById(R.id.onboarding_item_image)

        fun setup(onBoardingItem: OnBoardingItem) {
            title.setText(onBoardingItem.title)
            description.setText(onBoardingItem.description)
            image.setImageResource(onBoardingItem.image)
        }
    }
}