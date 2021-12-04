package com.aid.exam13

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.provider.ContactsContract
import android.security.keystore.KeyNotYetValidException
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.app.Activity
import android.view.inputmethod.InputMethodManager


class Fragment1 : Fragment(R.layout.fragment1) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editName = view.findViewById<EditText>(R.id.edit_name)
        val editPhone = view.findViewById<EditText>(R.id.edit_phone)
        val addContact = view.findViewById<AppCompatButton>(R.id.btn_add)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = SimpleAdapter {
            val bundle = Bundle()
            bundle.putParcelable("KEY", it)
            val fragment = Fragment2()
            fragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()

            hideKeyboard(requireContext(), view.findFocus())
        }

        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))


        addContact.setOnClickListener {
            val mName = editName.text.toString()
            val mPhone = editPhone.text.toString()

            if (mName.isEmpty()) {
                Toast.makeText(requireContext(), "Enter name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mPhone.isEmpty()) {
                Toast.makeText(requireContext(), "Enter phone", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val list = Contact(mName, mPhone)

            adapter.addContact(list)
            editName.text = null
            editPhone.text = null

            hideKeyboard(requireContext(), view.findFocus())
        }

    }

    fun hideKeyboard(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}


