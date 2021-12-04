package com.aid.exam13

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class SimpleAdapter(val click: (contact: Contact) -> Unit ) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    private var list = mutableListOf<Contact>()

    fun addContact(contact: Contact) {
        list.add(0, contact)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(itemView, click)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder (itemView: View, val click: (contact: Contact) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind (item: Contact) {
            val txt = itemView.findViewById<AppCompatTextView>(R.id.item_txt)
            txt.text = item.name
            val btn = itemView.findViewById<AppCompatButton>(R.id.item_btn)
            btn.setOnClickListener {
                click(item)
            }
        }

    }



}
