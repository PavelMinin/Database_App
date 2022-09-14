package com.example.databaseapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.databaseapp.appDatabase
import com.example.databaseapp.databinding.FragmentDatabaseShowBinding


class CharacterFragment : Fragment() {

    private var _binding: FragmentDatabaseShowBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val characterDao by lazy {
        requireContext().appDatabase.characterDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDatabaseShowBinding.inflate(inflater,container,false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showResult()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showResult() {
        with(binding) {
            textResult.text = characterDao.getAll().joinToString(separator = "\n")
        }
    }
}