package com.aid.exam13

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class SimpleAdapter() : RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
list = List<Contact>


    class ViewHolder : RecyclerView.ViewHolder(itemView) {
        val itemName: AppCompatTextView? = null
        val itemBtn: AppCompatButton? = null

        init {
            itemName = itemView.findViewById(R.id.item_txt)
            itemBtn = itemView.findViewById(R.id.item_btn)
        }
    }

    data class Contact (val Name: String, val Phone: String) {
        Name =
    }

}
