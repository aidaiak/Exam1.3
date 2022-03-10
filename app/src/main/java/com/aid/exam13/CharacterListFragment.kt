package com.aid.exam13

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.aid.exam13.databinding.CharactersListBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CharacterListFragment : Fragment(R.layout.characters_list) {
    private var _binding: CharactersListBinding? = null
    private val binding get() = _binding!!
    private val adapter = CharacterAdapter { id ->
        val detailsFragment = CharacterDetailsFragment()
        val bundle = Bundle()
        bundle.putLong("ID", id)
        detailsFragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frg_cont, detailsFragment)
            .addToBackStack("details")
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        _binding = CharactersListBinding.bind(view!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        loadData()
        swipeRefreshLayout.setOnRefreshListener {
            loadData(isRefresh = true)
        }
    }

    private fun loadData(isRefresh: Boolean = false) = with(binding) {
        progressBar.isVisible = !isRefresh
        swipeRefreshLayout.isRefreshing = isRefresh
        Injector.client.getCharacters().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter.setItems(it.items)
                progressBar.isVisible = false
                swipeRefreshLayout.isRefreshing = false
            }, {
                swipeRefreshLayout.isRefreshing = false
                progressBar.isVisible = false
                showErrorMessage(it)
                it.printStackTrace()
            })
    }

    private fun showErrorMessage(error: Throwable) {
        AlertDialog.Builder(requireContext())
            .setTitle("Error Occurred")
            .setMessage(error.message)
            .show()
    }
}


