package com.aid.exam13

import android.app.AlertDialog
import android.content.Context
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.aid.exam13.databinding.CharacterBinding
import com.aid.exam13.databinding.CharactersListBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterDetailsFragment : Fragment(R.layout.character) {
    private var _binding: CharacterBinding? = null
    private val binding get() = _binding!!
    private val id: Long by lazy { arguments?.getLong("ID", -1) ?: -1 }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        _binding = CharacterBinding.bind(view!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
    }

    private fun loadData() = with(binding) {
        progressBar.isVisible = true
        Injector.client.getCharacters(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                progressBar.isVisible = false
                binding.apply {
                    detailsImg =

                }
                Toast.makeText(requireContext(), "${it}", Toast.LENGTH_LONG).show()

            }, {
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