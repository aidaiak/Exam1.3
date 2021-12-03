package com.aid.exam13

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment

class Fragment2 : Fragment(R.layout.fragment2) {
    private lateinit var name: AppCompatTextView
    private lateinit var phone: AppCompatTextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = view.findViewById(R.id.txt_name)
        phone = view.findViewById(R.id.txt_phone)



    }
}